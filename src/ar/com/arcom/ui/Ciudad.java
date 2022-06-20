package ar.com.arcom.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ciudad extends JComponent {
    private final int ANCHO_CUADRO=25;
    private final int ALTO_CUADRO=25;

    public boolean drawPointA, drawPointB;
    public int x,y,a,b;

    private int widthBox, heightBox;
    private JInternalFrame internalFrame;
    public JFormattedTextField ftf_desde, ftf_hasta;
    private String desde, hasta;

    public Ciudad(int widthBox, int heightBox) {
        super();
        this.setLayout(null);

        drawPointA = false;
        this.widthBox = widthBox +1;
        this.heightBox = heightBox +1;

        desde = "";
        hasta = "";

        setLayout(null);

        internalFrame = new JInternalFrame("Como llegar");
        internalFrame.setBounds(48, 12, 240, 241);
        internalFrame.setContentPane(new JPanel());
        internalFrame.getContentPane().setLayout(null);
        add(internalFrame);

        JPanel panel_1_1 = new JPanel();
        panel_1_1.setLayout(null);
        panel_1_1.setBounds(0, 0, 230, 170);
        internalFrame.getContentPane().add(panel_1_1);

        JButton btnNewButton_1 = new JButton("Calcular ruta");
        btnNewButton_1.setBounds(12, 171, 209, 21);
        panel_1_1.add(btnNewButton_1);

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
        btnNewButton_1_1.addActionListener(new EventoBoton());
        btnNewButton_1_1.setActionCommand("cmd_invert");
        btnNewButton_1_1.setBounds(118, 138, 100, 21);
        panel_1_1.add(btnNewButton_1_1);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g;
        //graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //graphics2D.setColor(new Color(221,221,221,100));
        for(int i = ANCHO_CUADRO; i < widthBox *25; i+=ANCHO_CUADRO) {
            graphics2D.setColor(Color.GRAY);
            graphics2D.drawLine(i,0,i,25);
            graphics2D.setColor(Color.DARK_GRAY);
            graphics2D.drawLine(i, 25, i, heightBox *25);
            if(i%2 == 0) graphics2D.fillPolygon(new int[]{i,i+3,i-3},new int[]{0,8,8},3);
            else graphics2D.fillPolygon(new int[]{i,i+3,i-3},new int[]{heightBox *25,(heightBox *25)-8,(heightBox *25)-8},3);
        }
        for(int i = ANCHO_CUADRO; i < heightBox *25; i+=ALTO_CUADRO) {
            graphics2D.setColor(Color.GRAY);
            graphics2D.drawLine(0,i, widthBox *25,i);
            graphics2D.setColor(Color.DARK_GRAY);
            graphics2D.drawLine(25, i, widthBox *25, i);
            if(i%2 == 0) graphics2D.fillPolygon(new int[]{widthBox *25,(widthBox *25)-8,(widthBox *25)-8},new int[]{i,i-3,i+3},3);
            else graphics2D.fillPolygon(new int[]{0,8,8},new int[]{i,i-3,i+3},3);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D)g;
        if(drawPointA){
            graphics2D.setColor(Color.DARK_GRAY);
            graphics2D.drawString("ORIGEN",x-24,y-20);
            graphics2D.setColor(Color.GREEN);
            graphics2D.fillPolygon(new int[]{x,x+6,x-6},new int[]{y,y-16,y-16},3);
        }
        if(drawPointB){
            graphics2D.setColor(Color.DARK_GRAY);
            graphics2D.drawString("DESTINO",a-27,b-20);
            graphics2D.setColor(Color.RED);
            graphics2D.fillPolygon(new int[]{a,a+6,a-6},new int[]{b,b-16,b-16},3);
        }
    }

    public void setVisibleInternalFrame(boolean valor){
        internalFrame.setVisible(valor);
    }

    public void setWidthBox(int widthBox) {
        this.widthBox = widthBox +1;
    }

    public void setHeightBox(int heightBox) {
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

    private void setDesde() {
        if(x%25 == 0) desde = "Origen: Calle Vertical al " + (int)(((y-25)/25f)*100f);
        else if(y%25 == 0) desde = "Origen: Calle Horizontal al " + (int)(((x-25)/25f)*100f);
    }

    private void setHasta() {
        if(a%25 == 0) hasta = "Destino: Calle Vertical al " + (int)(((b-25)/25f)*100f);
        else if(b%25 == 0) hasta = "Destino: Calle Horizontal al " + (int)(((a-25)/25f)*100f);
    }

    public int getWidthBox() {
        return widthBox-1;
    }

    public int getHeightBox() {
        return heightBox-1;
    }

    private class EventoBoton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String ac = e.getActionCommand();
            if(ac.equals("cmd_invert")) {
                if (!ftf_desde.getText().equals("") && !ftf_hasta.getText().equals("")){
                    String str = ftf_desde.getText();
                    ftf_desde.setText(ftf_hasta.getText());
                    ftf_hasta.setText(str);;

                    int p = x, q = y;
                    setXY(a, b);
                    setAB(p, q);
                    repaint();
                }
            }
            else if(ac.equals("cmd_salir")) {
            }
        }
    }
}