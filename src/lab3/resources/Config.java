package lab3.resources;

import java.io.FileOutputStream;
import java.io.IOException;
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
    public static void edit(int width, int height, int minesNum) {
        try {
            Properties properties = new Properties();
            properties.load(Config.class.getResourceAsStream("config.properties"));
            properties.setProperty("width", String.valueOf(width));
            properties.setProperty("height", String.valueOf(height));
            properties.setProperty("mines_num", String.valueOf(minesNum));

            properties.store(new FileOutputStream(Config.class.getResource("config.properties").getFile()), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
