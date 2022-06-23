package ar.com.arcom.ui;

import ar.com.arcom.bin.Auto;
import ar.com.arcom.bin.Calle;
import ar.com.arcom.bin.Coordenada;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraficoAuto {
    // rotar e invertir no implementado todavia
    private int[] listaFiguras;
    private List<int[]> formaN;
    private List<int[]> formaS;
    private List<int[]> formaE;
    private List<int[]> formaO;
    private Auto auto;

    public final int RECTANGULO = 0, TRIANGULO = 1, CIRCULO = 2;

    public GraficoAuto(Auto auto) {
        this.auto = auto;
        formaN = new ArrayList<>();
        int[] f = coordenadaMapaEnGraficas(auto.getUbicacion().aCoordenadas());
        listaFiguras = new int[]{0,1};
        formaN.add(new int[]{f[0]-3,f[1],6,6});
        formaN.add(new int[]{f[0],f[0]-3,f[0]+3});
        formaN.add(new int[]{f[1]-9,f[1],f[1]});
    }

    public void draw(Graphics g){
        Graphics2D graphics2D = (Graphics2D)g;
        graphics2D.setColor(Color.BLUE);
        for(int i= 0; i < listaFiguras.length; i++){
            switch (listaFiguras[i]){
                case 0: graphics2D.fillRect(formaN.get(i)[0],formaN.get(i)[1],formaN.get(i)[2],formaN.get(i)[3]); break;
                case 1: graphics2D.fillPolygon(formaN.get(i),formaN.get(i+1),3); break;
            }

        }
    }

    public int [] coordenadaMapaEnGraficas(Coordenada coordenada){
        return new int[]{(int)(((coordenada.getX()/100f)*25f)+25),(int)(((coordenada.getY()/100f)*25f)+25)};
    }
}
