package ar.com.arcom.bin;

import java.util.List;
import java.util.TimerTask;

public interface Movedizo {
    public void trasladar(Coordenada coordenada);
    public void trasladarX(long x);
    public void trasladarY(long y);
    public void setCamino(List<Character> caminos);
    public List<Character> getCamino();
    void moverse(TimerTask timerTask);
}
