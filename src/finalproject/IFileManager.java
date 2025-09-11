package finalproject;

import java.util.List;
public interface IFileManager {
    void saveStudents(List<Student> students);
    List<Student> loadStudents();
    void saveBooks(List<Book> books);
    List<Book> loadBooks();
    void saveLoans(List<Loan> loans);
    List<Loan> loadLoans();
    void saveEmployees(List<Employee> employees);
    List<Employee> loadEmployees();
}
