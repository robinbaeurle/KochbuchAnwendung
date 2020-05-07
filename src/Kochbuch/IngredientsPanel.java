package Kochbuch;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

public class IngredientsPanel extends JPanel {

    private IngredientsTableModel ingredientsModel;
    private int portionCount;
    private double costPerPortion, costCount;
    private JButton btnMinusPortion, btnPlusPortion;
    private JLabel portionCountLabel, costCountLabel;

    public IngredientsPanel() {
        this.setLayout(new BorderLayout(0, 10));
        TitledBorder ingredientsBorder = BorderFactory.createTitledBorder(ResourceBundle.getBundle(KochbuchUI.I18N_BundleBaseName, getLocale()).getString("ingredientsBorder"));
        ingredientsBorder.setBorder(new EtchedBorder());
        this.setBorder(ingredientsBorder);
    }

    private void createAboveTablePanel() {
        JPanel aboveTablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel leftPanel = new JPanel(new FlowLayout());
        leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel portionSizePanel = new JPanel(new GridLayout(1, 3));
        btnPlusPortion = new JButton("+");
        portionCountLabel = new JLabel("1", SwingConstants.CENTER);
        btnMinusPortion = new JButton("−");
        portionSizePanel.add(btnPlusPortion, BorderLayout.WEST);
        portionSizePanel.add(portionCountLabel, BorderLayout.CENTER);
        portionSizePanel.add(btnMinusPortion, BorderLayout.EAST);
        leftPanel.add(new JLabel(
                ResourceBundle.getBundle(KochbuchUI.I18N_BundleBaseName, getLocale()).getString("portionsLabel") + ":"
        ));
        leftPanel.add(portionSizePanel);

        JPanel rightPanel = new JPanel(new FlowLayout());
        costCountLabel = new JLabel();
        rightPanel.add(new JLabel(
                ResourceBundle.getBundle(KochbuchUI.I18N_BundleBaseName, getLocale()).getString("costLabel") + ":"));
        rightPanel.add(costCountLabel);

        aboveTablePanel.add(leftPanel);
        aboveTablePanel.add(rightPanel);
        this.add(aboveTablePanel, BorderLayout.NORTH);
    }

    public IngredientsPanel createIngredientsTable(TableCellRenderer renderer) {
        createAboveTablePanel();
        ingredientsModel = new IngredientsTableModel(null);
        JTable ingredientsTable = new JTable(ingredientsModel);
        ingredientsTable.setTableHeader(null);
        ingredientsTable.setDefaultRenderer(String.class, renderer);
        ingredientsTable.setRowHeight(30);
        ingredientsTable.setShowGrid(false);
        ingredientsTable.setFillsViewportHeight(true);
        int[] columnsWidth = {50, 80};
        for (int i = 0; i < columnsWidth.length; i++) {
            TableColumn column = ingredientsTable.getColumnModel().getColumn(i);
            column.setMinWidth(columnsWidth[i]);
            column.setMaxWidth(columnsWidth[i]);
            column.setPreferredWidth(columnsWidth[i]);
        }
        JScrollPane ingredientsScroll = new JScrollPane(ingredientsTable);
        this.add(ingredientsScroll, BorderLayout.CENTER);
        return this;
    }

    public IngredientsPanel addButtonListeners(ActionListener listenerButtonPlus, ActionListener listenerButtonMinus) {
        btnPlusPortion.addActionListener(listenerButtonPlus);
        btnMinusPortion.addActionListener(listenerButtonMinus);
        return this;
    }

    public void updateData(int servings, double costEuro, List<Ingredient> ingredients) {
        this.portionCount = servings;
        this.costPerPortion = costEuro;
        this.costCount = costEuro;
        updatePortionAndCostLabels();
        ingredientsModel.updateData(ingredients);
    }

    public int getPortionWeightGrams() {
        return ingredientsModel.getPortionWeightGrams();
    }

    public void incrementPortion() {
        // restrict max portions to 20
        if (portionCount < 20) {
            portionCount++;
            costCount += costPerPortion;
            ingredientsModel.incrementPortion();
            updatePortionAndCostLabels();
        }
    }

    public void decrementPortion() {
        // restrict min portions to 1
        if (portionCount > 1) {
            portionCount--;
            costCount -= costPerPortion;
            ingredientsModel.decrementPortion();
            updatePortionAndCostLabels();
        }
    }

    private void updatePortionAndCostLabels() {
        portionCountLabel.setText(String.valueOf(portionCount));
        costCountLabel.setText(String.format("%.2f €", costCount));
    }


}
