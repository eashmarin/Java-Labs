package lab2.commands;

import java.util.Stack;
import java.util.TreeMap;

public class Pop implements Command {
    @Override
    public void exec(TreeMap<String, Double> vars, Stack<String> stack, String arg) {
        if (stack.isEmpty())
            throw new IllegalArgumentException("there is nothing to pop");
        stack.pop();
    }
}