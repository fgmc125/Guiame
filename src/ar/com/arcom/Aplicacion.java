package ar.com.arcom;

import ar.com.arcom.handlers.HelperHandler;

public class Aplicacion {
    private HelperHandler helperHandler;

    public Aplicacion(){
        helperHandler = new HelperHandler();
    }

    public void play(){
    }

    public final static void main(String[] arg){
        Aplicacion aplicacion = new Aplicacion();
        aplicacion.play();
    }
}
