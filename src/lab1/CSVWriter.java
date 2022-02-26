package lab1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.TreeMap;

public class CSVWriter {
    private TreeMap<String, Integer> data;
    private int wordsAmount;
    private String fileName;

    public CSVWriter(Stat stat, String fileName) {
        this.data = stat.getData();
        this.wordsAmount = stat.getWordsAmount();
        this.fileName = fileName;
    }

    public void write() {
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(fileName));

            for (Map.Entry<String, Integer> entry: data.entrySet()) {
                String line = entry.getKey() + "," + entry.getValue() + "," + String.format("%.1f", (float)entry.getValue() / wordsAmount * 100) + "%" + "\n";
                writer.write(line);
            }

        }
        catch (IOException e) {
            System.err.println("Error while writing file: " + e.getLocalizedMessage());
        }
        finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
}
