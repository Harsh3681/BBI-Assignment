const http = require('http');
const fs = require('fs');
const path = require('path');

const PORT = 3000;
const DIRECTORY = __dirname;

http.createServer((req, res) => {
    let filePath = path.join(DIRECTORY, req.url === '/' ? '/index.html' : req.url);

    fs.readFile(filePath, (err, data) => {
        if (err) {
            res.writeHead(404, { 'Content-Type': 'text/plain' });
            res.end('404 Not Found');
        } else {
            const ext = path.extname(filePath).toLowerCase();
            const mimeTypes = {
                '.html': 'text/html',
                '.js': 'application/javascript',
                '.css': 'text/css',
                '.txt': 'text/plain',
                '.json': 'application/json',
                '.jpg': 'image/jpeg',
                '.png': 'image/png',
                '.mp4': 'video/mp4',
                '.mp3': 'audio/mpeg',
                '.zip': 'application/zip',
                '.pdf': 'application/pdf',
            };

            const contentType = mimeTypes[ext] || 'application/octet-stream';
            res.writeHead(200, { 'Content-Type': contentType });
            res.end(data);
        }
    });
}).listen(PORT, () => {
    console.log(`Server running at http://localhost:${PORT}`);
});
