package lab3;

import lab3.resources.Config;

import java.util.TreeMap;

public class Model {
    private char[][] map;
    private boolean[][] revealedMap;
    private boolean[][] flagMap;
    private boolean isGenerated;
    private boolean gameOver;
    private boolean isWin;
    private String playerName;
    private final TreeMap<String, Double> rankingData;
    private int revealedCount;
    private double time;
    private long startTime;
    private int height;
    private int width;
    private int minesNum;

    public Model() {
        String pathToRanking = getClass().getResource("/lab3/resources/ranking.csv").getFile();

        rankingData = RankingParser.parse(Reader.read(pathToRanking));

        setDefaultModel();
    }

    public void setDefaultModel() {

        width = Integer.parseInt(Config.getProperty("width"));
        height = Integer.parseInt(Config.getProperty("height"));
        minesNum = Integer.parseInt(Config.getProperty("mines_num"));

        map = new char[height][width];
        revealedMap = new boolean[height][width];
        flagMap = new boolean[height][width];

        isGenerated = false;
        gameOver = false;
        isWin = false;
        revealedCount = 0;
        time = 0;

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                map[i][j] = '0';
                revealedMap[i][j] = false;
                flagMap[i][j] = false;
            }
    }

    public void generate(int centerX, int centerY) {

        startTime = System.nanoTime();

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

    private void placeMine(int x, int y) {
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

    public void reveal(int x, int y) {

        if (isMine(x, y)) {
            gameOver = true;
            revealMines();
        }

        if (!revealedMap[y][x] && !isFlag(x, y)) {

            if (!isGenerated) {
                generate(x, y);
            }

            revealedMap[y][x] = true;
            revealedCount++;

            if (revealedCount == width * height) {
                gameOver = true;
                isWin = true;
                fixRecord();
            }
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

    void fixRecord() {
        isWin = true;

        long estimatedTime = System.nanoTime() - startTime;
        estimatedTime *= 1e-9;

        if (!rankingData.containsKey(playerName)) {
            rankingData.put(playerName, (double) estimatedTime);
            RankingWriter.write(playerName, (double) estimatedTime, getClass().getResource("/lab3/resources/ranking.csv").getFile());
        }
        else {
            double oldTime = rankingData.get(playerName);
            if (time < oldTime) {
                rankingData.put(playerName, (double) estimatedTime);
                RankingWriter.write(playerName, (double) estimatedTime, getClass().getResource("/lab3/resources/ranking.csv").getFile());
            }
        }
    }

    public void setFlag(int x, int y) {
        flagMap[y][x] = !flagMap[y][x];

        if (!isMine(x, y) && !isFlag(x, y))
            revealedCount++;

        if (isMine(x, y) && isFlag(x, y))
            revealedCount++;

        if (!isMine(x, y)  && isFlag(x, y) || isMine(x, y) && !isFlag(x, y))
            revealedCount--;

        gameOver = (revealedCount == width * height);
        if (gameOver)
            fixRecord();
    }

    public void setPlayerName(String name) {
        playerName = name;
    }

    public boolean isMine(int x, int y) {
        return map[y][x] == 'B';
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void print() {
        System.out.print("\n\n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
                System.out.print(map[i][j] + "  ");
            System.out.println();
        }
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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void increaseTimeBySec() {
        time++;
    }

    public double getTime() {
        return time;
    }

    public TreeMap<String, Double> getRankingData() {
        return Sorter.sortData(rankingData);
    }

    public boolean isWin() {
        return isWin;
    }
}
