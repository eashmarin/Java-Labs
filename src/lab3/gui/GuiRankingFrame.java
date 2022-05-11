package lab3.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

public class GuiRankingFrame extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableCellRenderer renderer;
    private DefaultTableModel model;

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
        scrollPane = new JScrollPane(table);
        scrollPane.setViewportView(table);

        renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        table.setDefaultRenderer(Object.class, renderer);
        table.setFont(new Font(table.getFont().getFontName(), Font.PLAIN, 16));

        setSize(new Dimension(200, 300));
        setLayout(new BorderLayout());

        add(table, BorderLayout.CENTER);
        add(new JScrollPane(table));
    }

    public void setData(TreeMap<String, Double> rankingData) {
        model = new DefaultTableModel(new Object[] {"Player", "Time"}, 0);
        for (Map.Entry<String, Double> entry: rankingData.entrySet()) {
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
        table.setModel(model);
    }
}
