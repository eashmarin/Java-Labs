package lab2;

import lab2.commands.Command;
import lab2.commands.Print;
import lab2.exceptions.*;
import org.apache.log4j.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.*;

public class Calculator {
    LinkedHashMap<Command, String> cmds;
    TreeMap<String, Double> variables;
    Stack<String> stack;
    double dataToPrint;
    InitialContext context;
    private final Logger logger = Logger.getLogger(Calculator.class.getSimpleName());

    public Calculator(ArrayList<String> inputData) throws NoPropertyException {
        logger.info("========Loading Calculator========");
        try {
            Parser parser = new Parser();
            stack = new Stack<>();
            variables = new TreeMap<>();

            parser.Parse(inputData);
            cmds = parser.getCommands();

            try {
                context = new InitialContext() {

                    private final Map<String, Object> table = new HashMap<>();

                    public void bind(String key, Object value) {
                        table.put(key, value);
                    }

                    public Object lookup(String key) {
                        return table.get(key);
                    }
                };
                context.bind("variables", variables);
                context.bind("stack", stack);
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        catch (NoPropertyException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public double getDataToPrint() {
        return dataToPrint;
    }

    public void calculate() throws CalculatorException {
        logger.info("Calculating: ");
        for (Map.Entry<Command, String> currCmd: cmds.entrySet()) {
            try {
                logger.info(currCmd.getKey().getClass().getSimpleName() + " " + currCmd.getValue());

                try {
                    context.bind("argument", currCmd.getValue());
                } catch (NamingException e) {
                    e.printStackTrace();
                }

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
