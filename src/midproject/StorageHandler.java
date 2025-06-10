package midproject;

import java.io.IOException;
import java.util.ArrayList;

public interface StorageHandler {
    void saveLibrary(Library library) throws IOException;
    void loadLibrary(Library library) throws IOException;
}
