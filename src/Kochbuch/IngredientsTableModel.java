package Kochbuch;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class IngredientsTableModel extends AbstractTableModel {

    private List<Ingredient> ingredients;
    private List<Double> ingredientBaseValuesTEMP;

    public IngredientsTableModel(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void updateData(List<Ingredient> ingredients) {
        if (ingredientBaseValuesTEMP != null && this.ingredients != null) {
            // reset "value" of each ingredient because of call by reference in respect to the containing Recipe
            for (int i = 0; i < this.ingredients.size(); i++) {
                this.ingredients.get(i).setQuantity(ingredientBaseValuesTEMP.get(i));
            }
        }
        this.ingredients = ingredients;
        ingredientBaseValuesTEMP = new ArrayList<>();
        for (Ingredient i : ingredients) {
            ingredientBaseValuesTEMP.add(i.getQuantity());
        }
        fireTableDataChanged();
    }

    public void incrementPortion() {
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            ingredient.setQuantity(ingredient.getQuantity() + ingredientBaseValuesTEMP.get(i));
            fireTableCellUpdated(i, 0);
        }
    }

    public void decrementPortion() {
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            ingredient.setQuantity(ingredient.getQuantity() - ingredientBaseValuesTEMP.get(i));
            fireTableCellUpdated(i, 0);
        }
    }

    public int getPortionWeightGrams() {
        int grams = 0;
        if (ingredients != null) {
            for (Ingredient i : ingredients) {
                if (i.getUnit().equals("g") || i.getUnit().equals("ml")) {
                    grams += i.getQuantity();
                } else if (i.getUnit().equals("kg") || i.getUnit().equals("l")) {
                    grams += i.getQuantity() * 1000;
                } else if (i.getUnit().equals("Stueck") || i.getUnit().equals("Scheiben")) {
                    grams += i.getQuantity() * 100;
                }
            }
        }
        return grams;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public int getRowCount() {
        return ingredients != null ? ingredients.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Ingredient i = ingredients.get(row);
        if (col == 0) {
            double value = i.getQuantity();
            // cut off decimal places if there are none
            if ((int) value == value) {
                return String.valueOf((int) value);
            } else if (value == 0.25) {
                // return 1/4, 1/2, etc. as Unicode Char
                return "¼";
            } else if (value == 0.5) {
                return "½";
            } else if (value == 0.75) {
                return "¾";
            }
            return String.valueOf(value);
        } else if (col == 1) {
            return i.getUnit();
        } else if (col == 2) {
            return i.getName();
        }
        return null;
    }
}
