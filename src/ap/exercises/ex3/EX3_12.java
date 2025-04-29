package ap.exercises.ex3;

public class EX3_12 {
    private String name;
    private double salary;

    public EX3_12(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public double raisingSalary(double percent) {
        return salary = salary + (salary * (percent / 100));
    }
}
