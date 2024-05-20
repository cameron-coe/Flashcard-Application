package main.java.view.gui;

import main.java.view.gui.components.GuiComponentManager;
import main.java.view.gui.shapes.GuiButton;
import main.java.view.gui.shapes.GuiShape;

public class ButtonEngine {

    /*******************************************************************************************************************
     * Instance Variables
     */
    private GuiButton hoveredOverButton = null;
    private GuiButton highlightedButton = null;
    private GuiButton pressedDownButton = null;


    /*******************************************************************************************************************
     * Constructor
     */
    public ButtonEngine() {

    }


    /*******************************************************************************************************************
     * Update Buttons
     * Highlights buttons when the mouse hovers over them
     * Makes the active button the button being hovered over
     * returns true if there is an update
     */
    public boolean updateButtonsWhenMouseGoesInOrOut(Gui gui, GuiComponentManager guiComponentManager) {
        boolean update = false;

        // Button Activation
        hoveredOverButton = buttonTheMouseIsOver(gui, guiComponentManager);

        if(hoveredOverButton != null && highlightedButton == null) {
            // Repaints and updates when mouse enters a new button when highlightedButton is null
            hoveredOverButton.setMouseOver(true);
            highlightedButton = hoveredOverButton;
            update = true;
        }
        else if (hoveredOverButton == null && highlightedButton != null) {
            // Repaints and updates when the mouse leaves the button to non-button space
            if (pressedDownButton != null) {
                pressedDownButton.setMousePressed(false);
                pressedDownButton.setMouseOver(false);
                pressedDownButton = null;
            }
            highlightedButton = null;
            update = true;
        }
        else if (hoveredOverButton != null && highlightedButton != null) {
            // Highlights the hovered over button if the button loads under the mouse
            if(!hoveredOverButton.isMouseOver()) {
                highlightedButton.setMouseOver(false);
                hoveredOverButton.setMouseOver(true);
                highlightedButton = hoveredOverButton;
                update = true;
            }
        }

        return update;
    }


    /*******************************************************************************************************************
     * Sets pressedDownButton when the mouse presses down on the button
     * returns true if pressed down on a button
     */
    public boolean mouseDownOnButton(GuiComponentManager guiComponentManager) {
        if (hoveredOverButton != null) {
            pressedDownButton = hoveredOverButton;
            pressedDownButton.setMousePressed(true);
            return true;
        }
        return false;
    }


    /*******************************************************************************************************************
     * When the mouse completes a click on the button
     */
    public String mouseReleasedOnButton(Gui gui) {
        String result = null;
        if (hoveredOverButton != null && pressedDownButton != null) {
            if (hoveredOverButton.getShapeId().equals(pressedDownButton.getShapeId())) {
                result = pressedDownButton.getShapeId();
                pressedDownButton.setMousePressed(false);
                pressedDownButton = null;
            }
        }

        return result;
    }


    /*******************************************************************************************************************
     * Check if the mouse is over a button
     * return the button it's over as a GuiButton
     */
    private GuiButton buttonTheMouseIsOver(Gui gui, GuiComponentManager guiComponentManager) {
        GuiButton selectedButton = null;

        for (GuiShape shape : guiComponentManager.getShapesToDraw()) {
            if (shape instanceof GuiButton) {
                int mouseX = gui.getMouseX();
                int mouseY = gui.getMouseY();

                // Checks if mouse is inside button
                if (mouseX > shape.getPoint1X() && mouseX < shape.getPoint2X() && mouseY > shape.getPoint1Y() && mouseY < shape.getPoint2Y()) {
                    selectedButton = (GuiButton) shape;
                } else {
                    ((GuiButton) shape).setMouseOver(false);
                }
            }
        }
        return selectedButton;
    }
}
