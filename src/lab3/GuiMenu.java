package lab3;

import javax.swing.*;

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

        menu.add(newGame);
        menu.add(highScores);
        menu.add(about);
        menu.add(exit);

        add(menu);
        setVisible(true);
    }
}
