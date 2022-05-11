package lab3;

public interface Factory {
    View createView(Model model);
    Controller createController(Model model, View view);
}
