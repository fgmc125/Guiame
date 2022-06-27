package ar.com.arcom.ui;

import ar.com.arcom.Application;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class InternalFrameAutos extends JInternalFrame implements ItemListener {
    private JPanel contentPane;
    private Application application;
    private DefaultComboBoxModel defaultComboBoxModel;
    private JComboBox comboBox;

    private JButton btn_simular, btn_modificar;
    private JFormattedTextField ftf_ubicacion, ftf_velocidad, ftf_enDestino, ftf_destino, ftf_enRuta, ftf_pasajero;

    private int index, indey;

    public InternalFrameAutos(String title, Application application) {
        super(title);
        this.application = application;
        index = 0;
        indey = 0;
        initialize();
    }

    private void initialize(){
        setBounds(25, 25, 290, 410);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        getContentPane().setLayout(new BorderLayout(0, 0));
        contentPane.setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        tabbedPane.addTab("Autos", null, panel, null);

        JLabel jlb_visor_autos = new JLabel("Visor de autos");
        jlb_visor_autos.setHorizontalAlignment(SwingConstants.CENTER);
        jlb_visor_autos.setBounds(12, 12, 209, 21);
        panel.add(jlb_visor_autos);

        ftf_ubicacion = new JFormattedTextField();
        ftf_ubicacion.setHorizontalAlignment(SwingConstants.LEFT);
        ftf_ubicacion.setEditable(false);
        ftf_ubicacion.setBounds(12, 107, 244, 20);
        panel.add(ftf_ubicacion);

        ftf_velocidad = new JFormattedTextField();
        ftf_velocidad.setHorizontalAlignment(SwingConstants.RIGHT);
        ftf_velocidad.setEditable(false);
        ftf_velocidad.setBounds(81, 240, 175, 20);
        panel.add(ftf_velocidad);

        JLabel jlb_ubicacion = new JLabel("Ubicación:");
        jlb_ubicacion.setHorizontalAlignment(SwingConstants.CENTER);
        jlb_ubicacion.setBounds(12, 82, 244, 20);
        panel.add(jlb_ubicacion);

        JLabel jlb_velocidad = new JLabel("Velocidad:");
        jlb_velocidad.setHorizontalAlignment(SwingConstants.LEFT);
        jlb_velocidad.setBounds(12, 240, 70, 20);
        panel.add(jlb_velocidad);

        JLabel jlb_enRuta = new JLabel("En Ruta:");
        jlb_enRuta.setHorizontalAlignment(SwingConstants.CENTER);
        jlb_enRuta.setBounds(12, 187, 120, 20);
        panel.add(jlb_enRuta);

        ftf_enDestino = new JFormattedTextField();
        ftf_enDestino.setHorizontalAlignment(SwingConstants.CENTER);
        ftf_enDestino.setEditable(false);
        ftf_enDestino.setBounds(136, 208, 120, 20);
        panel.add(ftf_enDestino);

        JLabel jlb_enDestino = new JLabel("En Destino:");
        jlb_enDestino.setHorizontalAlignment(SwingConstants.CENTER);
        jlb_enDestino.setBounds(136, 187, 120, 20);
        panel.add(jlb_enDestino);

        btn_simular = new JButton("Simular");
        btn_simular.addActionListener(new EventoBoton());
        btn_simular.setActionCommand("cmd_simular");
        btn_simular.setBounds(169, 45, 87, 26);
        panel.add(btn_simular);

        btn_modificar = new JButton("Modificar");
        btn_modificar.addActionListener(new EventoBoton());
        btn_modificar.setActionCommand("cmd_mod");
        btn_modificar.setBounds(12, 300, 244, 26);
        panel.add(btn_modificar);

        ftf_destino = new JFormattedTextField();
        ftf_destino.setHorizontalAlignment(SwingConstants.RIGHT);
        ftf_destino.setEditable(false);
        ftf_destino.setBounds(12, 155, 244, 20);
        panel.add(ftf_destino);

        JLabel jlb_destino = new JLabel("Destino:");
        jlb_destino.setHorizontalAlignment(SwingConstants.CENTER);
        jlb_destino.setBounds(12, 130, 244, 20);
        panel.add(jlb_destino);

        ftf_enRuta = new JFormattedTextField();
        ftf_enRuta.setHorizontalAlignment(SwingConstants.CENTER);
        ftf_enRuta.setEditable(false);
        ftf_enRuta.setBounds(12, 208, 120, 20);
        panel.add(ftf_enRuta);

        ftf_pasajero = new JFormattedTextField();
        ftf_pasajero.setHorizontalAlignment(SwingConstants.RIGHT);
        ftf_pasajero.setEditable(false);
        ftf_pasajero.setBounds(81, 273, 175, 20);
        panel.add(ftf_pasajero);

        JLabel jlb_pasajero = new JLabel("Pasajero:");
        jlb_pasajero.setHorizontalAlignment(SwingConstants.LEFT);
        jlb_pasajero.setBounds(12, 273, 70, 20);
        panel.add(jlb_pasajero);

        cargarDatos();

        comboBox = new JComboBox();
        comboBox.setModel(defaultComboBoxModel);
        comboBox.addItemListener(this);
        comboBox.setBounds(12, 45, 145, 25);
        panel.add(comboBox);

        ftf_ubicacion.setText(application.getAutos().get(0).getUbicacion().getCalle().getNombre() + " al " + application.getAutos().get(0).getUbicacion().getValor());
        ftf_velocidad.setText(String.valueOf(application.getAutos().get(0).getVelocidadDeMovimiento()));
        ftf_enDestino.setText((application.getAutos().get(0).getDestino() !=null) ?
                (application.getAutos().get(0).getDestino().equals(application.getAutos().get(0).getUbicacion())) ? "Verdadero": "Falso" : "Falso");
        ftf_destino.setText((application.getAutos().get(0).getDestino() !=null) ? application.getAutos().get(0).getDestino().getCalle().getNombre() + " al " + application.getAutos().get(0).getDestino().getValor(): "Sin Destino Actualmente");
        ftf_enRuta.setText((application.getAutos().get(0).isEnRuta()) ? "Verdadero" : "Falso");
        ftf_pasajero.setText("Persona " + application.getPersonas().get(0).getId());

    }
    private void cargarDatos(){
        String[] str = new String[application.getAutos().size()];
        for (int i = 0; i < application.getAutos().size(); i++) {
            str[i] = "Auto " + application.getAutos().get(i).getId();
        }

        defaultComboBoxModel = new DefaultComboBoxModel<>(str);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == comboBox) {
            recargar();
            String seleccionado = (String) comboBox.getSelectedItem();

            index = 0;
            while (!seleccionado.equals("Auto " + application.getAutos().get(index).getId())) index++;
            ftf_ubicacion.setText(application.getAutos().get(index).getUbicacion().getCalle().getNombre() + " al " + application.getAutos().get(index).getUbicacion().getValor());
            ftf_velocidad.setText(String.valueOf(application.getAutos().get(index).getVelocidadDeMovimiento()));
            ftf_enDestino.setText((application.getAutos().get(index).getDestino() !=null) ?
                    (application.getAutos().get(index).getDestino().equals(application.getAutos().get(index).getUbicacion())) ? "Verdadero": "Falso" : "Falso");
            ftf_destino.setText((application.getAutos().get(index).getDestino() !=null) ? application.getAutos().get(index).getDestino().getCalle().getNombre() + " al " + application.getAutos().get(index).getDestino().getValor(): "Sin Destino Actualmente");
            ftf_enRuta.setText((application.getAutos().get(index).isEnRuta()) ? "Verdadero" : "Falso");
            ftf_pasajero.setText("Persona " + application.getPersonas().get(index).getId());
        }
    }

    private class EventoBoton implements ActionListener {
        private JDialog dialog;

        public EventoBoton() {
            dialog = null;
        }

        public void actionPerformed(ActionEvent e) {
            String ac = e.getActionCommand();
            if(ac.equals("cmd_mod")) {

            } else if(ac.equals("cmd_simular")) {
                ((GraficoAuto)application.getUI().getGraficoAutoList().get(index)).setSimular(true);
                ((GraficoAuto)application.getUI().getGraficoAutoList().get(index)).setDestino(
                        ((GraficoPersona)application.getUI().getGraficoPersonaList().get(index)).getPersona().getUbicacion()
                );
                recargar();
                ((GraficoAuto)application.getUI().getGraficoAutoList().get(index)).simular();
            } else if(ac.equals("cmd_parar")) {
                ((GraficoAuto)application.getUI().getGraficoAutoList().get(index)).getTimerTask().cancel();
                ((GraficoAuto)application.getUI().getGraficoAutoList().get(index)).getTimer().cancel();
                ((GraficoAuto)application.getUI().getGraficoAutoList().get(index)).getTimer().purge();
                ((GraficoAuto)application.getUI().getGraficoAutoList().get(index)).setSimular(false);
                recargar();
            }
        }
    }

    public void recargar() {
        ftf_ubicacion.setText(application.getAutos().get(index).getUbicacion().getCalle().getNombre() + " al " + application.getAutos().get(index).getUbicacion().getValor());
        ftf_velocidad.setText(String.valueOf(application.getAutos().get(index).getVelocidadDeMovimiento()));
        ftf_enDestino.setText((application.getAutos().get(index).getDestino() !=null) ?
                (application.getAutos().get(index).getDestino().equals(application.getAutos().get(index).getUbicacion())) ? "Verdadero": "Falso" : "Falso");
        ftf_destino.setText((application.getAutos().get(index).getDestino() !=null) ? application.getAutos().get(index).getDestino().getCalle().getNombre() + " al " + application.getAutos().get(index).getDestino().getValor(): "Sin Destino Actualmente");
        ftf_enRuta.setText((application.getAutos().get(index).isEnRuta()) ? "Verdadero" : "Falso");
        ftf_pasajero.setText("Persona " + application.getPersonas().get(index).getId());
        if (((GraficoAuto)application.getUI().getGraficoAutoList().get(index)).isSimular()) {
            btn_simular.setText("Parar");
            btn_simular.setActionCommand("cmd_parar");
        } else {
            btn_simular.setText("Simular");
            btn_simular.setActionCommand("cmd_simular");
        }
    }
}
