package lab2.commands;

import lab2.exceptions.CalculatorException;
import lab2.exceptions.StackException;
import lab2.exceptions.NegativeRootException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Stack;
import java.util.TreeMap;

public class Sqrt implements Command {

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

        if (stack.isEmpty())
            throw new StackException("stack is empty, operation \"SQRT\" can't be executed");

        if (vars.get(stack.lastElement()) < 0)
            throw new NegativeRootException("root expression is below 0");

        Double result = Math.sqrt(vars.get(stack.lastElement()));

        vars.put(stack.lastElement(), result);
    }
}
