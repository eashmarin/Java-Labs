package lab2.resources;

import lab2.exceptions.NoPropertyException;

import java.io.*;
import java.util.Properties;

public class Config {
    public static String getProperty(String key) throws NoPropertyException {
        String value = null;
        try {
            Properties properties = new Properties();
            properties.load((Config.class.getResourceAsStream("config.properties")));

            if (!properties.containsKey(key))
                throw new NoPropertyException("property \"" + key + "\" wasn't found");

            value = properties.getProperty(key);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }
}
