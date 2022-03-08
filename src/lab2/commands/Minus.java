package lab2.commands;

import java.util.Stack;
import java.util.TreeMap;

public class Minus implements Command{
    @Override
    public void exec(TreeMap<String, Double> vars, Stack<String> stack, String arg) {
        if (stack.size() < 2)
            throw new IllegalArgumentException("size of stack < 2, operation " + this.getClass().getSimpleName() + " can't be executed");

        Double subtrahend = vars.get(stack.lastElement());

        stack.pop();

        Double minuend = vars.get(stack.lastElement());

        Double result = minuend - subtrahend;

        vars.put(stack.lastElement(), result);
    }
}
