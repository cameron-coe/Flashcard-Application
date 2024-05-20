package main.java.view.gui.components;

import main.java.view.gui.shapes.GuiButton;

import javax.swing.*;
import java.awt.*;

public class NewFlashcardButtonComponent extends Component implements Componentable {
    /*******************************************************************************************************************
     * Constants
     */
    public static final String NEW_FLASHCARD_BUTTON = "newFlashcardButton";


    /*******************************************************************************************************************
     * Constructor
     */
    public NewFlashcardButtonComponent(GuiComponentManager guiComponentManager) {
        super(guiComponentManager);
    }


    /*******************************************************************************************************************
     * Instantiate New Subject Button
     */
    public void instantiate() {
        instantiateNewFlashcardButton();
    }


    /*******************************************************************************************************************
     * Update New Subject Button
     */
    @Override
    public void update(JFrame jFrame) {
        updateNewFlashcardButton(jFrame);
    }


    /*******************************************************************************************************************
     * Instantiate Elements
     */
    private void instantiateNewFlashcardButton() {
        GuiButton button = new GuiButton(NEW_FLASHCARD_BUTTON, 0, 0, 0, 0, 25);
        button.setFillColor(Color.WHITE);
        button.setHoverColor(Color.YELLOW);
        button.setPressedColor(Color.GREEN);
        addShapeToDraw(button);
    }


    /*******************************************************************************************************************
     * Update Elements
     */
    private void updateNewFlashcardButton(JFrame jFrame) {
        // Update
        int endX = jFrame.getWidth() - (jFrame.getWidth() / 20);
        int startX = (int) (endX - (0.2 * jFrame.getWidth()));

        int startY = jFrame.getHeight() - 75;
        int endY = jFrame.getHeight()-25;

        int indexOfObject = indexOfGuiShapeById(NEW_FLASHCARD_BUTTON);
        shapesToDraw().get(indexOfObject).setBounds(startX, startY, endX, endY);
    }
}
