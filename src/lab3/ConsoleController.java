package lab3;

import java.util.Scanner;

public class ConsoleController implements Controller{

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
    }

    @Override
    public void getInput() {
        while (!gameOver) {
            Scanner scanner = new Scanner(System.in);
            x = scanner.nextInt();
            y = scanner.nextInt();
            mode = scanner.next().charAt(0);

            if (mode == 'L'){
                if (!model.isMapGenerated()) {
                    model.generate(x, y);
                    model.print();              // TODO: DEBUG
                }

                if (model.isMine(x, y)) {
                    gameOver = true;
                    model.revealMines();
                }
                else
                    model.reveal(x, y);
            }

            if (mode == 'R')
                model.changeFlag(x, y);

            view.update(model);
        }
    }
}
