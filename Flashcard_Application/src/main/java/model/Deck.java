package main.java.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    // Static Constants
    public static final int MULTIPLE_CHOICE_QUIZ = 1;
    public static final int TRUE_FALSE_QUIZ = 2;
    public static final int FREE_ANSWER_QUIZ = 3;

    // Instance Variables
    private String deckName = "";
    private List<Flashcard> listOfFlashcardsInDeck;
    private int quizType;


    // Constructor
    public Deck(String deckName, int quizType) {
        this.deckName = deckName;
        this.quizType = quizType;
        this.listOfFlashcardsInDeck = new ArrayList<>();
    }


    // Getters
    public String getDeckName() {
        return this.deckName;
    }

    public int getQuizType(){
        return this.quizType;
    }

    public int getDeckSize() {
        return this.listOfFlashcardsInDeck.size();
    }

    public List<Flashcard> getFlashcardsInDeck() {
        return this.listOfFlashcardsInDeck;
    }

    public Flashcard getMatchingFlashcard(Flashcard flashcardToFind) {
        for(Flashcard flashcard : this.listOfFlashcardsInDeck) {
            if(flashcard.getFront().equals(flashcardToFind.getFront())) {
                if(flashcard.getBack().equals(flashcardToFind.getBack())) {
                    return flashcard;
                }
            }
        }

        return null;
    }


    // Setters
    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public void setListOfFlashcardsInDeck(List<Flashcard> flashcardList){
        this.listOfFlashcardsInDeck = flashcardList;
    }

    public void setQuizType(int quizType) {
        this.quizType = quizType;
    }


    // Methods
    public void addFlashcard(Flashcard flashcard) {
        this.listOfFlashcardsInDeck.add(flashcard);

    }

    public void removeFlashcard(Flashcard flashcardToRemove) {
        this.listOfFlashcardsInDeck.remove(flashcardToRemove);
    }

    public void setAllFlashcardsToFront() {
        for (Flashcard flashcard : listOfFlashcardsInDeck) {
            if (!flashcard.isFront()) {
                flashcard.flipCard();
            }
        }
    }


}
