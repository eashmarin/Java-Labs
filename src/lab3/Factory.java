package lab3;

public interface Factory {
    View createView();
    Controller createController(Model model, View view);
}
