package Kochbuch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class KochbuchUI extends JFrame {

    static final String I18N_BundleBaseName = "KochbuchBundle";

    private KochbuchUI() {
        super();
        setTitle(ResourceBundle.getBundle(I18N_BundleBaseName, getLocale()).getString("title"));

        // Read data from the json file and parse into objects
        RecipeParser recipeParser = new RecipeParser();
        List<Recipe> recipes = recipeParser.getRecipesFromJsonFile("recipes.json");

        // Initialize Layout
        JPanel mainContentPanel = new JPanel(new GridLayout(1, 3, 10, 50));
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        RecipePanel recipesPanel = new RecipePanel();
        JPanel ingredientsAndNutrientsPanel = new JPanel();
        IngredientsPanel ingredientsPanel = new IngredientsPanel();
        NutrientsPanel nutrientsPanel = new NutrientsPanel();
        ingredientsAndNutrientsPanel.setLayout(new BoxLayout(ingredientsAndNutrientsPanel, BoxLayout.Y_AXIS));
        InstructionsPanel instructionsPanel = new InstructionsPanel();

        // Recipes
        CustomListRenderer recipeListRenderer = new CustomListRenderer(CustomListRenderer.defaultCellShadingColor, CustomListRenderer.defaultCellSelectedColor);
        recipesPanel.createRecipesList(recipeListRenderer, null)
                .setData(recipes)
                .addListSelectionListener(listSelectionEvent -> {
                    Recipe r = recipesPanel.getSelectedRecipe();
                    // update the panels' contents
                    ingredientsPanel.updateData(r.getServings(), r.getCostEuro(), r.getIngredients());
                    nutrientsPanel.updateData(ingredientsPanel.getPortionWeightGrams(), r.getNutritionalData());
                    instructionsPanel.updateData(r.getInstructions());
                });

        // Ingredients and Nutrients
        CustomTableRenderer customTableRenderer = new CustomTableRenderer();
        // ingredientsPanel
        ingredientsPanel.createIngredientsTable(customTableRenderer)
                .addButtonListeners((ActionEvent e) -> {
                    // Plus Button
                    ingredientsPanel.incrementPortion();
                }, (ActionEvent e) -> {
                    // Minus Button
                    ingredientsPanel.decrementPortion();
                });
        ingredientsAndNutrientsPanel.add(ingredientsPanel);
        // nutrientsPanel
        nutrientsPanel.createNutrientsTable(customTableRenderer, 0);
        ingredientsAndNutrientsPanel.add(nutrientsPanel);

        // Instructions
        CustomListRenderer instructionsListRenderer = new CustomListRenderer(CustomListRenderer.defaultCellShadingColor, new Color(0, 199, 255, 177));
        instructionsPanel.createInstructionsList(instructionsListRenderer, null);

        // Combine panels
        mainContentPanel.add(recipesPanel, BorderLayout.WEST);
        mainContentPanel.add(ingredientsAndNutrientsPanel, BorderLayout.CENTER);
        mainContentPanel.add(instructionsPanel, BorderLayout.EAST);
        setContentPane(mainContentPanel);

        // Initialize selection to first list element
        recipesPanel.initializeSelection(0);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        mainContentPanel.setPreferredSize(dimension);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBounds(0, 0, (int) dimension.getWidth() - 200, (int) dimension.getHeight() - 200);
    }

    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(I18N_BundleBaseName, Locale.getDefault());
        String[] languages = new String[]{resourceBundle.getString("langDe"), resourceBundle.getString("langEn")};
        String s = (String) JOptionPane.showInputDialog(
                new JFrame(),
                resourceBundle.getString("promptLangChooser"),
                resourceBundle.getString("promptLangChooser"),
                JOptionPane.PLAIN_MESSAGE,
                null,
                languages,
                languages[0]);
        if (s != null && s.equals(resourceBundle.getString("langDe"))) {
            Locale.setDefault(Locale.GERMAN);
        } else {
            Locale.setDefault(Locale.ENGLISH);
        }
        new KochbuchUI();
    }

}
