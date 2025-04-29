package ap.exercises.ex4;

public class EX3_6 {
    private int[] switchStates;
    private int lampState;
    public EX3_6() {
        switchStates = new int[2];
        switchStates[0] = 0;
        switchStates[1] = 0;
        lampState = 0;
    }
    public int getSwitchState(int switchNum) {
        if (switchNum == 1 || switchNum == 2) {
            return switchStates[switchNum - 1];
        }
        else {
            throw new IllegalArgumentException("the number of switch can be 1 or 2");
        }
    }
    public int getLampState() {
        return lampState;
    }
    public void toggleSwitch(int switchNum) {
        if (switchNum == 1 || switchNum == 2) {
            switchStates[switchNum - 1] = 1 - switchStates[switchNum - 1];
        }
    }
    private void updateLampState() {
        if (switchStates[0] != switchStates[1]) {
            lampState = 1;
        }
        else {
            lampState = 0;
        }
    }
}
