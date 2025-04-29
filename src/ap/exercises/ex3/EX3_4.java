package ap.exercises.ex3;

public class EX3_4 {
    private int firstSwitch;
    private int secondSwitch;
    private int lampState;
    public EX3_4() {
        firstSwitch = 0;
        secondSwitch = 0;
        lampState = 0;
    }
    public int getFirstSwitch() {
        return firstSwitch;
    }
    public int getSecondSwitch() {
        return secondSwitch;
    }
    public int getLampState() {
        return lampState;
    }
    public void toggleFirstSwitch() {
        firstSwitch = 1 - firstSwitch;
        updateLampState();
    }
    public void toggleSecondSwitch() {
        secondSwitch = 1 - secondSwitch;
        updateLampState();
    }
    private void updateLampState() {
        if (firstSwitch != secondSwitch) {
            lampState = 1;
        }
        else {
            lampState = 0;
        }
    }
}
