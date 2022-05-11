package lab3.gui;

import lab3.Controller;
import lab3.Factory;
import lab3.Model;
import lab3.View;

public class GraphicalFactory implements Factory {
    @Override
    public View createView(Model model) {
        return new GraphicalView(model);
    }

    @Override
    public Controller createController(Model model, View view) {
        return new GraphicalController(model, (GraphicalView) view);
    }
}
