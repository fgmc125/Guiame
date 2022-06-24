package ar.com.arcom.bin;

import java.util.List;

public class NodoEsclavo extends Nodo {
    public NodoEsclavo(Ciudad ciudad, Ubicacion ubicacion) {
        super(ciudad, ubicacion);

    }

    public char work(){
        double num = distancia(getUbicacion().aCoordenadas(), NodoMaestro.coordenadaNodoFinal);
        List<Nodo> nodos = obtenerMovimientos();
        Nodo nodo = null;

        if(nodos.size()>1){
            for (Nodo nod : nodos) {
                if(nodo == null) nodo = nod; else if (distancia(nod.getUbicacion().aCoordenadas(), NodoMaestro.coordenadaNodoFinal)
                        < distancia(nodo.getUbicacion().aCoordenadas(), NodoMaestro.coordenadaNodoFinal)) nodo = nod;
            }
        } else nodo = nodos.get(0);

        setNodoSiguiente(nodo);
        getNodoSiguiente().setValorAcumulado(100);

        if(distancia(getUbicacion().aCoordenadas(), NodoMaestro.coordenadaNodoFinal) > 100) NodoMaestro.caminos.add(getNodoSiguiente().work());
        return getUbicacion().getCalle().getSentido();
    }
}
