package ap.exercises.ex6;

import java.util.ArrayList;
import java.util.List;

public class HtmlParser {

    public static List<String> extractAllUrls(List<String> htmlLines) {
        List<String> urls = new ArrayList<>();
        for (String line : htmlLines) {
            String url = extractUrl(line);
            if (url != null && !url.isEmpty()) {
                urls.add(url);
            }
        }
        return urls;
    }

    public static List<String> extractAllImageUrls(List<String> htmlLines) {
        List<String> imageUrls = new ArrayList<>();
        for (String line : htmlLines) {
            String imgUrl = extractImageUrl(line);
            if (imgUrl != null && !imgUrl.isEmpty()) {
                imageUrls.add(imgUrl);
            }
        }
        return imageUrls;
    }

    public static String extractUrl(String line) {
        return extractAttribute(line, "href=\"");
    }

    private static String extractImageUrl(String line) {
        return extractAttribute(line, "src=\"");
    }

    private static String extractAttribute(String line, String attribute) {
        int start = line.indexOf(attribute);
        if (start < 0) return null;

        start += attribute.length();
        int end = line.indexOf("\"", start);
        if (end < 0) return null;

        return line.substring(start, end);
    }
}

