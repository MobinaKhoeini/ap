package midproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigReader {
    public static String getStorageType() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("config.txt")));
        String[] parts = content.split("=");
        if (parts.length != 2) {
            throw new IOException("Invalid config file format");
        }
        return parts[1].trim().toLowerCase();
    }
}
