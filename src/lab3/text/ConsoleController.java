package lab3.text;

import lab3.Controller;
import lab3.Model;
import lab3.View;

import java.util.Scanner;

public class ConsoleController implements Controller {

    Model model;
    View view;

    int x;
    int y;
    char mode;

    boolean gameOver;

    public ConsoleController(Model model, View view) {
        this.model = model;
        this.view = view;
        gameOver = false;

        parseInput();
    }

    private void parseInput() {
        while (!model.isGameOver()) {
            Scanner scanner = new Scanner(System.in);
            x = scanner.nextInt();
            y = scanner.nextInt();
            mode = scanner.next().charAt(0);

            if (mode == 'L'){
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
