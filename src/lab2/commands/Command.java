package lab2.commands;

import lab2.resources.Config;
import java.util.Stack;
import java.util.TreeMap;

public interface Command{

    void  exec(TreeMap<String, Double> vars, Stack<String> stack, String arg);

    static Command of(String commandName) throws Exception {
        Class cmdType = null;
        Command cmd = null;
        try {
            cmdType = Class.forName("lab2.commands." + Config.getProperty(commandName));
            cmd = (Command) cmdType.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw e;
        }
        return cmd;
    }
}
