package main.java.view.gui.components;

import main.java.model.Subject;
import main.java.view.gui.shapes.GuiButton;
import main.java.view.gui.shapes.GuiShape;
import main.java.view.gui.shapes.GuiTextBox;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SubjectSelectionButtonsComponent extends SelectionButtonsComponent implements Componentable{

    /*******************************************************************************************************************
     * Constants
     */
    public static final String SELECT_SUBJECT_BUTTON_ID = "selectSubjectButton";
    private static final String SELECT_SUBJECT_BUTTON_TEXT_ID = "selectSubjectButtonText";


    /*******************************************************************************************************************
     * Constructor
     */
    public SubjectSelectionButtonsComponent(GuiComponentManager guiComponentManager) {
        super(guiComponentManager);
    }


    /*******************************************************************************************************************
     * Instantiate Component
     */
    public void instantiate() {
        instantiateSubjectSelectionButtons(getListOfSubjectsFromCollection());
    }


    /*******************************************************************************************************************
     * Update Component
     */
    @Override
    public void update(JFrame jFrame) {
        updateSubjectSelectionButtons(jFrame, getListOfSubjectsFromCollection());
    }





    /*******************************************************************************************************************
     * Instantiate Elements
     */
    public void instantiateSubjectSelectionButtons(List<Subject> subjects) {
        for(int i = 0; i < subjects.size(); i++) {
            String uniqueButtonId = SELECT_SUBJECT_BUTTON_ID + i;
            String uniqueButtonTextId = SELECT_SUBJECT_BUTTON_TEXT_ID + i;
            instantiateSubjectButton(uniqueButtonId);
            instantiateSubjectButtonText(uniqueButtonTextId, subjects.get(i));
        }
    }

    private void instantiateSubjectButton(String id) {
        GuiShape button = new GuiButton(id, 0, 0, 0, 0, 25);
        button.setFillColor(Color.magenta);
        ((GuiButton)button).setHoverColor(Color.YELLOW);
        ((GuiButton)button).setPressedColor(Color.GREEN);
        addShapeToDraw(button);
    }

    private void instantiateSubjectButtonText(String id, Subject subject) {
        GuiShape text = new GuiTextBox(id, subject.getName(), 0, 0, 0, 0);
        text.setFillColor(Color.BLACK);
        addShapeToDraw(text);
    }


    /*******************************************************************************************************************
     * Update Elements
     */
    public void updateSubjectSelectionButtons(JFrame jFrame, List<Subject> subjects) {
        // Todo
        int selectionStart = createSelectionButtonStartPoint();
        int selectionEnd = createSelectionButtonEndPoint(selectionStart, subjects.size());

        for(int i = selectionStart; i < selectionEnd; i++) {
            updateSubjectButton(jFrame, i);
            updateSubjectButtonText(i);

            setNumberOfSelectionPages(subjects.size());
        }
    }

    private void updateSubjectButton(JFrame jFrame, int index) {
        String uniqueId = SELECT_SUBJECT_BUTTON_ID + index;
        formatSelectionButtons(jFrame, index, uniqueId);
    }

    private void updateSubjectButtonText(int index) {
        String baseButtonId = SELECT_SUBJECT_BUTTON_ID + index;
        String uniqueButtonTextId = SELECT_SUBJECT_BUTTON_TEXT_ID + index;

        formatSelectionButtonText(baseButtonId, uniqueButtonTextId);
    }

}
