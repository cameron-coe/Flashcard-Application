package main.java.view.gui.shapes;

import java.awt.Font;

public class GuiTextBox extends GuiShape {

    /*******************************************************************************************************************
     * Instance Variables
     */
    Font font = new Font("Times New Roman", Font.PLAIN, 30);


    /*******************************************************************************************************************
     * Constructor
     */
    public GuiTextBox(String id, String text, int point1X, int point1Y, int point2X, int point2Y) {
        this.setShapeId(id);
        this.setBounds(point1X, point1Y, point2X, point2Y);
        this.setText(text);
        this.setTextScaleX(1);
    }


    /*******************************************************************************************************************
     * Getter
     */
    public Font getFont() {
        return font;
    }


    /*******************************************************************************************************************
     * Setter
     */
    public void setFont(Font font) {
        this.font = font;
    }
}
