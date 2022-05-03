package lab3.gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GuiMenu extends JMenuBar {

    JMenu menu;
    JMenuItem newGame;
    JMenuItem highScores;
    JMenu difficulty;
    JMenuItem beginner;
    JMenuItem intermediate;
    JMenuItem expert;
    JMenuItem about;
    JMenuItem exit;

    public GuiMenu() {
        menu = new JMenu("Menu");
        newGame = new JMenuItem("New Game");
        highScores = new JMenuItem("High Scores");
        difficulty = new JMenu("Difficulty");
        about = new JMenuItem("About");
        beginner = new JMenuItem("Beginner");
        intermediate = new JMenuItem("Intermediate");
        expert = new JMenuItem("Expert");
        exit = new JMenuItem("Exit");

        newGame.setName("new_game");
        highScores.setName("high_scores");
        difficulty.setName("difficulty");
        beginner.setName("beginner");
        intermediate.setName("intermediate");
        expert.setName("expert");
        about.setName("about");
        exit.setName("exit");

        menu.add(newGame);
        menu.add(highScores);
        menu.add(difficulty);
        difficulty.add(beginner);
        difficulty.add(intermediate);
        difficulty.add(expert);
        menu.add(about);
        menu.add(exit);

        add(menu);
        setVisible(true);
    }

    public void addMenuListener(ActionListener listener) {
        newGame.addActionListener(listener);
        highScores.addActionListener(listener);
        beginner.addActionListener(listener);
        intermediate.addActionListener(listener);
        expert.addActionListener(listener);
        about.addActionListener(listener);
        exit.addActionListener(listener);
        //menu.addMenuListener(listener);
    }
}
