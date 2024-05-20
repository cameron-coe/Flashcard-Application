package main.java.view.gui.components;

import main.java.model.Subject;
import main.java.view.gui.shapes.GuiButton;
import main.java.view.gui.shapes.GuiShape;

import javax.swing.*;
import java.awt.*;

public class AddNewSubjectButtonComponent extends Component implements Componentable{

    /*******************************************************************************************************************
     * Constants
     */
    public static final String ADD_NEW_SUBJECT_BUTTON_ID = "addNewSubjectButton";


    /*******************************************************************************************************************
     * Constructor
     */
    public AddNewSubjectButtonComponent(GuiComponentManager guiComponentManager) {
        super(guiComponentManager);
    }


    /*******************************************************************************************************************
     * Instantiate Add New Subject Button Components
     */
    public void instantiate() {
        instantiateAddNewSubjectButton();
    }

    /*******************************************************************************************************************
     * Update Add New Subject Component
     */
    @Override
    public void update(JFrame jFrame) {
        updateAddNewSubjectButton(jFrame);
    }


    /*******************************************************************************************************************
     * Instantiate Elements
     */
    private void instantiateAddNewSubjectButton() {
        GuiShape button = new GuiButton(ADD_NEW_SUBJECT_BUTTON_ID, 0, 0, 0, 0, 25);
        button.setFillColor(Color.CYAN);
        ((GuiButton)button).setHoverColor(Color.YELLOW);
        ((GuiButton)button).setPressedColor(Color.GREEN);
        addShapeToDraw(button);
    }

    /*******************************************************************************************************************
     * Update Elements
     */
    private void updateAddNewSubjectButton(JFrame jFrame) {
        // Update
        int startX = 50;
        int endX = 100;
        int startY = 150;
        int endY = 200;

        int indexOfObject = indexOfGuiShapeById(ADD_NEW_SUBJECT_BUTTON_ID);
        shapesToDraw().get(indexOfObject).setBounds(startX, startY, endX, endY);
    }
}
