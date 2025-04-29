package ap.exercises.ex4;

public class EX3_12_Tester {
    public static void main(String[] args) {
        EX3_12 ex3_12 = new EX3_12("Mobina",10_000_000);
        EX3_12 ex3_13 = new EX3_12("Hadis", 15_000_000);
        System.out.println("name: " + ex3_12.getName());
        System.out.println("salary: " + ex3_13.getSalary());
        System.out.println("the salary would be: " + ex3_12.raisingSalary(20));
        System.out.println("the salary is: " + ex3_13.raisingSalary(12));
    }
}
