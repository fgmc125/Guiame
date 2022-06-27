package ar.com.arcom.ui;

import ar.com.arcom.bin.Coordenada;
import ar.com.arcom.bin.Dibujable;
import ar.com.arcom.bin.Persona;

import java.awt.*;
import java.util.List;

public class GraficoPersona implements Dibujable {
    private Coordenada coordenada;
    private long prioridad;
    private Persona persona;
    public List<Character> caminos;

    public GraficoPersona(Persona persona) {
        this.persona = persona;
        coordenada = persona.getUbicacion().aCoordenadas();
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int [] coordenadaMapaEnGraficas(Coordenada coordenada){
    return new int[]{(int)(((coordenada.getX()/100f)*25f)+25),(int)(((coordenada.getY()/100f)*25f)+25)};
    }
    public void incrementaX(int i) {
        coordenada.setX(coordenada.getX() + i);
    }
    @Override
    public void dibujar(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.BLACK);
        int[] xy = GraficoCiudad.coordenadaMapaEnGraficas(coordenada);
        graphics2D.drawString("Persona " + persona.getId(), xy[0]-20, xy[1]-20);
        graphics2D.setColor(Color.YELLOW);
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
}
