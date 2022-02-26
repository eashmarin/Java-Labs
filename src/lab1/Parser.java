package lab1;

import java.util.Vector;
import java.util.regex.*;


public class Parser {
    public static Vector<String> parse(Vector<String> lines) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
        Vector<String> words = new Vector<String>();
        for (String line: lines) {
            Matcher matcher = pattern.matcher(line);
            while(matcher.find())
                words.add(line.substring(matcher.start(), matcher.end()));
        }
        return words;
    }
}
