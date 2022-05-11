package lab3;

public class Minesweeper {

    boolean gameOver = false;

    public Minesweeper(Factory factory) {
        Model model = new Model();
        View view = factory.createView(model);
        Controller controller = factory.createController(model, view);
    }
}
