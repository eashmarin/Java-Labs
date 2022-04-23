package lab3.gui;

import lab3.Controller;
import lab3.Factory;
import lab3.Model;
import lab3.View;

public class GraphicalFactory implements Factory {
    @Override
    public View createView(int height, int width) {
        return new GraphicalView(height, width);
    }

    @Override
    public Controller createController(Model model, View view) {
        return new GraphicalController(model, (GraphicalView) view);
    }
}
