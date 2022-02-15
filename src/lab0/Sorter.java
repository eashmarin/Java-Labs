package lab0;

import java.util.Comparator;
import java.util.TreeMap;

public class Sorter {
    public static TreeMap<String, Integer> sortData(TreeMap<String, Integer> data) {
        Comparator<String> valueComparator = new Comparator<String>() {
            public int compare(String a, String b) {
                int comp = data.get(a).compareTo(data.get(b));
                if (comp == 0)
                    return a.compareTo(b);
                return -comp;
            }
        };

        TreeMap<String, Integer> sorted = new TreeMap<String, Integer>(valueComparator);

        sorted.putAll(data);

        return sorted;
    }
}
