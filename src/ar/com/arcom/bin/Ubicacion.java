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

    public boolean esIgual(Ubicacion ubicacion) {
        if(getCalle().getNombre().contains("Horizontal") && ubicacion.getCalle().getNombre().contains("Horizontal")){
            if (getCalle().getId() == ubicacion.getCalle().getId() && valor == ubicacion.getValor()) {
                return true;
            }else return false;
        } else if(getCalle().getNombre().contains("Vertical") && ubicacion.getCalle().getNombre().contains("Vertical")){
            if (getCalle().getId() == ubicacion.getCalle().getId() && valor == ubicacion.getValor()) {
                return true;
            }else return false;
        } else if (esIgualAsuCortante(ubicacion)) return true; else return false;
    }

    private boolean esIgualAsuCortante(Ubicacion ubicacion){
        long x,y,a,b;
        if(getCalle().getNombre().contains("Horizontal")){
            x = getValor();
            y = (getCalle().getId()-1)*100;
            a = (ubicacion.getCalle().getId()-1)*100;
            b = ubicacion.getValor();
            if(x == a && y == b) return true; else return false;
        } else {
            x = (getCalle().getId()-1)*100;
            y = getValor();
            a = ubicacion.getValor();
            b = (ubicacion.getCalle().getId()-1)*100;
            if(x == a && y == b) return true; else return false;
        }
    }
}
