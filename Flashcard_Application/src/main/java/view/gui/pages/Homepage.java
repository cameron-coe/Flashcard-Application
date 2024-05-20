package main.java.view.gui.pages;

import main.java.view.gui.components.GuiComponentManager;

public class Homepage extends Page{
    /*******************************************************************************************************************
     * Constants
     */
    public final static String PAGE_ID = "homepage";


    /*******************************************************************************************************************
     * Constructor
     */
    public Homepage(GuiPageManager guiPageManager) {
        super(guiPageManager);

        instantiateHomepage(guiPageManager.getGuiComponentManager());
    }


    /*******************************************************************************************************************
     * Instantiate Homepage
     */
    public void instantiateHomepage(GuiComponentManager guiComponentManager) {
        getGuiPageManager().setCurrentPage(PAGE_ID);
        guiComponentManager.instantiateStartButton();
    }
}
