package lab2.commands;

import java.util.Stack;
import java.util.TreeMap;

public class Push implements Command {

    @Override
    public void exec(TreeMap<String, Double> vars, Stack<String> stack, String arg) {
        String variable = arg.split("^a-zA-z0-9")[0];

        if (variable.length() == 0)
            throw new IllegalArgumentException("operation \"PUSH\" must have an argument");

        if (!vars.containsKey(variable))
            throw new IllegalArgumentException("Definition of \"" + variable + "\" is missed");

        stack.push(variable);
    }

}
