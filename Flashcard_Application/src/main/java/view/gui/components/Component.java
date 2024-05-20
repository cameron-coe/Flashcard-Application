package main.java.view.gui.components;

import main.java.model.Deck;
import main.java.model.Subject;
import main.java.view.gui.shapes.GuiShape;

import java.util.List;

public abstract class Component {

    /*******************************************************************************************************************
     * Instance Variables
     */
    private GuiComponentManager guiComponentManager;


    /*******************************************************************************************************************
     * Constructor
     */
    public Component(GuiComponentManager guiComponentManager) {
        this.guiComponentManager = guiComponentManager;
    }


    /*******************************************************************************************************************
     * Adding a Shape to Draw
     */
    public void addShapeToDraw(GuiShape guiShape) {
        guiComponentManager.addShapeToDraw(guiShape);
    }


    /*******************************************************************************************************************
     * Getters
     */
    public List<GuiShape> shapesToDraw() {
        return guiComponentManager.getShapesToDraw();
    }

    public int getCurrentSelectionPageNumberFromApp() {
        return guiComponentManager.getCurrentSelectionPageNumberFromApp();
    }

    public int getMaxPageNumberFromApp() {
        return guiComponentManager.getMaxPageNumberFromApp();
    }

    public int getOptionsPerPageFromApp() {
        return guiComponentManager.getOptionsPerPageFromApp();
    }

    public int getButtonsPerRowFromApp() {
        return guiComponentManager.getButtonsPerRowFromApp();
    }

    public int getNumOfRowsFromApp() {
        return guiComponentManager.getNumOfRowsFromApp();
    }

    public List<Subject> getListOfSubjectsFromCollection() {
        return guiComponentManager.getListOfSubjectsFromCollection();
    }

    public List<Deck> getListOfDecksFromCurrentSubject() {
        return guiComponentManager.getListOfDecksFromCurrentSubject();
    }

    /*******************************************************************************************************************
     * Setters
     */
    public void setMaxPageNumberInApp(int maxPageNumber) {
        guiComponentManager.setMaxPageNumberInApp(maxPageNumber);
    }

    /*******************************************************************************************************************
     * Getting Values from the ShapesToDraw List in GuiComponentManager
     */
    public boolean shapesToDrawListContainsId(String id) {
        for (GuiShape shape : shapesToDraw()) {
            String shapeId = shape.getShapeId();
            if (shapeId != null) {
                if (shapeId.equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOfGuiShapeById(String id) {
        List<GuiShape> shapesToDraw = shapesToDraw();
        for (int i = 0; i < shapesToDraw.size(); i++) {
            GuiShape shape = shapesToDraw.get(i);
            String shapeId = shape.getShapeId();
            if (shapeId != null) {
                if (shapeId.equals(id)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
