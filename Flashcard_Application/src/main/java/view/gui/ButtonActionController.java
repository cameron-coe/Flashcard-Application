package main.java.view.gui;

import main.java.CollectionManager;
import main.java.model.Deck;
import main.java.model.Flashcard;
import main.java.model.Subject;
import main.java.view.gui.pages.*;
import main.java.view.gui.components.*;

import java.util.List;

public class ButtonActionController {

    /*******************************************************************************************************************
     * Instance Variable
     */
    private FlashcardApplication app;
    private GuiPageManager guiPageManager;
    private CollectionManager collectionManager;


    /*******************************************************************************************************************
     * Constructor
     */
    public ButtonActionController(FlashcardApplication app, CollectionManager collectionManager) {
        this.app = app;
        this.guiPageManager = app.getGuiPageManager();
        this.collectionManager = collectionManager;
    }

    /*******************************************************************************************************************
     * Button Actions
     */
    public void buttonActions(String command) {
        if(command.equals(StartButtonComponent.START_BUTTON)) {
            guiPageManager.instantiateSelectSubjectPage();
        }
        else if(command.contains(SubjectSelectionButtonsComponent.SELECT_SUBJECT_BUTTON_ID)) {
            // Stores the last page the subject selector was on, then resets the selector page
            app.setLastSubjectSelectionPageNumber(app.getCurrentSelectionPageNumber());
            app.setCurrentSelectionPageNumber(0);
            app.setMaxPageNumber(0);

            String stringIndexOfSubject = command.replace(SubjectSelectionButtonsComponent.SELECT_SUBJECT_BUTTON_ID, "");
            guiPageManager.instantiateCurrentSubjectPage(stringIndexOfSubject);
        }
        else if(command.contains(DeckSelectionButtonsComponent.SELECT_DECK_BUTTON_ID)) {
            // Stores the last page the deck selector was on, then resets the selector page
            app.setLastDeckSelectionPageNumber(app.getCurrentSelectionPageNumber());
            app.setCurrentSelectionPageNumber(0);
            app.setMaxPageNumber(0);

            String stringIndexOfDeck = command.replace(DeckSelectionButtonsComponent.SELECT_DECK_BUTTON_ID, "");
            guiPageManager.instantiateCurrentDeckPage(stringIndexOfDeck);
        }
        else if (command.equals(PageNavigatorComponent.SELECTION_PAGE_UP_BUTTON_ID)) {
            app.selectionPageUp();
        }
        else if (command.equals(PageNavigatorComponent.SELECTION_PAGE_DOWN_BUTTON_ID)) {
            app.selectionPageDown();
        }
        else if (command.equals(FlashcardComponent.FLASHCARD_FLIP_BUTTON_ID)) {
            app.getCurrentFlashcard().flipCard();
        }
        else if (command.equals(NewSubjectButtonComponent.NEW_SUBJECT_BUTTON)) {
            // Stores the last page the subject selector was on, then resets the selector page
            app.setLastSubjectSelectionPageNumber(app.getCurrentSelectionPageNumber());

            guiPageManager.clearPage();
            guiPageManager.instantiateNewSubjectPage();
        }
        else if (command.equals((AddNewSubjectButtonComponent.ADD_NEW_SUBJECT_BUTTON_ID))) {
            // Adds the subject from the edit subject component to the list of subjects
            List<Componentable> components = guiPageManager.getGuiComponentManager().getActiveComponents();
            EditSubjectComponent editSubjectComponent = null;
            for (Componentable currentComponent : components) {
                if (currentComponent instanceof EditSubjectComponent) {
                    editSubjectComponent = (EditSubjectComponent) currentComponent;
                    break;
                }
            }
            if (editSubjectComponent != null) {
                Subject subjectToAdd = editSubjectComponent.getSubject();
                app.addSubjectToCollectionManager(subjectToAdd);

                // Go to page of subject you just made
                app.setCurrentSubject(subjectToAdd);

                // Save the last subject selection page number
                app.setLastSubjectSelectionPageNumber(app.getMaxPageNumber());
                app.setCurrentSelectionPageNumber(0);
                app.setMaxPageNumber(0);

                guiPageManager.instantiateCurrentSubjectPage(subjectToAdd);
            }
        }
        else if (command.equals(NewDeckButtonComponent.NEW_DECK_BUTTON)) {
            // Stores the last page the deck selector was on, then resets the selector page
            app.setLastDeckSelectionPageNumber(app.getCurrentSelectionPageNumber());

            guiPageManager.clearPage();
            guiPageManager.instantiateNewDeckPage();
        }
        else if (command.equals((AddNewDeckButtonComponent.ADD_NEW_DECK_BUTTON_ID))) {
            // Adds the deck from the edit deck component to the current subject's list of decks
            List<Componentable> components = guiPageManager.getGuiComponentManager().getActiveComponents();
            EditDeckComponent editDeckComponent = null;
            for (Componentable currentComponent : components) {
                if (currentComponent instanceof EditDeckComponent) {
                    editDeckComponent = (EditDeckComponent) currentComponent;
                    break;
                }
            }
            if (editDeckComponent != null) {
                Deck deckToAdd = editDeckComponent.getDeck();
                app.addDeckToCurrentSubject(deckToAdd);

                // Go to the page of the Deck you just made
                app.setCurrentDeck(deckToAdd);

                // Save the last deck selection page number
                app.setLastDeckSelectionPageNumber(app.getMaxPageNumber());
                app.setCurrentSelectionPageNumber(0);
                app.setMaxPageNumber(0);

                guiPageManager.instantiateCurrentDeckPage(deckToAdd);
            }
        }
        else if (command.equals(NewFlashcardButtonComponent.NEW_FLASHCARD_BUTTON)) {
            guiPageManager.clearPage();
            guiPageManager.instantiateNewFlashcardPage();

        }
        else if (command.equals(AddNewFlashcardButtonComponent.ADD_NEW_FLASHCARD_BUTTON_ID)) {
            //TODO: add the edited flashcard
            // Adds the flashcard from the edit flashcard component to the current deck's list of flashcards
            List<Componentable> components = guiPageManager.getGuiComponentManager().getActiveComponents();
            EditFlashcardComponent editFlashcardComponent = null;
            for (Componentable currentComponent : components) {
                if (currentComponent instanceof EditFlashcardComponent) {
                    editFlashcardComponent = (EditFlashcardComponent) currentComponent;
                    break;
                }
            }

            if (editFlashcardComponent != null) {
                editFlashcardComponent.applyChanges();
                Flashcard newFlashcard = editFlashcardComponent.getFlashcard();
                app.getCurrentDeck().addFlashcard(newFlashcard);
            }

            // Go back to CurrentDeck Page
            guiPageManager.clearPage();
            app.setCurrentSelectionPageNumber( app.getCurrentDeck().getDeckSize() - 1 );
            guiPageManager.instantiateCurrentDeckPage( app.getCurrentDeck() );

        }
        else if (command.equals(EditFlashcardComponent.FLIP_EDITING_FLASHCARD_BUTTON_ID)) {
            // Get the editFlashcardComponent
            List<Componentable> components = guiPageManager.getGuiComponentManager().getActiveComponents();
            EditFlashcardComponent editFlashcardComponent = null;
            for (Componentable currentComponent : components) {
                if (currentComponent instanceof EditFlashcardComponent) {
                    editFlashcardComponent = (EditFlashcardComponent) currentComponent;
                    break;
                }
            }

            if (editFlashcardComponent != null) {
                editFlashcardComponent.flipCard();
            }
        }
        else if (command.equals(BackButtonComponent.BACK_BUTTON_ID)) {
            backButtonActions();
        }


    }


