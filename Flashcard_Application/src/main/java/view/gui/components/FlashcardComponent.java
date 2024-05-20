package main.java.view.gui.components;

import main.java.model.Flashcard;
import main.java.view.gui.shapes.*;

import javax.swing.*;
import java.awt.*;

public class FlashcardComponent extends Component implements Componentable{

    /*******************************************************************************************************************
     * Constants
     */
    public static final String FLASHCARD_FLIP_BUTTON_ID = "flashcardFlipButton";
    private final String FLASHCARD_BASE_ID = "flashcardBase";
    private final String FLASHCARD_BORDER_ID = "flashcardBorder";
    private final String FLASHCARD_HEADER_ID = "flashcardQuestionNumber";
    public static final String FLASHCARD_FRONT_TEXT_ID = "flashcardFrontText";
    private final String FLASHCARD_FRONT_TEXT_LINES_ID = "flashcardFrontTextLines";
    public static final String FLASHCARD_BACK_TEXT_ID = "flashcardBackTextLines";


    /*******************************************************************************************************************
     * Instance Variables
     */
    Flashcard flashcard;


    /*******************************************************************************************************************
     * Constructor
     */
    public FlashcardComponent(GuiComponentManager guiComponentManager) {
        super(guiComponentManager);

    }


    /*******************************************************************************************************************
     * Getter
     */
    public Flashcard getFlashcard() {
        return flashcard;
    }

    /*******************************************************************************************************************
     * Instantiate FlashcardComponent Group
     */
    public void instantiate(Flashcard currentFlashcard) {
        // Instantiate
        this.flashcard = currentFlashcard;

        instantiateFlashcardBase(FLASHCARD_BASE_ID);
        instantiateFlashcardFlipButton(FLASHCARD_FLIP_BUTTON_ID);
        instantiateFlashcardHeader(FLASHCARD_HEADER_ID);
        instantiateFlashcardTextLines(FLASHCARD_FRONT_TEXT_LINES_ID);
        instantiateFlashcardBorder(FLASHCARD_BORDER_ID);
        instantiateFrontCardText(FLASHCARD_FRONT_TEXT_ID);
        instantiateBackCardText(FLASHCARD_BACK_TEXT_ID);
    }


    /*******************************************************************************************************************
     * Update FlashcardComponent Group
     */
    @Override
    public void update(JFrame jFrame) {
        int indexOfFlashcardBase = updateFlashcardBase(FLASHCARD_BASE_ID, jFrame);
        GuiShape flashcardBase = shapesToDraw().get(indexOfFlashcardBase);

        updateFlashcardFlipButton(FLASHCARD_FLIP_BUTTON_ID, flashcardBase);
        updateFlashcardHeader(FLASHCARD_HEADER_ID, flashcardBase, jFrame);
        updateFlashcardBorder(FLASHCARD_BORDER_ID, flashcardBase);
        updateFlashcardTextLines(FLASHCARD_FRONT_TEXT_LINES_ID, flashcardBase, jFrame);
        updateFlashcardFrontText(FLASHCARD_FRONT_TEXT_ID, flashcardBase, jFrame);
        updateFlashcardBackText(FLASHCARD_BACK_TEXT_ID, flashcardBase, jFrame);
    }


    /*******************************************************************************************************************
     * Instantiate FlashcardComponent Elements
     */
    private void instantiateFlashcardBase(String id) {
        GuiShape base = new GuiRoundedRectangle(id, 0, 0, 0, 0, 25);
        base.setFillColor(Color.WHITE);
        base.noOutline();
        addShapeToDraw(base);
    }

    private void instantiateFlashcardFlipButton(String id) {
        GuiShape flashcardBaseButton = new GuiButton(id, 0, 0, 0, 0, 25);

        flashcardBaseButton.setFillColor(Color.WHITE);
        flashcardBaseButton.noOutline();
        ((GuiButton)flashcardBaseButton).setHoverColor(new Color(0,0,0, 5));
        ((GuiButton)flashcardBaseButton).setPressedColor(new Color(0,0,0, 15));

        addShapeToDraw(flashcardBaseButton);
    }

