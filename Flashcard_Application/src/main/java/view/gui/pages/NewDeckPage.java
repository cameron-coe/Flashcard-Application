package main.java.view.gui.pages;

import main.java.model.Deck;
import main.java.model.Subject;
import main.java.view.gui.components.GuiComponentManager;

public class NewDeckPage extends Page {
    /*******************************************************************************************************************
     * Constants
     */
    public final static String PAGE_ID = "newDeckPage";


    /*******************************************************************************************************************
     * Constructor
     */
    public NewDeckPage(GuiPageManager guiPageManager) {
        super(guiPageManager);
        instantiateNewDeckPage(guiPageManager.getGuiComponentManager());
    }


    /*******************************************************************************************************************
     * Instantiate New Deck Page
     */
    public void instantiateNewDeckPage(GuiComponentManager guiComponentManager) {
        getGuiPageManager().setCurrentPage(PAGE_ID);
        guiComponentManager.instantiateBackButton();
        //TODO: make another component that edits the quiz type
        guiComponentManager.instantiateEditDeckComponent(new Deck("Test String", Deck.MULTIPLE_CHOICE_QUIZ));
        guiComponentManager.instantiateAddNewDeckButtonComponent();
    }
}
