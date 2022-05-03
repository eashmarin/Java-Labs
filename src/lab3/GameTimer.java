package lab3;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GameTimer {
    private Timer timer;

    GameTimer(ActionListener listener) {
        timer = new Timer(1000, listener);
        timer.start();
    }
}
