package ap.exercises.ex3;

public class EX3_10 {
    private double purchase;
    private String receipt;
    public EX3_10() {
        purchase = 0;
        receipt = "";
    }
    public void managePurchases(double amount) {
        purchase += amount;
        receipt = receipt.concat(String.valueOf(amount)).concat("\n");
    }
    public double getPurchase() {
        return purchase;
    }
    public void printReceipt() {
        System.out.println("your receipt:");
        System.out.println(receipt);
        System.out.println("total: " + purchase);
        receipt = "";
    }
}
