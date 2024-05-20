package main.java.view.gui.components;

import main.java.view.gui.shapes.GuiButton;

import javax.swing.*;
import java.awt.*;

public class NewDeckButtonComponent extends Component implements Componentable {
    /*******************************************************************************************************************
     * Constants
     */
    public static final String NEW_DECK_BUTTON = "newDeckButton";


    /*******************************************************************************************************************
     * Constructor
     */
    public NewDeckButtonComponent(GuiComponentManager guiComponentManager) {
        super(guiComponentManager);
    }


    /*******************************************************************************************************************
     * Instantiate New Deck Button
     */
    public void instantiate() {
        instantiateNewDeckButton();
    }


    /*******************************************************************************************************************
     * Update New Deck Button
     */
    @Override
    public void update(JFrame jFrame) {
        updateNewDeckButton(jFrame);
    }


    /*******************************************************************************************************************
     * Instantiate Elements
     */
    private void instantiateNewDeckButton() {
        GuiButton button = new GuiButton(NEW_DECK_BUTTON, 0, 0, 0, 0, 25);
        button.setFillColor(Color.BLUE);
        button.setHoverColor(Color.YELLOW);
        button.setPressedColor(Color.GREEN);
        addShapeToDraw(button);
    }


    /*******************************************************************************************************************
     * Update Elements
     */
    private void updateNewDeckButton(JFrame jFrame) {
        // Update
        int endX = jFrame.getWidth() - (jFrame.getWidth() / 20);
        int startX = (int) (endX - (0.2 * jFrame.getWidth()));

        int startY = jFrame.getHeight() - 75;
        int endY = jFrame.getHeight()-25;

        int indexOfObject = indexOfGuiShapeById(NEW_DECK_BUTTON);
        shapesToDraw().get(indexOfObject).setBounds(startX, startY, endX, endY);
    }
}
