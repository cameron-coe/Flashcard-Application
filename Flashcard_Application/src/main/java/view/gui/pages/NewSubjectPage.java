package main.java.view.gui.pages;

import main.java.model.Subject;
import main.java.view.gui.components.GuiComponentManager;

public class NewSubjectPage extends Page {
    /*******************************************************************************************************************
     * Constants
     */
    public final static String PAGE_ID = "newSubjectPage";


    /*******************************************************************************************************************
     * Constructor
     */
    public NewSubjectPage(GuiPageManager guiPageManager) {
        super(guiPageManager);
        instantiateNewSubjectPage(guiPageManager.getGuiComponentManager());
    }

    /*******************************************************************************************************************
     * Instantiate New Subject Page
     */
    public void instantiateNewSubjectPage(GuiComponentManager guiComponentManager) {
        getGuiPageManager().setCurrentPage(PAGE_ID);
        guiComponentManager.instantiateBackButton();
        guiComponentManager.instantiateEditSubjectComponent(new Subject("Test String"));
        guiComponentManager.instantiateAddNewSubjectButtonComponent();
    }
}
