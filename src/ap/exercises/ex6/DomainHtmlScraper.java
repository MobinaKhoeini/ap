package ap.exercises.ex6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class DomainHtmlScraper {
    private final String mainDomain;
    private final Queue<String> urlQueue = new LinkedList<>();
    private final Set<String> visitedUrls = new HashSet<>();
    private final List<String> allImageUrls = new ArrayList<>();
    private final Path baseSavePath;

    public DomainHtmlScraper(String domainAddress, String savePath) throws URISyntaxException, IOException {
        URI uri = new URI(domainAddress);
        this.mainDomain = uri.getHost();
        this.baseSavePath = Paths.get(savePath);
        Files.createDirectories(baseSavePath);

        urlQueue.add(domainAddress);
        visitedUrls.add(domainAddress);
    }

    public void start() throws IOException {
        int counter = 0;

        while (!urlQueue.isEmpty() && counter < 100) {
            String currentUrl = urlQueue.remove();
            counter++;

            try {
                System.out.printf("[%d] Processing: %s%n", counter, currentUrl);


                Path savePath = determineSavePath(currentUrl);
                Files.createDirectories(savePath.getParent());


                List<String> htmlLines = HtmlFetcher.fetchHtml(currentUrl);
                saveHtmlContent(htmlLines, savePath);


                processLinks(htmlLines, currentUrl);

                System.out.printf("Queue size: %d%n", urlQueue.size());
            } catch (Exception e) {
                System.err.printf("Error processing %s: %s%n", currentUrl, e.getMessage());
            }
        }

        saveImageUrls();
        System.out.printf("Crawl complete. Processed %d pages.%n", counter);
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

    private void processLinks(List<String> htmlLines, String currentUrl) throws URISyntaxException {

        List<String> imageUrls = HtmlParser.extractAllImageUrls(htmlLines);
        allImageUrls.addAll(imageUrls);


        for (String url : HtmlParser.extractAllUrls(htmlLines)) {
            if (!visitedUrls.contains(url)) {
                String absoluteUrl = toAbsoluteUrl(url, currentUrl);
                if (isSameDomain(absoluteUrl)) {
                    visitedUrls.add(absoluteUrl);
                    urlQueue.add(absoluteUrl);
                }
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

    private void saveImageUrls() throws IOException {
        Path imageListPath = baseSavePath.resolve("image_urls.txt");
        Files.write(imageListPath, allImageUrls);
    }
}
