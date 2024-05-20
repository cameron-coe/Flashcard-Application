package main.java.view.gui.pages;

import main.java.model.Deck;
import main.java.model.Flashcard;
import main.java.view.gui.FlashcardApplication;
import main.java.view.gui.components.GuiComponentManager;

public class CurrentDeckPage extends Page{
    /*******************************************************************************************************************
     * Constants
     */
    public final static String PAGE_ID = "current deck page";


    /*******************************************************************************************************************
     * Constructors
     */
    public CurrentDeckPage(GuiPageManager guiPageManager, String stringIndexOfCurrentDeck) {
        super(guiPageManager);
        instantiateCurrentDeckPage(guiPageManager.getGuiComponentManager(), stringIndexOfCurrentDeck);
    }

    public CurrentDeckPage(GuiPageManager guiPageManager, Deck deck) {
        super(guiPageManager);
        instantiateCurrentDeckPage(guiPageManager.getGuiComponentManager(), deck);
    }


    /*******************************************************************************************************************
     * Instantiate Current Deck Page
     */
    public void instantiateCurrentDeckPage(GuiComponentManager guiComponentManager, String stringIndexOfCurrentDeck) {
        try {
            int indexOfDeck = Integer.parseInt(stringIndexOfCurrentDeck);
            FlashcardApplication app = getGuiPageManager().getFlashcardApplication();
            Deck currentDeck = app.getCurrentSubject().getListOfDecks().get(indexOfDeck);

            baseInstantiation(guiComponentManager, currentDeck);

        } catch (NumberFormatException e) {
            System.err.println("Number format exception in FlashcardApplication --> instantiateCurrentDeckPage method");
        }
    }

    public void instantiateCurrentDeckPage(GuiComponentManager guiComponentManager, Deck deck) {
            baseInstantiation(guiComponentManager, deck);
    }

    private void baseInstantiation(GuiComponentManager guiComponentManager, Deck deck) {
        FlashcardApplication app = getGuiPageManager().getFlashcardApplication();
        app.setCurrentDeck(deck);

        getGuiPageManager().setCurrentPage(PAGE_ID);
        app.setNumberOfSelectionOptionsPerPage();
        getGuiPageManager().clearPage();

        // Show flashcards only if there are flashcards in this deck
        if (deck.getFlashcardsInDeck().size() > 0) {
            app.setMaxPageNumber(deck.getFlashcardsInDeck().size() - 1);
            int currentSelectionPage = app.getCurrentSelectionPageNumber();
            Flashcard currentFlashcard = deck.getFlashcardsInDeck().get(currentSelectionPage);
            app.setCurrentFlashcard(currentFlashcard);
            guiComponentManager.instantiateFlashcard(currentFlashcard);
        }

        //Add Elements to the page
        guiComponentManager.instantiateCurrentDeckPageHeader(deck);
        guiComponentManager.instantiateSelectionPageNavigator();
        guiComponentManager.instantiateBackButton();
        guiComponentManager.instantiateNewFlashcardButton();
    }
}
