package lab3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomTimer implements Runnable{

    Model model;
    View view;
    Timer timer;

    public CustomTimer(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void startTimer() {
        timer.start();
    }


    @Override
    public void run() {

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!model.isGameOver()) {
                    model.increaseTimeBySec();
                    view.setTime((int)model.getTime());
                } else {
                    if (model.isWin()) {
                        view.showWinDialog();
                    }
                    else
                        view.showLoseDialog();
                    timer.stop();
                }
            }
        });
    }

    public void stop() {
        timer.stop();
    }
}
