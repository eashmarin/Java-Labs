package lab2.commands;

import lab2.exceptions.ArgumentException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Stack;
import java.util.TreeMap;

public class Push implements Command {

    public String generateName(TreeMap<String, Double> vars) {
        int length = 8;
        String name;
        do {
            System.out.println((char)(57));
            name = "";
            length++;
            for (int i = 0; i < length; i++) {
                char symbol;

                int typeOfSymbol = (int) (Math.random() * 3);
                if (typeOfSymbol == 0)                              // [0-9]
                    symbol = (char) (48 + (int) (Math.random() * 10));
                else {
                    if (typeOfSymbol == 1)                              // [A-Z]
                        symbol = (char) (65 + (int) (Math.random() * 27));
                    else                                                // [a-z]
                        symbol = (char) (97 + (int) (Math.random() * 27));
                }

                name += symbol;
            }
        } while (vars.containsKey(name));

        return  name;
    }

    @Override
    public void exec(InitialContext context) throws NamingException, ArgumentException {
        String arg = (String) context.lookup("argument");
        TreeMap<String, Double> vars = (TreeMap<String, Double>) context.lookup("variables");
        Stack<String> stack = (Stack<String>) context.lookup("stack");

        String variableName = arg.split("^a-zA-z0-9")[0];

        if (variableName.length() == 0)
            throw new ArgumentException("operation \"PUSH\" must have an argument");

        if (!vars.containsKey(variableName)) {
            try {
                Double value = Double.parseDouble(variableName);
                variableName = generateName(vars);
                vars.put(variableName, value);
            }
            catch (NumberFormatException e) {
                throw new ArgumentException("Definition of \"" + variableName + "\" is missed");
            }
        }

        stack.push(variableName);
    }
}
