package main.java.view.gui.components;

import main.java.view.gui.shapes.GuiButton;
import main.java.view.gui.shapes.GuiShape;

import javax.swing.*;
import java.awt.*;

public class StartButtonComponent extends Component implements Componentable{

    /*******************************************************************************************************************
     * Constants
     */
    public static final String START_BUTTON = "startButton";


    /*******************************************************************************************************************
     * Constructor
     */
    public StartButtonComponent(GuiComponentManager guiComponentManager) {
        super(guiComponentManager);
    }


    /*******************************************************************************************************************
     * Instantiate Start Studying Button
     */
    public void instantiate() {
        instantiateStartButton();
    }


    /*******************************************************************************************************************
     * Update Start Studying Button
     */
    @Override
    public void update(JFrame jFrame) {
        updateStartButton(jFrame);
    }


    /*******************************************************************************************************************
     * Instantiate Elements
     */
    private void instantiateStartButton() {
        GuiButton button = new GuiButton(START_BUTTON, 0, 0, 0, 0, 25);
        button.setFillColor(Color.cyan);
        button.setHoverColor(Color.YELLOW);
        button.setPressedColor(Color.GREEN);
        addShapeToDraw(button);
    }

    /*******************************************************************************************************************
     * Update Elements
     */
    private void updateStartButton(JFrame jFrame) {
        // Update
        int startX = (int) (0.2 * jFrame.getWidth());
        int endX = jFrame.getWidth() - startX;
        int startY = jFrame.getHeight() - 150;
        int endY = startY + 75;

        int indexOfObject = indexOfGuiShapeById(START_BUTTON);
        shapesToDraw().get(indexOfObject).setBounds(startX, startY, endX, endY);
    }
}
