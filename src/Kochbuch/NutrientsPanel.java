package Kochbuch;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ResourceBundle;

public class NutrientsPanel extends JPanel {

    private JLabel nutrientsPortionsizeLabel;
    private NutrientsTableModel nutrientsModel;

    public NutrientsPanel() {
        this.setLayout(new BorderLayout(0, 10));
        TitledBorder nutrientsBorder = BorderFactory.createTitledBorder(ResourceBundle.getBundle(KochbuchUI.I18N_BundleBaseName, getLocale()).getString("nutrientsBorder"));
        nutrientsBorder.setBorder(new EtchedBorder());
        this.setBorder(nutrientsBorder);
    }

    public NutrientsPanel createNutrientsTable(TableCellRenderer renderer, int width) {
        nutrientsModel = new NutrientsTableModel(getLocale());
        JTable nutrientsTable = new JTable(nutrientsModel);
        nutrientsTable.setTableHeader(null);
        nutrientsTable.setDefaultRenderer(String.class, renderer);
        nutrientsTable.setRowHeight(30);
        nutrientsTable.setShowGrid(false);
        if (width != 0) {
            nutrientsTable.setPreferredSize(new Dimension(width, nutrientsTable.getRowHeight() * 4));
        }
        nutrientsPortionsizeLabel = new JLabel();
        JLabel nutrientsRefIntakeInfoLabel = new JLabel(ResourceBundle.getBundle(KochbuchUI.I18N_BundleBaseName, getLocale()).getString("refIntakeInfoLabel"));
        this.add(nutrientsPortionsizeLabel, BorderLayout.NORTH);
        this.add(nutrientsTable, BorderLayout.CENTER);
        this.add(nutrientsRefIntakeInfoLabel, BorderLayout.SOUTH);
        return this;
    }

    public void updateData(int portionWeightGrams, NutritionalData nutritionalData) {
        nutrientsPortionsizeLabel.setText(String.format(ResourceBundle.getBundle(KochbuchUI.I18N_BundleBaseName, getLocale()).getString("perPortionLabel") + ":", portionWeightGrams));
        nutrientsModel.updateData(nutritionalData);
    }

}
