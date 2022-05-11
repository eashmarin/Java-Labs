package lab3.text;

import lab3.CustomTimer;
import lab3.Model;
import lab3.View;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ConsoleView implements View {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_BLACK_BRIGHT = "\033[0;90m";

    private int height;
    private int width;
    private CustomTimer timer;
    private Model model;

    public ConsoleView(Model model) {
        this.height = model.getHeight();
        this.width = model.getWidth();
        this.model = model;

        timer = new CustomTimer(model, this);

        Thread threadTimer = new Thread(timer);
        threadTimer.start();
    }

    @Override
    public void update() {
        System.out.print("\n\n");

        for (int i = 0; i < width; i++)
            System.out.print(ANSI_PURPLE + i + "  " + ANSI_RESET);
        System.out.println();

        char symbol;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (model.isRevealed(j, i)) {
                    symbol = model.at(j, i);
                }
                else {
                    if (model.isFlag(j, i)) {
                        symbol = '*';
                        System.out.print(ANSI_RED);
                    }
                    else
                        symbol = '^';
                }
                System.out.print(symbol + "  " + ANSI_RESET);
            }
            System.out.print(ANSI_BLUE + i + ANSI_RESET);
            System.out.println();
        }
        System.out.print("\n\n");
    }

    @Override
    public String getInputName() {
        System.out.print(ANSI_BLACK_BRIGHT + "Your name: " + ANSI_RESET);
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    @Override
    public void setTime(int time) {

    }

    @Override
    public void showWinDialog() {
        System.out.print("\n\n" + ANSI_GREEN + "VICTORY"  + ANSI_RESET + "\n\n");
    }

    @Override
    public void showLoseDialog() {
        System.out.print("\n\n" + ANSI_RED +  "DEFEAT" + ANSI_RESET + "\n\n" );
    }

    @Override
    public void showRanking(TreeMap<String, Double> rankingData) {
        for (Map.Entry<String, Double> entry: rankingData.entrySet()) {
            System.out.print(ANSI_PURPLE + entry.getKey());
            System.out.print(ANSI_RESET + " - ");
            System.out.print(ANSI_GREEN + entry.getValue() + ANSI_RESET + "\n");
        }
    }

    @Override
    public void showAbout() {
        System.out.println("This game was made by student of IT department - Evgeniy Ashmarin");
    }

    @Override
    public void startTimer() {
        timer.startTimer();
    }

    @Override
    public void stopTimer() {
        timer.stop();
    }
}
