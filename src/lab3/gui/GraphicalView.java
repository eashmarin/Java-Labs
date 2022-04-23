package lab3.gui;

import lab3.Model;
import lab3.View;

import javax.swing.*;
import lab3.gui.GraphicalController.GUIMenuListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GraphicalView implements View {
    int height;
    int width;
    GUI gui;
    ArrayList<JButton> buttons;
    ImageIcon buttonIcon;
    ImageIcon revealedIcon;
    ImageIcon oneIcon;
    ImageIcon twoIcon;
    ImageIcon threeIcon;
    ImageIcon fourIcon;
    ImageIcon fiveIcon;
    ImageIcon flagIcon;
    ImageIcon mineIcon;
    ImageIcon mineExplodedIcon;

    public GraphicalView(int height, int width) {
        this.height = height;
        this.width = width;

        gui = new GUI(height, width);

        buttons = gui.getButtons();

        buttonIcon = new ImageIcon(getClass().getResource("/lab3/resources/button.png"), "tg");
        revealedIcon = new ImageIcon(getClass().getResource("/lab3/resources/revealed.png"), "rv");
        oneIcon = new ImageIcon(getClass().getResource("/lab3/resources/1.png"), "1");
        twoIcon = new ImageIcon(getClass().getResource("/lab3/resources/2.png"), "2");
        threeIcon = new ImageIcon(getClass().getResource("/lab3/resources/3.png"), "3");
        fourIcon = new ImageIcon(getClass().getResource("/lab3/resources/4.png"), "4");
        fiveIcon = new ImageIcon(getClass().getResource("/lab3/resources/5.png"), "5");
        flagIcon = new ImageIcon(getClass().getResource("/lab3/resources/flag.png"), "flag");
        mineIcon = new ImageIcon(getClass().getResource("/lab3/resources/mine.png"), "mine");
        mineExplodedIcon = new ImageIcon(getClass().getResource("/lab3/resources/mine_exploded.png"), "mine_exploded");
        int size = width * height;
        for (int i = 0; i < size; i++)
            buttons.get(i).setIcon(buttonIcon);
    }

    @Override
    public void update(Model model) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int index = i * width + j;
                if (model.isRevealed(j, i)) {
                    if (model.at(j, i) == '0')
                        buttons.get(index).setIcon(revealedIcon);
                    if (model.at(j, i) == '1')
                        buttons.get(index).setIcon(oneIcon);
                    if (model.at(j, i) == '2')
                        buttons.get(index).setIcon(twoIcon);
                    if (model.at(j, i) == '3')
                        buttons.get(index).setIcon(threeIcon);
                    if (model.at(j, i) == '4')
                        buttons.get(index).setIcon(fourIcon);
                    if (model.at(j, i) == '5')
                        buttons.get(index).setIcon(fiveIcon);
                    if (model.isMine(j, i)) {
                        if (model.isFlag(j, i))
                            buttons.get(index).setIcon(mineIcon);
                        else
                            buttons.get(index).setIcon(mineExplodedIcon);
                    }
                } else {
                    if (model.isFlag(j, i))
                        buttons.get(index).setIcon(flagIcon);
                    else
                        buttons.get(index).setIcon(buttonIcon);
                }
            }
        }
    }

    public void addListeners(ActionListener buttonListener, MouseListener mouseListener, GUIMenuListener menuListener) {
        int size = height * width;
        for (int i = 0; i < size; i++) {
            buttons.get(i).addActionListener(buttonListener);
            buttons.get(i).addMouseListener(mouseListener);
        }

        gui.addMenuListener(menuListener);
    }

    public void setTime(int value) {
        gui.getTimerPanel().setTime(value);
    }


    public void showRankingFrame() {
        gui.showRankingFrame();
    }
}
