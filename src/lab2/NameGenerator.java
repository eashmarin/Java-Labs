package lab2;

import java.util.TreeMap;

public class NameGenerator {
    public static String generateName(TreeMap<String, Double> vars) {
        int length = 7;
        String name;
        do {
            name = "";
            length++;
            for (int i = 0; i < length; i++) {
                char symbol;

                int typeOfSymbol = (int) (Math.random() * 3);
                if (typeOfSymbol == 0)                              // [0-9]
                    symbol = (char) (48 + (int) (Math.random() * 10));
                else {
                    if (typeOfSymbol == 1)                              // [A-Z]
                        symbol = (char) (65 + (int) (Math.random() * 27));
                    else                                                // [a-z]
                        symbol = (char) (97 + (int) (Math.random() * 27));
                }

                name += symbol;
            }
        } while (vars.containsKey(name));

        return name;
    }
}
