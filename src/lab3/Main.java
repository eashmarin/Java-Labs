package lab3;

import lab3.gui.GraphicalFactory;
import lab3.text.ConsoleFactory;

public class Main {
    public static void main(String[] args) {

        Factory factory;

        if (args[0].equals("Console"))
            factory = new ConsoleFactory();
        else
            factory = new GraphicalFactory();

        Minesweeper minesweeper = new Minesweeper(factory);
    }
}
