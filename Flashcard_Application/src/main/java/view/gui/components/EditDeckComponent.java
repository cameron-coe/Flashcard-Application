package main.java.view.gui.components;

import main.java.model.Deck;
import main.java.view.gui.shapes.GuiRoundedRectangle;
import main.java.view.gui.shapes.GuiShape;
import main.java.view.gui.shapes.GuiTypingBox;

import javax.swing.*;
import java.awt.*;

public class EditDeckComponent extends Component implements Componentable {
    /*******************************************************************************************************************
     * Constants
     */
    private static final String BACKGROUND_ID = "editDeckBackgroundId";
    private static final String DECK_TYPING_BOX_ID = "deckTypingBoxId";


    /*******************************************************************************************************************
     * Instance Variables
     */
    private Deck deck;


    /*******************************************************************************************************************
     * Constructor
     */
    public EditDeckComponent(GuiComponentManager guiComponentManager, Deck deck) {
        super(guiComponentManager);
        this.deck = deck;
    }


    /*******************************************************************************************************************
     * Instantiate Edit Deck Component
     */
    public void instantiate() {
        instantiateBase(BACKGROUND_ID);
        instantiateTypingBox(DECK_TYPING_BOX_ID, deck.getDeckName());
    }


    /*******************************************************************************************************************
     * Update Edit Deck Component
     */
    @Override
    public void update(JFrame jFrame) {
        updateBase(BACKGROUND_ID, jFrame);
        updateTypingBox(DECK_TYPING_BOX_ID, jFrame);

        //Set the subject's name to be the text on the typing box
        int indexOfTypingBox = indexOfGuiShapeById(DECK_TYPING_BOX_ID);
        this.deck.setDeckName(shapesToDraw().get(indexOfTypingBox).getText());
    }


    /*******************************************************************************************************************
     * Getter
     */
    public Deck getDeck() {
        return deck;
    }

    /*******************************************************************************************************************
     * Setter
     */
    public void setDeckQuizType(int quizType) {
        deck.setQuizType(quizType);
    }


    /*******************************************************************************************************************
     * Instantiate Elements
     */
    private void instantiateBase(String id) {
        GuiShape base = new GuiRoundedRectangle(id, 0, 0, 0, 0, 25);
        base.setFillColor(Color.BLUE);
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
