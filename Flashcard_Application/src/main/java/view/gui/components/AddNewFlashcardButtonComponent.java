package main.java.view.gui.components;

import main.java.view.gui.shapes.GuiButton;
import main.java.view.gui.shapes.GuiShape;

import javax.swing.*;
import java.awt.*;

public class AddNewFlashcardButtonComponent extends Component implements Componentable {

    /*******************************************************************************************************************
     * Constants
     */
    public static final String ADD_NEW_FLASHCARD_BUTTON_ID = "addNewFlashcardButton";


    /*******************************************************************************************************************
     * Constructor
     */
    public AddNewFlashcardButtonComponent(GuiComponentManager guiComponentManager) {
        super(guiComponentManager);
    }


    /*******************************************************************************************************************
     * Instantiate Add New Flashcard Button Components
     */
    public void instantiate() {
        instantiateAddNewFlashcardButton();
    }


    /*******************************************************************************************************************
     * Update Add New Subject Component
     */
    @Override
    public void update(JFrame jFrame) {
        updateAddNewFlashcardButton(jFrame);
    }


    /*******************************************************************************************************************
     * Instantiate Elements
     */
    private void instantiateAddNewFlashcardButton() {
        GuiShape button = new GuiButton(ADD_NEW_FLASHCARD_BUTTON_ID, 0, 0, 0, 0, 25);
        button.setFillColor(Color.CYAN);
        ((GuiButton)button).setHoverColor(Color.YELLOW);
        ((GuiButton)button).setPressedColor(Color.GREEN);
        addShapeToDraw(button);
    }


    /*******************************************************************************************************************
     * Update Elements
     */
    private void updateAddNewFlashcardButton(JFrame jFrame) {
        // Update
        int startX = 50;
        int endX = 100;
        int startY = 150;
        int endY = 200;

        int indexOfObject = indexOfGuiShapeById(ADD_NEW_FLASHCARD_BUTTON_ID);
        shapesToDraw().get(indexOfObject).setBounds(startX, startY, endX, endY);
    }



}
