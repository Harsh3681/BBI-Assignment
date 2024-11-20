
importScripts('sharedFile.js');

onmessage = function (event) {
    const { start, end } = event.data;

    try {
        const primes = calculatePrimes(start, end);
        postMessage({ primes });
    } catch (error) {
        postMessage({ error: error.message });
    }
};

