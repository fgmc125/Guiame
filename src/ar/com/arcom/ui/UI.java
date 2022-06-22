package ar.com.arcom.ui;

import ar.com.arcom.Application;
import ar.com.arcom.bin.Ciudad;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UI extends JFrame {
    private Application application;
    // Atributos principales
    private GraficoCiudad graficoCiudad;
    /*
    private List<GraficoPersona> listaGraficoPersonas;
    private List<GraficoAuto> listaGraficoAutos;
    */

    // Atributos extra
    private boolean refresh;
    private JPanel contentPane;
    private JTextField jtf_barraEstado;

    // Atributos para MenuBar
    private final String[] ETIQUETAS_MENU_BAR = {"Archivo","Ver","Herramientas","Ayuda"};
    private final String[] ETIQUETAS_MENU_ITEMS = {"0_Nueva Ciudad","0*","0_Salir","1_Informacion general",
            "1_Panel de personas","1_Panel de autos","1*","1_Tablas de datos","2_Controles","3_Acerca de..."};
    private ArrayList<JMenu> componentArrayList;
    private ArrayList<JMenuItem> menuItemArrayList;
    // Fin atributos para MenuBar

    public UI(Application application) throws HeadlessException {
        super();
        this.application = application;
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

        // Inicio de la barra de estado
        jtf_barraEstado = new JTextField();
        jtf_barraEstado.setHorizontalAlignment(SwingConstants.RIGHT);
        jtf_barraEstado.setEditable(false);
        jtf_barraEstado.setBounds(503, 414, 104, 20);
        contentPane.add(jtf_barraEstado,BorderLayout.SOUTH);
        jtf_barraEstado.setColumns(20);
        // Fin barra de estado

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setMaximumSize(new Dimension(100*25,100*25));
        scrollPane.setBounds(12, 12, getWidth()-36, getHeight()-60);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        refresh = false;

        initializeMenuBar();

        setVisible(true);

        createCity(0,0);
        scrollPane.setViewportView(graficoCiudad);

        createPanel();
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

    public void setTextBarraEstado(String texto) {
        jtf_barraEstado.setText(texto);
    }

    public void createPanel() {
        JDialog dialog = new DialogCreaCiudad(this);
        dialog.setModal(true);
        dialog.setVisible(true);

        dialog = new DialogCreaPersonas(this);
        dialog.setModal(true);
        dialog.setVisible(true);

        if (refresh) {
            setVisible(false);
            setVisible(true);
            refresh = false;
        }

        if(application.getCiudad().getAncho() > 0 && application.getCiudad().getAlto() > 0) graficoCiudad.setVisibleInternalFrame(true);
    }

    public void createPeople(int i) {
        application.inicializarListaPersonasAleatorio(i);
        application.inicializarListaAutos(i);
    }

    public void createCity(long width, long height){
        if(graficoCiudad !=null){
            graficoCiudad.setWidthBox(width);
            graficoCiudad.setHeightBox(height);
            graficoCiudad.setPreferredSize(new Dimension((int)width*26,(int)height*26));
            refresh = true;
            application.setCiudad(new Ciudad(width,height));
        } else {
            graficoCiudad = new GraficoCiudad(width,height);
            graficoCiudad.setOpaque(false);
            graficoCiudad.addMouseListener(new EventoRaton(this.getLocationOnScreen()));
            graficoCiudad.setBackground(Color.WHITE);
            graficoCiudad.setPreferredSize(new Dimension((int)width*26,(int)height*26));
        }
    }

    public UI getThis(){
        return this;
    }

    public Application getApplication() {
        return application;
    }

    public void salir(){

    }

    private class EventoBoton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String ac = e.getActionCommand();
            if(ac.equals("cmd_invert")) {
            } else if(ac.equals("cmd_salir")) {
                System.exit(0);
            } else if(ac.equals("cmd_informacion general")) {
                graficoCiudad.displayInternalFrame(new InternalFrameVistaGeneral("Vista General"));
            } else if(ac.equals("cmd_panel de personas")) {
                graficoCiudad.displayInternalFrame(new InternalFramePersonas("Panel de Personas"));
            } else if(ac.equals("cmd_panel de autos")) {
                graficoCiudad.displayInternalFrame(new InternalFramePersonas("Panel de Autos"));
            } else if(ac.equals("cmd_controles")) {
                graficoCiudad.displayInternalFrame(new InternalFramePersonas("Controles"));
            } else if(ac.equals("cmd_acerca de...")) {
                graficoCiudad.displayInternalFrame(new InternalFramePersonas("Acerca de..."));
            } else if(ac.equals("cmd_nueva ciudad")) {
                createPanel();
            } else if(ac.equals("cmd_tablas de datos")) {
                JDialog dialog = new DialogVerPersonas(getThis());
                dialog.setModal(false);
                dialog.setVisible(true);
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

            if(aux[0] >= 25 && aux[1] >= 25 && aux[0] <= graficoCiudad.getWidthBox()*25 && aux[1] <= graficoCiudad.getHeightBox()*25){
                if(graficoCiudad.ftf_desde.isFocusOwner()) {
                    graficoCiudad.setXY(aux[0], aux[1]);
                    graficoCiudad.drawPointA = true;
                } else if(graficoCiudad.ftf_desde.getText().equals("")) graficoCiudad.drawPointA = false;

                if(graficoCiudad.ftf_hasta.isFocusOwner()) {
                    graficoCiudad.setAB(aux[0],aux[1]);
                    graficoCiudad.drawPointB = true;
                } else if(graficoCiudad.ftf_hasta.getText().equals("")) graficoCiudad.drawPointB = false;
            }
            graficoCiudad.repaint();
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