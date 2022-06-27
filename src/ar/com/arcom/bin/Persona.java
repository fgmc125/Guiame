package ar.com.arcom.bin;

public class Persona {
    private static long cnt = 0;

    private long id;
    private Ubicacion ubicacion, destino;
    private boolean enAuto;

    public Persona(Ubicacion ubicacion, Ubicacion destino, boolean enAuto) {
        this.ubicacion = ubicacion;
        this.destino = destino;
        this.enAuto = enAuto;
        id = cnt;
        cnt++;
    }
    public Persona(Ubicacion ubicacion, boolean enAuto) {
        this.ubicacion = ubicacion;
        this.enAuto = enAuto;
        destino = null;
        id = cnt;
        cnt++;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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
    public boolean isEnAuto() {
        return enAuto;
    }
    public void setEnAuto(boolean enAuto) {
        this.enAuto = enAuto;
    }
    public static void setCnt(long cnt) {
        Persona.cnt = cnt;
    }
}
