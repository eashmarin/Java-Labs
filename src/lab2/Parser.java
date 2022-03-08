package lab2;


import lab2.commands.Command;
import lab2.commands.Define;
import lab2.commands.Push;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private LinkedHashMap<Command, String> cmds;
    final Logger logger = Logger.getLogger(Parser.class.getSimpleName());
    //private TreeMap<String, Float> vars;

    Parser() {
        cmds = new LinkedHashMap<>();
        //vars = new TreeMap<String, Float>();
    }

    public void Parse(ArrayList<String> lines) throws Exception {
        logger.info("Parsing input data");
        if (lines == null || lines.isEmpty())
            throw new IllegalArgumentException("input is empty");

        Pattern pattern = Pattern.compile("[a-zA-Z0-9+*/-[.]]+");

        for (String line: lines) {
            if (line.length() == 0 || line.charAt(0) == '#')
                continue;

            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {
                String cmdName = line.substring(matcher.start(), matcher.end());
                Command cmd = Command.of(cmdName);
                String args = new String();
                while (matcher.find()) {
                    args += line.substring(matcher.start(), matcher.end()) + " ";
                }

                if (args.length() != 0)
                    args = args.substring(0, args.length() - 1);

                cmds.put(cmd, args);
            }
        }
    }

    public LinkedHashMap<Command, String> getCommands() {
        return cmds;
    }
}
