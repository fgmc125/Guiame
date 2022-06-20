package ar.com.arcom.ui;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextField extends JTextField implements KeyListener {
    public final static int CHARACTER = 1, NUMERIC = 2, ALL = 3;

    private int type;


    public TextField() {
        type = 3;
    }

    public TextField(String text) {
        super(text);
    }

    public TextField(int columns) {
        super(columns);
    }

    public TextField(String text, int columns) {
        super(text, columns);
    }

    public TextField(Document doc, String text, int columns) {
        super(doc, text, columns);
    }

    public void setType(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }

    @Override
    public void validate() {
        super.validate();
    }

    public Integer getValue(){
        return (this.getType() == 2 && !getText().equals("")) ? Integer.parseInt(getText()): null;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char character = e.getKeyChar();
        if(((character < '0') || (character > '9')) && (character != '\b')) e.consume();
        InputMap map2 = this.getInputMap(JTextField.WHEN_FOCUSED);
        map2.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK), "null");
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
