package lab3.text;

import lab3.Controller;
import lab3.Factory;
import lab3.Model;
import lab3.View;

public class ConsoleFactory implements Factory {
    @Override
    public View createView(Model model) {
        return new ConsoleView(model);
    }

    @Override
    public Controller createController(Model model, View view) {
        return new ConsoleController(model, view);
    }
}
