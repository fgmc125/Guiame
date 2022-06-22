package ar.com.arcom.bin;

import java.util.ArrayList;
import java.util.List;

public class Ciudad {
    private long alto, ancho;
    private List<Calle> listaDeCalles;

    public Ciudad(Long width, Long heigth) {
        ArrayList<Nodo> nodoArrayList;


    }

    public long getAlto() {
        return alto;
    }

    public void setAlto(long alto) {
        this.alto = alto;
    }

    public long getAncho() {
        return ancho;
    }

    public void setAncho(long ancho) {
        this.ancho = ancho;
    }

    public Calle getCalle(int indice) {
        return listaDeCalles.get(indice);
    }
}
