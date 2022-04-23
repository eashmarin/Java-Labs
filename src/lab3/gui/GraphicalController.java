package lab3.gui;

import lab3.Controller;
import lab3.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GraphicalController implements Controller {        // TODO: rename to GuiController

    Model model;
    GraphicalView view;
    Timer timer;
    ButtonListener buttonListener;
    MyMouseListener mouseListener;
    GUIMenuListener menuListener;
    TimeElapsedListener timeElapsedListener;
    boolean isRightButton;

    public GraphicalController(Model model, GraphicalView view) {
        this.model = model;
        this.view = view;

        isRightButton = false;
        buttonListener = new ButtonListener();
        mouseListener = new MyMouseListener();
        menuListener = new GUIMenuListener();
        timeElapsedListener = new TimeElapsedListener();

        view.addListeners(buttonListener, mouseListener, menuListener);

        timer = new Timer(1000, timeElapsedListener);
        timer.start();
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
                        model.print();
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
            if (name == "new_game") {
                model.setDefaultModel();
                view.setTime(0);
                timer.start();
                view.update(model);
            }

            if (name == "high_scores") {
                view.showRankingFrame();
            }

            if (name == "exit") {
                //view.getFrame().dispose();      // TODO: replace?
                System.exit(0);
            }
        }
    }

    class TimeElapsedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!model.isGameOver()) {
                model.increaseTimeBySec();
                view.setTime(model.getTime());
            } else {
                timer.stop();
            }
        }
    }
}
