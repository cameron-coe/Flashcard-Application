package main.java.view.gui.components;

import main.java.view.gui.shapes.GuiButton;

import javax.swing.*;
import java.awt.*;

public class NewSubjectButtonComponent extends Component implements Componentable{
    /*******************************************************************************************************************
     * Constants
     */
    public static final String NEW_SUBJECT_BUTTON = "newSubjectButton";


    /*******************************************************************************************************************
     * Constructor
     */
    public NewSubjectButtonComponent(GuiComponentManager guiComponentManager) {
        super(guiComponentManager);
    }


    /*******************************************************************************************************************
     * Instantiate New Subject Button
     */
    public void instantiate() {
        instantiateNewSubjectButton();
    }


    /*******************************************************************************************************************
     * Update New Subject Button
     */
    @Override
    public void update(JFrame jFrame) {
        updateNewSubjectButton(jFrame);
    }


    /*******************************************************************************************************************
     * Instantiate Elements
     */
    private void instantiateNewSubjectButton() {
        GuiButton button = new GuiButton(NEW_SUBJECT_BUTTON, 0, 0, 0, 0, 25);
        button.setFillColor(Color.MAGENTA);
        button.setHoverColor(Color.YELLOW);
        button.setPressedColor(Color.GREEN);
        addShapeToDraw(button);
    }


    /*******************************************************************************************************************
     * Update Elements
     */
    private void updateNewSubjectButton(JFrame jFrame) {
        // Update
        int endX = jFrame.getWidth() - (jFrame.getWidth() / 20);
        int startX = (int) (endX - (0.2 * jFrame.getWidth()));

        int startY = jFrame.getHeight() - 75;
        int endY = jFrame.getHeight()-25;

        int indexOfObject = indexOfGuiShapeById(NEW_SUBJECT_BUTTON);
        shapesToDraw().get(indexOfObject).setBounds(startX, startY, endX, endY);
    }

}
