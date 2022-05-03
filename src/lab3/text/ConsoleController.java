package lab3.text;

import lab3.Controller;
import lab3.Model;
import lab3.CustomTimer;
import lab3.View;

import java.util.Scanner;

public class ConsoleController implements Controller {

    Model model;
    View view;

    int x;
    int y;
    String line;
    CustomTimer timer;

    boolean gameOver;

    public ConsoleController(Model model, View view) {
        this.model = model;
        this.view = view;
        gameOver = false;

        timer = new CustomTimer(model, view);

        Thread threadTimer = new Thread(timer);
        threadTimer.start();

        parseInput();
    }

    private void parseInput() {

        model.setPlayerName(view.getInputName());

        while (true) {
            Scanner scanner = new Scanner(System.in);
            line = scanner.nextLine();

            if (line.equals("new game")) {
                model.setDefaultModel();
                view.setTime(0);
                timer.startTimer();
                continue;
            }

            if (line.equals("ranking")) {
                view.showRanking(model.getRankingData());
                continue;
            }

            if (line.equals("about")) {
                view.showAbout();
                continue;
            }

            if (line.equals("exit"))
                return;

            if (!model.isGameOver()) {
                String[] splitLine = line.split("[ ]");

                if (splitLine.length > 2) {
                    x = Integer.parseInt(splitLine[0]);
                    y = Integer.parseInt(splitLine[1]);

                    char mode = splitLine[2].charAt(0);

                    if (mode == 'L') {
                        if (!model.isMapGenerated()) {
                            model.generate(x, y);
                            model.print();              // TODO: DEBUG
                        }
                        model.reveal(x, y);
                    }

                    if (mode == 'R') {
                        if (model.isFlag(x, y))
                            model.removeFlag(x, y);
                        else
                            model.setFlag(x, y);
                    }
                    view.update(model);
                }
            }
        }
    }
}