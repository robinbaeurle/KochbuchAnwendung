package Kochbuch;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.stream.Collectors;

public class RecipePanel extends JPanel {

    private JList<String> recipesJList;
    private Vector<String> recipeNames;
    private List<Recipe> recipesData;
    private Vector<Recipe> recipesDisplay;

    public RecipePanel() {
        this.setLayout(new BorderLayout(0, 10));
        TitledBorder recipesBorder = BorderFactory.createTitledBorder(ResourceBundle.getBundle(KochbuchUI.I18N_BundleBaseName, getLocale()).getString("recipesBorder"));
        recipesBorder.setBorder(new EtchedBorder());
        this.setBorder(recipesBorder);
    }

    public RecipePanel setData(List<Recipe> recipes) {
        this.recipesData = recipes;
        this.recipesDisplay = new Vector<>(recipes);
        recipeNames = new Vector<>();
        for (Recipe r : recipes) {
            recipeNames.add(r.getName());
        }
        recipesJList.setListData(recipeNames);
        return this;
    }

    public RecipePanel createRecipesList(ListCellRenderer renderer, Dimension prefScrollSize) {
        recipesJList = new JList<>();
        recipesJList.setCellRenderer(renderer);
        recipesJList.setFixedCellHeight(30);
        recipesJList.setVisibleRowCount(20);
        JScrollPane recipesScroll = new JScrollPane(recipesJList);
        if (prefScrollSize != null) {
            recipesScroll.setPreferredSize(prefScrollSize);
        }
        JTextField searchField = new JTextField();
        String searchFieldHint = ResourceBundle.getBundle(KochbuchUI.I18N_BundleBaseName, getLocale()).getString("searchFieldHint");
        searchField.setToolTipText(searchFieldHint);
        searchField.setText(searchFieldHint);
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchTerm = searchField.getText();
                if (!searchTerm.isEmpty()) {
                    recipesDisplay = recipesData.stream()
                            .filter(recipe -> recipe.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                            .collect(Collectors.toCollection(Vector::new));
                    Vector<String> filteredNames = recipesDisplay.stream()
                            .map(Recipe::getName)
                            .collect(Collectors.toCollection(Vector::new));
                    recipesJList.setListData(filteredNames);
                } else {
                    recipesDisplay.clear();
                    recipesDisplay.addAll(recipesData);
                    recipesJList.setListData(recipeNames);
                }
            }
        });
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals(searchFieldHint)) {
                    searchField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().equals("")) {
                    searchField.setText(searchFieldHint);
                }
            }
        });
        this.add(searchField, BorderLayout.NORTH);
        this.add(recipesScroll, BorderLayout.CENTER);
        return this;
    }

    public RecipePanel addListSelectionListener(ListSelectionListener listener) {
        recipesJList.addListSelectionListener(listener);
        return this;
    }

    public Recipe getSelectedRecipe() {
        int index = recipesJList.getSelectedIndex();
        index = index >= 0 ? index : 0;
        return recipesDisplay.get(index);
    }

    public void initializeSelection(int index) {
        recipesJList.setSelectedIndex(index);
    }

}
