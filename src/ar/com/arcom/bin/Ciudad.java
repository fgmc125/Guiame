package ar.com.arcom.bin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ciudad {
    private long alto, ancho;
    private List<Calle> listaDeCalles;

    public Ciudad(Long width, Long heigth) {
        this.ancho = width;
        this.alto = heigth;
        initialize();
    }

    private void initialize(){
        listaDeCalles = new ArrayList<>();
        Random random = new Random();

        for(int i = 1; i <= ancho;i++){
            Calle calle = new Calle(i, Calle.ORIENTACION_VERTICAL, ((i%2 != 0) ? Calle.DIR_SUR : Calle.DIR_NORTE));
            listaDeCalles.add(calle);
        }

        for(int i = 1; i <= alto;i++){
            Calle calle = new Calle(i, Calle.ORIENTACION_HORIZONTAL, ((i%2 == 0) ? Calle.DIR_ESTE : Calle.DIR_OESTE));
            listaDeCalles.add(calle);
        }
    }

    public List<Calle> getListaDeCalles() {
        return listaDeCalles;
    }
    public void setListaDeCalles(List<Calle> listaDeCalles) {
        this.listaDeCalles = listaDeCalles;
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
    public int indexOf(int x, int y){
        int k = ((x % 100 == 0) ? (x / 100) : (y / 100));
        char orientation = (x % 100 == 0) ? 'V': 'H';
        String nombre = (orientation == 'H') ? "Calle Horizontal " + k: "Calle Vertical " + k;
        int i = 0;
        while (!listaDeCalles.get(i).getNombre().equals(nombre) && i< listaDeCalles.size()) i++;

        return (i < listaDeCalles.size()) ? i: -1;
    }
    public Calle indexOf(Coordenada coordenada){
        int i = indexOf((int)coordenada.getX(), (int)coordenada.getY());
        return (i != -1) ? listaDeCalles.get(i) : null;
    }
    public Calle indexOf(Coordenada coordenada, char orientacion){
        int k = 0;
        String nombre = null;
        if(orientacion == Calle.ORIENTACION_HORIZONTAL){
            k = 1 + (int)coordenada.getY() / 100;
            nombre = "Calle Horizontal " + k;
        } else {
            k = 1 + (int)coordenada.getX() / 100;
            nombre = "Calle Vertical " + k;
        }

        int i = 0;
        while (!listaDeCalles.get(i).getNombre().equals(nombre) && i < listaDeCalles.size()){
            i++;
        }

        return (i < listaDeCalles.size()) ? listaDeCalles.get(i) : null;
    }
}
