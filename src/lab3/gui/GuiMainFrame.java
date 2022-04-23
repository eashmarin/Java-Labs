package lab3.gui;

import javax.swing.*;
import java.awt.*;

public class GuiMainFrame extends JFrame {


    public GuiMainFrame(int height, int width) {

        setTitle("Minesweeper");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(width * 30 + width, height * 30 + height));
        setLayout(new BorderLayout());
        setMinimumSize(getSize());
        setMaximumSize(getSize());
        //getContentPane().setLayout(new BorderLayout(height, width));



        //timeLabel.setLocation(getWidth() / 2 - (10) / 2, 50);
        //timeLabel.setText("5555");

        //().add(timeLabel);

        getContentPane().setVisible(true);
        setVisible(true);
    }

    //public void addPanel(JPanel panel) {
    //    addPanel(panel);
    //}

}
