package Kochbuch;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CustomTableRenderer extends JLabel implements TableCellRenderer {

    private static Color cellShadingColor;

    public CustomTableRenderer() {
        super.setOpaque(true);
        cellShadingColor = new Color(219, 219, 219, 100);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String text = "";
        if (value instanceof String) {
            text = (String) value;
        }
        if ((row + 1) % 2 == 0) {
            super.setBackground(cellShadingColor);
        } else {
            super.setBackground(Color.WHITE);
        }
        super.setText(text);
        super.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return this;
    }
}
