package lab3.gui;

import lab3.RankingData;
import lab3.Sorter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class GuiRankingFrame extends JFrame {
    JTable table;
    TreeMap<String, Integer> data;
    RankingData tableData;
    DefaultTableCellRenderer renderer;

    GuiRankingFrame() {

        tableData = new RankingData();

        data = tableData.loadFromFile(getClass().getResource("/lab3/resources/ranking.csv").getFile());

        data = Sorter.sortData(data);

        DefaultTableModel model = new DefaultTableModel(new Object[] {"Key", "Value"}, 0);
        for (Map.Entry<String, Integer> entry: data.entrySet())
            model.addRow(new Object[] {entry.getKey(), entry.getValue()});

        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        table.setDefaultRenderer(Object.class, renderer);
        table.setFont(new Font(table.getFont().getFontName(), Font.BOLD, 16));

        //table.setTableHeader();
        //table.setBackground(Color.DARK_GRAY);

        setTitle("Rankings");
        setSize(new Dimension(75, 150));
        setLayout(new BorderLayout());



        add(table, BorderLayout.CENTER);
        //setVisible(true);
    }
}
