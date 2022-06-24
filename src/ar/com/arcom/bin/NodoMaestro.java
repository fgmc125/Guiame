package ar.com.arcom.bin;

import ar.com.arcom.Application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NodoMaestro extends Nodo {
    protected static Coordenada coordenadaNodoFinal;
    protected static Coordenada coordenadaInicial;
    protected static List<Character> caminos;

    private double distanciaNodoFinal;
    private Nodo nodoFinal, nodoAnteFinal;

    public NodoMaestro(Ciudad ciudad, Ubicacion ubicacion) {
        super(ciudad, ubicacion);
        coordenadaInicial = ubicacion.aCoordenadas();
        this.nodoFinal = null;
        this.nodoAnteFinal = null;
        caminos = new ArrayList<>();
    }

    public void work(Ubicacion ubicacion){
        NodoMaestro.coordenadaNodoFinal = ubicacion.aCoordenadas();
        nodoFinal = new NodoEsclavo(getCiudad(), ubicacion);
        setNodoSiguiente(creaNodoSiguiente());
        nodoAnteFinal = nodoAnteFinal(ubicacion);

        getNodoSiguiente().setValorAcumulado((long)distancia(getUbicacion().aCoordenadas(),getNodoSiguiente().getUbicacion().aCoordenadas()));
        System.out.println(getNodoSiguiente().getValorAcumulado());
        NodoMaestro.caminos.add(getNodoSiguiente().work());
        Collections.reverse(caminos);
        System.out.println(caminos);
    }
    private Nodo creaNodoSiguiente(){
        int direction, k,p;
        NodoEsclavo nodo;
        switch (getUbicacion().getCalle().getSentido()) {
            case 'N', 'O' -> direction = -1;
            case 'S', 'E' -> direction = 1;
            default -> direction = 0;
        }

        if(direction > 0 && getUbicacion().getCalle().getOrientacion() == Calle.ORIENTACION_VERTICAL){
            k = ((int)(getUbicacion().getCalle().getId()) - 1) * 100;
            p = ((int)getUbicacion().getValor()) + (100 - (((int)getUbicacion().getValor())%100));
            Calle calle = getCiudad().indexOf(new Coordenada(k,p), Calle.ORIENTACION_VERTICAL);
            nodo = new NodoEsclavo(getCiudad(), new Ubicacion(calle,p));
        } else if(direction < 0 && getUbicacion().getCalle().getOrientacion() == Calle.ORIENTACION_VERTICAL){
            k = ((int)(getUbicacion().getCalle().getId()) - 1) * 100;
            p = ((int)getUbicacion().getValor()) - ((((int)getUbicacion().getValor())%100));
            Calle calle = getCiudad().indexOf(new Coordenada(k,p),Calle.ORIENTACION_VERTICAL);
            nodo = new NodoEsclavo(getCiudad(), new Ubicacion(calle, p));
        } else if(direction > 0 && getUbicacion().getCalle().getOrientacion() == Calle.ORIENTACION_HORIZONTAL){
            k = ((int)(getUbicacion().getCalle().getId()) - 1) * 100;
            p = ((int)getUbicacion().getValor()) + (100 - (((int)getUbicacion().getValor())%100));
            Calle calle = getCiudad().indexOf(new Coordenada(p,k),Calle.ORIENTACION_HORIZONTAL);
            nodo = new NodoEsclavo(getCiudad(), new Ubicacion(calle, p));
        } else if(direction < 0 && getUbicacion().getCalle().getOrientacion() == Calle.ORIENTACION_HORIZONTAL){
            k = ((int)(getUbicacion().getCalle().getId()) - 1) * 100;
            p = ((int)getUbicacion().getValor()) - ((((int)getUbicacion().getValor())%100));
            Calle calle = getCiudad().indexOf(new Coordenada(p,k),Calle.ORIENTACION_HORIZONTAL);
            nodo = new NodoEsclavo(getCiudad(), new Ubicacion(calle, p));
        } else nodo = null;

        return nodo;
    }
    private Nodo nodoAnteFinal(Ubicacion ubicacion){
        int direction, k,p;
        NodoEsclavo nodo;
        switch (ubicacion.getCalle().getSentido()) {
            case 'N', 'O' -> direction = -1;
            case 'S', 'E' -> direction = 1;
            default -> direction = 0;
        }
        k = ((int)(ubicacion.getCalle().getId()) - 1) * 100;
        if(direction > 0 && ubicacion.getCalle().getOrientacion() == Calle.ORIENTACION_VERTICAL){
            p = ((int)ubicacion.getValor()) - ((((int)ubicacion.getValor())%100));
            Calle calle = getCiudad().indexOf(new Coordenada(k,p), Calle.ORIENTACION_VERTICAL);
            nodo = new NodoEsclavo(getCiudad(), new Ubicacion(calle,p));
        } else if(direction < 0 && ubicacion.getCalle().getOrientacion() == Calle.ORIENTACION_VERTICAL){
            p = ((int)ubicacion.getValor()) + (100 - (((int)ubicacion.getValor())%100));
            Calle calle = getCiudad().indexOf(new Coordenada(k,p),Calle.ORIENTACION_VERTICAL);
            nodo = new NodoEsclavo(getCiudad(), new Ubicacion(calle, p));
        } else if(direction > 0 && ubicacion.getCalle().getOrientacion() == Calle.ORIENTACION_HORIZONTAL){
            p = ((int)ubicacion.getValor()) - ((((int)ubicacion.getValor())%100));
            Calle calle = getCiudad().indexOf(new Coordenada(p,k),Calle.ORIENTACION_HORIZONTAL);
            nodo = new NodoEsclavo(getCiudad(), new Ubicacion(calle, p));
        } else if(direction < 0 && ubicacion.getCalle().getOrientacion() == Calle.ORIENTACION_HORIZONTAL){
            p = ((int)ubicacion.getValor()) + (100 - (((int)ubicacion.getValor())%100));
            Calle calle = getCiudad().indexOf(new Coordenada(p,k),Calle.ORIENTACION_HORIZONTAL);
            nodo = new NodoEsclavo(getCiudad(), new Ubicacion(calle, p));
        } else nodo = null;

        return nodo;
    }
}
