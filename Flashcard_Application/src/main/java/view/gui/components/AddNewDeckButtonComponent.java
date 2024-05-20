package main.java.view.gui.components;

import main.java.view.gui.shapes.GuiButton;
import main.java.view.gui.shapes.GuiShape;

import javax.swing.*;
import java.awt.*;

public class AddNewDeckButtonComponent extends Component implements Componentable {

    /*******************************************************************************************************************
     * Constants
     */
    public static final String ADD_NEW_DECK_BUTTON_ID = "addNewDeckButton";


    /*******************************************************************************************************************
     * Constructor
     */
    public AddNewDeckButtonComponent(GuiComponentManager guiComponentManager) {
        super(guiComponentManager);
    }


    /*******************************************************************************************************************
     * Instantiate Add New Deck Button Components
     */
    public void instantiate() {
        instantiateAddNewDeckButton();
    }

    /*******************************************************************************************************************
     * Update Add New Subject Component
     */
    @Override
    public void update(JFrame jFrame) {
        updateAddNewDeckButton(jFrame);
    }


    /*******************************************************************************************************************
     * Instantiate Elements
     */
    private void instantiateAddNewDeckButton() {
        GuiShape button = new GuiButton(ADD_NEW_DECK_BUTTON_ID, 0, 0, 0, 0, 25);
        button.setFillColor(Color.CYAN);
        ((GuiButton)button).setHoverColor(Color.YELLOW);
        ((GuiButton)button).setPressedColor(Color.GREEN);
        addShapeToDraw(button);
    }

    /*******************************************************************************************************************
     * Update Elements
     */
    private void updateAddNewDeckButton(JFrame jFrame) {
        // Update
        int startX = 50;
        int endX = 100;
        int startY = 150;
        int endY = 200;

        int indexOfObject = indexOfGuiShapeById(ADD_NEW_DECK_BUTTON_ID);
        shapesToDraw().get(indexOfObject).setBounds(startX, startY, endX, endY);
    }
}