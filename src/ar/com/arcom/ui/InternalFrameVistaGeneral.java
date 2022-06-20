package ar.com.arcom.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InternalFrameVistaGeneral extends JInternalFrame {
    private JPanel contentPane;

    public InternalFrameVistaGeneral(String title) {
        super(title);
        initialize();
    }

    private void initialize(){
        setBounds(25, 25, 280, 410);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        getContentPane().setLayout(new BorderLayout(0, 0));
        contentPane.setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        JPanel panel_1_1_1_1_1 = new JPanel();
        panel_1_1_1_1_1.setLayout(null);
        tabbedPane.addTab("Vista General", null, panel_1_1_1_1_1, null);

        JLabel lblNewLabel_2_1_1_2 = new JLabel("INFORMACIÓN");
        lblNewLabel_2_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1_1_2.setBounds(12, 12, 241, 21);
        panel_1_1_1_1_1.add(lblNewLabel_2_1_1_2);

        JFormattedTextField formattedTextField_3_2 = new JFormattedTextField();
        formattedTextField_3_2.setHorizontalAlignment(SwingConstants.RIGHT);
        formattedTextField_3_2.setEditable(false);
        formattedTextField_3_2.setBounds(153, 88, 100, 20);
        panel_1_1_1_1_1.add(formattedTextField_3_2);

        JFormattedTextField formattedTextField_1_2_1 = new JFormattedTextField();
        formattedTextField_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
        formattedTextField_1_2_1.setEditable(false);
        formattedTextField_1_2_1.setBounds(153, 66, 100, 20);
        panel_1_1_1_1_1.add(formattedTextField_1_2_1);

        JLabel lblNewLabel_2_1_1_1_3 = new JLabel("Cantidad de personas:");
        lblNewLabel_2_1_1_1_3.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2_1_1_1_3.setBounds(12, 88, 130, 20);
        panel_1_1_1_1_1.add(lblNewLabel_2_1_1_1_3);

        JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("Tamaño de la ciudad:");
        lblNewLabel_2_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2_1_1_1_1_1.setBounds(12, 66, 130, 20);
        panel_1_1_1_1_1.add(lblNewLabel_2_1_1_1_1_1);

        JFormattedTextField formattedTextField_3_1_2 = new JFormattedTextField();
        formattedTextField_3_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
        formattedTextField_3_1_2.setEditable(false);
        formattedTextField_3_1_2.setBounds(153, 110, 100, 20);
        panel_1_1_1_1_1.add(formattedTextField_3_1_2);

        JTextPane txtpnIngreseElTamao_2_1 = new JTextPane();
        txtpnIngreseElTamao_2_1.setText("*Los datos ingresados tienen que ser pares.");
        txtpnIngreseElTamao_2_1.setOpaque(false);
        txtpnIngreseElTamao_2_1.setMargin(new Insets(0, 0, 0, 0));
        txtpnIngreseElTamao_2_1.setFocusable(false);
        txtpnIngreseElTamao_2_1.setFocusTraversalKeysEnabled(false);
        txtpnIngreseElTamao_2_1.setFocusCycleRoot(false);
        txtpnIngreseElTamao_2_1.setEditable(false);
        txtpnIngreseElTamao_2_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        txtpnIngreseElTamao_2_1.setBounds(12, 272, 241, 52);
        panel_1_1_1_1_1.add(txtpnIngreseElTamao_2_1);

        JLabel lblNewLabel_2_1_1_1_1_1_1 = new JLabel("Cantidad de vehiculos:");
        lblNewLabel_2_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2_1_1_1_1_1_1.setBounds(12, 110, 130, 20);
        panel_1_1_1_1_1.add(lblNewLabel_2_1_1_1_1_1_1);

        JLabel lblNewLabel_2_1_1_1_1_1_1_1 = new JLabel("Personas sin acción:");
        lblNewLabel_2_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2_1_1_1_1_1_1_1.setBounds(12, 186, 130, 20);
        panel_1_1_1_1_1.add(lblNewLabel_2_1_1_1_1_1_1_1);

        JLabel lblNewLabel_2_1_1_1_3_1 = new JLabel("Personas con taxi:");
        lblNewLabel_2_1_1_1_3_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2_1_1_1_3_1.setBounds(12, 164, 130, 20);
        panel_1_1_1_1_1.add(lblNewLabel_2_1_1_1_3_1);

        JLabel lblNewLabel_2_1_1_1_1_1_2 = new JLabel("Personas esperando:");
        lblNewLabel_2_1_1_1_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2_1_1_1_1_1_2.setBounds(12, 142, 130, 20);
        panel_1_1_1_1_1.add(lblNewLabel_2_1_1_1_1_1_2);

        JFormattedTextField formattedTextField_1_2_1_1 = new JFormattedTextField();
        formattedTextField_1_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        formattedTextField_1_2_1_1.setEditable(false);
        formattedTextField_1_2_1_1.setBounds(153, 142, 100, 20);
        panel_1_1_1_1_1.add(formattedTextField_1_2_1_1);

        JFormattedTextField formattedTextField_3_2_1 = new JFormattedTextField();
        formattedTextField_3_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
        formattedTextField_3_2_1.setEditable(false);
        formattedTextField_3_2_1.setBounds(153, 164, 100, 20);
        panel_1_1_1_1_1.add(formattedTextField_3_2_1);

        JFormattedTextField formattedTextField_3_1_2_1 = new JFormattedTextField();
        formattedTextField_3_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
        formattedTextField_3_1_2_1.setEditable(false);
        formattedTextField_3_1_2_1.setBounds(153, 186, 100, 20);
        panel_1_1_1_1_1.add(formattedTextField_3_1_2_1);

        JFormattedTextField formattedTextField_3_1_2_1_1 = new JFormattedTextField();
        formattedTextField_3_1_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        formattedTextField_3_1_2_1_1.setEditable(false);
        formattedTextField_3_1_2_1_1.setBounds(153, 240, 100, 20);
        panel_1_1_1_1_1.add(formattedTextField_3_1_2_1_1);

        JLabel lblNewLabel_2_1_1_1_1_1_1_1_1 = new JLabel("Vehiculos sin acción:");
        lblNewLabel_2_1_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2_1_1_1_1_1_1_1_1.setBounds(12, 240, 130, 20);
        panel_1_1_1_1_1.add(lblNewLabel_2_1_1_1_1_1_1_1_1);

        JLabel lblNewLabel_2_1_1_1_3_1_1 = new JLabel("Vehiculos en camino:");
        lblNewLabel_2_1_1_1_3_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2_1_1_1_3_1_1.setBounds(12, 218, 130, 20);
        panel_1_1_1_1_1.add(lblNewLabel_2_1_1_1_3_1_1);

        JFormattedTextField formattedTextField_3_2_1_1 = new JFormattedTextField();
        formattedTextField_3_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        formattedTextField_3_2_1_1.setEditable(false);
        formattedTextField_3_2_1_1.setBounds(153, 218, 100, 20);
        panel_1_1_1_1_1.add(formattedTextField_3_2_1_1);

        JLabel lblNewLabel_2_1_1_1_1_1_3 = new JLabel("Tiempo transcurrido:");
        lblNewLabel_2_1_1_1_1_1_3.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2_1_1_1_1_1_3.setBounds(12, 38, 130, 20);
        panel_1_1_1_1_1.add(lblNewLabel_2_1_1_1_1_1_3);

        JFormattedTextField formattedTextField_1_2_1_2 = new JFormattedTextField();
        formattedTextField_1_2_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
        formattedTextField_1_2_1_2.setEditable(false);
        formattedTextField_1_2_1_2.setBounds(153, 38, 100, 20);
        panel_1_1_1_1_1.add(formattedTextField_1_2_1_2);
    }
}
