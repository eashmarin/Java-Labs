package lab3;

public class Minesweeper {

    boolean gameOver = false;

    public Minesweeper(Factory factory) {
        Model model = new Model();
        View view = factory.createView(model.getHeight(), model.getWidth());
        Controller controller = factory.createController(model, view);
        controller.getInput();

        //while (!gameOver) {
            //controller.getInput();

            //view.update(model);

        //}
    }
}
