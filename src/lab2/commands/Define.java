package lab2.commands;

import java.util.Stack;
import java.util.TreeMap;

public class Define implements Command {
    @Override
    public void exec(TreeMap<String, Double> vars, Stack<String> stack, String arg) {
        String[] args = arg.split("[^a-zA-z0-9[.]]");
        if (args.length < 2)
            throw new IllegalArgumentException("operation \"DEFINE\" must have 2 arguments" );
        try {
            Double.parseDouble(args[1]);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("variable in operation \"DEFINE\" must be a number");
        }
        vars.put(args[0], Double.parseDouble(args[1]));
    }
}
