package lab3;

public class ConsoleFactory implements Factory{
    @Override
    public View createView(int height, int width) {
        return new ConsoleView(height, width);
    }

    @Override
    public Controller createController(Model model, View view) {
        return new ConsoleController(model, view);
    }
}
