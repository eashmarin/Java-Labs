package lab2.commands;

import lab2.NameGenerator;
import lab2.exceptions.ArgumentException;
import lab2.exceptions.CalculatorException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Stack;
import java.util.TreeMap;

public class Push implements Command {

    @Override
    public void exec(InitialContext context) throws CalculatorException {
        String arg = null;
        TreeMap<String, Double> vars = null;
        Stack<String> stack = null;
        try {
            arg = (String) context.lookup("argument");
            vars = (TreeMap<String, Double>) context.lookup("variables");
            stack = (Stack<String>) context.lookup("stack");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        String pushValue = arg.split("^a-zA-z0-9")[0];

        if (pushValue.length() == 0)
            throw new ArgumentException("operation \"PUSH\" must have an argument");

        if (!vars.containsKey(pushValue)) {     // if pushValue is a number
            try {
                Double value = Double.parseDouble(pushValue);
                pushValue = NameGenerator.generateName(vars);
                vars.put(pushValue, value);
            }
            catch (NumberFormatException e) {
                throw new ArgumentException("Definition of \"" + pushValue + "\" is missed");
            }
        }

        stack.push(pushValue);
    }
}
