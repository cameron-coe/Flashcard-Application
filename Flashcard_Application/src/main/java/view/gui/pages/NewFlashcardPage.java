package main.java.view.gui.pages;

import main.java.model.Deck;
import main.java.model.Flashcard;
import main.java.view.gui.components.GuiComponentManager;

public class NewFlashcardPage extends Page {

    /*******************************************************************************************************************
     * Constants
     */
    public final static String PAGE_ID = "newFlashcardPage";


    /*******************************************************************************************************************
     * Constructor
     */
    public NewFlashcardPage(GuiPageManager guiPageManager) {
        super(guiPageManager);
        instantiateNewFlashcardPage(guiPageManager.getGuiComponentManager());
    }


    /*******************************************************************************************************************
     * Instantiate New Flashcard Page
     */
    public void instantiateNewFlashcardPage(GuiComponentManager guiComponentManager) {
        getGuiPageManager().setCurrentPage(PAGE_ID);
        guiComponentManager.instantiateBackButton();
        //TODO: make another component that edits the formatting of the flashcard

        Flashcard newFlashcard = new Flashcard("", "");
        guiComponentManager.instantiateFlashcard(newFlashcard);
        guiComponentManager.instantiateEditFlashcardComponent(newFlashcard);

        guiComponentManager.instantiateAddNewFlashcardButtonComponent();
    }
}
