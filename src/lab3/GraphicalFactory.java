package lab3;

public class GraphicalFactory implements Factory{
    @Override
    public View createView() {
        return new GraphicalView();
    }

    @Override
    public Controller createController(Model model, View view) {
        return new GraphicalController();
    }
}
