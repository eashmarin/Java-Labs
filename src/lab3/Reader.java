package lab3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
    public static ArrayList<String> read(String fileName) {
        ArrayList<String> lines = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String currLine;
            while ((currLine = reader.readLine()) != null)
                lines.add(currLine);
        }
        catch(IOException e) {
            System.out.println("Error while reading file " + e.getLocalizedMessage());
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
        return lines;
    }
}
