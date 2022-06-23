package ar.com.arcom.ui;

import ar.com.arcom.Application;
import ar.com.arcom.bin.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class GraficoCiudad extends JComponent {
    private Application application;

    public boolean drawPointA, drawPointB;
    public int x,y,a,b;

    private long widthBox, heightBox;
    private JInternalFrame internalFrame;
    public JFormattedTextField ftf_desde, ftf_hasta;
    private String desde, hasta;

    private Ubicacion ubicacionDesde, ubicacionHasta;


    private List<GraficoAuto> graficoAutoList;


    public GraficoCiudad(long widthBox, long heightBox, Application application) {
        super();
        this.setLayout(null);
        this.application = application;
        drawPointA = false;
        this.widthBox = widthBox +1;
        this.heightBox = heightBox +1;
        graficoAutoList = new ArrayList<>();
        desde = "";
        hasta = "";

        setLayout(null);

        creaInternalFrameDefault();
    }

    public void creaInternalFrameDefault(){
        internalFrame = new JInternalFrame("Como llegar");
        internalFrame.setBounds(48, 12, 240, 241);
        internalFrame.setContentPane(new JPanel());
        internalFrame.getContentPane().setLayout(null);
        internalFrame.setClosable(true);
        add(internalFrame);

        JPanel panel_1_1 = new JPanel();
        panel_1_1.setLayout(null);
        panel_1_1.setBounds(0, 0, 230, 170);
        internalFrame.setContentPane(panel_1_1);

        JButton jbn_calcularRuta = new JButton("Calcular ruta");
        jbn_calcularRuta.addActionListener(new GraficoCiudad.EventoBoton());
        jbn_calcularRuta.setActionCommand("cmd_calcular");
        jbn_calcularRuta.setBounds(12, 171, 209, 21);
        panel_1_1.add(jbn_calcularRuta);

        JLabel lblNewLabel_2 = new JLabel("Como llegar...");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(12, 12, 209, 21);
        panel_1_1.add(lblNewLabel_2);

        JCheckBox chckbxNewCheckBox_1 = new JCheckBox("La más corta");
        chckbxNewCheckBox_1.setSelected(true);
        chckbxNewCheckBox_1.setBounds(12, 139, 100, 21);
        panel_1_1.add(chckbxNewCheckBox_1);

        ftf_desde = new JFormattedTextField();
        ftf_desde.setBounds(12, 85, 208, 20);
        panel_1_1.add(ftf_desde);

        ftf_hasta = new JFormattedTextField();
        ftf_hasta.setBounds(12, 111, 208, 20);
        panel_1_1.add(ftf_hasta);

        JTextPane txtpnIngreseElTamao_1 = new JTextPane();
        txtpnIngreseElTamao_1.setText("Ingrese punto de partida y destino, o haga clic en el ciudad");
        txtpnIngreseElTamao_1.setOpaque(false);
        txtpnIngreseElTamao_1.setMargin(new Insets(0, 0, 0, 0));
        txtpnIngreseElTamao_1.setFocusable(false);
        txtpnIngreseElTamao_1.setFocusTraversalKeysEnabled(false);
        txtpnIngreseElTamao_1.setFocusCycleRoot(false);
        txtpnIngreseElTamao_1.setEditable(false);
        txtpnIngreseElTamao_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        txtpnIngreseElTamao_1.setBounds(12, 35, 209, 42);
        panel_1_1.add(txtpnIngreseElTamao_1);

        JButton btnNewButton_1_1 = new JButton("Invertir");
        btnNewButton_1_1.addActionListener(new GraficoCiudad.EventoBoton());
        btnNewButton_1_1.setActionCommand("cmd_invert");
        btnNewButton_1_1.setBounds(118, 138, 100, 21);
        panel_1_1.add(btnNewButton_1_1);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g;
        //graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //graphics2D.setColor(new Color(221,221,221,100));
        int ANCHO_CUADRO = 25;
        for(int i = ANCHO_CUADRO; i < widthBox *25; i+= ANCHO_CUADRO) {
            graphics2D.setColor(Color.GRAY);
            graphics2D.drawLine(i,0,i,25);
            graphics2D.setColor(Color.DARK_GRAY);
            graphics2D.drawLine(i, 25, i,  (int)heightBox *25);
            if(i%2 == 0) graphics2D.fillPolygon(new int[]{i,i+3,i-3},new int[]{0,8,8},3);
            else graphics2D.fillPolygon(new int[]{i,i+3,i-3},new int[]{(int) heightBox *25,((int) heightBox *25)-8,((int) heightBox *25)-8},3);
        }
        int ALTO_CUADRO = 25;
        for(int i = ANCHO_CUADRO; i < heightBox *25; i+= ALTO_CUADRO) {
            graphics2D.setColor(Color.GRAY);
            graphics2D.drawLine(0,i, (int) widthBox *25,i);
            graphics2D.setColor(Color.DARK_GRAY);
            graphics2D.drawLine(25, i, (int) widthBox *25, i);
            if(i%2 == 0) graphics2D.fillPolygon(new int[]{(int) widthBox *25,((int) widthBox *25)-8,((int) widthBox *25)-8},new int[]{i,i-3,i+3},3);
            else graphics2D.fillPolygon(new int[]{0,8,8},new int[]{i,i-3,i+3},3);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D)g;

        if(drawPointA){
            graphics2D.setColor(Color.DARK_GRAY);
            graphics2D.drawString("ORIGEN", x-24,y-20);
            graphics2D.setColor(Color.GREEN);
            graphics2D.fillPolygon(new int[]{x,x+6,x-6},new int[]{y,y-16,y-16},3);
        }
        if(drawPointB){
            graphics2D.setColor(Color.DARK_GRAY);
            graphics2D.drawString("DESTINO",a-27,b-20);
            graphics2D.setColor(Color.RED);
            graphics2D.fillPolygon(new int[]{a,a+6,a-6},new int[]{b,b-16,b-16},3);
        }
        /*for (GraficoAuto graficoAuto : graficoAutoList) {
            graficoAuto.draw(g);
        }*/
        dibujanodos(g);

    }
    public void dibujanodos(Graphics g){
        Graphics2D graphics2D = (Graphics2D)g;
        int[] au;
        if(ubicacionDesde != null){
            au = coordenadaMapaEnGraficas(ubicacionDesde.aCoordenadas());
            graphics2D.setColor(Color.BLACK);
            graphics2D.fillOval(au[0]-3,au[1]-3,6,6);
        }
        if (ubicacionHasta != null){
            au = coordenadaMapaEnGraficas(ubicacionHasta.aCoordenadas());
            graphics2D.setColor(Color.BLUE);
            graphics2D.fillOval(au[0]-3,au[1]-3,6,6);
        }

    }

    private void creaObjetos(Graphics g){
        Graphics2D graphics2D = (Graphics2D)g;
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(a-(3),b-(3),6,6);
        graphics2D.fillPolygon(new int[]{a,a-6,a+6},new int[]{b-9,b,b},3);
        graphics2D.fillRect(x-(3),y-(9),6,6);
        graphics2D.fillPolygon(new int[]{x,x-6,x+6},new int[]{y+3,y-4,y-4},3);
    }

    public void setGraficoAutoList(List<GraficoAuto> graficoAutoList) {
        this.graficoAutoList = graficoAutoList;
    }

    public void setVisibleInternalFrame(boolean valor){
        internalFrame.setVisible(valor);
    }
    public void setWidthBox(long widthBox) {
        this.widthBox = widthBox +1;
    }
    public void setHeightBox(long heightBox) {
        this.heightBox = heightBox +1;
    }
    public void setXY(int x, int y) {
        this.x = x; this.y = y;
        setDesde();
        ftf_desde.setText(desde);
    }
    public void setAB(int a, int b) {
        this.a = a; this.b = b;
        setHasta();
        ftf_hasta.setText(hasta);
    }
    public void setUbicacionDesde(Ubicacion ubicacionDesde) {
        this.ubicacionDesde = ubicacionDesde;
    }
    public void setUbicacionHasta(Ubicacion ubicacionHasta) {
        this.ubicacionHasta = ubicacionHasta;
    }
    private void setDesde() {
        if(x%25 == 0) desde = "Origen: Calle Vertical [" + x/25 + "] al " + (int)(((y-25)/25f)*100f);
        else if(y%25 == 0) desde = "Origen: Calle Horizontal [" + y/25 + "] al " + (int)(((x-25)/25f)*100f);
    }
    private void setHasta() {
        if(a%25 == 0) hasta = "Destino: Calle Vertical [" + a/25 + "] al " + (int)(((b-25)/25f)*100f);
        else if(b%25 == 0) hasta = "Destino: Calle Horizontal [" + b/25 + "] al " + (int)(((a-25)/25f)*100f);
    }
    public double  getWidthBox() {
        return widthBox-1;
    }
    public double getHeightBox() {
        return heightBox-1;
    }
    public void displayInternalFrame(JInternalFrame internalFrame) {
        this.internalFrame.dispose();
        this.internalFrame = internalFrame;
        add(internalFrame);
        internalFrame.setClosable(true);
        internalFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        internalFrame.setVisible(true);
    }
    public int [] coordenadaMapaEnGraficas(int x,int y){
        return new int[]{(int)(((x/100f)*25f)+25),(int)(((y/100f)*25f)+25)};
    }
    public int [] coordenadaMapaEnGraficas(Coordenada coordenada){
        return new int[]{(int)(((coordenada.getX()/100f)*25f)+25),(int)(((coordenada.getY()/100f)*25f)+25)};
    }


    class EventoBoton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String ac = e.getActionCommand();
            if(ac.equals("cmd_invert")) {
                if (!ftf_desde.getText().equals("") && !ftf_hasta.getText().equals("")){
                    String str = ftf_desde.getText();
                    ftf_desde.setText(ftf_hasta.getText());
                    ftf_hasta.setText(str);;

                    Ubicacion ubicacion = ubicacionDesde;
                    ubicacionDesde = ubicacionHasta;
                    ubicacionHasta = ubicacion;

                    int p = x, q = y;
                    setXY(a, b);
                    setAB(p, q);
                    repaint();
                }
            } else if(ac.equals("cmd_calcular")) {
                NodoMaestro nodo;
                nodo = new NodoMaestro(ubicacionDesde, application);
                nodo.work(ubicacionHasta);

            }
        }
    }
}