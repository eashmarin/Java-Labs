package lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Reader {
    //private static final Logger logger = Logger.getLogger(Reader.class.getSimpleName());

    public static ArrayList<String> read(String fileName) {
        //Logger.getLogger("main").warning("Reading file");
        ArrayList<String> lines = new ArrayList<String>();

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
