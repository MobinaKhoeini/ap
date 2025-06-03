package ap.exercises.ex6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DomainHtmlScraper {
    private final String domainAddress;
    private final Queue<String> queue;
    private final Set<String> visitedUrls;
    private final HtmlFileManager htmlFileManager;
    private final List<String> allImageUrls;
    private final String saveDirectory;

    public DomainHtmlScraper(String domainAddress, String savePath) {
        this.domainAddress = domainAddress;
        this.queue = new LinkedList<>();
        this.visitedUrls = new HashSet<>();
        this.htmlFileManager = new HtmlFileManager(savePath);
        this.allImageUrls = new ArrayList<>();
        this.saveDirectory = savePath;
    }

    public void start() throws IOException {
        queue.add(domainAddress);
        visitedUrls.add(domainAddress);
        int counter = 0;

        while (!queue.isEmpty() && counter < 100) {
            String url = queue.remove();
            counter++;

            try {
                List<String> htmlLines = HtmlFetcher.fetchHtml(url);
                this.htmlFileManager.save(htmlLines);


                List<String> imageUrls = extractImageUrls(htmlLines);
                allImageUrls.addAll(imageUrls);


                List<String> urls = extractUrls(htmlLines);

                for (String newUrl : urls) {
                    if (!visitedUrls.contains(newUrl)) {
                        String absoluteUrl = makeAbsoluteUrl(newUrl);
                        if (absoluteUrl.startsWith(domainAddress)) {
                            visitedUrls.add(absoluteUrl);
                            queue.add(absoluteUrl);
                        }
                    }
                }

                System.out.println("[" + counter + "] " + url + " processed. Queue size: " + queue.size());
            } catch (Exception e) {
                System.out.println("ERROR processing " + url + ": " + e.getMessage());
            }
        }

        saveImageUrlsToFile();
        System.out.println("Operation complete. Total pages processed: " + counter);
    }


    private List<String> extractUrls(List<String> htmlLines) {
        List<String> urls = new ArrayList<>();
        for (String line : htmlLines) {
            String url = extractUrlFromLine(line);
            if (url != null && !url.isEmpty()) {
                urls.add(url);
            }
        }
        return urls;
    }

    private List<String> extractImageUrls(List<String> htmlLines) {
        List<String> imageUrls = new ArrayList<>();
        for (String line : htmlLines) {
            String imageUrl = extractImageUrlFromLine(line);
            if (imageUrl != null && !imageUrl.isEmpty()) {
                imageUrls.add(imageUrl);
            }
        }
        return imageUrls;
    }

    private String extractUrlFromLine(String line) {
        return extractAttribute(line, "href=\"");
    }

    private String extractImageUrlFromLine(String line) {
        return extractAttribute(line, "src=\"");
    }

    private String extractAttribute(String line, String attribute) {
        int start = line.indexOf(attribute);
        if (start < 0) return null;

        start += attribute.length();
        int end = line.indexOf("\"", start);
        if (end < 0) return null;

        return line.substring(start, end);
    }

    private String makeAbsoluteUrl(String url) {
        if (url.startsWith("http")) {
            return url;
        }
        return domainAddress + (url.startsWith("/") ? url : "/" + url);
    }

    private void saveImageUrlsToFile() {
        try {
            Path imageUrlsPath = Paths.get(saveDirectory, "image_urls.txt");
            Files.write(imageUrlsPath, allImageUrls);
            System.out.println("Image URLs saved to: " + imageUrlsPath);
        } catch (IOException e) {
            System.err.println("Failed to save image URLs: " + e.getMessage());
        }
    }
}
