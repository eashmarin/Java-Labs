package lab2.commands;

import lab2.exceptions.StackException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Stack;
import java.util.TreeMap;

public class Print implements Command {
    @Override
    public void exec(InitialContext context) throws NamingException, StackException {
        TreeMap<String, Double> vars = (TreeMap<String, Double>) context.lookup("variables");
        Stack<String> stack = (Stack<String>) context.lookup("stack");

        if (stack.empty())
            throw new StackException("there is nothing to print, stack is empty");

        System.out.println(vars.get(stack.lastElement()));
    }
}
