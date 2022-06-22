package ar.com.arcom.ui;

import javax.swing.*;
import java.awt.*;

public class InternalFrameModoViaje extends JInternalFrame{
    public InternalFrameModoViaje(String title) {
        super(title);
        initialize();
    }

    private void initialize(){

        /*internalFrame = new JInternalFrame("Como llegar");
        internalFrame.setBounds(48, 12, 240, 241);
        internalFrame.setContentPane(new JPanel());
        internalFrame.getContentPane().setLayout(null);
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
        //btnNewButton_1_1.addActionListener(new GraficoCiudad.EventoBoton());
        btnNewButton_1_1.setActionCommand("cmd_invert");
        btnNewButton_1_1.setBounds(118, 138, 100, 21);
        panel_1_1.add(btnNewButton_1_1);*/
    }
}
