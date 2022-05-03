package lab3.gui;

import lab3.Controller;
import lab3.CustomTimer;
import lab3.Model;
import lab3.resources.Config;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GraphicalController implements Controller {        // TODO: rename to GuiController

    Model model;
    GraphicalView view;
    CustomTimer timer;
    ButtonListener buttonListener;
    MyMouseListener mouseListener;
    GUIMenuListener menuListener;
    boolean isRightButton;

    public GraphicalController(Model model, GraphicalView view) {
        this.model = model;
        this.view = view;

        model.setPlayerName(view.getInputName());

        isRightButton = false;
        buttonListener = new ButtonListener();
        mouseListener = new MyMouseListener();
        menuListener = new GUIMenuListener();

        view.addListeners(buttonListener, mouseListener, menuListener);

        timer = new CustomTimer(model, view);

        Thread threadTimer = new Thread(timer);
        threadTimer.start();
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!model.isGameOver()) {
                String text = ((JButton) e.getSource()).getName();
                String[] coords = text.split("[ ]");
                int y = Integer.parseInt(coords[0]);
                int x = Integer.parseInt(coords[1]);

                if (!isRightButton) {
                    if (!model.isMapGenerated()) {
                        model.generate(x, y);
                        model.print();  //TODO: DEBUG
                    }

                    model.reveal(x, y);
                } else {
                    if (model.isFlag(x,y))
                        model.removeFlag(x, y);
                    else
                        model.setFlag(x, y);
                }

                view.update(model);
            }
        }
    }

    class MyMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)) {
                isRightButton = true;
                ((JButton)e.getComponent()).doClick();
                isRightButton = false;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    class GUIMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = ((JMenuItem) e.getSource()).getName();
            if (name.equals("new_game")) {
                model.setDefaultModel();
                view.setTime(0);
                timer.startTimer();
                view.update(model);
            }

            if (name.equals("high_scores")) {
                view.showRanking(model.getRankingData());
            }

            if (name.equals("about"))
                view.showAbout();

            if (name.equals("beginner") || name.equals("intermediate") || name.equals("expert")) {
                int newWidth;
                int newHeight;
                int newMinesNum;
                if (name.equals("beginner")) {
                    newWidth = 9;
                    newHeight = 9;
                    newMinesNum = 10;
                }
                else {
                    if (name.equals("intermediate")) {
                        newWidth = 16;
                        newHeight = 16;
                        newMinesNum = 40;
                    }
                    else {
                        newWidth = 30;
                        newHeight = 16;
                        newMinesNum = 99;
                    }
                }

                if (model.getWidth() != newWidth || model.getHeight() != newHeight) {
                    if (model.isGameOver()) {
                        view.setTime(0);
                        timer.startTimer();
                        view.update(model);
                    }

                    Config.edit(newWidth, newHeight, newMinesNum);
                    view.changeSize(newWidth, newHeight);
                    view.addListeners(buttonListener, mouseListener, menuListener);
                    model.setDefaultModel();
                }

            }

            if (name.equals("exit")) {
                //view.getFrame().dispose();      // TODO: replace?
                System.exit(0);
            }
        }
    }
}
