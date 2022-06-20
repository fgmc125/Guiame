package ar.com.arcom.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InternalFramePersonas extends JInternalFrame {
    private JPanel contentPane;

    public InternalFramePersonas(String title) {
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

        JPanel panel_1_1_1_1 = new JPanel();
        panel_1_1_1_1.setLayout(null);

        tabbedPane.addTab("Personas", null, panel_1_1_1_1, null);

        JLabel lblNewLabel_2_1_1 = new JLabel("Visor de personas");
        lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1_1.setBounds(12, 12, 209, 21);
        panel_1_1_1_1.add(lblNewLabel_2_1_1);

        JFormattedTextField formattedTextField_3 = new JFormattedTextField();
        formattedTextField_3.setHorizontalAlignment(SwingConstants.RIGHT);
        formattedTextField_3.setEditable(false);
        formattedTextField_3.setBounds(121, 104, 100, 20);
        panel_1_1_1_1.add(formattedTextField_3);

        JFormattedTextField formattedTextField_1_2 = new JFormattedTextField();
        formattedTextField_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
        formattedTextField_1_2.setEditable(false);
        formattedTextField_1_2.setBounds(121, 82, 100, 20);
        panel_1_1_1_1.add(formattedTextField_1_2);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Persona 1", "Persona 2", "Persona 3", "Persona 4", "Persona 5", "Persona 6", "Persona 1", "Persona 2", "Persona 3", "Persona 4", "Persona 5", "Persona 6", "Persona 1", "Persona 2", "Persona 3", "Persona 4", "Persona 5", "Persona 6"}));
        comboBox.setBounds(12, 45, 210, 25);
        panel_1_1_1_1.add(comboBox);

        JLabel lblNewLabel_2_1_1_1 = new JLabel("Ubicación:");
        lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2_1_1_1.setBounds(12, 104, 100, 20);
        panel_1_1_1_1.add(lblNewLabel_2_1_1_1);

        JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Tiempo:");
        lblNewLabel_2_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2_1_1_1_1.setBounds(12, 82, 100, 20);
        panel_1_1_1_1.add(lblNewLabel_2_1_1_1_1);

        JLabel lblNewLabel_2_1_1_1_2 = new JLabel("Vehiculo:");
        lblNewLabel_2_1_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2_1_1_1_2.setBounds(12, 126, 100, 20);
        panel_1_1_1_1.add(lblNewLabel_2_1_1_1_2);

        JFormattedTextField formattedTextField_3_1 = new JFormattedTextField();
        formattedTextField_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
        formattedTextField_3_1.setEditable(false);
        formattedTextField_3_1.setBounds(121, 126, 100, 20);
        panel_1_1_1_1.add(formattedTextField_3_1);

        JTextPane txtpnIngreseElTamao_2 = new JTextPane();
        txtpnIngreseElTamao_2.setText("*Los datos ingresados tienen que ser pares.");
        txtpnIngreseElTamao_2.setOpaque(false);
        txtpnIngreseElTamao_2.setMargin(new Insets(0, 0, 0, 0));
        txtpnIngreseElTamao_2.setFocusable(false);
        txtpnIngreseElTamao_2.setFocusTraversalKeysEnabled(false);
        txtpnIngreseElTamao_2.setFocusCycleRoot(false);
        txtpnIngreseElTamao_2.setEditable(false);
        txtpnIngreseElTamao_2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        txtpnIngreseElTamao_2.setBounds(12, 190, 209, 42);
        panel_1_1_1_1.add(txtpnIngreseElTamao_2);

        JFormattedTextField formattedTextField_3_1_1 = new JFormattedTextField();
        formattedTextField_3_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        formattedTextField_3_1_1.setEditable(false);
        formattedTextField_3_1_1.setBounds(121, 158, 100, 20);
        panel_1_1_1_1.add(formattedTextField_3_1_1);

        JLabel lblNewLabel_2_1_1_1_2_1 = new JLabel("Estado:");
        lblNewLabel_2_1_1_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2_1_1_1_2_1.setBounds(12, 158, 100, 20);
        panel_1_1_1_1.add(lblNewLabel_2_1_1_1_2_1);
    }
}
