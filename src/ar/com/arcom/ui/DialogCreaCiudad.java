package ar.com.arcom.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class DialogCreaCiudad extends JDialog {
    private final JPanel contentPane;
    private JLabel jlb_title;
    private JCheckBox jcb_random;
    private JTextField ftf_width, ftf_height;

    public DialogCreaCiudad(Frame owner) {
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
        jbn_gen.addActionListener(new DialogCreaCiudad.EventoBoton());
        jbn_gen.setActionCommand("cmd_gen");
        jbn_gen.setBounds(121, 129, 100, 21);
        panel.add(jbn_gen);

        jlb_title = new JLabel("Ingrese el tamaño de la ciudad");
        jlb_title.setBounds(12, 12, 209, 21);
        panel.add(jlb_title);
        jlb_title.setHorizontalAlignment(SwingConstants.CENTER);

        jcb_random = new JCheckBox("Aleatorio");
        jcb_random.setBounds(13, 129, 100, 21);
        jcb_random.addChangeListener(new EventoBoton());
        panel.add(jcb_random);

        ftf_width = new JTextField();
        ftf_width.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')) e.consume();
            }
        });
        InputMap map2 = ftf_width.getInputMap(JTextField.WHEN_FOCUSED);
        map2.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK), "null");
        ftf_width.setBounds(12, 98, 100, 20);
        panel.add(ftf_width);

        ftf_height = new JTextField();
        ftf_height.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char character = e.getKeyChar();
                if(((character < '0') || (character > '9')) && (character != '\b')) e.consume();
            }
        });
        map2 = ftf_height.getInputMap(JTextField.WHEN_FOCUSED);
        map2.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK), "null");

        ftf_height.setBounds(121, 98, 100, 20);
        panel.add(ftf_height);

        JTextPane jtp_info = new JTextPane();
        jtp_info.setMargin(new Insets(0, 0, 0, 0));
        jtp_info.setFocusCycleRoot(false);
        jtp_info.setFocusTraversalKeysEnabled(false);
        jtp_info.setFocusable(false);
        jtp_info.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        jtp_info.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        jtp_info.setText("*Los datos ingresados tienen que ser pares y mayores que 0.");
        jtp_info.setOpaque(false);
        jtp_info.setEditable(false);
        jtp_info.setBounds(12, 35, 209, 42);
        panel.add(jtp_info);

        JLabel lblNewLabel_1 = new JLabel("Ancho");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(12, 78, 100, 16);
        panel.add(lblNewLabel_1);

        JLabel lbl_height = new JLabel("Alto");
        lbl_height.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_height.setBounds(121, 78, 100, 16);
        panel.add(lbl_height);

        Box verticalBox = Box.createVerticalBox();
        verticalBox.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
        verticalBox.setBounds(6, 75, 219, 50);
        panel.add(verticalBox);
    }

    private class EventoBoton implements ActionListener, ChangeListener {
        public void actionPerformed(ActionEvent e) {
            String ac = e.getActionCommand();
            if(ac.equals("cmd_gen")) {
                if(jcb_random.isSelected()){
                    Random random = new Random();
                    ((GUI) getOwner()).createCity((random.nextInt(1000)+1)*2,(random.nextInt(1000)+1)*2);

                    dispose();
                } else if(!ftf_height.getText().equals("") && !ftf_width.getText().equals("") &&
                        Integer.parseInt(ftf_height.getText())%2 == 0 && Integer.parseInt(ftf_width.getText())%2 == 0 &&
                        Integer.parseInt(ftf_height.getText())>0 && Integer.parseInt(ftf_width.getText())>0){
                    ((GUI) getOwner()).createCity(Integer.parseInt(ftf_width.getText()),Integer.parseInt(ftf_height.getText()));
                    dispose();
                } else JOptionPane.showMessageDialog(null,"Ancho y Alto tienen que ser mayor a 0 y multiplo de 2","Error",JOptionPane.ERROR_MESSAGE);

            }
            else if(ac.equals("cmd_salir")) {
                dispose();
            }
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            if (jcb_random.isSelected()) {
                ftf_width.setEnabled(false);
                ftf_width.setEditable(false);
                ftf_height.setEnabled(false);
                ftf_height.setEditable(false);
            }else {
                ftf_width.setEnabled(true);
                ftf_width.setEditable(true);
                ftf_height.setEnabled(true);
                ftf_height.setEditable(true);
            }
        }
    }
}
