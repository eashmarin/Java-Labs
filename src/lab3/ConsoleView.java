package lab3;

public class ConsoleView implements View{

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";

    @Override
    public void update(Model model) {
        System.out.print("\n\n");

        for (int i = 0; i < model.width; i++)
            System.out.print(ANSI_GREEN + i + "  " + ANSI_RESET);
        System.out.println();

        char symbol;
        for (int i = 0; i < model.height; i++) {
            for (int j = 0; j < model.width; j++) {
                if (model.isRevealed(j, i)) {
                    if (model.isMine(j, i))
                        System.out.print(ANSI_RED);
                    symbol = model.at(j, i);
                }
                else {
                    if (model.isFlag(j, i))
                        symbol = '*';
                    else
                        symbol = '^';
                }
                System.out.print(symbol + "  " + ANSI_RESET);
            }
            System.out.print(ANSI_BLUE + i + ANSI_RESET);
            System.out.println();
        }
        System.out.print("\n\n");
    }
}
