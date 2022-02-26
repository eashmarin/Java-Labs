package lab1;

public class Main {

    public static void main(String[] args) {
        String fileIn, fileOut;
        if (args.length == 2) {
            fileIn = args[0];
            fileOut = args[1];
        }
        else {
            fileIn = "in.txt";
            fileOut = "out.csv";
        }

        Stat stat = new Stat(fileIn);
        CSVWriter writer = new CSVWriter(stat, fileOut);
        writer.write();
    }
}
