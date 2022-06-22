package ar.com.arcom.bin;

import java.util.List;

public class NodoMaestro extends Nodo{
    protected static Coordenada coordenadaNodoFinal;
    protected static Coordenada coordenadaInicial;
    protected static List<Double> caminos;;
    protected static Ciudad ciudad;

    private double distanciaNodoFinal;


    private Nodo nodoFinal;

    public NodoMaestro(Ubicacion ubicacion) {
        super(ubicacion);
        this.distanciaNodoFinal = distanciaNodoFinal;
        this.nodoFinal = nodoFinal;
    }

    /*public void work(Coordenada coordenada){
        NodoMaestro.coordenadaNodoFinal = coordenada;
        //NodoMaestro.coordenadaInicial = getCoordenada();

        setSiguiente(creaNodo());
    }

    private Nodo creaNodo(){
        // Por donde puedo ir?

        return new Nodo(this,new Ubicacion(calle, k));
    }*/


}
