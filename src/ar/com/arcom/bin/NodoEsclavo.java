package ar.com.arcom.bin;

import java.util.List;

public class NodoEsclavo extends Nodo {

    public NodoEsclavo(Ciudad ciudad, Ubicacion ubicacion, Coordenada coordenadaNodoFinal, Nodo nodoAnteFinal) {
        super(ciudad, ubicacion, coordenadaNodoFinal, nodoAnteFinal);
    }

    public NodoEsclavo(Ciudad ciudad, Ubicacion ubicacion) {
        super(ciudad, ubicacion);
    }

    public NodoEsclavo(Ciudad ciudad, Ubicacion ubicacion, Coordenada coordenadaNodoFinal) {
        super(ciudad, ubicacion, coordenadaNodoFinal);
    }

    public void work(){
        List<Nodo> nodos = obtenerMovimientos();
        Nodo nodo = null;

        setRutas(nodos);

        if(nodos.size()>1){
            for (Nodo nodoAux : nodos) {
                if(nodo == null) nodo = nodoAux; else if (distancia(nodoAux.getUbicacion().aCoordenadas(), getCoordenadaNodoFinal())
                        < distancia(nodo.getUbicacion().aCoordenadas(), getCoordenadaNodoFinal())) nodo = nodoAux;
            }
        } else {
            setRecorrido(true);
            nodo = nodos.get(0);
        }

        Nodo nodoAuy = buscaNodoSimilar(getUbicacion());

        if (nodoAuy == null){
            setNodoSiguiente(nodo);
            getNodoSiguiente().setValorAcumulado(getValorAcumulado()+100);
            if(distancia(getUbicacion().aCoordenadas(), getNodoAnteFinal().getUbicacion().aCoordenadas()) > 99){
                getNodoSiguiente().work();
                //NodoMaestro.caminos.add(getNodoSiguiente().work());
            }
        } else {
            /* PROBLEMA!
            al no verificar si su peso es menor que el que esta ocupado en la igualdad, ocurre que evita ir a es enodoy lo rodea...
            genera un camino mucho mas largo
            solucion: implementar un if como el de abajo ero bien hecho
            "if (getValorAcumulado() < nodoAuy.getValorAcumulado()){};"
            */
            setTuboProblemas(true);
            nodoAuy = buscaNodoNoRecorrido();
            if(nodoAuy != null){
                nodoAuy.setNodoSiguiente(nodoAuy.siguienteRuta(getUbicacion()));
                if(distancia(getUbicacion().aCoordenadas(), getNodoAnteFinal().getUbicacion().aCoordenadas()) > 99) {
                    nodoAuy.getNodoSiguiente().work();
                    //NodoMaestro.caminos.add();
                }
            }
        }

        return;
        //return getUbicacion().getCalle().getSentido();
    }



}
