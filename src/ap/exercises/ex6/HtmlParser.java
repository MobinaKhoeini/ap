package ap.exercises.ex6;

public class HtmlParser {
    public static String extractFirstUrl(String htmlLine) {
        if (htmlLine == null || htmlLine.isEmpty()) {
            return null;
        }

        int hrefIndex = htmlLine.indexOf("href=\"");
        if (hrefIndex < 0) {
            return null;
        }

        int start = hrefIndex + 6;
        int end = htmlLine.indexOf("\"", start);

        if (end < 0) {
            return null;
        }

        return htmlLine.substring(start, end);
    }
}

