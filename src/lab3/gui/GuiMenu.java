package lab3.gui;

import javax.swing.*;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuListener;
import java.awt.event.ActionListener;

public class GuiMenu extends JMenuBar {

    JMenu menu;
    JMenuItem newGame;
    JMenuItem highScores;
    JMenuItem about;
    JMenuItem exit;

    public GuiMenu() {
        menu = new JMenu("Menu");
        newGame = new JMenuItem("New Game");
        highScores = new JMenuItem("High Scores");
        about = new JMenuItem("About");
        //newGame = new JMenuItem("New Game");
        exit = new JMenuItem("Exit");

        newGame.setName("new_game");
        highScores.setName("high_scores");
        about.setName("about");
        exit.setName("exit");

        menu.add(newGame);
        menu.add(highScores);
        menu.add(about);
        menu.add(exit);

        add(menu);
        setVisible(true);
    }

    public void addMenuListener(ActionListener listener) {
        newGame.addActionListener(listener);
        highScores.addActionListener(listener);
        about.addActionListener(listener);
        exit.addActionListener(listener);
        //menu.addMenuListener(listener);
    }
}
