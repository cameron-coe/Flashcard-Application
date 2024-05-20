package main.java.view.gui.components;

import main.java.model.Flashcard;
import main.java.view.gui.shapes.GuiButton;
import main.java.view.gui.shapes.GuiShape;
import main.java.view.gui.shapes.GuiTextBox;
import main.java.view.gui.shapes.GuiTypingBox;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EditFlashcardComponent extends Component implements Componentable {

    /*******************************************************************************************************************
     * Constants
     */
    private static final String FLASHCARD_TYPING_BOX_ID = "flashcardTypingBoxId";
    public static final String FLIP_EDITING_FLASHCARD_BUTTON_ID = "FlipEditedFlashcardButtonId";


    /*******************************************************************************************************************
     * Instance Variables
     */
    private Flashcard flashcard;
    private String tempFlashcardFrontText = "";
    private String tempFlashcardBackText = "";


    /*******************************************************************************************************************
     * Constructor
     */
    public EditFlashcardComponent(GuiComponentManager guiComponentManager, Flashcard flashcard) {
        super(guiComponentManager);
        this.flashcard = flashcard;
        hideFlashcardFlipButton();
    }


    /*******************************************************************************************************************
     * Instantiate the Edit Flashcard Component
     */
    public void instantiate() {
        instantiateTypingBox(FLASHCARD_TYPING_BOX_ID);
        instantiateFlipEditingFlashcardButton();
    }


    /*******************************************************************************************************************
     * Update the Edit Flashcard Component
     */
    @Override
    public void update(JFrame jFrame) {
        updateTypingBox();
        updateAddNewFlashcardButton(jFrame);
    }


    /*******************************************************************************************************************
     * Getter
     */
    public Flashcard getFlashcard() {
        return flashcard;
    }


    /*******************************************************************************************************************
     * Setters
     */
    public void setTextHorizontalAlignment() {
        //TODO
    }

    public void setTextVerticalAlignment() {
        //TODO
    }

    public void applyChanges() {
        if (!flashcard.isFront()) {
            flashcard.flipCard();
        }
        flashcard.setFront(tempFlashcardFrontText);
        flashcard.setBack(tempFlashcardBackText);
    }


    /*******************************************************************************************************************
     * Instantiate Elements
     */
    private void instantiateTypingBox(String id) {
        GuiShape typingBox = new GuiTypingBox(id, "", 0,0,0,0);
        typingBox.setFillColor(Color.BLACK);
        addShapeToDraw(typingBox);
    }

    private void instantiateFlipEditingFlashcardButton() {
        GuiShape button = new GuiButton(FLIP_EDITING_FLASHCARD_BUTTON_ID, 0, 0, 0, 0, 25);
        button.setFillColor(Color.ORANGE);
        ((GuiButton)button).setHoverColor(Color.YELLOW);
        ((GuiButton)button).setPressedColor(Color.GREEN);
        addShapeToDraw(button);
    }


    /*******************************************************************************************************************
     * Update Elements
     */
    private void updateTypingBox() {
        // Get index of typing box
        int indexOfTypingBox = indexOfGuiShapeById(FLASHCARD_TYPING_BOX_ID);

        // Get active text box from flashcard
        GuiTextBox flashcardTextBox = getActiveTextBoxOfFlashcard();

        // Set font to match the flashcard
        ((GuiTypingBox)shapesToDraw().get(indexOfTypingBox)).setFont( flashcardTextBox.getFont() );

        // Set bounds to match the flashcard's text box
        int startX = flashcardTextBox.getPoint1X();
        int endX = flashcardTextBox.getPoint2X();
        int startY = flashcardTextBox.getPoint1Y();
        int endY = flashcardTextBox.getPoint2Y();

        shapesToDraw().get(indexOfTypingBox).setBounds(startX, startY, endX, endY);

        // Update the text to reflect the typing
        if (flashcard.isFront()) {
            tempFlashcardFrontText = shapesToDraw().get(indexOfTypingBox).getText();
        } else {
            tempFlashcardBackText = shapesToDraw().get(indexOfTypingBox).getText();
        }
    }

    private void updateAddNewFlashcardButton(JFrame jFrame) {
        // Update
        int startX = 50;
        int endX = 100;
        int startY = 250;
        int endY = 300;

        int indexOfObject = indexOfGuiShapeById(FLIP_EDITING_FLASHCARD_BUTTON_ID);
        shapesToDraw().get(indexOfObject).setBounds(startX, startY, endX, endY);
    }


    /*******************************************************************************************************************
     * Helper Methods
     */
    private GuiTextBox getActiveTextBoxOfFlashcard() {
        GuiTextBox flashcardTextBox = null;

        for (GuiShape shape : shapesToDraw()) {
            if (flashcard.isFront()) {
                if (shape.getShapeId().equals(FlashcardComponent.FLASHCARD_FRONT_TEXT_ID)) {
                    flashcardTextBox = (GuiTextBox) shape;
                    break;
                }
            } else {
                if (shape.getShapeId().equals(FlashcardComponent.FLASHCARD_BACK_TEXT_ID)) {
                    flashcardTextBox = (GuiTextBox) shape;
                    break;
                }
            }
        }

        return flashcardTextBox;
    }


    private void hideFlashcardFlipButton() {
        GuiButton flashcardFlipButton = null;

        for (GuiShape shape : shapesToDraw()) {
            if (shape.getShapeId().equals(FlashcardComponent.FLASHCARD_FLIP_BUTTON_ID)) {
                flashcardFlipButton = (GuiButton) shape;
                break;
            }
        }

        if (flashcardFlipButton != null) {
            flashcardFlipButton.setHoverColor(new Color(0, 0, 0, 0));
            flashcardFlipButton.setPressedColor(new Color(0, 0, 0, 0));
        }
    }


    public void flipCard() {
        this.flashcard.flipCard();

        // Get index of typing box
        int indexOfTypingBox = indexOfGuiShapeById(FLASHCARD_TYPING_BOX_ID);

        // Clear the typing box
        ((GuiTypingBox)shapesToDraw().get(indexOfTypingBox)).reset();

        if (flashcard.isFront()) {
            ((GuiTypingBox)shapesToDraw().get(indexOfTypingBox)).setStringBeforeCursor( tempFlashcardFrontText );
            ((GuiTypingBox)shapesToDraw().get(indexOfTypingBox)).setText( tempFlashcardFrontText );
        } else {
            ((GuiTypingBox)shapesToDraw().get(indexOfTypingBox)).setStringBeforeCursor( tempFlashcardBackText );
            ((GuiTypingBox)shapesToDraw().get(indexOfTypingBox)).setText( tempFlashcardBackText );
        }


    }


}
