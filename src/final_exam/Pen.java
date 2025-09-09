package final_exam;

public class Pen extends Product{
    public enum Color {
        GREEN, BLUE, RED, BLACK
    }
    private Color color;
    public Pen(String name, long price, Color color) {
        super(name, price);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Pen{" +
                "name=" + getName() +
                ",color=" + color +
                ", price=" + getPrice() +
                '}';
    }
}

