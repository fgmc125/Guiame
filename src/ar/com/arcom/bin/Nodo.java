package ar.com.arcom.bin;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class Nodo {

    private Ciudad ciudad;
    private Nodo NodoAnterior;
    private long valorAcumulado;
    private Ubicacion ubicacion;
    private boolean recorrido;
    private List<Nodo> rutas;
    private Nodo nodoSiguiente;

    public Nodo(Ciudad ciudad, Ubicacion ubicacion) {
        this.ciudad = ciudad;
        this.NodoAnterior = null;
        this.ubicacion = ubicacion;
        this.valorAcumulado = 0;
        rutas = new ArrayList<>();
        recorrido = false;
        nodoSiguiente = null;
    }

    public char work() {
        return 0;
    }

    protected List<Nodo> obtenerMovimientos(){
        List<Nodo> nodos = new ArrayList<>();
        Ubicacion ubicacion = null;

        if(getUbicacion().getCalle().getNombre().contains("Horizontal") && getUbicacion().getCalle().getSentido() == Calle.DIR_OESTE){
            if(getUbicacion().getValor() - 50 > 0) {
                ubicacion = new Ubicacion(getUbicacion().getCalle(),getUbicacion().getValor()-50);
                nodos.add(creaNodo(ubicacion));
                System.out.println("[1] Horizontal : Oeste : verdarero");
            }
            Calle calle = ciudad.indexOf(getUbicacion().aCoordenadas(),Calle.ORIENTACION_VERTICAL);
            ubicacion = new Ubicacion(calle,(calle.getSentido() == Calle.DIR_NORTE) ? getUbicacion().aCoordenadas().getY()-50 : getUbicacion().aCoordenadas().getY()+50);
            if(ubicacion.getValor() > 0 && ubicacion.getValor() < (ciudad.getAlto()-1)*100) {
                nodos.add(creaNodo(ubicacion));
                System.out.println("[2] Horizontal : Oeste : Siguiente");
            }
        }
        if(getUbicacion().getCalle().getNombre().contains("Horizontal") && getUbicacion().getCalle().getSentido() == Calle.DIR_ESTE){
            if(getUbicacion().getValor() + 50 > (ciudad.getAncho()-1)*100){
                ubicacion = new Ubicacion(getUbicacion().getCalle(),getUbicacion().getValor()+50);
                nodos.add(creaNodo(ubicacion));
                System.out.println("[3] Horizontal : Este : verdarero");
            }
            Calle calle = ciudad.indexOf(getUbicacion().aCoordenadas(),Calle.ORIENTACION_VERTICAL);
            ubicacion = new Ubicacion(calle,(calle.getSentido() == Calle.DIR_NORTE) ? getUbicacion().aCoordenadas().getY()-50 : getUbicacion().aCoordenadas().getY()+50);
            if(ubicacion.getValor() > 0 && ubicacion.getValor() < (ciudad.getAlto()-1)*100) {
                nodos.add(creaNodo(ubicacion));
                System.out.println("[4] Horizontal : Este : Siguiente");
            }
        }

        if(getUbicacion().getCalle().getNombre().contains("Vertical") && getUbicacion().getCalle().getSentido() == Calle.DIR_NORTE){
            if(getUbicacion().getValor() - 50 > 0) {
                ubicacion = new Ubicacion(getUbicacion().getCalle(),getUbicacion().getValor()-50);
                nodos.add(creaNodo(ubicacion));
                System.out.println("[5] Vertical : Norte : verdarero");
            }
            Calle calle = ciudad.indexOf(getUbicacion().aCoordenadas(),Calle.ORIENTACION_HORIZONTAL);
            ubicacion = new Ubicacion(calle,(calle.getSentido() == Calle.DIR_OESTE) ? getUbicacion().aCoordenadas().getX()-50 : getUbicacion().aCoordenadas().getX()+50);
            if(ubicacion.getValor() > 0 && ubicacion.getValor() < (ciudad.getAncho()-1)*100) {
                System.out.println("[6] Vertical : Norte : Siguiente");
                nodos.add(creaNodo(ubicacion));
            }
        }
        if(getUbicacion().getCalle().getNombre().contains("Vertical") && getUbicacion().getCalle().getSentido() == Calle.DIR_SUR){
            if(getUbicacion().getValor() + 50 < (ciudad.getAlto()-1) * 100){
                ubicacion = new Ubicacion(getUbicacion().getCalle(),getUbicacion().getValor() + 50);
                nodos.add(creaNodo(ubicacion));
                System.out.println("[7] Vertical : Sur : verdarero");
            }
            Calle calle = ciudad.indexOf(getUbicacion().aCoordenadas(),Calle.ORIENTACION_HORIZONTAL);
            ubicacion = new Ubicacion(calle,(calle.getSentido() == Calle.DIR_OESTE) ? getUbicacion().aCoordenadas().getX()-50 : getUbicacion().aCoordenadas().getX()+50);
            if(ubicacion.getValor() > 0 && ubicacion.getValor() < (ciudad.getAncho()-1)*100) {
                System.out.println("[8] Vertical : Sur : Siguiente");
                nodos.add(creaNodo(ubicacion));
            }
        }
        System.out.println("tamanio de nodeborrars posibilidades: " + nodos.size());
        return nodos;
    }
    private Nodo creaNodo(Ubicacion ubicacion){
        int direction, k,p;
        Nodo nodo;
        switch (ubicacion.getCalle().getSentido()) {
            case 'N', 'O' -> direction = -1;
            case 'S', 'E' -> direction = 1;
            default -> direction = 0;
        }

        if(direction > 0 && ubicacion.getCalle().getOrientacion() == Calle.ORIENTACION_VERTICAL){
            k = ((int)(ubicacion.getCalle().getId()) - 1) * 100;
            p = ((int)ubicacion.getValor()) + (100 - (((int)ubicacion.getValor())%100));
            Calle calle = ciudad.indexOf(new Coordenada(k,p), Calle.ORIENTACION_VERTICAL);
            nodo = new NodoEsclavo(ciudad, new Ubicacion(calle,p));
        } else if(direction < 0 && ubicacion.getCalle().getOrientacion() == Calle.ORIENTACION_VERTICAL){
            k = ((int)(ubicacion.getCalle().getId()) - 1) * 100;
            p = ((int)ubicacion.getValor()) - ((((int)ubicacion.getValor())%100));
            Calle calle = ciudad.indexOf(new Coordenada(k,p),Calle.ORIENTACION_VERTICAL);
            nodo = new NodoEsclavo(ciudad, new Ubicacion(calle, p));
        } else if(direction > 0 && ubicacion.getCalle().getOrientacion() == Calle.ORIENTACION_HORIZONTAL){
            k = ((int)(ubicacion.getCalle().getId()) - 1) * 100;
            p = ((int)ubicacion.getValor()) + (100 - (((int)ubicacion.getValor())%100));
            Calle calle = ciudad.indexOf(new Coordenada(p,k),Calle.ORIENTACION_HORIZONTAL);
            nodo = new NodoEsclavo(ciudad,new Ubicacion(calle, p));
        } else if(direction < 0 && ubicacion.getCalle().getOrientacion() == Calle.ORIENTACION_HORIZONTAL){
            k = ((int)(ubicacion.getCalle().getId()) - 1) * 100;
            p = ((int)ubicacion.getValor()) - ((((int)ubicacion.getValor())%100));
            Calle calle = ciudad.indexOf(new Coordenada(p,k),Calle.ORIENTACION_HORIZONTAL);
            nodo = new NodoEsclavo(ciudad, new Ubicacion(calle, p));
        } else nodo = null;

        return nodo;
    }

    public static double distancia(double x,double y,double a,double b){
        return Math.abs(Math.sqrt(Math.pow(a-x,2) + Math.pow(b-y,2)));
    }
    public static double distancia(@NotNull Coordenada p, @NotNull Coordenada q){
        return Math.abs(Math.sqrt(Math.pow(q.getX() - p.getX(),2) + Math.pow(q.getY()- p.getY(),2)));
    }

    public Ciudad getCiudad() {
        return ciudad;
    }
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    public Nodo getNodoAnterior() {
        return NodoAnterior;
    }
    public void setNodoAnterior(Nodo nodoAnterior) {
        NodoAnterior = nodoAnterior;
    }
    public long getValorAcumulado() {
        return valorAcumulado;
    }
    public void setValorAcumulado(long valorAcumulado) {
        this.valorAcumulado = valorAcumulado;
    }
    public Ubicacion getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
    public boolean isRecorrido() {
        return recorrido;
    }
    public void setRecorrido(boolean recorrido) {
        this.recorrido = recorrido;
    }
    public List<Nodo> getRutas() {
        return rutas;
    }
    public void setRutas(List<Nodo> rutas) {
        this.rutas = rutas;
    }
    public Nodo getNodoSiguiente() {
        return nodoSiguiente;
    }
    public void setNodoSiguiente(Nodo nodoSiguiente) {
        this.nodoSiguiente = nodoSiguiente;
    }


}
