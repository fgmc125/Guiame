package ar.com.arcom.bin;

public class Ubicacion {
    private Calle calle;
    private double valor;

    public Ubicacion(Calle calle, double valor) {
        this.calle = calle;
        this.valor = valor;
    }

    public Calle getCalle() {
        return calle;
    }

    public void setCalle(Calle calle) {
        this.calle = calle;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
