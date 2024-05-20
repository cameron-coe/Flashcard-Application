package main.java.view.gui.components;

import main.java.model.Deck;
import main.java.view.gui.shapes.GuiButton;
import main.java.view.gui.shapes.GuiShape;
import main.java.view.gui.shapes.GuiTextBox;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DeckSelectionButtonsComponent  extends SelectionButtonsComponent implements Componentable{
    /*******************************************************************************************************************
     * Constants
     */
    public static final String SELECT_DECK_BUTTON_ID = "selectDeckButton";
    private static final String SELECT_DECK_BUTTON_TEXT_ID = "selectDeckButtonText";


    /*******************************************************************************************************************
     * Constructor
     */
    public DeckSelectionButtonsComponent(GuiComponentManager guiComponentManager) {
        super(guiComponentManager);
    }


    /*******************************************************************************************************************
     * Instantiate Component
     */
    public void instantiate() {
        instantiateDeckSelectionButtons(getListOfDecksFromCurrentSubject());
    }


    /*******************************************************************************************************************
     * Update Component
     */
    @Override
    public void update(JFrame jFrame) {
        updateDeckSelectionButtons(jFrame, getListOfDecksFromCurrentSubject());
    }


    /*******************************************************************************************************************
     * Instantiate Elements
     */
    public void instantiateDeckSelectionButtons(List<Deck> decks) {
        int selectionStart = createSelectionButtonStartPoint();
        int selectionEnd = createSelectionButtonEndPoint(selectionStart, decks.size());

        for(int i = selectionStart; i < selectionEnd; i++) {
            String uniqueButtonId = SELECT_DECK_BUTTON_ID + i;
            String uniqueButtonTextId = SELECT_DECK_BUTTON_TEXT_ID + i;
            instantiateDeckButton(uniqueButtonId);
            instantiateDeckButtonText(uniqueButtonTextId, decks.get(i));
        }
    }

    private void instantiateDeckButton(String id) {
        GuiShape button = new GuiButton(id, 0, 0, 0, 0, 25);
        button.setFillColor(Color.BLUE);
        ((GuiButton)button).setHoverColor(Color.YELLOW);
        ((GuiButton)button).setPressedColor(Color.GREEN);
        addShapeToDraw(button);
    }

    private void instantiateDeckButtonText(String id, Deck deck) {
        GuiShape text = new GuiTextBox(id, deck.getDeckName(), 0, 0, 0, 0);
        text.setFillColor(Color.BLACK);
        addShapeToDraw(text);
    }

    /*******************************************************************************************************************
     * Update Elements
     */
    private void updateDeckSelectionButtons(JFrame jFrame, List<Deck> decks) {
        int selectionStart = createSelectionButtonStartPoint();
        int selectionEnd = createSelectionButtonEndPoint(selectionStart, decks.size());

        for(int i = selectionStart; i < selectionEnd; i++) {
            updateDeckButton(jFrame, i);
            updateDeckButtonText(i);

            setNumberOfSelectionPages(decks.size());
        }
    }

    private void updateDeckButton(JFrame jFrame, int index) {
        String uniqueId = SELECT_DECK_BUTTON_ID + index;
        formatSelectionButtons(jFrame, index, uniqueId);
    }

    private void updateDeckButtonText(int index) {
        String baseButtonId = SELECT_DECK_BUTTON_ID + index;
        String uniqueButtonTextId = SELECT_DECK_BUTTON_TEXT_ID + index;

        formatSelectionButtonText(baseButtonId, uniqueButtonTextId);
    }


}
