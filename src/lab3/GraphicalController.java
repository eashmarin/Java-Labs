package lab3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GraphicalController implements Controller{

    Model model;
    View view;
    ButtonListener buttonListener;
    MyMouseListener mouseListener;
    boolean isRightButton;
    boolean gameOver;

    public GraphicalController(Model model, GraphicalView view) {
        this.model = model;
        this.view = view;
        isRightButton = false;
        gameOver = false;
        buttonListener = new ButtonListener();
        mouseListener = new MyMouseListener();
        view.addListeners(buttonListener, mouseListener);
    }

    @Override
    public void getInput() {

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
                    //if (model.isMine(x, y)) {
                        //gameOver = true;
                    //    model.revealMines();
                    //}
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
}
