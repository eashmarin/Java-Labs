package lab3.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;

public class GUI {
    GuiMainFrame mainFrame;
    GuiRankingFrame rankingFrame;
    GuiMenu guiMenu;
    GuiPlayingArea playingArea;
    GuiTimerPanel timerPanel;
    GuiAboutFrame aboutFrame;

    public GUI(int height, int width) {
        mainFrame = new GuiMainFrame(height, width);
        rankingFrame = new GuiRankingFrame();
        guiMenu = new GuiMenu();
        playingArea = new GuiPlayingArea(height, width);
        timerPanel = new GuiTimerPanel();
        aboutFrame = new GuiAboutFrame();

        mainFrame.add(playingArea, BorderLayout.CENTER);

        mainFrame.add(timerPanel, BorderLayout.NORTH);

        mainFrame.setJMenuBar(guiMenu);

        mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/lab3/resources/logo.png").getFile()));

        rankingFrame.setLocation(mainFrame.getLocation().x + mainFrame.getWidth() + 10, 0);
    }

    public String getInputName() {
        String name = new String();
        while (name == null || name.isEmpty()) {
            name = JOptionPane.showInputDialog(null, "Type your name:", "Authorization", JOptionPane.DEFAULT_OPTION);
        }
        return name;
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

    public void showRankingFrame(TreeMap<String, Double> rankingData) {
        rankingFrame.setData(rankingData);
        rankingFrame.setVisible(true);
    }

    public void changeSize(int width, int height) {
        mainFrame.setVisible(false);
        playingArea.setVisible(false);

        mainFrame = new GuiMainFrame(height, width);
        playingArea = new GuiPlayingArea(height, width);

        mainFrame.add(playingArea, BorderLayout.CENTER);
        mainFrame.add(timerPanel, BorderLayout.NORTH);
        mainFrame.setJMenuBar(guiMenu);
    }
}
