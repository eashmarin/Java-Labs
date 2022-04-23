package lab3;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class RankingData {

    TreeMap<String, Integer> data;

    public RankingData() {
        data = new TreeMap<>();
    }

    public RankingData(String fileName) {
        loadFromFile(fileName);
    }

    public TreeMap<String, Integer> loadFromFile(String fileName) {
        data = Parser.parse(Reader.read(fileName));
        //Sorter.sortData(data);
        return data;
    }

}
