package lab2.commands;

import java.util.Stack;
import java.util.TreeMap;

public class Plus implements Command{

    @Override
    public void exec(TreeMap<String, Double> vars, Stack<String> stack, String arg) {
        if (stack.size() < 2)
            throw new IllegalArgumentException("size of stack < 2, operation \"+\" can't be executed");

        Double summand1 = vars.get(stack.lastElement());

        stack.pop();

        Double summand2 = vars.get(stack.lastElement());

        Double result = summand1 + summand2;

        vars.put(stack.lastElement(), result);
    }
}
