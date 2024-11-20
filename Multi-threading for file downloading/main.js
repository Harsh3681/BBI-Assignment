const urlInput = document.getElementById("url-input");
const addDownloadButton = document.getElementById("add-download");
const downloadsContainer = document.getElementById("downloads");

const workers = {}; // Store workers for each download
const downloadStates = {}; // Track download states (running, paused)
const pausedDownloads = new Set(); // Track downloads paused due to offline state

addDownloadButton.addEventListener("click", () => {
  const url = urlInput.value.trim();
  if (!isValidUrl(url)) {
    alert("Please enter a valid URL.");
    return;
  }

  const downloadId = createDownloadItem(url);
  startDownload(url, downloadId);
  urlInput.value = ""; // Clear input field
});

function createDownloadItem(url) {

  const downloadId = `download-${Date.now()}`;

  const downloadItem = document.createElement("div");
  downloadItem.classList.add("download-item");
  downloadItem.id = downloadId;

  downloadItem.innerHTML = `
    <p><strong>URL:</strong> ${url}</p>
    <div class="progress-container">
      <div class="progress-bar" id="${downloadId}-progress"></div>
    </div>
    <p class="download-time" id="${downloadId}-time"></p>
    <button id="${downloadId}-pause-resume">Pause</button>
  `;

  downloadsContainer.appendChild(downloadItem);

  const pauseResumeButton = document.getElementById(`${downloadId}-pause-resume`);
  pauseResumeButton.addEventListener("click", () => togglePauseResume(downloadId, pauseResumeButton));

  return downloadId;
}

function startDownload(url, downloadId) {
  const worker = new Worker("worker.js");
  workers[downloadId] = worker;
  downloadStates[downloadId] = "running";

  const progressBar = document.getElementById(`${downloadId}-progress`);
  const timeDisplay = document.getElementById(`${downloadId}-time`);

  let startTime = Date.now();

  worker.postMessage({ action: "start", url, id: downloadId });

//------

  worker.onmessage = (e) => {
    const { type, progress, blob, error, id } = e.data;

    if (type === "progress") {
      progressBar.style.width = `${progress}%`;
    } 
    else if (type === "completed") {
      const blobUrl = URL.createObjectURL(blob);
      const link = document.createElement("a");
      link.href = blobUrl;
      link.download = "DownloadedFile";
      link.textContent = "Download Completed - Click to Save";
      link.style.color = "#4caf50";
      link.style.display = "block";
      document.getElementById(id).appendChild(link);

      const downloadTime = ((Date.now() - startTime) / 1000).toFixed(2);
      timeDisplay.textContent = `Download completed in ${downloadTime} seconds.`;

      cleanupWorker(id);
    } else if (type === "error") {
      alert(`Error: ${error}`);
      cleanupWorker(id);
    }
  };
}

function togglePauseResume(downloadId, button) {
  const state = downloadStates[downloadId]; // downloadStates check running or pause
  const worker = workers[downloadId];

  if (state === "running") {
    worker.postMessage({ action: "pause" });
    downloadStates[downloadId] = "paused";
    button.textContent = "Resume";
  } else if (state === "paused") {
    worker.postMessage({ action: "resume" });
    downloadStates[downloadId] = "running";
    button.textContent = "Pause";
  }
}

function cleanupWorker(downloadId) {
  if (workers[downloadId]) {
    workers[downloadId].terminate();
    delete workers[downloadId];
    delete downloadStates[downloadId];
  }
}

function isValidUrl(url) {
  const regex = /^(https?|ftp):\/\/[^\s/$.?#].[^\s]*$/i;
  return regex.test(url);
}

// Offline/Online handling logic

window.addEventListener("online", () => {
  pausedDownloads.forEach((downloadId) => {
    if (workers[downloadId]) {
      workers[downloadId].postMessage({ action: "resume", id: downloadId });
      const pauseButton = document.getElementById(`${downloadId}-pause-resume`);
      pauseButton.textContent = "Pause";
    }
  });
  pausedDownloads.clear();
  alert("You are back online. Downloads have resumed.");
});

window.addEventListener("offline", () => {
  Object.keys(workers).forEach((downloadId) => {
    if (downloadStates[downloadId] === "running") {
      workers[downloadId].postMessage({ action: "pause", id: downloadId });
      pausedDownloads.add(downloadId);
      const pauseButton = document.getElementById(`${downloadId}-pause-resume`);
      pauseButton.textContent = "Resume";
    }
  });
  alert("You are offline. Downloads have been paused.");
});
console.log('Ans  ',pausedDownloads);
