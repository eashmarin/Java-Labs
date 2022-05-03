package lab3;

import java.util.TreeMap;

public interface View {
    void update(Model model);

    String getInputName();

    void setTime(int time);

    void showWinDialog();

    void showLoseDialog();

    void showRanking(TreeMap<String, Double> rankingData);

    void showAbout();
}
