package main.java.view.gui;


import main.java.CollectionManager;
import main.java.model.Deck;
import main.java.model.Flashcard;
import main.java.model.Subject;
import main.java.view.gui.pages.GuiPageManager;
import main.java.view.gui.shapes.GuiShape;
import main.java.view.gui.shapes.GuiTypingBox;

import javax.swing.*;
import java.util.List;

/**
 * TODO: Create a new FLashcard page
 * TODO: Put Selection Page Buttons on the sides of the window
 *
 * TODO: Change xScaleText to xScale for all objects
 * TODO: Select Flashcards
 * TODO: Make it so you can draw on the flashcard
 * TODO: Make an isCenteredVertically Boolean on text
 * TODO: make a show lines option for flashcards
 * TODO: make another component that edits the formatting of the flashcard in the new flashcard page
 * TODO: make ellipses on the last line of text boxes instead of deleting the whole word
 * TODO: Make placeholder text & a placeholder text variable for the typing box
 * TODO: make another component that edits the quiz type on the new deck page (so it cna be omitted on the edit deck page)
 *
 */

public class FlashcardApplication {

    /*******************************************************************************************************************
     * Constants
     */


    private final static int  SHORT_QUIZ = 5;
    private final static int  MID_SIZED_QUIZ = 15;


    /*******************************************************************************************************************
     * Instance Variables
     */
    private Gui gui;
    // private GuiComponentManager guiComponentManager;
    private GuiPageManager guiPageManager;
    private ButtonEngine buttonEngine;
    private CollectionManager collectionManager;
    private ButtonActionController buttonActionController;

    // Variables that control the number of Subjects/ Decks that show up per page
    private int buttonsPerRow = 2;
    private int numOfRows = 3;
    private int optionsPerPage = 1;
    private int maxPageNumber = -1;
    private int currentSelectionPageNumber = 0;

    private int lastSubjectSelectionPageNumber = 0;
    private int lastDeckSelectionPageNumber = 0;



    /*******************************************************************************************************************
     * Constructor
     */
    public FlashcardApplication(Gui gui) {
        this.gui = gui;
        //this.guiComponentManager = new GuiComponentManager(this);
        this.guiPageManager = new GuiPageManager(this);
        this.buttonEngine = new ButtonEngine();
        this.collectionManager = new CollectionManager();
        this.buttonActionController = new ButtonActionController(this, collectionManager);


        //TODO: Remove Later
        Subject s1 = new Subject("Subject 1");

        Deck deck1 = new Deck("Deck 1", 1);
        deck1.addFlashcard(new Flashcard("This is the Front", "This is the Back"));
        deck1.addFlashcard(new Flashcard("Knock, knock", "There aren't enough sides for a proper knock knock joke..."));

        s1.addDeck(deck1);
        s1.addDeck(new Deck("Deck 2", 1));
        collectionManager.addSubject(s1);
        Subject s2 = new Subject("Subject B");
        collectionManager.addSubject(s2);
        Subject s3 = new Subject("Advanced Neuroscience Terms");
        s3.addDeck(new Deck("Deck 0", 1));
        s3.addDeck(new Deck("Deck 1", 1));
        s3.addDeck(new Deck("Deck 2", 1));
        s3.addDeck(new Deck("Deck 3", 1));
        s3.addDeck(new Deck("Deck 4", 1));
        s3.addDeck(new Deck("Deck 5", 1));
        s3.addDeck(new Deck("Deck 6", 1));
        s3.addDeck(new Deck("Deck 7", 1));
        s3.addDeck(new Deck("Deck 8", 1));
        s3.addDeck(new Deck("Deck 9", 1));
        s3.addDeck(new Deck("Deck 10", 1));
        s3.addDeck(new Deck("Deck 11", 1));
        s3.addDeck(new Deck("Deck 12", 1));
        s3.addDeck(new Deck("Deck 13", 1));
        collectionManager.addSubject(s3);
        Subject s4 = new Subject("Marine Biology");
        collectionManager.addSubject(s4);
        collectionManager.addSubject(new Subject("Subject 5"));
        collectionManager.addSubject(new Subject("Subject 6"));
        collectionManager.addSubject(new Subject("Subject 7"));
    }


    /*******************************************************************************************************************
     * Main Method
     */
    public static void main(String[] args) {
        Gui gui = new Gui();
        FlashcardApplication flashcardApplication = new FlashcardApplication(gui);
        flashcardApplication.run();
    }

    /*******************************************************************************************************************
     * Runtime Method
     */
    public void run () {
        //
    }


    /*******************************************************************************************************************
     * Getters
     */
    public List<GuiShape> getShapesToDrawFromGuiElements() {
        return guiPageManager.getShapesToDraw();
    }

    public GuiPageManager getGuiPageManager() {
        return guiPageManager;
    }

    public int getOptionsPerPage() {
        return this.optionsPerPage;
    }

    public int getCurrentSelectionPageNumber() {
        return this.currentSelectionPageNumber;
    }

