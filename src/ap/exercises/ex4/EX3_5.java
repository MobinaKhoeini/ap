package ap.exercises.ex4;

public class EX3_5 {
    public static void main(String[] args) {
        EX3_4 circuit = new EX3_4();
        combinationTest(circuit, 0, 0);
        combinationTest(circuit, 0, 1);
        combinationTest(circuit, 1, 0);
        combinationTest(circuit, 1, 1);
    }
    public static void combinationTest(EX3_4 circuit, int firstSwitch, int secondSwitch) {
        setSwitchPositions(circuit, firstSwitch, secondSwitch);
        int actualSwitch1 = circuit.getFirstSwitch();
        int actualSwitch2 = circuit.getSecondSwitch();
        int actualLamp = circuit.getLampState();
        int expectedLamp = (firstSwitch != secondSwitch) ? 1 : 0;
        System.out.println("testing combinations:");
        System.out.println("first switch statement");
        System.out.println("actual:" + actualSwitch1 + "\t" + "expected:" + firstSwitch);
        System.out.println("second switch statement");
        System.out.println("actual:" + actualSwitch2 + "\t" + "expected:" + secondSwitch);
        System.out.println("lamp statement");
        System.out.println("actual:" + actualLamp + "\t" + "expected:" + expectedLamp);
        if (actualSwitch1 == firstSwitch && actualSwitch2 == secondSwitch && actualLamp == expectedLamp) {
            System.out.println("passed the test!");
        }
        else {
            System.out.println("failed the test!");
        }
    }
    public static void setSwitchPositions(EX3_4 circuit, int firstSwitch, int secondSwitch) {
        if (circuit.getFirstSwitch() != firstSwitch) {
            circuit.toggleFirstSwitch();
        }
        if (circuit.getSecondSwitch() != secondSwitch) {
            circuit.toggleSecondSwitch();
        }
    }
}
