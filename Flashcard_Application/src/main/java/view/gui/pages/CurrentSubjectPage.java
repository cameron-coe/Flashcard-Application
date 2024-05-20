package main.java.view.gui.pages;

import main.java.model.Subject;
import main.java.view.gui.FlashcardApplication;
import main.java.view.gui.components.GuiComponentManager;

public class CurrentSubjectPage extends Page{

    /*******************************************************************************************************************
     * Constants
     */
    public final static String PAGE_ID = "current subject page";


    /*******************************************************************************************************************
     * Constructors
     */
    public CurrentSubjectPage(GuiPageManager guiPageManager, String stringIndexOfCurrentSubject) {
        super(guiPageManager);
        instantiateCurrentSubjectPage(guiPageManager.getGuiComponentManager(), stringIndexOfCurrentSubject);
    }

    public CurrentSubjectPage(GuiPageManager guiPageManager, Subject subject) {
        super(guiPageManager);
        instantiateCurrentSubjectPage(guiPageManager.getGuiComponentManager(), subject);
    }


    /*******************************************************************************************************************
     * Instantiate Current Subject Page
     */
    public void instantiateCurrentSubjectPage(GuiComponentManager guiComponentManager, String stringIndexOfCurrentSubject) {
        try {
            int indexOfSubject = Integer.parseInt(stringIndexOfCurrentSubject);

            FlashcardApplication app = getGuiPageManager().getFlashcardApplication();

            Subject currentSubject = app.getListOfSubjects().get(indexOfSubject);
            app.setCurrentSubject(currentSubject);

            baseInstantiation(guiComponentManager, currentSubject);
        } catch (NumberFormatException e) {
            System.err.println("Number format exception in CurrentSubjectPage --> instantiateCurrentSubjectPage method");
        }
    }

    public void instantiateCurrentSubjectPage(GuiComponentManager guiComponentManager, Subject subject) {
            FlashcardApplication app = getGuiPageManager().getFlashcardApplication();

            app.setCurrentSubject(subject);

            baseInstantiation(guiComponentManager, subject);
    }

    private void baseInstantiation (GuiComponentManager guiComponentManager, Subject subject) {
        FlashcardApplication app = getGuiPageManager().getFlashcardApplication();
        getGuiPageManager().setCurrentPage(PAGE_ID);
        app.setNumberOfSelectionOptionsPerPage();

        getGuiPageManager().clearPage();

        //Add Elements to the page
        guiComponentManager.instantiateCurrentSubjectPageHeader(subject);
        guiComponentManager.instantiateDeckSelectionButtons();
        guiComponentManager.instantiateSelectionPageNavigator();
        guiComponentManager.instantiateBackButton();
        guiComponentManager.instantiateNewDeckButton();
    }
}