    /*******************************************************************************************************************
     * Back button Actions
     */
    private void backButtonActions() {
        guiPageManager.clearPage();

        if(app.getCurrentPage().equals(SelectSubjectPage.PAGE_ID)) {
            guiPageManager.instantiateHomepage();
        } else if (app.getCurrentPage().equals(CurrentSubjectPage.PAGE_ID)
                    || app.getCurrentPage().equals(NewSubjectPage.PAGE_ID)) {
            app.setCurrentSelectionPageNumber( app.getLastSubjectSelectionPageNumber() );
            guiPageManager.instantiateSelectSubjectPage();
        } else if (app.getCurrentPage().equals(CurrentDeckPage.PAGE_ID)) {
            // Set all flashcards to front before leaving if leaving the current deck page
            app.getCurrentDeck().setAllFlashcardsToFront();

            goBackToSubjectPage();
        } else if (app.getCurrentPage().equals(NewDeckPage.PAGE_ID)) {
            goBackToSubjectPage();
        } else if (app.getCurrentPage().equals(NewFlashcardPage.PAGE_ID)) {
            guiPageManager.clearPage();
            guiPageManager.instantiateCurrentDeckPage( app.getCurrentDeck() );
        }
    }


    /*******************************************************************************************************************
     * Helper Methods
     */
    private void goBackToSubjectPage() {
        app.setCurrentSelectionPageNumber( app.getLastDeckSelectionPageNumber() );
        Subject currentSubject = collectionManager.getCurrentSubject();
        String stringIndexOfSubject = "" + collectionManager.getSubjectList().indexOf(currentSubject);
        guiPageManager.instantiateCurrentSubjectPage(stringIndexOfSubject);
    }

}
