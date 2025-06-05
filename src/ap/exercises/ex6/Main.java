package ap.exercises.ex6;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        try {
            String domainAddress = Conf.DOMAIN_ADDRESS;
            String savePath = DirectoryTools.createDirectoryWithTimeStamp(Conf.SAVE_DIRECTORY);

            DomainHtmlScraper domainHtmlScraper = new DomainHtmlScraper(domainAddress, savePath);
            domainHtmlScraper.start();

            HtmlAnalyzer.printTopCountUrls(10);
        } catch (Exception e) {
            System.err.println("Error in main: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
