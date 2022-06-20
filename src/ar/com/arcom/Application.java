package ar.com.arcom;

import ar.com.arcom.handlers.HelperHandler;
import ar.com.arcom.ui.GUI;

import java.awt.*;
import java.util.ArrayList;

public class Application {
    // Atributos rincipales
    private Ciudad ciudad;
    private ArrayList<Persona> personas;
    private ArrayList<Auto> autos;

    // Atributos extra
    private GUI gui;
    private HelperHandler helperHandler;

    public Application(){
        helperHandler = new HelperHandler();
        gui = new GUI();
    }

    public void play(){
        gui.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Application application = new Application();
                    application.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}