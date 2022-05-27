package lab3;

import java.io.*;

public class RankingWriter {
    public static void write(String playerName, Double time, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write("\n" + playerName + "," + time);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