    public void instantiateFlashcardHeader(String id) {
        String headerText = "For ";
        headerText += flashcard.getPoints();
        headerText += " Point" + (flashcard.getPoints() > 1 ? "s" : "");
        GuiShape cardHeader = new GuiTextBox(id, headerText, 0, 0, 0, 0);
        cardHeader.setFillColor(Color.BLACK);
        addShapeToDraw(cardHeader);
    }

    private void instantiateFlashcardBorder(String id) {
        GuiShape flashcardBase = new GuiRoundedRectangle(id, 0, 0, 0, 0, 0);
        flashcardBase.setOutlineColor(Color.ORANGE);
        flashcardBase.noFill();
        addShapeToDraw(flashcardBase);
    }

    private void instantiateFlashcardTextLines(String id) {
        GuiShape frontTextLines = new GuiTextBoxLines(id, 0, 0, 0, 0);
        frontTextLines.setOutlineColor(new Color(0,0, 255, 100));
        frontTextLines.setOutlineWidth(2);
        addShapeToDraw(frontTextLines);
    }

    private void instantiateFrontCardText(String id) {
        GuiShape frontText = new GuiTextBox(id, flashcard.getFront(), 0, 0, 0, 0);
        frontText.setFillColor(Color.BLACK);
        addShapeToDraw(frontText);

        int indexOfText = indexOfGuiShapeById(id);
    }

    private void instantiateBackCardText(String id) {
        GuiShape frontText = new GuiTextBox(id, flashcard.getBack(), 0, 0, 0, 0);
        frontText.setFillColor(Color.BLACK);
        addShapeToDraw(frontText);

        int indexOfText = indexOfGuiShapeById(id);
    }


    /*******************************************************************************************************************
     * Update FlashcardComponent Elements
     */
    public int updateFlashcardBase(String id, JFrame jFrame) {
        int cardHeight = jFrame.getHeight() - 200;
        int cardWidth = (cardHeight * 5) / 3;

        int cardStartX = (jFrame.getWidth() - cardWidth) / 2;
        int cardEndX = cardStartX + cardWidth;
        int cardStartY = 100;
        cardStartY += 20; // Moves card down
        int cardEndY = jFrame.getHeight() - 100;

        // Update
        int indexOfFlashcardBase = indexOfGuiShapeById(id);
        shapesToDraw().get(indexOfFlashcardBase).setBounds(cardStartX, cardStartY, cardEndX, cardEndY);

        return indexOfFlashcardBase;
    }

    public void updateFlashcardFlipButton(String id, GuiShape flashcardBase) {
        int arc = flashcardBase.getArc();

        int startX = flashcardBase.getPoint1X();
        int startY = flashcardBase.getPoint1Y();
        int endX = flashcardBase.getPoint2X();
        int endY = flashcardBase.getPoint2Y();

        int indexOfBorder = indexOfGuiShapeById(id);
        shapesToDraw().get(indexOfBorder).setBounds(startX, startY, endX, endY);
        shapesToDraw().get(indexOfBorder).setArc(arc);
    }

    public void updateFlashcardHeader(String id, GuiShape flashcardBase, JFrame jFrame) {
        double flashcardBaseWidth = flashcardBase.getPoint2X() - flashcardBase.getPoint1X();
        double marginWidth = flashcardBaseWidth * 0.02;

        int startX = (int) (flashcardBase.getPoint1X() + marginWidth);
        int startY = (int) (flashcardBase.getPoint1Y() + (marginWidth * 3));
        int endX = (int) (flashcardBase.getPoint2X() - marginWidth);
        int endY = (int) (flashcardBase.getPoint2Y() - marginWidth);

        // Hide if not the front
        if (!flashcard.isFront()) {
            startY += jFrame.getHeight();
            endY += jFrame.getHeight();
        }

        // Set Borders
        int indexOfHeader = indexOfGuiShapeById(id);
        shapesToDraw().get(indexOfHeader).setBounds(startX, startY, endX, endY);

        // Set font size based on flashcard width
        int fontSize = flashcardBase.getPoint2X() - flashcardBase.getPoint1X();
        fontSize = fontSize / 15;
        ((GuiTextBox)shapesToDraw().get(indexOfHeader)).setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
    }

