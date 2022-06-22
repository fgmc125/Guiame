package ar.com.arcom.bin;

import java.awt.image.BandCombineOp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ciudad {
    private long alto, ancho;
    private List<Calle> listaDeCalles;

    public Ciudad(Long width, Long heigth) {
        ArrayList<Nodo> nodoArrayList;
        this.ancho = width;
        this.alto = heigth;
        initialize();
    }

    private void initialize(){
        listaDeCalles = new ArrayList<>();
        Random random = new Random();
        long aux;

        for(int i = 0; i < ancho;i++){
            Calle calle = new Calle(i, Calle.ORIENTACION_VERTICAL, ((i%2 == 0) ? Calle.DIR_SUR : Calle.DIR_NORTE));
            listaDeCalles.add(calle);
        }

        for(int i = 0; i < alto;i++){
            Calle calle = new Calle(i, Calle.ORIENTACION_HORIZONTAL, ((i%2 == 0) ? Calle.DIR_SUR : Calle.DIR_NORTE));
            listaDeCalles.add(calle);
        }
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
