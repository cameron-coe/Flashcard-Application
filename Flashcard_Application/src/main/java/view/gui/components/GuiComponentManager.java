package main.java.view.gui.components;

import main.java.model.Deck;
import main.java.model.Flashcard;
import main.java.model.Subject;
import main.java.view.gui.FlashcardApplication;
import main.java.view.gui.shapes.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GuiComponentManager {




    /*******************************************************************************************************************
     * Instance Variables
     */
    private List<GuiShape> shapesToDraw;
    private List<Componentable> activeComponents;
    private FlashcardApplication flashcardApplication;


    /*******************************************************************************************************************
     * Constructor
     */
    public GuiComponentManager(FlashcardApplication flashcardApplication) {
        this.shapesToDraw = new ArrayList<>();
        this.activeComponents = new ArrayList<>();
        this.flashcardApplication = flashcardApplication;
    }


    /*******************************************************************************************************************
     * Getters
     */
    public List<GuiShape> getShapesToDraw() {
        return shapesToDraw;
    }

    public List<Componentable> getActiveComponents() {
        return activeComponents;
    }

    public int getCurrentSelectionPageNumberFromApp() {
        return flashcardApplication.getCurrentSelectionPageNumber();
    }

    public int getMaxPageNumberFromApp() {
        return flashcardApplication.getMaxPageNumber();
    }

    public int getOptionsPerPageFromApp() {
        return flashcardApplication.getOptionsPerPage();
    }

    public int getButtonsPerRowFromApp() {
        return flashcardApplication.getButtonsPerRow();
    }

    public int getNumOfRowsFromApp() {
        return flashcardApplication.getNumOfRows();
    }

    public List<Subject> getListOfSubjectsFromCollection() {
        return flashcardApplication.getListOfSubjectsFromCollection();
    }

    public List<Deck> getListOfDecksFromCurrentSubject() {
        return flashcardApplication.getListOfDecksFromCurrentSubject();
    }


    /*******************************************************************************************************************
     * Setters
     */
    public void setShapesToDraw(List<GuiShape> shapesToDraw) {
        this.shapesToDraw = shapesToDraw;
    }

    public void addShapeToDraw(GuiShape guiShape) {
        this.shapesToDraw.add(guiShape);
    }

    public void setMaxPageNumberInApp(int maxPageNumber) {
        flashcardApplication.setMaxPageNumber(maxPageNumber);
    }


    /*******************************************************************************************************************
     * Update all Components
     */
    public void updateAllActiveComponents(JFrame jFrame) {
        for (Componentable component : activeComponents) {
            component.update(jFrame);
        }
    }


    /*******************************************************************************************************************
     * Clear All Active Components
     */
    public void removeAllComponents() {
        activeComponents = new ArrayList<>();
    }



    /*******************************************************************************************************************
     * Flashcard Component Methods
     */
    public void instantiateFlashcard(Flashcard currentFlashcard){
        FlashcardComponent flashcard = new FlashcardComponent(this);
        flashcard.instantiate(currentFlashcard);
        activeComponents.add(flashcard);
    }


    /*******************************************************************************************************************
     * Page Navigator Component Methods
     */
    public void instantiateSelectionPageNavigator() {
        PageNavigatorComponent pageNavigator = new PageNavigatorComponent(this);
        pageNavigator.instantiate();
        activeComponents.add(pageNavigator);
    }


    /*******************************************************************************************************************
     * Back Button Component Methods
     */
    public void instantiateBackButton() {
        BackButtonComponent backButtonComponent = new BackButtonComponent(this);
        backButtonComponent.instantiate();
        activeComponents.add(backButtonComponent);
    }


    /*******************************************************************************************************************
     * Start Button Component Method
     */
    public void instantiateStartButton() {
        StartButtonComponent startButtonComponent = new StartButtonComponent(this);
        startButtonComponent.instantiate();
        activeComponents.add(startButtonComponent);
    }


    /*******************************************************************************************************************
     * Header Component Methods
     */
    public void instantiateSubjectPageHeader() {
        HeaderComponent headerComponent = new HeaderComponent(this);
        headerComponent.instantiate("Select a Subject");
        activeComponents.add(headerComponent);
    }

    public void instantiateCurrentSubjectPageHeader(Subject subject) {
        HeaderComponent headerComponent = new HeaderComponent(this);
        headerComponent.instantiate(subject.getName());
        activeComponents.add(headerComponent);
    }

    public void instantiateCurrentDeckPageHeader(Deck deck) {
        HeaderComponent headerComponent = new HeaderComponent(this);
        headerComponent.instantiate("All Flashcards in " +  deck.getDeckName());
        activeComponents.add(headerComponent);
    }


    /*******************************************************************************************************************
     * Subject Selection Buttons Component Method
     */
    public void instantiateSubjectSelectionButtons() {
        SubjectSelectionButtonsComponent subjectSelectionButtons = new SubjectSelectionButtonsComponent(this);
        subjectSelectionButtons.instantiate();
        activeComponents.add(subjectSelectionButtons);
    }


    /*******************************************************************************************************************
     * Deck Selection Buttons Component Method
     */
    public void instantiateDeckSelectionButtons() {
        DeckSelectionButtonsComponent deckSelectionButtons = new DeckSelectionButtonsComponent(this);
        deckSelectionButtons.instantiate();
        activeComponents.add(deckSelectionButtons);
    }


    /*******************************************************************************************************************
     * New Subject Button Component Method
     */
    public void instantiateNewSubjectButton() {
        NewSubjectButtonComponent newSubjectButtonComponent = new NewSubjectButtonComponent(this);
        newSubjectButtonComponent.instantiate();
        activeComponents.add(newSubjectButtonComponent);
    }


    /*******************************************************************************************************************
     * New Subject Button Component Method
     */
    public void instantiateNewDeckButton() {
        NewDeckButtonComponent newDeckButtonComponent = new NewDeckButtonComponent(this);
        newDeckButtonComponent.instantiate();
        activeComponents.add(newDeckButtonComponent);
    }


    /*******************************************************************************************************************
     * New Subject Button Component Method
     */
    public void instantiateNewFlashcardButton() {
        NewFlashcardButtonComponent newFlashcardButtonComponent = new NewFlashcardButtonComponent(this);
        newFlashcardButtonComponent.instantiate();
        activeComponents.add(newFlashcardButtonComponent);
    }

    /*******************************************************************************************************************
     * Edit Subject Component
     */
    public void instantiateEditSubjectComponent(Subject subject) {
        EditSubjectComponent editSubjectComponent = new EditSubjectComponent(this, subject);
        editSubjectComponent.instantiate();
        activeComponents.add(editSubjectComponent);
    }

    /*******************************************************************************************************************
     * Add New Subject Button Component
     */
    public void instantiateAddNewSubjectButtonComponent() {
        AddNewSubjectButtonComponent addNewSubjectButtonComponent = new AddNewSubjectButtonComponent(this);
        addNewSubjectButtonComponent.instantiate();
        activeComponents.add(addNewSubjectButtonComponent);
    }

    /*******************************************************************************************************************
     * Edit Deck Component
     */
    public void instantiateEditDeckComponent(Deck deck) {
        EditDeckComponent editDeckComponent = new EditDeckComponent(this, deck);
        editDeckComponent.instantiate();
        activeComponents.add(editDeckComponent);
    }

    /*******************************************************************************************************************
     * Add New Deck Button Component
     */
    public void instantiateAddNewDeckButtonComponent() {
        AddNewDeckButtonComponent addNewDeckButtonComponent = new AddNewDeckButtonComponent(this);
        addNewDeckButtonComponent.instantiate();
        activeComponents.add(addNewDeckButtonComponent);
    }


    /*******************************************************************************************************************
     * Edit Flashcard Component
     */
    public void instantiateEditFlashcardComponent(Flashcard flashcard) {
        EditFlashcardComponent editFlashcardComponent = new EditFlashcardComponent(this, flashcard);
        editFlashcardComponent.instantiate();
        activeComponents.add(editFlashcardComponent);
    }


    /*******************************************************************************************************************
     * Add New Flashcard Button Component
     */
    public void instantiateAddNewFlashcardButtonComponent() {
        AddNewFlashcardButtonComponent addNewFlashcardButtonComponent = new AddNewFlashcardButtonComponent(this);
        addNewFlashcardButtonComponent.instantiate();
        activeComponents.add(addNewFlashcardButtonComponent);
    }


}
