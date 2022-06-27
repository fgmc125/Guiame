package ar.com.arcom.ui;

import ar.com.arcom.Application;
import ar.com.arcom.bin.*;

import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GraficoAuto implements Dibujable, Movedizo {
    private Application application;
    private long prioridad;
    private Auto auto;
    private Persona persona;

    private NodoMaestro nodoMaestro;
    public List<Character> caminos;
    List<Ubicacion> ubicaciones;

    // Atriutos para el reloj
    private Timer timer;
    private TimerTask timerTask;
    private boolean simular;

    public GraficoAuto(Auto auto, Application application) {
        this.application = application;
        this.auto = auto;
        nodoMaestro = new NodoMaestro(this.application.getCiudad(), this.auto.getUbicacion());
    }

    public int [] coordenadaMapaEnGraficas(Coordenada coordenada){
    return new int[]{(int)(((coordenada.getX()/100f)*25f)+25),(int)(((coordenada.getY()/100f)*25f)+25)};
    }

    private void encontrarCaminoSentidos(){
        nodoMaestro.work(auto.getDestino());
        this.caminos = nodoMaestro.getCaminos();
    }
    public List<Ubicacion> encontrarCaminoUbicaciones(){
        nodoMaestro.work(auto.getDestino());
        return nodoMaestro.getCaminosConUbicaciones();
    }

    public Auto getAuto() {
        return auto;
    }
    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    @Override
    public void dibujar(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.BLACK);
        int[] xy = GraficoCiudad.coordenadaMapaEnGraficas(auto.getUbicacion().aCoordenadas());
        graphics2D.drawString("Auto " + auto.getId(), xy[0]-17, xy[1]-20);
        graphics2D.setColor(Color.BLUE);
        graphics2D.fillPolygon(new int[]{xy[0],xy[0]+6,xy[0]-6},
                new int[]{xy[1],xy[1]-16,xy[1]-16},3);
    }
    @Override
    public void setPrioridad(long prioridad) {
        this.prioridad = prioridad;
    }
    @Override
    public void setWidthBox(long widthBox) {

    }
    @Override
    public void setHeightBox(long heightBox) {

    }

    @Override
    public void trasladar(Coordenada coordenada) {
    }
    @Override
    public void trasladarX(long x) {
        auto.getUbicacion().setValor(auto.getUbicacion().getValor() + x);
    }
    @Override
    public void trasladarY(long y) {
        auto.getUbicacion().setValor(auto.getUbicacion().getValor() + y);
    }
    @Override
    public void setCamino(List<Character> caminos) {
        this.caminos = caminos;
    }
    @Override
    public List<Character> getCamino() {
        return caminos;
    }

    @Override
    public void moverse(TimerTask timerTask) {
        if (ubicaciones.size() > 0) {
            auto.setEnRuta(true);
            ir();
        } else {
            auto.setEnRuta(false);
            simular = false;
            timerTask.cancel();
            timer.cancel();
            timer.purge();
        }
        application.getUI().getGraficoCiudad().repaint();
    }

    private void ir() {
        long value = (long) Math.abs(auto.getVelocidadDeMovimiento()*(25f/100f));

        if((auto.getUbicacion().getCalle().getNombre().contains("Horizontal") && ubicaciones.get(0).getCalle().getNombre().contains("Horizontal")) ||
                (auto.getUbicacion().getCalle().getNombre().contains("Vertical") && ubicaciones.get(0).getCalle().getNombre().contains("Vertical"))){
            // Caso 1a que ambas sean Horizontales
            if(auto.getUbicacion().getCalle().getNombre().contains("Horizontal") && ubicaciones.get(0).getCalle().getNombre().contains("Horizontal")){
                // Caso 1aa que tengan sentido Oeste
                if(auto.getUbicacion().getCalle().getSentido() == Calle.DIR_OESTE){
                    // Caso 1aaa que [(A.valor - K) > (B.valor)]
                    if((auto.getUbicacion().getValor() - value) > ubicaciones.get(0).getValor()){
                        trasladarX(-value);
                    } else {
                        long l = value - Math.abs(auto.getUbicacion().getValor()-ubicaciones.get(0).getValor());
                        getAuto().setUbicacion(ubicaciones.get(0));
                        ubicaciones.remove(0);
                        if(ubicaciones.size() == 0) return;
                        // Se debe sumar restar el restante valor "l"
                        if(ubicaciones.get(0).getCalle().getNombre().contains("Vertical")){
                            long k = (auto.getUbicacion().getCalle().getId() - 1) * 100;
                            auto.setUbicacion(new Ubicacion(application.getCiudad().indexOf(auto.getUbicacion().aCoordenadas(),
                                    Calle.ORIENTACION_VERTICAL),k));
                            if(auto.getUbicacion().getCalle().getSentido() == Calle.DIR_NORTE){
                                trasladarY(-l);
                            } else {
                                trasladarY(l);
                            }
                        } else {
                            trasladarX(-l);
                        }
                    } // Caso 1aab que [(A.valor + K) < (B.valor)]
                } else if((auto.getUbicacion().getValor() + value) < ubicaciones.get(0).getValor()){
                        trasladarX(value);
                } else {
                    long l = value - Math.abs(auto.getUbicacion().getValor()-ubicaciones.get(0).getValor());
                    getAuto().setUbicacion(ubicaciones.get(0));
                    ubicaciones.remove(0);
                    if(ubicaciones.size() == 0) return;
                    // Se debe sumar restar el restante valor "l"
                    if(ubicaciones.get(0).getCalle().getNombre().contains("Vertical")){
                        long k = (auto.getUbicacion().getCalle().getId() - 1) * 100;
                        auto.setUbicacion(new Ubicacion(application.getCiudad().indexOf(auto.getUbicacion().aCoordenadas(),
                                Calle.ORIENTACION_VERTICAL),k));
                        if(auto.getUbicacion().getCalle().getSentido() == Calle.DIR_NORTE){
                            trasladarX(-l);
                        } else {
                            trasladarX(l);
                        }
                    } else {
                        trasladarX(l);
                    }
                }
            }
            else if(auto.getUbicacion().getCalle().getSentido() == Calle.DIR_NORTE){
                // Caso 1aaa que [(A.valor - K) > (B.valor)]
                if((auto.getUbicacion().getValor() - value) > ubicaciones.get(0).getValor()){
                    trasladarY(-value);
                } else {
                    long l = value - Math.abs(auto.getUbicacion().getValor()-ubicaciones.get(0).getValor());
                    getAuto().setUbicacion(ubicaciones.get(0));
                    ubicaciones.remove(0);
                    if(ubicaciones.size() == 0) return;
                    // Se debe sumar restar el restante valor "l"
                    if(ubicaciones.get(0).getCalle().getNombre().contains("Horizontal")){
                        long k = (auto.getUbicacion().getCalle().getId() - 1) * 100;
                        auto.setUbicacion(new Ubicacion(application.getCiudad().indexOf(auto.getUbicacion().aCoordenadas(),
                                Calle.ORIENTACION_HORIZONTAL),k));
                        if(auto.getUbicacion().getCalle().getSentido() == Calle.DIR_OESTE){
                            if((auto.getUbicacion().getValor() - value) <= ubicaciones.get(0).getValor()){
                                auto.setUbicacion(ubicaciones.get(0));
                                ubicaciones.remove(0);
                                if(ubicaciones.size() == 0) return;
                            } else trasladarX(-l);
                        } else {
                            if((auto.getUbicacion().getValor() + value) >= ubicaciones.get(0).getValor()){
                                auto.setUbicacion(ubicaciones.get(0));
                                ubicaciones.remove(0);
                                if(ubicaciones.size() == 0) return;
                            } else trasladarX(l);
                        }
                    } else {
                        if((auto.getUbicacion().getValor() - value) <= ubicaciones.get(0).getValor()){
                            auto.setUbicacion(ubicaciones.get(0));
                            ubicaciones.remove(0);
                            if(ubicaciones.size() == 0) return;
                        } else trasladarY(-l);
                    }
                } // Caso 1aab que [(A.valor + K) < (B.valor)]
            } else if((auto.getUbicacion().getValor() + value) < ubicaciones.get(0).getValor()){
                trasladarY(value);
            } else {
                long l = value - Math.abs(auto.getUbicacion().getValor()-ubicaciones.get(0).getValor());
                auto.setUbicacion(ubicaciones.get(0));
                ubicaciones.remove(0);
                if(ubicaciones.size() == 0) return;
                // Se debe sumar restar el restante valor "l"
                if(ubicaciones.get(0).getCalle().getNombre().contains("Horizontal")){
                    long k = (auto.getUbicacion().getCalle().getId() - 1) * 100;
                    auto.setUbicacion(new Ubicacion(application.getCiudad().indexOf(auto.getUbicacion().aCoordenadas(),
                            Calle.ORIENTACION_HORIZONTAL),k));
                    if(auto.getUbicacion().getCalle().getSentido() == Calle.DIR_OESTE){
                        if((auto.getUbicacion().getValor() - value) <= ubicaciones.get(0).getValor()){
                            auto.setUbicacion(ubicaciones.get(0));
                            ubicaciones.remove(0);
                            if(ubicaciones.size() == 0) return;
                        } else trasladarX(-l);
                    } else {
                        if((auto.getUbicacion().getValor() + value) >= ubicaciones.get(0).getValor()){
                            auto.setUbicacion(ubicaciones.get(0));
                            ubicaciones.remove(0);
                            if(ubicaciones.size() == 0) return;
                        } else trasladarX(l);
                    }
                } else {
                    if((auto.getUbicacion().getValor() + value) >= ubicaciones.get(0).getValor()){
                        auto.setUbicacion(ubicaciones.get(0));
                        ubicaciones.remove(0);
                        if(ubicaciones.size() == 0) return;
                    } else trasladarY(l);
                }
            }
        }
    }
    public void setDestino(Ubicacion ubicacion){
        auto.setDestino(ubicacion);
        nodoMaestro.work(auto.getDestino());
        ubicaciones = nodoMaestro.getCaminosConUbicaciones();
        ubicaciones.remove(0);
    }
    public void simular() {
        setSimular(true);
        setDestino(((GraficoPersona)application.getUI().getGraficoPersonaList().get((int)auto.getId())).getPersona().getUbicacion());

        // Crea el reloj
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (simular) moverse(this);
                else timerTask.cancel();
            }
        };
        timer.schedule(this.timerTask,0, 1000);
    }
    public long getPrioridad() {
        return prioridad;
    }
    public Persona getPersona() {
        return persona;
    }
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    public Timer getTimer() {
        return timer;
    }
    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    public TimerTask getTimerTask() {
        return timerTask;
    }
    public void setTimerTask(TimerTask timerTask) {
        this.timerTask = timerTask;
    }
    public boolean isSimular() {
        return simular;
    }
    public void setSimular(boolean simular) {
        this.simular = simular;
    }
}
