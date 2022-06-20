package ar.com.arcom.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GUI extends JFrame {

    private JPanel contentPane;
    private LaminaCiudad laminaCiudad;
    private boolean refresh;

    // Atributos para MenuBar
    private final String[] ETIQUETAS_MENU_BAR = {"Archivo","Ver","Herramientas","Ayuda"};
    private final String[] ETIQUETAS_MENU_ITEMS = {"0_Nueva laminaCiudad","0*","0_Salir","1_Informacion general",
            "1_Panel de personas","1_Panel de autos","1*","1_Tablas de datos","2_Controles","3_Acerca de..."};
    private ArrayList<JMenu> componentArrayList;
    private ArrayList<JMenuItem> menuItemArrayList;
    public GUI() throws HeadlessException {
        super();
        initialize();
    }

    private void initialize(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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

        initializeMenuBar();

        setVisible(true);
        createCity(0,0);
        scrollPane.setViewportView(laminaCiudad);

        DialogCreaCiudad dialog = new DialogCreaCiudad(this);
        dialog.setModal(true);
        dialog.setVisible(true);

        if (refresh) {
            setVisible(false);
            setVisible(true);
            refresh = false;
        }
    }

    public void createCity(int width, int height){
        if(laminaCiudad !=null){
            laminaCiudad.setWidthBox(width);
            laminaCiudad.setHeightBox(height);
            laminaCiudad.setPreferredSize(new Dimension(width*26,height*26));
            refresh = true;
            if(width > 0 && height > 0) laminaCiudad.setVisibleInternalFrame(true);
        } else {
            laminaCiudad = new LaminaCiudad(width,height);
            laminaCiudad.setOpaque(false);
            laminaCiudad.addMouseListener(new EventoRaton(this.getLocationOnScreen()));
            laminaCiudad.setBackground(Color.WHITE);
            laminaCiudad.setPreferredSize(new Dimension(width*26,height*26));
            if(width > 0 && height > 0) laminaCiudad.setVisibleInternalFrame(true);
        }
    }

    private void initializeMenuBar(){
        componentArrayList = new ArrayList<>() ;
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuItemArrayList = new ArrayList<>();

        for(int i = 0; i<ETIQUETAS_MENU_BAR.length;i++){
            componentArrayList.add(new JMenu(ETIQUETAS_MENU_BAR[i]));
            menuBar.add(componentArrayList.get(i));
        }
        for(int i = 0; i<ETIQUETAS_MENU_ITEMS.length;i++){
            if(ETIQUETAS_MENU_ITEMS[i].charAt(1) != '*'){
                menuItemArrayList.add(new JMenuItem(ETIQUETAS_MENU_ITEMS[i].substring(ETIQUETAS_MENU_ITEMS[i].indexOf("_")+1)));
                menuItemArrayList.get(i).addActionListener(new EventoBoton());
                menuItemArrayList.get(i).setActionCommand("cmd_"+ ETIQUETAS_MENU_ITEMS[i].substring(ETIQUETAS_MENU_ITEMS[i].indexOf("_")+1).toLowerCase());
                System.out.println(menuItemArrayList.get(i).getActionCommand());
                componentArrayList.get(Integer.parseInt(""+ETIQUETAS_MENU_ITEMS[i].charAt(0))).add(menuItemArrayList.get(i));
            } else {
                menuItemArrayList.add(null);
                (componentArrayList.get(Integer.parseInt(""+ETIQUETAS_MENU_ITEMS[i].charAt(0)))).add(new JSeparator());
            }
        }
    }

    private class EventoBoton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String ac = e.getActionCommand();
            System.out.println(ac);
            if(ac.equals("cmd_invert")) {

            } else if(ac.equals("cmd_salir")) {

            } else if(ac.equals("cmd_informacion general")) {
                laminaCiudad.displayInternalFrame(new InternalFrameVistaGeneral("Vista General"));
            } else if(ac.equals("cmd_panel de personas")) {
                laminaCiudad.displayInternalFrame(new InternalFramePersonas("Panel de Personas"));
            }

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

            if(aux[0] >= 25 && aux[1] >= 25 && aux[0] <= laminaCiudad.getWidthBox()*25 && aux[1] <= laminaCiudad.getHeightBox()*25){
                if(laminaCiudad.ftf_desde.isFocusOwner()) {
                    laminaCiudad.setXY(aux[0], aux[1]);
                    laminaCiudad.drawPointA = true;
                } else if(laminaCiudad.ftf_desde.getText().equals("")) laminaCiudad.drawPointA = false;

                if(laminaCiudad.ftf_hasta.isFocusOwner()) {
                    laminaCiudad.setAB(aux[0],aux[1]);
                    laminaCiudad.drawPointB = true;
                } else if(laminaCiudad.ftf_hasta.getText().equals("")) laminaCiudad.drawPointB = false;
            }
            laminaCiudad.repaint();
        }

        private int[] verifica(int x, int y){
            x -= 25; y -= 25;
            int a = ((x)/25), b = ((y)/25);

            if(x>=(a*25)+12.5f) a = (a + 1) * 25; else a *= 25;
            if(y>=(b*25)+12.5f) b = (b + 1) * 25; else b *= 25;

            if (Math.abs(x-a) <= Math.abs(y-b)) return new int[]{a+25,y+25}; else return new int[]{x+25,b+25};
        }
    }
}