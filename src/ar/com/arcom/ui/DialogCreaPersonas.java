package ar.com.arcom.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class DialogCreaPersonas extends JDialog {
    private final JPanel contentPane;
    private JLabel jlb_title;
    private JCheckBox jcb_random;
    private JTextField ftf_catidad;

    public DialogCreaPersonas(Frame owner) {
        super(owner);
        setUndecorated(true);
        setSize(255, 210);
        setResizable(false);
        setLocationRelativeTo(null);

        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(pantalla.width/2-(getWidth()/2), pantalla.height/2-(getHeight()/2));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        setModal(true);
        initialize();
    }

    private void initialize() {
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JButton jbn_gen = new JButton("Generar");
        jbn_gen.addActionListener(new DialogCreaPersonas.EventoBoton());
        jbn_gen.setActionCommand("cmd_gen");
        jbn_gen.setBounds(121, 129, 100, 21);
        panel.add(jbn_gen);

        jcb_random = new JCheckBox("Aleatorio");
        jbn_gen.setSelected(true);
        jcb_random.setBounds(13, 129, 100, 21);
        jcb_random.addChangeListener(new EventoBoton());
        panel.add(jcb_random);

        ftf_catidad = new JTextField();
        ftf_catidad.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')) e.consume();
            }
        });
        InputMap map2 = ftf_catidad.getInputMap(JTextField.WHEN_FOCUSED);
        map2.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK), "null");
        ftf_catidad.setBounds(12, 97, 209, 20);
        panel.add(ftf_catidad);

        JLabel lblNewLabel_3 = new JLabel("Ingrese la cantidad de personas");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setBounds(12, 12, 209, 21);
        panel.add(lblNewLabel_3);

        JTextPane txtpnIngreseElTamao_3 = new JTextPane();
        txtpnIngreseElTamao_3.setText("* El valor ingresado tiene que ser mayor a 0.");
        txtpnIngreseElTamao_3.setOpaque(false);
        txtpnIngreseElTamao_3.setMargin(new Insets(0, 0, 0, 7));
        txtpnIngreseElTamao_3.setFocusable(false);
        txtpnIngreseElTamao_3.setFocusTraversalKeysEnabled(false);
        txtpnIngreseElTamao_3.setFocusCycleRoot(false);
        txtpnIngreseElTamao_3.setEditable(false);
        txtpnIngreseElTamao_3.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        txtpnIngreseElTamao_3.setBounds(12, 35, 209, 42);
        panel.add(txtpnIngreseElTamao_3);

        JLabel lblNewLabel_1_1_1 = new JLabel("Cantidad de personas");
        lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1.setBounds(12, 69, 209, 16);
        panel.add(lblNewLabel_1_1_1);
    }

    private class EventoBoton implements ActionListener, ChangeListener {
        public void actionPerformed(ActionEvent e) {
            String ac = e.getActionCommand();
            if(ac.equals("cmd_gen")) {
                if(jcb_random.isSelected()){
                    Random random = new Random();
                    ((UI) getOwner()).createPeople((random.nextInt(1000)+1));
                    dispose();
                } else if(!ftf_catidad.getText().equals("") && Integer.parseInt(ftf_catidad.getText())>0){
                    ((UI) getOwner()).createPeople(Integer.parseInt(ftf_catidad.getText()));
                    dispose();
                } else JOptionPane.showMessageDialog(null,"Cantidad de personas tienen que ser mayor a 0","Error",JOptionPane.ERROR_MESSAGE);

            }
            else if(ac.equals("cmd_salir")) {
                dispose();
            }
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            if (jcb_random.isSelected()) {
                ftf_catidad.setEnabled(false);
                ftf_catidad.setEditable(false);

            }else {
                ftf_catidad.setEnabled(true);
                ftf_catidad.setEditable(true);
            }
        }
    }
}
