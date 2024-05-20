package main.java.view.gui.pages;

import main.java.model.Deck;
import main.java.model.Flashcard;
import main.java.model.Subject;
import main.java.view.gui.FlashcardApplication;
import main.java.view.gui.components.GuiComponentManager;
import main.java.view.gui.shapes.GuiShape;

import java.util.ArrayList;
import java.util.List;

public class GuiPageManager {

    /*******************************************************************************************************************
     * Constants
     */
    // TODO -- Maybe move these to the page manager

    public final static String DELETE_SUBJECT_PAGE = "delete subject page";
    public final static String NEW_SUBJECT_PAGE = "new subject page";

    public final static String NEW_DECK_PAGE = "new deck dage";
    public final static String DELETE_DECK_PAGE = "delete deck page";
    public final static String NEW_FLASHCARD_PAGE = "new card page";
    public final static String ALL_FLASHCARDS_IN_DECK_PAGE = "look through all flashcards in deck page";
    public final static String FLASHCARD_PAGE = "flashcard page";
    public final static String RENAME_DECK_PAGE = "rename deck page";
    public final static String EDIT_FLASHCARD_PAGE = "edit flashcard page";
    public final static String QUIZZES_PAGE = "quizzes page";


    /*******************************************************************************************************************
     * Instance Variables
     */
    private GuiComponentManager guiComponentManager;
    private FlashcardApplication flashcardApplication;
    private String currentPage = Homepage.PAGE_ID;


    /*******************************************************************************************************************
     * Constructor
     */
    public GuiPageManager(FlashcardApplication flashcardApplication) {
        this.flashcardApplication = flashcardApplication;
        this.guiComponentManager = new GuiComponentManager(flashcardApplication);
    }




    /*******************************************************************************************************************
     * Getters
     */
    public String getCurrentPage() {
        return currentPage;
    }

    public List<GuiShape> getShapesToDraw() {
        return guiComponentManager.getShapesToDraw();
    }

    public GuiComponentManager getGuiComponentManager() {
        return guiComponentManager;
    }

    public FlashcardApplication getFlashcardApplication() {
        return flashcardApplication;
    }

    /*******************************************************************************************************************
     * setters
     */
    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }


    /*******************************************************************************************************************
     * Clears the GUI's shapes to draw and active components
     */
    public void clearPage() {
        guiComponentManager.setShapesToDraw(new ArrayList<>());
        guiComponentManager.removeAllComponents();
    }


    /*******************************************************************************************************************
     * Homepage Methods
     */
    public void instantiateHomepage() {
        new Homepage(this);
    }


    /*******************************************************************************************************************
     * Instantiate Select Subject Page
     */
    public void instantiateSelectSubjectPage() {
        new SelectSubjectPage(this);
    }


    /*******************************************************************************************************************
     * Instantiate New Subject Page
     */
    public void instantiateNewSubjectPage() {
        new NewSubjectPage(this);
    }


    /*******************************************************************************************************************
     * Instantiate Current Subject Page
     */
    public void instantiateCurrentSubjectPage(String stringIndexOfCurrentSubject) {
        new CurrentSubjectPage(this, stringIndexOfCurrentSubject);
    }

    public void instantiateCurrentSubjectPage(Subject subject) {
        new CurrentSubjectPage(this, subject);
    }


    /*******************************************************************************************************************
     * Instantiate Current Deck Page
     */
    public void instantiateCurrentDeckPage(String stringIndexOfCurrentDeck) {
        new CurrentDeckPage(this, stringIndexOfCurrentDeck);
    }

    public void instantiateCurrentDeckPage(Deck deck) {
        new CurrentDeckPage(this, deck);
    }


    /*******************************************************************************************************************
     * Instantiate New Deck Page
     */
    public void instantiateNewDeckPage() {
        new NewDeckPage(this);
    }


    /*******************************************************************************************************************
     * Instantiate New Flashcard Page
     */
    public void instantiateNewFlashcardPage() {
        new NewFlashcardPage(this);
    }





    /*******************************************************************************************************************
     * Reload Current Page
     */
    public void reloadPage() {
        clearPage();
        if (currentPage.equals(SelectSubjectPage.PAGE_ID)) {
            instantiateSelectSubjectPage();
        }
        else if (currentPage.equals(CurrentSubjectPage.PAGE_ID)) {
            Subject currentSubject = flashcardApplication.getCurrentSubject();
            String stringIndexOfSubject = "" + flashcardApplication.getListOfSubjects().indexOf(currentSubject);
            instantiateCurrentSubjectPage(stringIndexOfSubject);
        }
        else if (currentPage.equals(CurrentDeckPage.PAGE_ID)) {
            Deck currenDeck = flashcardApplication.getCurrentDeck();
            String stringIndexOfDeck = "" + flashcardApplication.getCurrentSubject().getListOfDecks().indexOf(currenDeck);
            instantiateCurrentDeckPage(stringIndexOfDeck);
        } else {
            System.out.println("Page being reloaded not added to reloadPage() method.");
        }
    }
}
