package Kochbuch;

import javax.swing.*;
import java.awt.*;

public class CustomListRenderer extends JLabel implements ListCellRenderer {

    public static final Color defaultCellShadingColor = new Color(219, 219, 219, 100);
    public static final Color defaultCellSelectedColor = new Color(219, 138, 0, 133);
    private Color cellShadingColor, cellSelectedColor;

    public CustomListRenderer(Color cellShadingColor, Color cellSelectedColor) {
        super.setOpaque(true);
        this.cellShadingColor = cellShadingColor;
        this.cellSelectedColor = cellSelectedColor;
    }

    @Override
    public Component getListCellRendererComponent(JList jList, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String text = "";
        if (value instanceof String) {
            text = (String) value;
        }
        if (isSelected) {
            super.setBackground(cellSelectedColor);
        } else {
            if ((index + 1) % 2 == 0) {
                super.setBackground(cellShadingColor);
            } else {
                super.setBackground(Color.WHITE);
            }
        }
        super.setText(text);
        super.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return this;
    }

}
