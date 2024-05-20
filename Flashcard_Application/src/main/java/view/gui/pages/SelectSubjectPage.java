package main.java.view.gui.pages;

import main.java.view.gui.FlashcardApplication;
import main.java.view.gui.components.GuiComponentManager;

public class SelectSubjectPage extends Page{
    /*******************************************************************************************************************
     * Constants
     */
    public final static String PAGE_ID = "select subject page";


    /*******************************************************************************************************************
     * Constructor
     */
    public SelectSubjectPage(GuiPageManager guiPageManager) {
        super(guiPageManager);

        instantiateSelectSubjectPage(guiPageManager.getGuiComponentManager());
    }


    /*******************************************************************************************************************
     * Instantiate Subject Page Methods
     */
    public void instantiateSelectSubjectPage(GuiComponentManager guiComponentManager) {
        getGuiPageManager().setCurrentPage(PAGE_ID);
        FlashcardApplication app = getGuiPageManager().getFlashcardApplication();

        app.setNumberOfSelectionOptionsPerPage();
        getGuiPageManager().clearPage();
        guiComponentManager.instantiateSubjectPageHeader();
        guiComponentManager.instantiateSubjectSelectionButtons();
        guiComponentManager.instantiateSelectionPageNavigator();
        guiComponentManager.instantiateBackButton();
        guiComponentManager.instantiateNewSubjectButton();

    }
}
