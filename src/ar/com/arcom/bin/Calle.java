package ar.com.arcom.bin;

public class Calle {
    public final static char DIR_NORTE = 'N', DIR_SUR = 'S', DIR_ESTE = 'E', DIR_OESTE = 'O';
    public final static char ORIENTACION_VERTICAL = 'V', ORIENTACION_HORIZONTAL = 'H';
    private static long idCout = 0;

    private long id;
    private String nombre;
    private char orientacion, sentido;

    public Calle(char orientacion, char sentido) {
        this.orientacion = orientacion;
        this.sentido = sentido;
        id = idCout;
        idCout++;
        generarNombre();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public char getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(char orientacion) {
        this.orientacion = orientacion;
    }

    public char getSentido() {
        return sentido;
    }

    public void setSentido(char sentido) {
        this.sentido = sentido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void generarNombre(){
        nombre = "Calle " + ((orientacion == 'h') ? "Horizontal" : "Vertical");
    }
}
