package main.java.view.gui.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuiTypingBox extends GuiTextBox{
    /*******************************************************************************************************************
     * Instance Variables
     */
    private String stringBeforeCursor = "";
    private String stringAfterCursor = "";

    private List<String> lines = new ArrayList<>();

    private int currentLineNumber = 0;
    private int cursorXPosition = 0;

    private FontMetrics fontMetrics;



    /*******************************************************************************************************************
     * Constructor
     */
    public GuiTypingBox(String id, String text, int point1X, int point1Y, int point2X, int point2Y) {
        super(id, text, point1X, point1Y, point2X, point2Y);

        // Start cursor at the end of the string
        stringBeforeCursor = text;
        stringAfterCursor = "";
        this.setText(this.getFullText());
    }


    /*******************************************************************************************************************
     * Getter
     */
    public String getFullText() {
        String fullText = this.stringBeforeCursor + this.stringAfterCursor;
        return fullText;
    }

    public List<String> getLines() {
        return lines;
    }

    public int getCurrentLineNumber() {
        return currentLineNumber;
    }

    public int getCursorXPosition() {
        return cursorXPosition;
    }



    /*******************************************************************************************************************
     * Setters
     */
    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public void addLine(String line) {
        this.lines.add(line);
    }

    public void setFontMetrics(FontMetrics fontMetrics) {
        this.fontMetrics = fontMetrics;
    }

    public void setStringBeforeCursor(String stringBeforeCursor) {
        this.stringBeforeCursor = stringBeforeCursor;
    }

    public void setStringAfterCursor(String stringAfterCursor) {
        this.stringAfterCursor = stringAfterCursor;
    }

    public void reset() {
        this.stringBeforeCursor = "";
        this.stringAfterCursor = "";
        this.setText("");
    }


    /*******************************************************************************************************************
     * Key Pressed Actions
     * Returns true if any special actions are taken
     */
    public boolean keyPressedActions(String keyChar, int keyCode) {
        boolean specialActionDone = false;

        // Checks if the keyChar is a letter, number, or punctuation
        if (keyChar.matches("[A-Za-z0-9\\p{Punct}]+")) {
            this.addCharacter(keyChar);
            specialActionDone = true;

        } else if (keyChar.equals(" ")) {
            // First character cannot be a space
            if(this.stringBeforeCursor.length() != 0) {

                // Adds space if it doesn't create a double space
                if (this.stringBeforeCursor.charAt(this.stringBeforeCursor.length() - 1) != ' ') {
                    if (this.stringAfterCursor.length() == 0) {
                        this.addCharacter(keyChar);
                        specialActionDone = true;
                    } else if (this.stringAfterCursor.charAt(0) != ' ') {
                        this.addCharacter(keyChar);
                        specialActionDone = true;
                    } else if (this.stringAfterCursor.charAt(0) == ' ') {
                        stringAfterCursor = stringAfterCursor.substring(1, stringAfterCursor.length());
                        this.addCharacter(keyChar);
                        specialActionDone = true;
                    }
                }
            }
        } else if (keyCode == 8) {
            this.backspace();
            specialActionDone = true;
        } else if (keyCode == 37) {
            this.moveCursorLeft();
            specialActionDone = true;
        } else if (keyCode == 38) {
            this.moveCursorUp();
            specialActionDone = true;
        }  else if (keyCode == 39) {
            this.moveCursorRight();
            specialActionDone = true;
        } else if (keyCode == 40) {
            this.moveCursorDown();
            specialActionDone = true;
        }

        return specialActionDone;
    }


    /*******************************************************************************************************************
     * Typing Methods
     */
    private void addCharacter(String character) {
        this.stringBeforeCursor = this.stringBeforeCursor + character;
        this.setText(this.getFullText());
    }


    /*******************************************************************************************************************
     * Backspace
     */
    private void backspace() {
        if(this.stringBeforeCursor.length() > 0) {
            this.stringBeforeCursor = this.stringBeforeCursor.substring(0, this.stringBeforeCursor.length() - 1);
            this.setText(this.getFullText());
        }
    }

    /*******************************************************************************************************************
     * Move Cursor
     */
    private void moveCursorLeft() {
        if(this.stringBeforeCursor.length() > 0) {
            String characterMovingOver = this.stringBeforeCursor.substring(this.stringBeforeCursor.length() - 1);
            this.stringBeforeCursor = this.stringBeforeCursor.substring(0, this.stringBeforeCursor.length() - 1);
            this.stringAfterCursor = characterMovingOver + this.stringAfterCursor;
            this.setText(this.getFullText());
        }
    }

    private void moveCursorRight() {
        if(this.stringAfterCursor.length() > 0) {
            String characterMovingOver = this.stringAfterCursor.substring(0, 1);
            this.stringBeforeCursor = this.stringBeforeCursor + characterMovingOver;
            this.stringAfterCursor = this.stringAfterCursor.substring(1, this.stringAfterCursor.length());
            this.setText(this.getFullText());
        }
    }

    private void moveCursorUp() {
        // Triggers only if below the first line
        if (currentLineNumber > 0) {
            // Moves cursor up by simulating a mouse press one line up
            int lineHeight = fontMetrics.getHeight();
            int xPos = getAbsoluteXPositionOfCursor();
            int yPos = getAbsoluteYPositionOfCursor() - lineHeight;

            mousePressTypingBox(xPos, yPos);
        }
    }

    private void moveCursorDown() {
        // triggers only if above the last line
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> " + currentLineNumber);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> " + (lines.size() - 1));
        if (currentLineNumber < lines.size() - 1) {
            // Moves cursor down by simulating a mouse press one line down
            int lineHeight = fontMetrics.getHeight();
            int xPos = getAbsoluteXPositionOfCursor();
            int yPos = getAbsoluteYPositionOfCursor() + lineHeight;

            mousePressTypingBox(xPos, yPos);
        }
    }

    /*******************************************************************************************************************
     * Mouse Click on Text
     * Puts cursor on text
     */
    public void mousePressTypingBox(int mouseX, int mouseY) {

        // Makes sure the mouse is within the textBox
        if (isMouseWithinTextBoxBounds(mouseX, mouseY)) {
            // Get height of the line
            int lineHeight = fontMetrics.getHeight();

            String newStringBeforeCursor = "";
            String newStringAfterCursor = "";

            boolean isBeforeCursor = true;

            int lineStartY = this.getPoint1Y();
            int lineEndY = lineStartY + lineHeight;

            for (String currentLine : lines) {
                if (mouseY >= lineStartY && mouseY < lineEndY) {
                    int width = this.getPoint2X() - this.getPoint1X();
                    int characterXStart = this.getPoint1X();

                    //Center the lines Horizontally (And adjusts for scaling)
                    characterXStart -= (fontMetrics.stringWidth(currentLine)) / 2;
                    characterXStart += width / 2;

                    if (mouseX <= characterXStart) {
                        // makes the mouse show up at the beginning of the line it's on
                        if (currentLine.charAt(0) == ' ') {
                            newStringBeforeCursor += " ";
                        }

                    }

                    // Move the mouse to the right character on the line
                    for (int i = 0; i < currentLine.length(); i++) {
                        String currentCharacter = "" + currentLine.charAt(i);

                        int characterXEnd = characterXStart + fontMetrics.stringWidth(currentCharacter);
                        int characterXMidpoint = characterXStart + (fontMetrics.stringWidth(currentCharacter) / 2);

                        if (mouseX >= characterXEnd) {
                            // When mouseX is past the character
                            newStringBeforeCursor += currentCharacter;
                        } else if (mouseX >= characterXStart && mouseX < characterXEnd) {
                            if (mouseX > characterXMidpoint) {
                                newStringBeforeCursor += currentCharacter;
                            }
                            break;
                        }
                        characterXStart = characterXEnd;
                    }

                    break;
                } else if (mouseY >= lineEndY) {
                    // when mouse is lower than the bottom of the current line
                    newStringBeforeCursor += currentLine;
                    lineStartY += lineHeight;
                    lineEndY += lineHeight;
                }


            }

            // remove the space that appears in front of the first line
            if (newStringBeforeCursor.length() > 0) {
                newStringBeforeCursor = newStringBeforeCursor.substring(1, newStringBeforeCursor.length());
            }

            this.stringAfterCursor = getFullText().substring(newStringBeforeCursor.length(), getFullText().length());
            this.stringBeforeCursor = newStringBeforeCursor;
            this.setText(this.getFullText());

        }
    }


    /*******************************************************************************************************************
     * Helper Methods
     */
    private boolean isMouseWithinTextBoxBounds(int mouseX, int mouseY) {
        return (mouseX >= this.getPoint1X() &&
                mouseX <= this.getPoint2X() &&
                mouseY >= this.getPoint1Y() &&
                mouseY <= this.getPoint2Y());
    }

    public void setCursorPosition() {
        setCurrentLineCursorIsOn();
        setCursorXPosition();
    }

    private void setCurrentLineCursorIsOn() {
        int lineNumber = 0;

        int width = this.getPoint2X() - this.getPoint1X();

        // Get the string before the cursor plus the full word the cursor is on
        String fullWordsOnAndBeforeCursor = stringBeforeCursor;
        for (int i=0; i < stringAfterCursor.length(); i++) {
            if (stringAfterCursor.charAt(i) == ' ') {
                break;
            } else {
                fullWordsOnAndBeforeCursor += stringAfterCursor.charAt(i);
            }
        }


        String[] words = fullWordsOnAndBeforeCursor.split(" ");
        String currentLine = "";
        for (String word : words) {
            if (fontMetrics.stringWidth(currentLine + "-" + word) > width) {
                // Clear the current line
                currentLine = "";
                lineNumber += 1;
            }

            // Add the word to the line
            currentLine += " " + word;
        }

        this.currentLineNumber = lineNumber;
    }

    private void setCursorXPosition() {
        String textBeforeCursorOnCurrentLine = this.stringBeforeCursor;

        // Remove text from previous lines
        if (this.currentLineNumber > 0) {
            for (int i = 0; i < this.currentLineNumber; i++) {
                // Prevents index out of bounds errors if the typing box resizes and cuts off some lines
                try {
                    textBeforeCursorOnCurrentLine = textBeforeCursorOnCurrentLine.substring(this.lines.get(i).length());
                }
                catch (IndexOutOfBoundsException ignore) {}
            }
        }

        int newXPosition = fontMetrics.stringWidth(textBeforeCursorOnCurrentLine);
        newXPosition += fontMetrics.stringWidth(" ") / 2;
        this.cursorXPosition = newXPosition;
    }

    private int getAbsoluteXPositionOfCursor() {
        // Get the starting X position of the typing box's bounds
        int absoluteXPosition = this.getPoint1X();

        // add half the width of the typing box to account for text centering
        absoluteXPosition += (this.getPoint2X() - this.getPoint1X()) / 2;

        // Move absoluteXPosition to the start of the current line
        absoluteXPosition -= (fontMetrics.stringWidth(lines.get(currentLineNumber)) / 2);

        // Get the position on the text line starting from the beginning of the line
        absoluteXPosition += this.cursorXPosition;

        // Keeps the cursor aligned when it moves up and down
        absoluteXPosition += fontMetrics.stringWidth(" ") / 2;

        return absoluteXPosition;
    }

    private int getAbsoluteYPositionOfCursor() {
        // Get the starting Y position of the typing box's bounds
        int absoluteYPosition = this.getPoint1Y();

        //Move down based on the number of lines
        int lineHeight = fontMetrics.getHeight();
        absoluteYPosition += (currentLineNumber * lineHeight);

        // Move the absoluteYPosition to the center of the line
        absoluteYPosition += lineHeight / 2;

        return  absoluteYPosition;
    }

}
