package ar.com.arcom.bin;

public class Coordenada {
    private long x;
    private long y;

    public Coordenada(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public void setCoordenadas(long x, long y) {
        this.x = x;
        this.y = y;
    }
}
