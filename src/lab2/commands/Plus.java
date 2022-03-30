package lab2.commands;

import lab2.exceptions.StackException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Stack;
import java.util.TreeMap;

public class Plus implements Command{

    @Override
    public void exec(InitialContext context) throws NamingException, StackException {;
        TreeMap<String, Double> vars = (TreeMap<String, Double>) context.lookup("variables");
        Stack<String> stack = (Stack<String>) context.lookup("stack");

        if (stack.size() < 2)
            throw new StackException("size of stack < 2, operation \"+\" can't be executed");

        Double summand1 = vars.get(stack.lastElement());

        stack.pop();

        Double summand2 = vars.get(stack.lastElement());

        Double result = summand1 + summand2;

        vars.put(stack.lastElement(), result);
    }
}
