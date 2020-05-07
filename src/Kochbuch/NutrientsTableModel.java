package Kochbuch;

import javax.swing.table.AbstractTableModel;
import java.util.Locale;
import java.util.ResourceBundle;

public class NutrientsTableModel extends AbstractTableModel {

    private String nutrientsPercentageOfRIFormatText;
    private String nutrientsValueFormatText;

    private String[][] data;

    public NutrientsTableModel(Locale localeForTextDisplay) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(KochbuchUI.I18N_BundleBaseName, localeForTextDisplay);
        data = new String[][]{
                {resourceBundle.getString("nutrientsCaloriesLabel"), ""},
                {resourceBundle.getString("nutrientsFatLabel"), ""},
                {resourceBundle.getString("nutrientsCarbsLabel"), ""},
                {resourceBundle.getString("nutrientsProteinLabel"), ""}
        };
        nutrientsPercentageOfRIFormatText = resourceBundle.getString("nutrientsPercentageOfRILabel");
        nutrientsValueFormatText = resourceBundle.getString("nutrientsValue");
    }

    public void updateData(NutritionalData nutritionalData) {
        this.data[0][1] = String.format(nutrientsPercentageOfRIFormatText, nutritionalData.getKcal(), nutritionalData.calculateKcalPercentageOfRefIntake());
        this.data[1][1] = String.format(nutrientsValueFormatText, nutritionalData.getFat());
        this.data[2][1] = String.format(nutrientsValueFormatText, nutritionalData.getCarbs());
        this.data[3][1] = String.format(nutrientsValueFormatText, nutritionalData.getProtein());
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return 4;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
}
