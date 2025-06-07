package ap.exercises.ex6;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParser {
    private static final Pattern AUDIO_PATTERN = Pattern.compile(
            "(https?://[^\\s\"']+\\.(mp3|wav|ogg|m4a))",
            Pattern.CASE_INSENSITIVE
    );

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
    public static List<String> extractAllAudioUrls(List<String> htmlLines) {
        List<String> audioUrls = new ArrayList<>();
        for (String line : htmlLines) {
            Matcher matcher = AUDIO_PATTERN.matcher(line);
            while (matcher.find()) {
                audioUrls.add(matcher.group(1));
            }
        }
        return audioUrls;
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

