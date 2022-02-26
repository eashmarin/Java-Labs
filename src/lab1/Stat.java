package lab1;

import java.io.*;
import java.util.*;

public class Stat {
    private String fileName;
    private BufferedReader reader;
    private Vector<String> buffer;
    private TreeMap<String, Integer> data;
    private int wordsAmount;

    public Stat(String fileName) {
        this.fileName =  fileName;

        buffer = new Vector<>();
        data = new TreeMap<>();

        read();

        buffer = Parser.parse(buffer);
        wordsAmount = buffer.size();

        transferToMap();
        sort();
    }

    private void read() {
        reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));

            String currLine;

            while ((currLine = reader.readLine()) != null)
                buffer.add(currLine);

        }
        catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    private void transferToMap() {
        for (String key: buffer) {

            if (data.containsKey(key))
                data.put(key, data.get(key) + 1);
            else
                data.put(key, 1);
        }
    }

    private void sort() {
        data = Sorter.sortData(data);
    }

    public TreeMap<String, Integer> getData() { return this.data; }

    public int getWordsAmount() { return wordsAmount; }
}
