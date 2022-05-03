package lab3;

import java.util.Comparator;
import java.util.TreeMap;

public class Sorter {
    public static TreeMap<String, Double> sortData(TreeMap<String, Double> data) {
        Comparator<String> valueComparator = new Comparator<String>() {
            public int compare(String a, String b) {
                int comp = data.get(a).compareTo(data.get(b));
                if (comp == 0)
                    return a.compareTo(b);
                return comp;
            }
        };

        TreeMap<String, Double> sorted = new TreeMap<>(valueComparator);

        sorted.putAll(data);

        return sorted;
    }
}