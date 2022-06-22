package ar.com.arcom.bin;

public class Nodo {
    private Nodo anterior;
    private Ubicacion ubicacion;
    private double valorAcumulado;
    private boolean recorrido;
    private Nodo siguiente;

    public Nodo(Nodo anterior, Ubicacion ubicacion, double valorAcumulado) {
        this.anterior = anterior;
        this.ubicacion = ubicacion;
        this.valorAcumulado = valorAcumulado;
        recorrido = false;
        siguiente = null;
    }

    public Nodo(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
        anterior = null;
        siguiente = null;
        recorrido = false;
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

    /*public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }*/

    // extras
    private Nodo dondeVoy(){
        return new Nodo(null);
    }
    private int[] porDondePuedoIr(){

        return new int[]{};
    }

}
