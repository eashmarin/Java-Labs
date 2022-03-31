package lab2.commands;

import lab2.exceptions.*;
import lab2.resources.Config;

import javax.naming.InitialContext;
import java.lang.reflect.InvocationTargetException;

public interface Command{

    void exec(InitialContext context) throws CalculatorException;//TreeMap<String, Double> vars, Stack<String> stack, String arg);

    static Command of(String commandName) throws NoPropertyException {
        Class cmdType = null;
        Command cmd = null;

        try {
            cmdType = Class.forName("lab2.commands." + Config.getProperty(commandName));
            cmd = (Command) cmdType.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return cmd;
    }
}
