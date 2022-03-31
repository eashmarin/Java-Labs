package lab3;

public class GraphicalFactory implements Factory{
    @Override
    public View createView(int height, int width) {
        return new GraphicalView(height, width);
    }

    @Override
    public Controller createController(Model model, View view) {
        return new GraphicalController(model, (GraphicalView) view);
    }
}
