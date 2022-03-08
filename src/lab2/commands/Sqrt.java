package lab2.commands;

import java.util.Stack;
import java.util.TreeMap;

public class Sqrt implements Command {

    @Override
    public void exec(TreeMap<String, Double> vars, Stack<String> stack, String arg) {
        if (stack.isEmpty())
            throw new IllegalArgumentException("stack is empty, operation \"SQRT\" can't be executed");

        Double result = Math.sqrt(vars.get(stack.lastElement()));

        vars.put(stack.lastElement(), result);
    }
}
