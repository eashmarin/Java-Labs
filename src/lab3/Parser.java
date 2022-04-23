package lab3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static TreeMap<String, Integer> parse(ArrayList<String> lines) {
        TreeMap<String, Integer> result = new TreeMap<>();
        for (String line: lines) {
            String[] s = line.split("[,]");
            String name = s[0];
            Integer record = Integer.parseInt(s[1]);
            result.put(name, record);
        }
        return result;
    }

}
