package lab2.commands;

import lab2.exceptions.CalculatorException;
import lab2.exceptions.StackException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Stack;

public class Pop implements Command {
    @Override
    public void exec(InitialContext context) throws CalculatorException {
        Stack<String> stack = null;
        try {
            stack = (Stack<String>) context.lookup("stack");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        if (stack.isEmpty())
            throw new StackException("there is nothing to pop, stack is empty");

        stack.pop();
    }
}