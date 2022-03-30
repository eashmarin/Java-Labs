package lab2.commands;

import lab2.exceptions.StackException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Stack;
import java.util.TreeMap;

public class Pop implements Command {
    @Override
    public void exec(InitialContext context) throws NamingException, StackException {
        Stack<String> stack = (Stack<String>) context.lookup("stack");

        if (stack.isEmpty())
            throw new StackException("there is nothing to pop, stack is empty");

        stack.pop();
    }
}