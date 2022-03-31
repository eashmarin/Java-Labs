package lab2.commands;

import lab2.exceptions.CalculatorException;
import lab2.exceptions.StackException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Stack;
import java.util.TreeMap;

public class Print implements Command {
    @Override
    public void exec(InitialContext context) throws CalculatorException {
        TreeMap<String, Double> vars = null;
        Stack<String> stack = null;
        try {
            vars = (TreeMap<String, Double>) context.lookup("variables");
            stack = (Stack<String>) context.lookup("stack");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        if (stack.empty())
            throw new StackException("there is nothing to print, stack is empty");

        System.out.println(vars.get(stack.lastElement()));
    }
}
