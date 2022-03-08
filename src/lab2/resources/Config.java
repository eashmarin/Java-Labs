package lab2.resources;

import java.io.*;
import java.util.Properties;

public class Config {
    public static String getProperty(String key) {
        String value = null;
        try {
            Properties properties = new Properties();
            properties.load((Config.class.getResourceAsStream("config.properties")));
            if (!properties.containsKey(key))
                throw new IllegalArgumentException("invalid command - " + key);
            value = properties.getProperty(key);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }
}
