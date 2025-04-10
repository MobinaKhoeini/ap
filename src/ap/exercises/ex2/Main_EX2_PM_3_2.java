package ap.exercises.ex2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main_EX2_PM_3_2 extends JFrame implements KeyListener {
    private int SquareSize;
    private Point playerPos = new Point(1, 1);
    private static final int DOT_SIZE = 10;
    private static final int WALL_THICKNESS = 5;
    private boolean gameRunning = true;

    public Main_EX2_PM_3_2(int size) {
        this.SquareSize = size;
        setSize(SquareSize * DOT_SIZE + 100, SquareSize * DOT_SIZE + 150);
        setTitle("packman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setResizable(false);

        String input = JOptionPane.showInputDialog("Enter the square size:");
        SquareSize = Integer.parseInt(input);

        JOptionPane.showMessageDialog(this,
                "Controls:\nArrow keys to move\nQ to quit");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);
        g.fillRect(50, 80, SquareSize * DOT_SIZE, WALL_THICKNESS);
        g.fillRect(50, 80, WALL_THICKNESS, SquareSize * DOT_SIZE);
        g.fillRect(50, 80 + SquareSize * DOT_SIZE - WALL_THICKNESS,
                SquareSize * DOT_SIZE, WALL_THICKNESS);
        g.fillRect(50 + SquareSize * DOT_SIZE - WALL_THICKNESS, 80,
                WALL_THICKNESS, SquareSize * DOT_SIZE);

        g.setColor(Color.BLUE);
        g.fillOval(50 + playerPos.x * DOT_SIZE, 80 + playerPos.y * DOT_SIZE, DOT_SIZE, DOT_SIZE);
    }

    private void movePlayer(int direction) {
        Point newPos = new Point(playerPos);

        switch (direction) {
            case KeyEvent.VK_UP: newPos.y--; break;
            case KeyEvent.VK_DOWN: newPos.y++; break;
            case KeyEvent.VK_LEFT: newPos.x--; break;
            case KeyEvent.VK_RIGHT: newPos.x++; break;
        }

        if (newPos.x > 0 && newPos.x < SquareSize -1 && newPos.y > 0 && newPos.y < SquareSize -1) {
            playerPos = newPos;
            repaint();
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameRunning) return;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                movePlayer(e.getKeyCode());
                break;
            case KeyEvent.VK_Q:
                gameRunning = false;
                System.exit(0);
                break;
        }
    }

    public static void main(String[] args) {
        Main_EX2_PM_3_2 game = new Main_EX2_PM_3_2(15);
        game.setVisible(true);
    }
}