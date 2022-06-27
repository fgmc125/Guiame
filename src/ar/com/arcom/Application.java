package ar.com.arcom;

import ar.com.arcom.bin.*;
import ar.com.arcom.ui.UI;

import java.awt.*;
import java.util.*;

public class Application {
    // Atributos rincipales
    private Ciudad ciudad;
    private ArrayList<Persona> personas;
    private ArrayList<Auto> autos;

    // Atributos extra
    private UI UI;

    // Constructores
    public Application(){
        UI = new UI(this);
    }

    // Métodos
    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }

    public ArrayList<Auto> getAutos() {
        return autos;
    }

    public void setAutos(ArrayList<Auto> autos) {
        this.autos = autos;
    }

    public ar.com.arcom.ui.UI getUI() {
        return UI;
    }

    public void play(){
        UI.setVisible(true);

    }

    public final static void main(String[] args) {
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

    public void inicializarListaPersonasAleatorio(long valor) {
        personas = new ArrayList<>();
        Random random = new Random();
        long aux;
        Calle calle;
        for(int i = 0; i < valor;i++){
            aux = random.nextLong(ciudad.getAncho() + ciudad.getAlto());
            calle = ciudad.getCalle((int)aux);

            if (calle.getOrientacion() == 'H') aux = random.nextLong(ciudad.getAncho()*25);
            else aux = random.nextLong(ciudad.getAlto()*25);

            Ubicacion ubicacion = new Ubicacion(calle, aux);

            personas.add(new Persona(ubicacion, false));
        }
    }

    public void inicializarListaAutos(int valor) {
        autos = new ArrayList<>();
        Random random = new Random();
        long aux;
        Calle calle;
        for(int i = 0; i < valor;i++){
            aux = random.nextLong(ciudad.getAncho() + ciudad.getAlto());
            calle = ciudad.getCalle((int)aux);

            if (calle.getOrientacion() == 'H') aux = random.nextLong(ciudad.getAncho()*25);
            else aux = random.nextLong(ciudad.getAlto()*25);

            Ubicacion ubicacion = new Ubicacion(calle, aux);
            autos.add(new Auto(ubicacion));
        }
    }


}