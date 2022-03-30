package lab3;

public class Main {
    public static void main(String[] args) {

        Factory factory;

        System.out.println(args[0]);

        if (args[0].equals("Console"))
            factory = new ConsoleFactory();
        else
            factory = new GraphicalFactory();

        Minesweeper minesweeper = new Minesweeper(factory);
    }
}
