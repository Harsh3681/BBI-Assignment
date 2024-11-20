let paused = false;
let receivedLength = 0;
let chunks = [];
let abortController = null;
let downloadUrl = "";
let fileId = "";

self.onmessage = async (e) => {
  const { action, url, id } = e.data;

  if (action === "pause") {
    paused = true;
    if (abortController) abortController.abort(); // Stop the fetch
    return;
  }

  if (action === "resume") {
    if (paused && downloadUrl && fileId) {
      paused = false;
      await downloadFile(downloadUrl, fileId); // Resume downloading
    }
    return;
  }

  if (action === "start") {
    paused = false;
    receivedLength = 0;
    chunks = []; 
    downloadUrl = url; 
    fileId = id; 
    await downloadFile(downloadUrl, fileId);
  }
};

async function downloadFile(url, id) {
  try {
    abortController = new AbortController();
    const headers = receivedLength > 0 ? { Range: `bytes=${receivedLength}-` } : {}; 
    const response = await fetch(url, { signal: abortController.signal, headers });

    if (!response.ok) {
      self.postMessage({ type: "error", error: response.statusText, id });
      return;
    }

    const contentLength =
      parseInt(response.headers.get("Content-Length")) + receivedLength; 
    const reader = response.body.getReader();

    while (true) {
      if (paused) return; 

      const { done, value } = await reader.read();

      if (done) break;

      chunks.push(value); 
      receivedLength += value.length;

      const progress = ((receivedLength / contentLength) * 100).toFixed(2);
      self.postMessage({ type: "progress", progress, id });

      if (receivedLength === contentLength) {
        const blob = new Blob(chunks,{type:"audio/mpeg"});
        self.postMessage({ type: "completed", blob, id });
      }
    }
  } catch (error) {
    if (error.name === "AbortError") return;
    self.postMessage({ type: "error", error: error.message, id });
  }
}
