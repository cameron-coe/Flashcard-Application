package main.java.view.gui.components;

import main.java.view.gui.shapes.GuiButton;
import main.java.view.gui.shapes.GuiShape;
import main.java.view.gui.shapes.GuiTextBox;

import javax.swing.*;
import java.awt.*;

public class PageNavigatorComponent extends Component implements Componentable{

    /*******************************************************************************************************************
     * Constants
     */
    public static final String SELECTION_PAGE_UP_BUTTON_ID = "pageUpButton";
    public static final String SELECTION_PAGE_DOWN_BUTTON_ID = "pageDownButton";
    private static final String CURRENT_SELECTION_PAGE_TEXT_ID = "currentPageText";


    /*******************************************************************************************************************
     * Constructor
     */
    public PageNavigatorComponent(GuiComponentManager guiComponentManager) {
        super(guiComponentManager);
    }


    /*******************************************************************************************************************
     * Instantiate Page Navigator
     */
    public void instantiate() {
        instantiateSelectionPageUpButton();
        instantiateSelectionPageDownButton();
        instantiateSelectionPageNavigationText();
    }

    /*******************************************************************************************************************
     * Update Page Navigator
     */
    @Override
    public void update(JFrame jFrame) {
        int startX = jFrame.getWidth()/2 - 150;
        int endX = jFrame.getWidth()/2 + 150;
        int startY = jFrame.getHeight() - 75;
        int endY = jFrame.getHeight()-25;

        int height = endY - startY;

        updateSelectionPageUpButton(endX - height, startY, endX, endY);
        updateSelectionPageDownButton(startX, startY, startX + height, endY);
        updateSelectionPageNavigationText(startX, startY, endX, endY);
    }


    /*******************************************************************************************************************
     * Instantiate Elements
     */
    private void instantiateSelectionPageNavigationText() {
        GuiShape subjectPageHeader = new GuiTextBox(CURRENT_SELECTION_PAGE_TEXT_ID, "Page --/--", 0, 0, 0, 0);
        subjectPageHeader.setFillColor(Color.BLACK);
        addShapeToDraw(subjectPageHeader);
    }

    private void instantiateSelectionPageUpButton() {
        GuiButton button = new GuiButton(SELECTION_PAGE_UP_BUTTON_ID, 0, 0, 0, 0, 25);
        button.setFillColor(Color.WHITE);
        button.setHoverColor(Color.YELLOW);
        button.setPressedColor(Color.GREEN);
        addShapeToDraw(button);
    }

    private void instantiateSelectionPageDownButton() {
        GuiButton button = new GuiButton(SELECTION_PAGE_DOWN_BUTTON_ID, 0, 0, 0, 0, 25);
        button.setFillColor(Color.WHITE);
        button.setHoverColor(Color.YELLOW);
        button.setPressedColor(Color.GREEN);
        addShapeToDraw(button);
    }



    /*******************************************************************************************************************
     * Update Elements
     */
    private void updateSelectionPageNavigationText(int startX, int startY, int endX, int endY) {
        int indexOfPageHeader = indexOfGuiShapeById(CURRENT_SELECTION_PAGE_TEXT_ID);
        shapesToDraw().get(indexOfPageHeader).setBounds(startX, startY, endX, endY);

        String currentPage = "Page ";
        currentPage += (getCurrentSelectionPageNumberFromApp() + 1);
        currentPage += "/";
        currentPage += (getMaxPageNumberFromApp() + 1);

        shapesToDraw().get(indexOfPageHeader).setText(currentPage);
    }

    private void updateSelectionPageUpButton(int startX, int startY, int endX, int endY) {
        int indexOfObject = indexOfGuiShapeById(SELECTION_PAGE_UP_BUTTON_ID);
        shapesToDraw().get(indexOfObject).setBounds(startX, startY, endX, endY);
    }

    private void updateSelectionPageDownButton(int startX, int startY, int endX, int endY) {
        int indexOfObject = indexOfGuiShapeById(SELECTION_PAGE_DOWN_BUTTON_ID);
        shapesToDraw().get(indexOfObject).setBounds(startX, startY, endX, endY);
    }
}
