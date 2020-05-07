package Kochbuch;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Vector;

public class InstructionsPanel extends JPanel {

    private DefaultListModel<String> instructionsModel;

    public InstructionsPanel() {
        this.setLayout(new BorderLayout(0, 10));
        TitledBorder instructionsBorder = BorderFactory.createTitledBorder(ResourceBundle.getBundle(KochbuchUI.I18N_BundleBaseName, getLocale()).getString("instructionsBorder"));
        instructionsBorder.setBorder(new EtchedBorder());
        this.setBorder(instructionsBorder);
    }

    public InstructionsPanel createInstructionsList(ListCellRenderer renderer, Dimension prefScrollSize) {
        instructionsModel = new DefaultListModel<>();
        JList<String> instructionsJList = new JList<>(instructionsModel);
        instructionsJList.setFixedCellHeight(30);
        instructionsJList.setCellRenderer(renderer);
        JScrollPane instructionsScroll = new JScrollPane(instructionsJList);
        instructionsScroll.setPreferredSize(instructionsJList.getPreferredScrollableViewportSize());
        if (prefScrollSize != null) {
            instructionsScroll.setPreferredSize(prefScrollSize);
        }
        this.add(instructionsScroll, BorderLayout.CENTER);
        return this;
    }

    public void updateData(String instructions) {
        Vector<String> instructionsData = new Vector<String>(Arrays.asList(instructions.split("\n")));
        instructionsModel.clear();
        instructionsModel.addAll(instructionsData);
    }


}
