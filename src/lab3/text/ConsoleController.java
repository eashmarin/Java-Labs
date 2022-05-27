package lab3.text;

import lab3.Controller;
import lab3.Model;
import lab3.View;

import java.util.Scanner;

public class ConsoleController implements Controller {

    private final Model model;
    private final View view;

    public ConsoleController(Model model, View view) {
        this.model = model;
        this.view = view;

        parseInput();
    }

    private void parseInput() {
        model.setPlayerName(view.getInputName());

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();

            if (line.equals("new game")) {
                model.setDefaultModel();
                view.setTime(0);
                view.stopTimer();
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
                if (model.getTime() == 0)
                    view.startTimer();

                String[] splitLine = line.split("[ ]");

                if (splitLine.length > 2) {
                    int x = Integer.parseInt(splitLine[0]);
                    int y = Integer.parseInt(splitLine[1]);

                    char mode = splitLine[2].charAt(0);

                    if (mode == 'L')
                        model.reveal(x, y);

                    if (mode == 'R')
                        model.setFlag(x, y);

                    view.update();
                }
            }
        }
    }
}