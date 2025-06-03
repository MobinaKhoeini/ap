package ap.exercises.ex6;

import java.io.PrintWriter;
import java.util.List;

public class HtmlFileManager {
    private final String saveDirectory;
    private int fileCounter = 0;

    public HtmlFileManager(String saveDirectory) {
        this.saveDirectory = saveDirectory;
    }

    public void save(List<String> lines) {
        String filePath = getNextFilePath();
        try (PrintWriter out = new PrintWriter(filePath)) {
            for (String line : lines) {
                out.println(line);
            }
            System.out.println("File saved: " + filePath);
        } catch (Exception e) {
            System.err.println("Save failed for " + filePath + ": " + e.getMessage());
        }
    }

    private String getNextFilePath() {
        fileCounter++;
        return saveDirectory + "/page_" + fileCounter + ".html";
    }

    public String getSaveDirectory() {
        return saveDirectory;
    }
}
