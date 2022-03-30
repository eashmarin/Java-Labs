package lab3;

public class ConsoleFactory implements Factory{
    @Override
    public View createView() {
        return new ConsoleView();
    }

    @Override
    public Controller createController(Model model, View view) {
        return new ConsoleController(model, view);
    }
}
