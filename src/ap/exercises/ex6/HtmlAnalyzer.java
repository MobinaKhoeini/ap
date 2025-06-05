package ap.exercises.ex6;

import ap.exercises.ex6.Conf;
import ap.exercises.ex6.DirectoryTools;
import ap.exercises.ex6.FileTools;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class HtmlAnalyzer {
    private static List<String> fileList = DirectoryTools.getFilesAbsolutePathInDirectory(Conf.SAVE_DIRECTORY);

    public static List<String> getAllUrls() {
        return fileList.stream()
                .map(FileTools::getTextFileLines)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .map(line -> HtmlParser.extractUrl(line))
                .filter(Objects::nonNull)
                .filter(url -> !url.isEmpty())
                .collect(Collectors.toList());
    }

    public static List<String> getTopUrls(int k) {
        Map<String, Long> urlCount = getAllUrls().stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        return urlCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static void printTopCountUrls(int k) {
        ObjectCounter urlCounter = new ObjectCounter();
        getAllUrls().forEach(urlCounter::add);
        urlCounter.getTop(k).forEach(entry ->
                System.out.println(entry.getKey() + " -> " + entry.getValue())
        );
    }

    public static void main(String[] args) {
        System.out.println("Top 10 URLs by count:");
        printTopCountUrls(10);

        System.out.println("\n____________________\n");

        System.out.println("Top 10 URLs:");
        getTopUrls(10).forEach(System.out::println);
    }
}
