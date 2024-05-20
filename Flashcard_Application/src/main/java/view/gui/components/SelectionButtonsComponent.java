package main.java.view.gui.components;

import main.java.view.gui.shapes.GuiShape;

import javax.swing.*;

public abstract class SelectionButtonsComponent extends Component{


    /*******************************************************************************************************************
     * Constructor
     */
    public SelectionButtonsComponent(GuiComponentManager guiComponentManager) {
        super(guiComponentManager);
    }


    /*******************************************************************************************************************
     * Format Selection Buttons
     */
    public void formatSelectionButtons(JFrame jFrame, int index, String id) {
        // Offsets index based on page number
        index -= (getCurrentSelectionPageNumberFromApp() * getOptionsPerPageFromApp());

        /******** Only Need to modify these variables to format the buttons ********/
        double allButtonsStartX = jFrame.getWidth() / 20;
        double allButtonsEndX = jFrame.getWidth() - (allButtonsStartX);
        double allButtonsStartY = 100;
        double allButtonsEndY = jFrame.getHeight() - 100;

        double buttonSpacingX = jFrame.getWidth() / 40;
        double buttonSpacingY = jFrame.getHeight() / 20;



        int buttonsPerRow = getButtonsPerRowFromApp();
        int numOfRows = getNumOfRowsFromApp();

        /******** Only Need to modify the variables above this line ********/

        double buttonWidth = ((allButtonsEndX - allButtonsStartX) / buttonsPerRow) - (( (double)(buttonsPerRow-1) / (double)(buttonsPerRow) ) * buttonSpacingX);
        double buttonHeight = ((allButtonsEndY - allButtonsStartY) / numOfRows) - (( (double)(numOfRows-1) / (double)(numOfRows) ) * buttonSpacingY);

        int startX = (int) (allButtonsStartX + ((index % buttonsPerRow) * buttonWidth) + ( (index % buttonsPerRow) * buttonSpacingX ));
        int startY = (int) (allButtonsStartY + ((index / buttonsPerRow) * buttonHeight) + ( (index / buttonsPerRow) * buttonSpacingY ));
        int endX = (int) (startX + buttonWidth);
        int endY = (int) (startY + buttonHeight);

        // Update
        int indexOfButton = indexOfGuiShapeById(id);
        shapesToDraw().get(indexOfButton).setBounds(startX, startY, endX, endY);
    }

    public void formatSelectionButtonText(String baseButtonId, String textId) {
        int indexOfBaseButton = indexOfGuiShapeById(baseButtonId);
        GuiShape baseButton = shapesToDraw().get(indexOfBaseButton);

        int startX = baseButton.getPoint1X();
        int endX = baseButton.getPoint2X();
        int startY = baseButton.getPoint1Y();
        int endY = baseButton.getPoint2Y();

        // Update
        int indexOfButtonText = indexOfGuiShapeById(textId);
        shapesToDraw().get(indexOfButtonText).setBounds(startX, startY, endX, endY);
    }

    public void setNumberOfSelectionPages(int listSize) {

        int optionsPerPage = getOptionsPerPageFromApp();
        int numberOfPages = listSize / optionsPerPage;
        setMaxPageNumberInApp(numberOfPages);
    }

    public int createSelectionButtonStartPoint() {
        int selectionStart = (getCurrentSelectionPageNumberFromApp() * getOptionsPerPageFromApp());
        return selectionStart;
    }

    public int createSelectionButtonEndPoint(int selectionStart, int listSize) {
        int selectionEnd = selectionStart + getOptionsPerPageFromApp();

        // Makes selectionEnd less than the number of options per page if there aren't enough options
        // to fill the whole page
        if ( (listSize - selectionStart) < getOptionsPerPageFromApp() ) {
            selectionEnd = selectionStart + listSize - selectionStart;
        }

        return selectionEnd;
    }
}
