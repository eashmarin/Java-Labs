package lab3.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GuiPlayingArea extends JPanel {
    private ArrayList<JButton> buttons;

    GuiPlayingArea(int height, int width) {
        int hgap = 1;
        int vgap = 1;

        buttons = new ArrayList<>();
        setLayout(new GridLayout(height, width, hgap, vgap));

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                JButton button = new JButton();
                button.setSize(30, 30);
                button.setName(i + " " + j);
                add(button);
                buttons.add(button);
            }
        }

    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }
}
