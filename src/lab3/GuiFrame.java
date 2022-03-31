package lab3;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GuiFrame extends JFrame {
    ArrayList<JButton> buttons;

    public GuiFrame(int height, int width) {

        buttons = new ArrayList<>();
        //int size = height * width;

        int hgap = 1;
        int vgap = 1;

        setTitle("Minesweeper");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(width * 30 + width * hgap, height * 30 + height * vgap));
        setMinimumSize(getSize());
        setMaximumSize(getSize());
        getContentPane().setLayout(new GridLayout(height, width, hgap, vgap));

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                JButton button = new JButton();
                button.setSize(30, 30);
                button.setName(i + " " + j);
                getContentPane().add(button);
                buttons.add(button);
            }
        }
        getContentPane().setVisible(true);
        setVisible(true);
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }
}
