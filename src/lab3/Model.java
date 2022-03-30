package lab3;

import lab3.resources.ConfigParser;

public class Model {
    char[][] map;
    boolean[][] revealedMap;
    boolean[][] flagMap;
    boolean isGenerated;
    int height;
    int width;
    int minesNum;

    public Model() {
        width = Integer.parseInt(ConfigParser.getProperty("width"));
        height = Integer.parseInt(ConfigParser.getProperty("height"));
        minesNum = Integer.parseInt(ConfigParser.getProperty("mines_num"));
        isGenerated = false;
        map = new char[height][width];
        revealedMap = new boolean[height][width];
        flagMap = new boolean[height][width];

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                map[i][j] = '0';
                revealedMap[i][j] = false;
                flagMap[i][j] = false;
            }
    }

    public void generate(int centerX, int centerY) {
        int leftCornerX = Math.max(0, centerX - 2);
        int leftCornerY = Math.max(0, centerY - 2);
        int rightCornerX = Math.min(width - 1, centerX + 2);
        int rightCornerY = Math.min(height - 1, centerY + 2);


        for (int i = 0; i < minesNum; i++) {
            int x;
            int y;

            do {
                x = (int) (Math.random() * width);
                y = (int) (Math.random() * height);
            } while (isMine(x, y) || ((leftCornerX <= x && leftCornerY <= y) && (x <= rightCornerX && y <= rightCornerY)));

            placeMine(x, y);
        }

        isGenerated = true;

    }

    void placeMine(int x, int y) {
        int leftCornerX = Math.max(0, x - 1);
        int leftCornerY = Math.max(0, y - 1);
        int rightCornerX = Math.min(width - 1, x + 1);
        int rightCornerY = Math.min(height - 1, y + 1);

        for (int dy = leftCornerY; dy <= rightCornerY; dy++) {
            for (int dx = leftCornerX; dx <= rightCornerX; dx++) {
                if (dx == x && dy == y)
                    map[dy][dx] = 'B';
                else
                    if (map[dy][dx] != 'B')
                        map[dy][dx]++;
            }
        }
    }

    void reveal(int x, int y) {

        if (!revealedMap[y][x] && !isFlag(x, y)) {
            revealedMap[y][x] = true;
            if (map[y][x] == '0') {

                if (x > 0)
                    reveal(x - 1, y);
                if (y > 0)
                    reveal(x, y - 1);
                if (x + 1 < width)
                    reveal(x + 1, y);
                if (y + 1 < height)
                    reveal(x, y + 1);

                if (x > 0 && y > 0)
                    reveal(x - 1, y - 1);
                if (x > 0 && y + 1 < height)
                    reveal(x - 1, y + 1);
                if (x + 1 < width && y > 0)
                    reveal(x + 1, y - 1);
                if (x + 1 < width && y + 1 < height)
                    reveal(x + 1, y + 1);

            }
        }
    }

    void changeFlag(int x, int y) {
        flagMap[y][x] = !flagMap[y][x];
    }

    boolean isMine(int x, int y) {
        return map[y][x] == 'B';
    }

    void print() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
                System.out.print(map[i][j] + "  ");
            System.out.println();
        }
    }

    public boolean isMapGenerated() {
        return isGenerated;
    }

    public boolean isRevealed(int x, int y) {
        return revealedMap[y][x];
    }

    public boolean isFlag(int x, int y) {
        return flagMap[y][x];
    }

    public char at(int x, int y) {
        return map[y][x];
    }

    public void revealMines() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                if (isMine(j, i))
                    revealedMap[i][j] = true;
    }
}
