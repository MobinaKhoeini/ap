package ap.exercises.ex5;

import ap.exercises.ex5.Conf;
import ap.exercises.ex5.DomainHtmlScraper;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String domainAddress = Conf.DOMAIN_ADDRESS;
        String savePath = Conf.SAVE_DIRECTORY;

        DomainHtmlScraper domainHtmlScraper = new DomainHtmlScraper(domainAddress, savePath);
        domainHtmlScraper.start();
    }
}
