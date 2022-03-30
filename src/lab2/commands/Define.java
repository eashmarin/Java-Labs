package lab2.commands;

import lab2.exceptions.ArgumentException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Stack;
import java.util.TreeMap;

public class Define implements Command {
    @Override
    public void exec(InitialContext context) throws NamingException, ArgumentException {
        String arg = (String) context.lookup("argument");
        TreeMap<String, Double> vars = (TreeMap<String, Double>) context.lookup("variables");

        String[] args = arg.split("[^a-zA-z0-9[.]]");
        if (args.length < 2)
            throw new ArgumentException("operation \"DEFINE\" must have 2 arguments");

        try {
            Double.parseDouble(args[1]);
        }
        catch (NumberFormatException e) {
            throw new ArgumentException("variable in operation \"DEFINE\" must be a number");
        }

        vars.put(args[0], Double.parseDouble(args[1]));
    }
}
