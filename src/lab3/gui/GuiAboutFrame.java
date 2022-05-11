package lab3.gui;

import javax.swing.*;
import java.awt.*;

public class GuiAboutFrame extends JFrame {

    public GuiAboutFrame() {
        JLabel label = new JLabel("This game was made by student of IT department - Evgeniy Ashmarin");
        add(label);
        label.setSize(200, 50);
        setSize(new Dimension(200, 50));
    }
}