    public void updateFlashcardBorder(String id, GuiShape flashcardBase) {
        double flashcardBaseWidth = flashcardBase.getPoint2X() - flashcardBase.getPoint1X();
        double marginWidth = flashcardBaseWidth * 0.02;
        int arc = (int) (flashcardBase.getArc() * 0.9);

        int startX = (int) (flashcardBase.getPoint1X() + marginWidth);
        int startY = (int) (flashcardBase.getPoint1Y() + marginWidth);
        int endX = (int) (flashcardBase.getPoint2X() - marginWidth);
        int endY = (int) (flashcardBase.getPoint2Y() - marginWidth);

        int indexOfBorder = indexOfGuiShapeById(id);
        shapesToDraw().get(indexOfBorder).setBounds(startX, startY, endX, endY);
        shapesToDraw().get(indexOfBorder).setArc(arc);
    }

    public void updateFlashcardTextLines(String id, GuiShape flashcardBase, JFrame jFrame) {
        // update to match the front text settings
        updateFlashcardFrontText(id, flashcardBase, jFrame);
    }

    public void updateFlashcardFrontText(String id, GuiShape flashcardBase, JFrame jFrame) {
        double flashcardBaseWidth = flashcardBase.getPoint2X() - flashcardBase.getPoint1X();
        double marginWidth = flashcardBaseWidth * 0.02;

        int startX = (int) (flashcardBase.getPoint1X() + (marginWidth * 2));
        int startY = (int) (flashcardBase.getPoint1Y() + (marginWidth * 8));
        int endX = (int) (flashcardBase.getPoint2X() - (marginWidth * 2));
        int endY = (int) (flashcardBase.getPoint2Y() - (marginWidth * 1));

        // Hide if not the front
        if (!flashcard.isFront()) {
            startY += jFrame.getHeight();
            endY += jFrame.getHeight();
        }

        // Set bounds
        int indexOfText = indexOfGuiShapeById(id);
        shapesToDraw().get(indexOfText).setBounds(startX, startY, endX, endY);

        // Set font size based on flashcard width
        int fontSize = flashcardBase.getPoint2X() - flashcardBase.getPoint1X();
        fontSize = fontSize / 25;
        ((GuiTextBox)shapesToDraw().get(indexOfText)).setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
    }

    public void updateFlashcardBackText(String id, GuiShape flashcardBase, JFrame jFrame) {
        double flashcardBaseWidth = flashcardBase.getPoint2X() - flashcardBase.getPoint1X();
        double marginWidth = flashcardBaseWidth * 0.02;

        int startX = (int) (flashcardBase.getPoint1X() + (marginWidth * 2));
        int startY = (int) (flashcardBase.getPoint1Y() + (marginWidth * 8));
        int endX = (int) (flashcardBase.getPoint2X() - (marginWidth * 2));
        int endY = (int) (flashcardBase.getPoint2Y() - (marginWidth * 1));

        // Hide if the Front of the card is shown
        if (flashcard.isFront()) {
            startY += jFrame.getHeight();
            endY += jFrame.getHeight();
        }

        // Set bounds
        int indexOfText = indexOfGuiShapeById(id);
        shapesToDraw().get(indexOfText).setBounds(startX, startY, endX, endY);

        // Set font size based on flashcard width
        int fontSize = flashcardBase.getPoint2X() - flashcardBase.getPoint1X();
        fontSize = fontSize / 15;
        ((GuiTextBox)shapesToDraw().get(indexOfText)).setFont(new Font("Times New Roman", Font.BOLD, fontSize));
    }





}
