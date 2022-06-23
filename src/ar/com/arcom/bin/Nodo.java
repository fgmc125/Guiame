package ar.com.arcom.bin;

import ar.com.arcom.Application;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
    private Nodo anterior;
    private Ubicacion ubicacion;
    private double valorAcumulado;
    private boolean recorrido;
    private Nodo siguiente;

    private Application application;

    public Nodo(Nodo anterior, Ubicacion ubicacion, double valorAcumulado) {
        this.anterior = anterior;
        this.ubicacion = ubicacion;
        this.valorAcumulado = valorAcumulado;
        recorrido = false;
        siguiente = null;

    }

    public Nodo(Ubicacion ubicacion, Application application) {
        this.ubicacion = ubicacion;
        anterior = null;
        siguiente = null;
        recorrido = false;
        this.application = application;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }

    public double getValorAcumulado() {
        return valorAcumulado;
    }

    public void setValorAcumulado(double valorAcumulado) {
        this.valorAcumulado = valorAcumulado;
    }

    public boolean isRecorrido() {
        return recorrido;
    }

    public void setRecorrido(boolean recorrido) {
        this.recorrido = recorrido;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public static double calularDistanciaEntreNodos(){
        return 0D;
    }

    public double distancia(double x,double y,double a,double b){
        return Math.abs(Math.sqrt(Math.pow(a-x,2) + Math.pow(b-y,2)));
    }

    public double distancia(Coordenada p,Coordenada q){
        return Math.abs(Math.sqrt(Math.pow(q.getX() - p.getX(),2) + Math.pow(q.getY()- p.getY(),2)));
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
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
            Calle calle = application.getCiudad().indexOf(new Coordenada(k,p), Calle.ORIENTACION_VERTICAL);
            nodo = new Nodo(new Ubicacion(calle,p), getApplication());
        } else if(direction < 0 && ubicacion.getCalle().getOrientacion() == Calle.ORIENTACION_VERTICAL){
            k = ((int)(ubicacion.getCalle().getId()) - 1) * 100;
            p = ((int)ubicacion.getValor()) - ((((int)ubicacion.getValor())%100));
            Calle calle = application.getCiudad().indexOf(new Coordenada(k,p),Calle.ORIENTACION_VERTICAL);
            nodo = new Nodo(new Ubicacion(calle, p), getApplication());
        } else if(direction > 0 && ubicacion.getCalle().getOrientacion() == Calle.ORIENTACION_HORIZONTAL){
            k = ((int)(ubicacion.getCalle().getId()) - 1) * 100;
            p = ((int)ubicacion.getValor()) + (100 - (((int)ubicacion.getValor())%100));
            Calle calle = application.getCiudad().indexOf(new Coordenada(p,k),Calle.ORIENTACION_HORIZONTAL);
            nodo = new Nodo(new Ubicacion(calle, p), getApplication());
        } else if(direction < 0 && ubicacion.getCalle().getOrientacion() == Calle.ORIENTACION_HORIZONTAL){
            k = ((int)(ubicacion.getCalle().getId()) - 1) * 100;
            p = ((int)ubicacion.getValor()) - ((((int)ubicacion.getValor())%100));
            Calle calle = application.getCiudad().indexOf(new Coordenada(p,k),Calle.ORIENTACION_HORIZONTAL);
            nodo = new Nodo(new Ubicacion(calle, p), getApplication());
        } else nodo = null;

        return nodo;
    }
    public Application getApplication() {
        return application;
    }

    public char work(){
        double num = distancia(getUbicacion().aCoordenadas(), NodoMaestro.coordenadaNodoFinal);
        List<Nodo> nodos = obtenerMovimientos();
        Nodo node = null;
        if(nodos.size()>1){
            for (Nodo nodo : nodos) {
                if(node == null) node = nodo; else if (distancia(nodo.getUbicacion().aCoordenadas(), NodoMaestro.coordenadaNodoFinal)
                        < distancia(node.getUbicacion().aCoordenadas(), NodoMaestro.coordenadaNodoFinal)) node = nodo;
            }
        } else node = nodos.get(0);

        setSiguiente(node);
        getSiguiente().setValorAcumulado(100);
        if(distancia(getUbicacion().aCoordenadas(), NodoMaestro.coordenadaNodoFinal) > 100) NodoMaestro.caminos.add(getSiguiente().work());
        return getUbicacion().getCalle().getSentido();
    }

    private List<Nodo> obtenerMovimientos(){
        List<Nodo> nodos = new ArrayList<>();
        Ubicacion ubicacion = null;

        if(getUbicacion().getCalle().getNombre().contains("Horizontal") && getUbicacion().getCalle().getSentido() == Calle.DIR_OESTE){
            if(getUbicacion().getValor() - 50 > 0) {
                ubicacion = new Ubicacion(getUbicacion().getCalle(),getUbicacion().getValor()-50);
                nodos.add(creaNodo(ubicacion));
                System.out.println("[1] Horizontal : Oeste : verdarero");
            }
            Calle calle = application.getCiudad().indexOf(getUbicacion().aCoordenadas(),Calle.ORIENTACION_VERTICAL);
            ubicacion = new Ubicacion(calle,(calle.getSentido() == Calle.DIR_NORTE) ? getUbicacion().aCoordenadas().getY()-50 : getUbicacion().aCoordenadas().getY()+50);
            nodos.add(creaNodo(ubicacion));
            System.out.println("[2] Horizontal : Oeste : Siguiente");
        }
        if(getUbicacion().getCalle().getNombre().contains("Horizontal") && getUbicacion().getCalle().getSentido() == Calle.DIR_ESTE){
            if(getUbicacion().getValor() + 50 > (application.getCiudad().getAncho()-1)*100){
                ubicacion = new Ubicacion(getUbicacion().getCalle(),getUbicacion().getValor()+50);
                nodos.add(creaNodo(ubicacion));
                System.out.println("[3] Horizontal : Este : verdarero");
            }
            System.out.println("[4] Horizontal : Este : Siguiente");
            Calle calle = application.getCiudad().indexOf(getUbicacion().aCoordenadas(),Calle.ORIENTACION_VERTICAL);
            ubicacion = new Ubicacion(calle,(calle.getSentido() == Calle.DIR_NORTE) ? getUbicacion().aCoordenadas().getY()-50 : getUbicacion().aCoordenadas().getY()+50);
            nodos.add(creaNodo(ubicacion));
        }

        if(getUbicacion().getCalle().getNombre().contains("Vertical") && getUbicacion().getCalle().getSentido() == Calle.DIR_NORTE){
            if(getUbicacion().getValor() - 50 > 0) {
                ubicacion = new Ubicacion(getUbicacion().getCalle(),getUbicacion().getValor()-50);
                nodos.add(creaNodo(ubicacion));
                System.out.println("[5] Vertical : Norte : verdarero");
            }
            System.out.println("[6] Vertical : Norte : Siguiente");
            Calle calle = application.getCiudad().indexOf(getUbicacion().aCoordenadas(),Calle.ORIENTACION_HORIZONTAL);
            ubicacion = new Ubicacion(calle,(calle.getSentido() == Calle.DIR_OESTE) ? getUbicacion().aCoordenadas().getX()-50 : getUbicacion().aCoordenadas().getX()+50);
            nodos.add(creaNodo(ubicacion));
        }
        if(getUbicacion().getCalle().getNombre().contains("Vertical") && getUbicacion().getCalle().getSentido() == Calle.DIR_SUR){
            if(getUbicacion().getValor() + 50 < (application.getCiudad().getAlto()-1) * 100){
                ubicacion = new Ubicacion(getUbicacion().getCalle(),getUbicacion().getValor() + 50);
                nodos.add(creaNodo(ubicacion));
                System.out.println("[7] Vertical : Sur : verdarero");
            }
            System.out.println("[8] Vertical : Sur : Siguiente");
            Calle calle = application.getCiudad().indexOf(getUbicacion().aCoordenadas(),Calle.ORIENTACION_HORIZONTAL);
            ubicacion = new Ubicacion(calle,(calle.getSentido() == Calle.DIR_OESTE) ? getUbicacion().aCoordenadas().getX()-50 : getUbicacion().aCoordenadas().getX()+50);
            nodos.add(creaNodo(ubicacion));
        }
        System.out.println("tamanio de nodos posibilidades: " + nodos.size());
        return nodos;
    }
}
