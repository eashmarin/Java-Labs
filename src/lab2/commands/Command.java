package lab2.commands;

import lab2.exceptions.ArgumentException;
import lab2.exceptions.DivByZeroException;
import lab2.exceptions.NegativeRootException;
import lab2.exceptions.StackException;
import lab2.resources.Config;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Stack;
import java.util.TreeMap;

public interface Command{

    void exec(InitialContext context) throws NamingException, ArgumentException, StackException, DivByZeroException, NegativeRootException;//TreeMap<String, Double> vars, Stack<String> stack, String arg);

    static Command of(String commandName) throws Exception {
        Class cmdType = null;
        Command cmd = null;

        cmdType = Class.forName("lab2.commands." + Config.getProperty(commandName));
        cmd = (Command) cmdType.getDeclaredConstructor().newInstance();

        return cmd;
    }
}
