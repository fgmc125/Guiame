package ar.com.arcom;

import ar.com.arcom.bin.*;
import ar.com.arcom.handlers.HelperHandler;
import ar.com.arcom.ui.UI;

import java.awt.*;
import java.util.*;

public class Application {
    // Atributos rincipales
    private Ciudad ciudad;
    private ArrayList<Persona> personas;
    private ArrayList<Auto> autos;

    // Atriutos para el reloj
    private Timer timer;
    private Long segundos;
    private TimerTask timerTask;

    // Atributos extra
    private UI UI;
    private HelperHandler helperHandler;

    // Constructores
    public Application(){
        helperHandler = new HelperHandler();
        UI = new UI(this);

        // Crea el reloj
        segundos = 0L;
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (segundos > Long.MAX_VALUE-1) segundos = 0L;
                else segundos++;
                UI.setTextBarraEstado(String.valueOf((segundos)));
            }
        };
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

    public Long getSegundos() {
        return segundos;
    }

    public void setSegundos(Long segundos) {
        this.segundos = segundos;
    }

    public ar.com.arcom.ui.UI getUI() {
        return UI;
    }

    public void play(){
        timer.schedule(timerTask,0, 1000);
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
            Ubicacion ubicacion = new Ubicacion(ciudad.getCalle((int)aux),
                    (calle.getOrientacion() == 'H') ? random.nextLong(ciudad.getAncho()) : random.nextLong(ciudad.getAlto()));

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

            if (calle.getOrientacion() == 'H') aux = random.nextLong(ciudad.getAncho());
            else aux = random.nextLong(ciudad.getAlto());

            Ubicacion ubicacion = new Ubicacion(calle, aux);
            autos.add(new Auto(ubicacion));
        }
    }

    public void simular(TimerTask timerTask) {
        timer.cancel();
        this.timerTask = timerTask;
        timer.schedule(this.timerTask,0, 1000);
    }
}