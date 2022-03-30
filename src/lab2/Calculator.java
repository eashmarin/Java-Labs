package lab2;

import lab2.commands.Command;
import lab2.commands.Print;
import lab2.exceptions.*;
import org.apache.log4j.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import java.util.*;

public class Calculator {
    LinkedHashMap<Command, String> cmds;
    TreeMap<String, Double> variables;
    Stack<String> stack;
    double dataToPrint;
    InitialContext context;
    private final Logger logger = Logger.getLogger(Calculator.class.getSimpleName());
    public Calculator(ArrayList<String> inputData) throws Exception {
        logger.info("========Loading Сalculator========");
        try {
            Parser parser = new Parser();
            stack = new Stack<>();
            variables = new TreeMap<>();

            parser.Parse(inputData);
            cmds = parser.getCommands();

            context = new InitialContext() {

                private Map<String, Object> table = new HashMap<>();

                public void bind(String key, Object value) {
                    table.put(key, value);
                }

                public Object lookup(String key) throws NamingException {
                    return table.get(key);
                }
            };
            context.bind("variables", variables);
            context.bind("stack", stack);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public double getDataToPrint() {
        return dataToPrint;
    }

    public void calculate() throws NamingException, NegativeRootException, DivByZeroException, StackException, ArgumentException {

        logger.info("Calculating: ");
        for (Map.Entry<Command, String> currCmd: cmds.entrySet()) {
            try {
                logger.info(currCmd.getKey().getClass().getSimpleName() + " " + currCmd.getValue());

                context.bind("argument", currCmd.getValue());

                currCmd.getKey().exec(context);
                if (currCmd.getKey() instanceof Print) {
                    dataToPrint = variables.get(stack.lastElement());
                }
            }
            catch (CalculatorException e) {
                logger.error(e.getMessage());
                throw e;
            }

        }
    }
}
