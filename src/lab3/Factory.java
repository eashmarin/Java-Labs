package lab3;

public interface Factory {
    View createView(int height, int width);
    Controller createController(Model model, View view);
}
