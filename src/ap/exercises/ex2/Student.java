package ap.exercises.ex2;

class Student {
    String firstName;
    String lastName;
    int studentNumber;
    String fieldOfStudy;
    Book[] borrowedBooks = new Book[5];
    int borrowedCount = 0;
    public Student(String firstName, String lastName, int studentNumber, String fieldOfStudy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        this.fieldOfStudy = fieldOfStudy;
    }
    void borrowBook(Book book) {
        if (borrowedCount < borrowedBooks.length) {
            borrowedBooks[borrowedCount++] = book;
        }
    }
}


