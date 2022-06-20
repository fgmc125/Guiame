package ar.com.arcom.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {

    private JPanel contentPane;
    private Ciudad ciudad;
    private boolean refresh;

    public GUI() throws HeadlessException {
        super();
        initialize();
    }

    private void initialize(){
        setResizable(true);
        setLocationRelativeTo(null);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

        setSize(621, 494);
        setLocation(pantalla.width/2-(getWidth()/2), pantalla.height/2-(getHeight()/2));

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        getContentPane().setLayout(new BorderLayout(0, 0));
        contentPane.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setMaximumSize(new Dimension(100*25,100*25));
        scrollPane.setBounds(12, 12, getWidth()-36, getHeight()-60);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        refresh = false;

        setVisible(true);
        createCity(0,0);
        scrollPane.setViewportView(ciudad);

        WorldSize2 dialog = new WorldSize2(this);
        dialog.setModal(true);
        dialog.setVisible(true);

        if (refresh) {
            setVisible(false);
            setVisible(true);
            refresh = false;
        }
    }

    public void createCity(int width, int height){
        if(ciudad!=null){
            ciudad.setWidthBox(width);
            ciudad.setHeightBox(height);
            ciudad.setPreferredSize(new Dimension(width*26,height*26));
            refresh = true;
            if(width > 0 && height > 0) ciudad.setVisibleInternalFrame(true);
        } else {
            ciudad = new Ciudad(width,height);
            ciudad.setOpaque(false);
            ciudad.addMouseListener(new EventoRaton(this.getLocationOnScreen()));
            ciudad.setBackground(Color.WHITE);
            ciudad.setPreferredSize(new Dimension(width*26,height*26));
            if(width > 0 && height > 0) ciudad.setVisibleInternalFrame(true);
        }
    }

    public class EventoRaton extends MouseAdapter {
        private static int aux_ancho = 0;
        private static int aux_alto = 0;
        private static float aux_posX=0;
        private static float aux_posY=0;
        private java.awt.Point point;

        public EventoRaton(Point point) {
            this.point = point;
        }

        public void mouseClicked(MouseEvent e) {
            if(aux_ancho==0 && aux_alto==0) {
                aux_ancho = 25;
                aux_alto = 25;
            }
            aux_posX = (float)((MouseInfo.getPointerInfo().getLocation().getX() - contentPane.getLocationOnScreen().getX())/25);
            aux_posY = (float)((MouseInfo.getPointerInfo().getLocation().getY() - contentPane.getLocationOnScreen().getY())/25);

           /* double tempX = MouseInfo.getPointerInfo().getLocation().getX();
            double tempY = MouseInfo.getPointerInfo().getLocation().getY();
            double tmpPosX = point.getX();
            double tmpPosY = point.getY();
            int x_1= (int)((tempX-tmpPosX)/25)*25;
            int y_1= (int)((tempY-tmpPosY)/25)*25;

            fix pendiente.. al usar el scroll se buggea el dibujo de las posiciones :(

            //JOptionPane.showMessageDialog(null, x_1 + " / " + y_1);*/

            int[] aux = verifica((int)(aux_posX*25)-5, (int) (aux_posY*25)-5);

            if(aux[0] >= 25 && aux[1] >= 25 && aux[0] <= ciudad.getWidthBox()*25 && aux[1] <= ciudad.getHeightBox()*25){
                if(ciudad.ftf_desde.isFocusOwner()) {
                    ciudad.setXY(aux[0], aux[1]);
                    ciudad.drawPointA = true;
                } else if(ciudad.ftf_desde.getText().equals("")) ciudad.drawPointA = false;

                if(ciudad.ftf_hasta.isFocusOwner()) {
                    ciudad.setAB(aux[0],aux[1]);
                    ciudad.drawPointB = true;
                } else if(ciudad.ftf_hasta.getText().equals("")) ciudad.drawPointB = false;
            }
            ciudad.repaint();
        }

        private int[] verifica(int x, int y){
            x -= 25; y -= 25;
            int a = ((x)/25), b = ((y)/25);

            if(x>=(a*25)+12.5f) a = (a + 1) * 25; else a *= 25;
            if(y>=(b*25)+12.5f) b = (b + 1) * 25; else b *= 25;

            if (Math.abs(x-a) <= Math.abs(y-b)) return new int[]{a+25,y+25}; else return new int[]{x+25,b+25};
        }
    }

    public static void main(String[] args) {
        try {
            GUI gui = new GUI();
            gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
            gui.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
