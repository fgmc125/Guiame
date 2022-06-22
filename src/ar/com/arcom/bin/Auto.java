package ar.com.arcom.bin;

public class Auto {
    private static long idCount = 0;
    private long id, largo;
    private Ubicacion ubicacion, destino;

    private float aceleracion, velocidadDeMovimiento, velocidadDeFreno, velocidadDeGiro;
    private boolean enRuta, sePuedeMover;

    public Auto(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
        destino = null;
        id = idCount;
        idCount++;

        enRuta = false;
        sePuedeMover = false;
        largo = 5;
        aceleracion = 5;
        velocidadDeMovimiento = 5;
        velocidadDeFreno = 5;
        velocidadDeGiro = 5;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLargo() {
        return largo;
    }

    public void setLargo(long largo) {
        this.largo = largo;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Ubicacion getDestino() {
        return destino;
    }

    public void setDestino(Ubicacion destino) {
        this.destino = destino;
    }

    public float getAceleracion() {
        return aceleracion;
    }

    public void setAceleracion(float aceleracion) {
        this.aceleracion = aceleracion;
    }

    public float getVelocidadDeMovimiento() {
        return velocidadDeMovimiento;
    }

    public void setVelocidadDeMovimiento(float velocidadDeMovimiento) {
        this.velocidadDeMovimiento = velocidadDeMovimiento;
    }

    public float getVelocidadDeFreno() {
        return velocidadDeFreno;
    }

    public void setVelocidadDeFreno(float velocidadDeFreno) {
        this.velocidadDeFreno = velocidadDeFreno;
    }

    public float getVelocidadDeGiro() {
        return velocidadDeGiro;
    }

    public void setVelocidadDeGiro(float velocidadDeGiro) {
        this.velocidadDeGiro = velocidadDeGiro;
    }

    public boolean isEnRuta() {
        return enRuta;
    }

    public void setEnRuta(boolean enRuta) {
        this.enRuta = enRuta;
    }

    public boolean isSePuedeMover() {
        return sePuedeMover;
    }

    public void setSePuedeMover(boolean sePuedeMover) {
        this.sePuedeMover = sePuedeMover;
    }
}
