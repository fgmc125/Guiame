package ar.com.arcom.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JDialog {
    private final JPanel contentPane;
    private JLabel jlb_notific, jlb_user, jlb_pass;
    private JTextField jtf_user;
    private JPasswordField jtf_password;
    private JButton jbn_login, jbn_join, jbn_exit;

    public Login() {
        setUndecorated(true);
        setSize(254, 175);
        setResizable(false);
        setLocationRelativeTo(null);

        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        setLocation(width/2-(getWidth()/2), height/2-(getHeight()/2));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        initialize();
    }

    private void initialize() {
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        jtf_user = new JTextField();
        jtf_user.setBorder(null);
        jtf_user.setText("Usuario");
        jtf_user.setBounds(10, 16, 225, 20);
        jtf_user.setColumns(10);
        panel.add(jtf_user);

        jtf_password = new JPasswordField();
        jtf_password.setBorder(null);
        jtf_password.setText("Contrase\u00F1a");
        jtf_password.setBounds(10, 38, 225, 20);
        panel.add(jtf_password);


        jbn_login = new JButton("Entrar");
        jbn_login.addActionListener(new EventoBoton());
        jbn_login.setActionCommand("cmd_entrar");
        jbn_login.setBounds(10, 70, 225, 23);
        panel.add(jbn_login);

        jbn_join = new JButton("Registrarse");
        jbn_join.addActionListener(new EventoBoton());
        jbn_join.setActionCommand("cmd_registrarse");
        jbn_join.setBounds(10, 96, 225, 23);
        panel.add(jbn_join);

        jbn_exit = new JButton("Salir");
        jbn_exit.addActionListener(new EventoBoton());
        jbn_exit.setActionCommand("cmd_salir");
        jbn_exit.setBounds(165, 130, 70, 23);
        panel.add(jbn_exit);

        JCheckBox jcb0 = new JCheckBox("Recordar Usuario");
        jcb0.setAlignmentX(Component.CENTER_ALIGNMENT);
        jcb0.setBounds(10, 130, 149, 23);
        panel.add(jcb0);
    }

    private class EventoBoton implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String ac = e.getActionCommand();
            if(ac.equals("cmd_entrar")) {

            }
            else if(ac.equals("cmd_salir")) {
                dispose();
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
