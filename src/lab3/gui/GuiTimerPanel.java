package lab3.gui;

import javax.swing.*;
import java.awt.*;

public class GuiTimerPanel extends JPanel {
    private final JLabel label;
    public GuiTimerPanel() {

        label = new JLabel("0");
        label.setSize(150, 150);
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 18));

        setLayout(new GridBagLayout());

        add(label);
    }

    public void setTime(int value) {
        label.setText(Integer.toString(value));
    }
}
