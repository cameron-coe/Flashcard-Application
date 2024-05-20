package main.java.view.gui.components;

import main.java.view.gui.shapes.GuiShape;
import main.java.view.gui.shapes.GuiTextBox;

import javax.swing.*;
import java.awt.*;

public class HeaderComponent extends Component implements Componentable{

    /*******************************************************************************************************************
     * Constants
     */
    public static final String PAGE_HEADER_ID = "pageHeader";


    /*******************************************************************************************************************
     * Constructor
     */
    public HeaderComponent(GuiComponentManager guiComponentManager) {
        super(guiComponentManager);
    }

    /*******************************************************************************************************************
     * Instantiate Header Component
     */
    public void instantiate(String headerText) {
        instantiateSubjectPageHeader(headerText);
    }

    /*******************************************************************************************************************
     * Update Header Component
     */
    @Override
    public void update(JFrame jFrame) {
        updatePageHeader(jFrame);
    }


    /*******************************************************************************************************************
     * Instantiate Elements
     */
    private void instantiateSubjectPageHeader(String headerText) {
        GuiShape subjectPageHeader = new GuiTextBox(PAGE_HEADER_ID, headerText, 0, 0, 0, 0);
        subjectPageHeader.setFillColor(Color.BLACK);
        addShapeToDraw(subjectPageHeader);
    }


    /*******************************************************************************************************************
     * Update Elements
     */
    private void updatePageHeader(JFrame jFrame) {
        int indexOfPageHeader = indexOfGuiShapeById(PAGE_HEADER_ID);
        shapesToDraw().get(indexOfPageHeader).setBounds(0, 50, jFrame.getWidth(), 150);
    }
}
