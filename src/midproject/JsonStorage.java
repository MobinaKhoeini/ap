package midproject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

public class JsonStorage implements StorageHandler {
    private final String FILE_PATH = "library.json";
    private final Gson gson = new Gson();

    @Override
    public void saveLibrary(Library library) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            writer.println(gson.toJson(library.getBooks()));
            writer.println(gson.toJson(library.getStudents()));
            writer.println(gson.toJson(library.getEmployees()));
            writer.println(gson.toJson(library.getBorrowedBooks()));
            writer.println(gson.toJson(library.getLoans()));
            writer.println(gson.toJson(library.getReceives()));
        }
    }

    @Override
    public void loadLibrary(Library library) throws IOException {
        ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(Paths.get(FILE_PATH));
        Type listType = new TypeToken<ArrayList<Book>>(){}.getType();

        library.setBooks(gson.fromJson(lines.get(0), listType));
        library.setStudents(gson.fromJson(lines.get(1), new TypeToken<ArrayList<Student>>(){}.getType()));
        library.setEmployees(gson.fromJson(lines.get(2), new TypeToken<ArrayList<Employee>>(){}.getType()));
        library.setBorrowedBooks(gson.fromJson(lines.get(3), new TypeToken<ArrayList<Loan>>(){}.getType()));
        library.setLoans(gson.fromJson(lines.get(4), new TypeToken<ArrayList<Loan>>(){}.getType()));
        library.setReceives(gson.fromJson(lines.get(5), new TypeToken<ArrayList<Loan>>(){}.getType()));
    }
}