    public int getButtonsPerRow() {
        return buttonsPerRow;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public int getMaxPageNumber() {
        return maxPageNumber;
    }

    public String getCurrentPage() {
        return guiPageManager.getCurrentPage();
    }

    public int getLastSubjectSelectionPageNumber() {
        return lastSubjectSelectionPageNumber;
    }

    public int getLastDeckSelectionPageNumber() {
        return lastDeckSelectionPageNumber;
    }

    public List<Subject> getListOfSubjectsFromCollection() {
        return collectionManager.getSubjectList();
    }

    public List<Deck> getListOfDecksFromCurrentSubject() {
        return collectionManager.getCurrentSubject().getListOfDecks();
    }

    public List<Subject> getListOfSubjects() {
        return collectionManager.getSubjectList();
    }

    public Subject getCurrentSubject() {
        return collectionManager.getCurrentSubject();
    }
    public Deck getCurrentDeck() {
        return collectionManager.getCurrentDeck();
    }

    public Flashcard getCurrentFlashcard() {
        return collectionManager.getCurrentFlashcard();
    }



    /*******************************************************************************************************************
     * Setters
     */
    public void setNumberOfSelectionOptionsPerPage() {
        this.optionsPerPage = (this.buttonsPerRow * this.numOfRows);
    }

    public void setMaxPageNumber(int maxPageNumber) {
        this.maxPageNumber = maxPageNumber;
    }

    public void selectionPageUp() {
        if (this.currentSelectionPageNumber < this.maxPageNumber) {
            this.currentSelectionPageNumber += 1;
            guiPageManager.reloadPage();
        }
    }

    public void selectionPageDown() {
        if (this.currentSelectionPageNumber > 0) {
            this.currentSelectionPageNumber -= 1;
            guiPageManager.reloadPage();
        }
    }

    public void setLastSubjectSelectionPageNumber(int lastSubjectSelectionPageNumber) {
        this.lastSubjectSelectionPageNumber = lastSubjectSelectionPageNumber;
    }

    public void setLastDeckSelectionPageNumber(int lastDeckSelectionPageNumber) {
        this.lastDeckSelectionPageNumber = lastDeckSelectionPageNumber;
    }

    public void setCurrentSelectionPageNumber(int currentSelectionPageNumber) {
        this.currentSelectionPageNumber = currentSelectionPageNumber;
    }

    public void setCurrentSubject (Subject subject) {
        collectionManager.setCurrentSubject(subject);
    }

    public void setCurrentDeck (Deck deck) {
        collectionManager.setCurrentDeck(deck);
    }

    public void setCurrentFlashcard (Flashcard flashcard) {
        collectionManager.setCurrentFlashcard(flashcard);
    }

    public void addSubjectToCollectionManager(Subject subject) {
        collectionManager.addSubject(subject);
    }

    public void addDeckToCurrentSubject(Deck deck) {
        collectionManager.getCurrentSubject().addDeck(deck);
    }

    public void addFlashcardToCurrentDeck(Flashcard flashcard) {
        collectionManager.getCurrentDeck().addFlashcard(flashcard);
    }


    /*******************************************************************************************************************
     * Subscribes Event Actions to Listeners
     */
    public void runtimeStartEvent(JFrame jFrame) {
        guiPageManager.instantiateHomepage();
        updateAllGuiElements(jFrame);
        gui.repaint();
        // TODO LATER: collectionManager.loadData();
        System.out.println("RUNTIME START EVENT >>> " + guiPageManager.getShapesToDraw().size());
    }

    public void windowSizeChangeEvent(JFrame jFrame) {
        updateAllGuiElements(jFrame);
        gui.repaint();
        System.out.println("WINDOW SIZE CHANGE EVENT >>> " + guiPageManager.getShapesToDraw().size());
    }

    public void mouseMoveEvent(JFrame jFrame) {
        boolean isUpdate = buttonEngine.updateButtonsWhenMouseGoesInOrOut(gui, guiPageManager.getGuiComponentManager());
        if (isUpdate) {
            updateAllGuiElements(jFrame);
            gui.repaint();
        }
    }

    public void mousePressedEvent(JFrame jFrame) {
        boolean isAButtonPressed = buttonEngine.mouseDownOnButton(guiPageManager.getGuiComponentManager());
        if (isAButtonPressed) {
            gui.repaint();
        }

        GuiTypingBox typingBox = getTypingBox();
        if (typingBox != null) {
            typingBox.mousePressTypingBox(gui.getMouseX(), gui.getMouseY());
            gui.repaint();
        }

        List<GuiShape> shapesToDraw = updateAllGuiElements(jFrame);
        System.out.println("MOUSE PRESSED EVENT >>> " + shapesToDraw.size());
    }

    public void mouseReleasedEvent(JFrame jFrame) {
        String result = buttonEngine.mouseReleasedOnButton(gui);

        if (result != null) {
            buttonActionController.buttonActions(result);

            // Makes sure all shapes are prepped to be redrawn
            updateAllGuiElements(jFrame);

            // Checks if mouse is over a newly-loaded button
            buttonEngine.updateButtonsWhenMouseGoesInOrOut(gui, guiPageManager.getGuiComponentManager());

            updateAllGuiElements(jFrame);
            gui.repaint();
        }

        System.out.println("MOUSE RELEASED EVENT >>> " + result);
    }

    public void keyPressedEvent(JFrame jFrame, String keyChar, int keyCode) {
        GuiTypingBox typingBox = getTypingBox();

        if (typingBox != null) {
            boolean isSpecialActionTaken = typingBox.keyPressedActions(keyChar, keyCode);
            if (isSpecialActionTaken) {
                updateAllGuiElements(jFrame);
                gui.repaint();
            }

            System.out.println("KEY PRESSED EVENT >>> " + keyChar + ": " + keyCode);
        }
    }


    /*******************************************************************************************************************
     * Update Method - does not refresh gui elements
     */
    public List<GuiShape> updateAllGuiElements(JFrame jFrame) {
        guiPageManager.getGuiComponentManager().updateAllActiveComponents(jFrame);
        return guiPageManager.getGuiComponentManager().getShapesToDraw();
    }


    /*******************************************************************************************************************
     * Get Gui Typing Box
     */
    private GuiTypingBox getTypingBox() {
        for(GuiShape shape : guiPageManager.getShapesToDraw()){
            if(shape instanceof GuiTypingBox) {
                return (GuiTypingBox) shape;
            }
        }
        return null;
    }











}
