package lab2.commands;

import java.util.Stack;
import java.util.TreeMap;

public class Divide implements Command{

    @Override
    public void exec(TreeMap<String, Double> vars, Stack<String> stack, String arg) {
        if (stack.size() < 2)
            throw new IllegalArgumentException("size of stack < 2, operation " + this.getClass().getSimpleName() + " can't be executed");

        Double divider = vars.get(stack.lastElement());

        if (divider == 0)
            throw new ArithmeticException("division by zero");

        stack.pop();

        Double divisible = vars.get(stack.lastElement());

        Double result = divisible / divider;

        vars.put(stack.lastElement(), result);
    }
}
