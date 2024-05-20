package main.java.view.gui.components;

import main.java.model.Subject;
import main.java.view.gui.shapes.GuiRoundedRectangle;
import main.java.view.gui.shapes.GuiShape;
import main.java.view.gui.shapes.GuiTypingBox;

import javax.swing.*;
import java.awt.*;

public class EditSubjectComponent extends Component implements Componentable {
    /*******************************************************************************************************************
     * Constants
     */
    private static final String BACKGROUND_ID = "editSubjectBackgroundId";
    private static final String SUBJECT_TYPING_BOX_ID = "subjectTypingBoxId";


    /*******************************************************************************************************************
     * Instance Variables
     */
    private Subject subject;


    /*******************************************************************************************************************
     * Constructor
     */
    public EditSubjectComponent(GuiComponentManager guiComponentManager, Subject subject) {
        super(guiComponentManager);
        this.subject = subject;
    }


    /*******************************************************************************************************************
     * Instantiate Edit Subject Component
     */
    public void instantiate() {
        instantiateBase(BACKGROUND_ID);
        instantiateTypingBox(SUBJECT_TYPING_BOX_ID, subject.getName());
    }


    /*******************************************************************************************************************
     * Update Edit Subject Component
     */
    @Override
    public void update(JFrame jFrame) {
        updateBase(BACKGROUND_ID, jFrame);
        updateTypingBox(SUBJECT_TYPING_BOX_ID, jFrame);

        //Set the subject's name to be the text on the typing box
        int indexOfTypingBox = indexOfGuiShapeById(SUBJECT_TYPING_BOX_ID);
        this.subject.setName(shapesToDraw().get(indexOfTypingBox).getText());
    }


    /*******************************************************************************************************************
     * Getter
     */
    public Subject getSubject() {
        return subject;
    }

    /*******************************************************************************************************************
     * Instantiate Elements
     */
    private void instantiateBase(String id) {
        GuiShape base = new GuiRoundedRectangle(id, 0, 0, 0, 0, 25);
        base.setFillColor(Color.MAGENTA);
        base.noOutline();
        addShapeToDraw(base);
    }

    private void instantiateTypingBox(String id, String existingTitle) {
        GuiShape typingBox = new GuiTypingBox(id, existingTitle, 0,0,0,0);
        typingBox.setFillColor(Color.BLACK);
        addShapeToDraw(typingBox);
    }


    /*******************************************************************************************************************
     * Update Elements
     */
    private void updateBase(String id, JFrame jFrame) {
        int startX = (int) (jFrame.getWidth() * 0.2);
        int endX = jFrame.getWidth() - startX;
        int startY = (int) (jFrame.getHeight() * 0.2);
        int endY = jFrame.getHeight() - startY;

        // Set bounds
        int indexOfBase = indexOfGuiShapeById(id);
        shapesToDraw().get(indexOfBase).setBounds(startX, startY, endX, endY);
    }


    private void updateTypingBox(String id, JFrame jFrame) {
        int startX = (int) (jFrame.getWidth() * 0.2);
        int endX = jFrame.getWidth() - startX;
        int startY = (int) (jFrame.getHeight() * 0.2);
        int endY = jFrame.getHeight() - startY;

        // Set bounds
        int indexOfTypingBox = indexOfGuiShapeById(id);
        shapesToDraw().get(indexOfTypingBox).setBounds(startX, startY, endX, endY);

        // Set font size based on component width
        int fontSize = endX - startX;
        fontSize = fontSize / 10;
        ((GuiTypingBox)shapesToDraw().get(indexOfTypingBox)).setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
    }
}
