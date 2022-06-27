package ar.com.arcom.bin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NodoMaestro extends Nodo {
    private List<Character> caminos;
    private Nodo nodoFinal;
    private Coordenada coordenadaNodoInicial;

    public NodoMaestro(Ciudad ciudad, Ubicacion ubicacion) {
        super(ciudad, ubicacion);
        coordenadaNodoInicial = ubicacion.aCoordenadas();
        caminos = new ArrayList<>();
    }

    public void work(Ubicacion ubicacion){
        /* PROBLEMA!
        La creacion de nodo incondicionalmente en nodo maestro es un error:
        *existe el caso en que la distancia entre el el inicio y final sea a unos metros.
        en ese caso primero antes de hacer xtodo el proceso deberia preguntarse eso.. queda por arreglar
         */
        setCoordenadaNodoFinal(ubicacion.aCoordenadas());
        nodoFinal = new NodoEsclavo(getCiudad(), ubicacion);
        setNodoAnteFinal(creaNodoAnteFinal(ubicacion));
        setNodoSiguiente(creaNodoSiguiente());
        getNodoSiguiente().setNodoAnterior(this);

        getNodoSiguiente().setValorAcumulado((long)distancia(getUbicacion().aCoordenadas(),getNodoSiguiente().getUbicacion().aCoordenadas()));
        getNodoSiguiente().work();

        caminos.clear();
        char c = obtenerRutasEnCardinales(caminos);
        Collections.reverse(caminos);
        caminos.remove(caminos.size()-1);
        caminos.add(nodoFinal.getUbicacion().getCalle().getSentido());
        //System.out.println(caminos);
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
            nodo = new NodoEsclavo(getCiudad(), new Ubicacion(calle,p), getCoordenadaNodoFinal(),getNodoAnteFinal());
        } else if(direction < 0 && getUbicacion().getCalle().getOrientacion() == Calle.ORIENTACION_VERTICAL){
            k = ((int)(getUbicacion().getCalle().getId()) - 1) * 100;
            p = ((int)getUbicacion().getValor()) - ((((int)getUbicacion().getValor())%100));
            Calle calle = getCiudad().indexOf(new Coordenada(k,p),Calle.ORIENTACION_VERTICAL);
            nodo = new NodoEsclavo(getCiudad(), new Ubicacion(calle, p), getCoordenadaNodoFinal(),getNodoAnteFinal());
        } else if(direction > 0 && getUbicacion().getCalle().getOrientacion() == Calle.ORIENTACION_HORIZONTAL){
            k = ((int)(getUbicacion().getCalle().getId()) - 1) * 100;
            p = ((int)getUbicacion().getValor()) + (100 - (((int)getUbicacion().getValor())%100));
            Calle calle = getCiudad().indexOf(new Coordenada(p,k),Calle.ORIENTACION_HORIZONTAL);
            nodo = new NodoEsclavo(getCiudad(), new Ubicacion(calle, p), getCoordenadaNodoFinal(),getNodoAnteFinal());
        } else if(direction < 0 && getUbicacion().getCalle().getOrientacion() == Calle.ORIENTACION_HORIZONTAL){
            k = ((int)(getUbicacion().getCalle().getId()) - 1) * 100;
            p = ((int)getUbicacion().getValor()) - ((((int)getUbicacion().getValor())%100));
            Calle calle = getCiudad().indexOf(new Coordenada(p,k),Calle.ORIENTACION_HORIZONTAL);
            nodo = new NodoEsclavo(getCiudad(), new Ubicacion(calle, p), getCoordenadaNodoFinal(),getNodoAnteFinal());
        } else nodo = null;

        return nodo;
    }
    private Nodo creaNodoAnteFinal(Ubicacion ubicacion){
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
            nodo = new NodoEsclavo(getCiudad(), new Ubicacion(calle,p), getCoordenadaNodoFinal());
        } else if(direction < 0 && ubicacion.getCalle().getOrientacion() == Calle.ORIENTACION_VERTICAL){
            p = ((int)ubicacion.getValor()) + (100 - (((int)ubicacion.getValor())%100));
            Calle calle = getCiudad().indexOf(new Coordenada(k,p),Calle.ORIENTACION_VERTICAL);
            nodo = new NodoEsclavo(getCiudad(), new Ubicacion(calle, p), getCoordenadaNodoFinal());
        } else if(direction > 0 && ubicacion.getCalle().getOrientacion() == Calle.ORIENTACION_HORIZONTAL){
            p = ((int)ubicacion.getValor()) - ((((int)ubicacion.getValor())%100));
            Calle calle = getCiudad().indexOf(new Coordenada(p,k),Calle.ORIENTACION_HORIZONTAL);
            nodo = new NodoEsclavo(getCiudad(), new Ubicacion(calle, p), getCoordenadaNodoFinal());
        } else if(direction < 0 && ubicacion.getCalle().getOrientacion() == Calle.ORIENTACION_HORIZONTAL){
            p = ((int)ubicacion.getValor()) + (100 - (((int)ubicacion.getValor())%100));
            Calle calle = getCiudad().indexOf(new Coordenada(p,k),Calle.ORIENTACION_HORIZONTAL);
            nodo = new NodoEsclavo(getCiudad(), new Ubicacion(calle, p), getCoordenadaNodoFinal());
        } else nodo = null;

        return nodo;
    }

    public List<Character> getCaminos() {
        return caminos;
    }
    public List<Ubicacion> getCaminosConUbicaciones() {
        List<Ubicacion> ubicacionList = new ArrayList<>();
        Ubicacion ubicacion = obtenerRutasPorUbicaciones(ubicacionList);
        ubicacionList.set(0,nodoFinal.getUbicacion());
        ubicacionList.add(ubicacion);
        Collections.reverse(ubicacionList);
        return ubicacionList;
    }
}
