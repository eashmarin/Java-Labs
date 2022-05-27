package lab3.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

public class GuiRankingFrame extends JFrame {
    private final JTable table;

    GuiRankingFrame() {

        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setPreferredScrollableViewportSize(new Dimension(100, 34));
        table.setFillsViewportHeight(true);
        table.setRowHeight(20);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setViewportView(table);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        table.setDefaultRenderer(Object.class, renderer);
        table.setFont(new Font(table.getFont().getFontName(), Font.PLAIN, 16));

        setSize(new Dimension(200, 300));
        setLayout(new BorderLayout());

        add(table, BorderLayout.CENTER);
        add(new JScrollPane(table));
    }

    public void setData(TreeMap<String, Double> rankingData) {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Player", "Time"}, 0);
        for (Map.Entry<String, Double> entry: rankingData.entrySet()) {
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
        table.setModel(model);
    }
}
