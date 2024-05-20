package main.java.view.gui.components;

import main.java.view.gui.shapes.GuiButton;
import main.java.view.gui.shapes.GuiShape;

import javax.swing.*;
import java.awt.*;

public class BackButtonComponent extends Component implements Componentable{

    /*******************************************************************************************************************
     * Constants
     */
    public static final String BACK_BUTTON_ID = "backButton";


    /*******************************************************************************************************************
     * Constructor
     */
    public BackButtonComponent(GuiComponentManager guiComponentManager) {
        super(guiComponentManager);
    }


    /*******************************************************************************************************************
     * Instantiate Back Button Components
     */
    public void instantiate() {
        instantiateBackButton();
    }

    /*******************************************************************************************************************
     * Update Back Button Components
     */
    @Override
    public void update(JFrame jFrame) {
        updateBackButton(jFrame);
    }


    /*******************************************************************************************************************
     * Instantiate Elements
     */
    private void instantiateBackButton() {
        GuiShape button = new GuiButton(BACK_BUTTON_ID, 0, 0, 0, 0, 25);
        button.setFillColor(Color.RED);
        ((GuiButton)button).setHoverColor(Color.YELLOW);
        ((GuiButton)button).setPressedColor(Color.GREEN);
        addShapeToDraw(button);
    }

    /*******************************************************************************************************************
     * Update Elements
     */
    private void updateBackButton(JFrame jFrame) {
        // Update
        int startX = 50;
        int endX = 100;
        int startY = 50;
        int endY = 100;

        int indexOfObject = indexOfGuiShapeById(BACK_BUTTON_ID);
        shapesToDraw().get(indexOfObject).setBounds(startX, startY, endX, endY);
    }

}
