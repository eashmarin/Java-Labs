package lab3;

import java.util.ArrayList;
import java.util.TreeMap;

public class RankingParser {
    public static TreeMap<String, Double> parse(ArrayList<String> lines) {
        TreeMap<String, Double> result = new TreeMap<>();
        for (String line: lines) {
            String[] s = line.split("[,]");
            String name = s[0];
            Double record = Double.parseDouble(s[1]);
            result.put(name, record);
        }
        return result;
    }

}
