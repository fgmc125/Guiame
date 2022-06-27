package ar.com.arcom.bin;

import java.awt.*;

public interface Dibujable {

    public void dibujar(Graphics graphics);
    public void setPrioridad(long prioridad);
    public void setWidthBox(long widthBox);
    public void setHeightBox(long heightBox);
}
