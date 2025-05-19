package midproject;

public class Employee {
    private String firstName;
    private String lastName;
    private int employeeNumber;
    private int receiveNum;
    private int deliveryNum;
    public Employee(int employeeNumber) {
        this.employeeNumber = employeeNumber;
        this.receiveNum = 0;
        this.deliveryNum = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public int getReceiveNum() {
        return receiveNum;
    }

    public int getDeliveryNum() {
        return deliveryNum;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setReceiveNum(int receiveNum) {
        this.receiveNum = receiveNum;
    }

    public void setDeliveryNum(int deliveryNum) {
        this.deliveryNum = deliveryNum;
    }

    public void receiveNumIncrement() {
        receiveNum++;
    }
    public void deliveryNumIncrement() {
        deliveryNum++;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeNumber=" + employeeNumber +
                ", receiveNum=" + receiveNum +
                ", deliveryNum=" + deliveryNum +
                '}';
    }
}
