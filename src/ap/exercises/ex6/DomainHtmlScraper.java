package ap.exercises.ex6;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

public class DomainHtmlScraper {
    private final String mainDomain;
    private final Queue<String> urlQueue = new ConcurrentLinkedQueue<>();
    private final Set<String> visitedUrls = Collections.synchronizedSet(new HashSet<>());
    private final List<String> allImageUrls = Collections.synchronizedList(new ArrayList<>());
    private final Path baseSavePath;
    private final Path imagesPath;
    private final Path songsPath;
    private final ExecutorService executorService;
    private final int threadCount;
    private final CountDownLatch completionLatch;

    public DomainHtmlScraper(String domainAddress, String savePath) throws URISyntaxException, IOException {
        URI uri = new URI(domainAddress);
        this.mainDomain = uri.getHost();
        this.baseSavePath = Paths.get(savePath, "html");
        this.imagesPath = Paths.get(savePath, "images");
        this.songsPath = Paths.get(savePath, "songs");

        this.threadCount = Conf.THREAD_COUNT > 0 ? Conf.THREAD_COUNT : 1;
        this.executorService = Executors.newFixedThreadPool(threadCount);
        this.completionLatch = new CountDownLatch(threadCount);

        Files.createDirectories(baseSavePath);
        Files.createDirectories(imagesPath);
        Files.createDirectories(songsPath);

        urlQueue.add(domainAddress);
        visitedUrls.add(domainAddress);
    }

    public void start() throws IOException, InterruptedException {
        if (threadCount > 1) {

            for (int i = 0; i < threadCount; i++) {
                executorService.submit(new CrawlerTask());
            }
            completionLatch.await();
            executorService.shutdown();
        } else {

            int counter = 0;
            while (!urlQueue.isEmpty() && counter < 100) {
                processNextUrl();
                counter++;
            }
        }
        System.out.printf("Crawl complete. Processed %d pages.%n", visitedUrls.size());
    }

    private class CrawlerTask implements Runnable {
        @Override
        public void run() {
            try {
                while (!urlQueue.isEmpty()) {
                    processNextUrl();
                }
            } finally {
                completionLatch.countDown();
            }
        }
    }

    private void processNextUrl() {
        String currentUrl = urlQueue.poll();
        if (currentUrl == null) {
            return;
        }

        try {
            TimeUnit.SECONDS.sleep(Conf.DOWNLOAD_DELAY_SECONDS);

            System.out.printf("[Thread-%d] Processing: %s%n", Thread.currentThread().getId(), currentUrl);

            Path savePath = determineSavePath(currentUrl);
            Files.createDirectories(savePath.getParent());

            List<String> htmlLines = HtmlFetcher.fetchHtml(currentUrl);
            saveHtmlContent(htmlLines, savePath);

            processResources(htmlLines, currentUrl);

            System.out.printf("Queue size: %d%n", urlQueue.size());
        } catch (Exception e) {
            System.err.printf("Error processing %s: %s%n", currentUrl, e.getMessage());
        }
    }


    private void processResources(List<String> htmlLines, String baseUrl) throws URISyntaxException, IOException {
        List<String> imageUrls = HtmlParser.extractAllImageUrls(htmlLines);
        downloadResources(imageUrls, imagesPath, baseUrl);

        List<String> audioUrls = HtmlParser.extractAllAudioUrls(htmlLines);
        downloadResources(audioUrls, songsPath, baseUrl);

        for (String url : HtmlParser.extractAllUrls(htmlLines)) {
            if (!visitedUrls.contains(url)) {
                String absoluteUrl = toAbsoluteUrl(url, baseUrl);
                if (isSameDomain(absoluteUrl)) {
                    visitedUrls.add(absoluteUrl);
                    urlQueue.add(absoluteUrl);
                }
            }
        }
    }

    private Path determineSavePath(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String path = uri.getPath();
        String subdomain = getSubdomain(uri);

        Path savePath = baseSavePath;

        if (!subdomain.isEmpty()) {
            savePath = savePath.resolve("_" + subdomain);
        }

        if (path == null || path.isEmpty() || path.equals("/")) {
            return savePath.resolve("index.html");
        }

        String filePath = path.startsWith("/") ? path.substring(1) : path;

        if (path.endsWith("/")) {
            return savePath.resolve(filePath).resolve("index.html");
        }

        return savePath.resolve(filePath);
    }

    private void downloadResources(List<String> urls, Path targetDir, String baseUrl)
            throws URISyntaxException, IOException {
        for (String url : urls) {
            try {
                String absoluteUrl = toAbsoluteUrl(url, baseUrl);
                String fileName = getFileNameFromUrl(absoluteUrl);
                Path targetPath = targetDir.resolve(fileName);

                if (!Files.exists(targetPath)) {
                    System.out.println("Downloading: " + absoluteUrl);
                    try (InputStream in = new URL(absoluteUrl).openStream()) {
                        Files.copy(in, targetPath);
                    }
                    TimeUnit.SECONDS.sleep(Conf.DOWNLOAD_DELAY_SECONDS);
                }
            } catch (Exception e) {
                System.err.println("Failed to download resource: " + url + " - " + e.getMessage());
            }
        }
    }

    private String getFileNameFromUrl(String url) {
        return url.substring(url.lastIndexOf('/') + 1);
    }

    private String getSubdomain(URI uri) {
        String host = uri.getHost();
        if (host == null || host.equals(mainDomain)) {
            return "";
        }

        return host.substring(0, host.indexOf("." + mainDomain));
    }

    private void saveHtmlContent(List<String> htmlLines, Path savePath) throws IOException {
        Files.createDirectories(savePath.getParent());

        try (PrintWriter out = new PrintWriter(savePath.toFile(), "UTF-8")) {
            for (String line : htmlLines) {
                out.println(line);
            }
        }
    }

    private String toAbsoluteUrl(String url, String baseUrl) throws URISyntaxException {
        if (url.startsWith("http")) {
            return url;
        }

        URI baseUri = new URI(baseUrl);
        if (url.startsWith("/")) {
            return baseUri.getScheme() + "://" + baseUri.getHost() + url;
        }

        return baseUrl + (baseUrl.endsWith("/") ? "" : "/") + url;
    }

    private boolean isSameDomain(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String host = uri.getHost();
        return host != null && (host.equals(mainDomain) || host.endsWith("." + mainDomain));
    }
}
