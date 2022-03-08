package lab2.commands;

import java.util.Stack;
import java.util.TreeMap;

public class Print implements Command {
    @Override
    public void exec(TreeMap<String, Double> vars, Stack<String> stack, String arg) {
        if (stack.empty())
            throw new IllegalArgumentException("there is nothing to print");

        System.out.println(vars.get(stack.lastElement()));
    }
}
