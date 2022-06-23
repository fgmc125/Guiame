package ar.com.arcom.bin;

public class Ubicacion {
    private Calle calle;
    private long valor;

    public Ubicacion(Calle calle, long valor) {
        this.calle = calle;
        this.valor = valor;
    }

    public Calle getCalle() {
        return calle;
    }

    public void setCalle(Calle calle) {
        this.calle = calle;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public Coordenada aCoordenadas() {
        Coordenada coordenada = (calle.getNombre().contains("Calle Horizontal")) ? new Coordenada(valor, (calle.getId()*100L)-100) : new Coordenada((calle.getId()*100L)-100, valor);
        return coordenada;
    }
    public void aumentaValor(long valor){
        this.valor += valor;
    }

}
