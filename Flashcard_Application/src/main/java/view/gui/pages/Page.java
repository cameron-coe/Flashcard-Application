package main.java.view.gui.pages;

public abstract class Page {

    /*******************************************************************************************************************
     * Instance Variables
     */
    private GuiPageManager guiPageManager;


    /*******************************************************************************************************************
     * Constructor
     */
    public Page(GuiPageManager guiPageManager) {
        this.guiPageManager = guiPageManager;
    }


    /*******************************************************************************************************************
     * Getters
     */
    public GuiPageManager getGuiPageManager() {
        return guiPageManager;
    }
}
