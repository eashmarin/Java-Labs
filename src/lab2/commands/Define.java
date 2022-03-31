package lab2.commands;

import lab2.exceptions.ArgumentException;
import lab2.exceptions.CalculatorException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.TreeMap;

public class Define implements Command {
    @Override
    public void exec(InitialContext context) throws CalculatorException {
        String arg = null;
        TreeMap<String, Double> vars = null;
        try {
            arg = (String) context.lookup("argument");
            vars = (TreeMap<String, Double>) context.lookup("variables");
        } catch (NamingException e) {
            e.printStackTrace();
        }

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
