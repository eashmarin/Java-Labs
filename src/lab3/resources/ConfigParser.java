package lab3.resources;

import lab2.resources.Config;

import java.io.IOException;
import java.util.Properties;

public class ConfigParser {
    public static String getProperty(String key) {
        String value = null;
        try {
            Properties properties = new Properties();
            properties.load((ConfigParser.class.getResourceAsStream("config.properties")));
            if (!properties.containsKey(key))
                throw new IllegalArgumentException("invalid command - " + key);
            value = properties.getProperty(key);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }
}
