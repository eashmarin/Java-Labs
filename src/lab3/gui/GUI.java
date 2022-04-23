package lab3.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {
    GuiMainFrame mainFrame;
    GuiRankingFrame rankingFrame;
    GuiMenu guiMenu;
    GuiPlayingArea playingArea;
    GuiTimerPanel timerPanel;

    public GUI(int height, int width) {
        mainFrame = new GuiMainFrame(height, width);
        rankingFrame = new GuiRankingFrame();
        guiMenu = new GuiMenu();
        playingArea = new GuiPlayingArea(height, width);
        timerPanel = new GuiTimerPanel();

        mainFrame.add(playingArea, BorderLayout.CENTER);

        mainFrame.add(timerPanel, BorderLayout.NORTH);
        //guiFrame.add(guiMenu, BorderLayout.NORTH);


        mainFrame.setJMenuBar(guiMenu);
    }

    public ArrayList<JButton> getButtons() {
        return playingArea.getButtons();
    }

    public void addMenuListener(ActionListener listener) {
        guiMenu.addMenuListener(listener);
    }

    GuiTimerPanel getTimerPanel() {
        return timerPanel;
    }


    public void showRankingFrame() {
        rankingFrame.setVisible(true);
    }
}
