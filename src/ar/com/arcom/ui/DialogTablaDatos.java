package ar.com.arcom.ui;

import ar.com.arcom.bin.Auto;
import ar.com.arcom.bin.Calle;
import ar.com.arcom.bin.Persona;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class DialogTablaDatos extends JDialog {
    private final JPanel contentPane;
    private JTable tablePersonas, tableAutos, tableCalles;
    private DefaultTableModel defaultTableModelPersonas, defaultTableModelAutos, defaultTableModelCalles;

    public DialogTablaDatos(Frame owner) {
        super(owner);
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

        initialize();
    }

    private void initialize() {
        setBounds(602, 12, 643, 418);
        setVisible(true);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        tablePersonas = new JTable();

        tablePersonas.setAutoCreateRowSorter(true);

        defaultTableModelPersonas = new DefaultTableModel(
                createListPersonas(),
                new String[] {
                        "ID", "UBICACION", "DESTINO", "EN AUTO"
                }
        );

        tablePersonas.setModel(defaultTableModelPersonas);

        tablePersonas.getColumnModel().getColumn(0).setPreferredWidth(25);
        tablePersonas.getColumnModel().getColumn(1).setPreferredWidth(69);
        tablePersonas.getColumnModel().getColumn(2).setPreferredWidth(206);
        tablePersonas.getColumnModel().getColumn(3).setPreferredWidth(40);

        JScrollPane scrollPane = new JScrollPane(tablePersonas);
        //scrollPane.setPreferredSize(new Dimension(470, 300));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        tabbedPane.add(scrollPane,BorderLayout.CENTER);
        tabbedPane.setTitleAt(0, "Lista de Personas");

        // Carga autos
        tableAutos = new JTable();

        tableAutos.setAutoCreateRowSorter(true);

        defaultTableModelAutos = new DefaultTableModel(
                createListAutos(),
                new String[] {
                        "ID", "UBICACION", "DESTINO", "EN RUTA"
                }
        );

        tableAutos.setModel(defaultTableModelAutos);

        tableAutos.getColumnModel().getColumn(0).setPreferredWidth(25);
        tableAutos.getColumnModel().getColumn(1).setPreferredWidth(69);
        tableAutos.getColumnModel().getColumn(2).setPreferredWidth(206);
        tableAutos.getColumnModel().getColumn(3).setPreferredWidth(40);

        JScrollPane scrollPane1 = new JScrollPane(tableAutos);
        //scrollPane.setPreferredSize(new Dimension(470, 300));
        scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        tabbedPane.add(scrollPane1,BorderLayout.CENTER);
        tabbedPane.setTitleAt(1, "Lista de Autos");

        // Carga autos
        tableCalles = new JTable();

        tableCalles.setAutoCreateRowSorter(true);

        defaultTableModelCalles = new DefaultTableModel(
                createListCalles(),
                new String[] {
                        "ID", "ORIENTACION", "SENTIDO"
                }
        );

        tableCalles.setModel(defaultTableModelCalles);

        tableCalles.getColumnModel().getColumn(0).setPreferredWidth(25);
        tableCalles.getColumnModel().getColumn(1).setPreferredWidth(69);
        tableCalles.getColumnModel().getColumn(2).setPreferredWidth(206);

        JScrollPane scrollPane2 = new JScrollPane(tableCalles);
        //scrollPane.setPreferredSize(new Dimension(470, 300));
        scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        tabbedPane.add(scrollPane2,BorderLayout.CENTER);
        tabbedPane.setTitleAt(2, "Lista de Calles");
    }

    public DefaultTableModel getDefaultTableModelPersonas() {
        return defaultTableModelPersonas;
    }

    public void setDefaultTableModelPersonas(DefaultTableModel defaultTableModelPersonas) {
        this.defaultTableModelPersonas = defaultTableModelPersonas;
    }

    private Object[][] createListPersonas(){
        List<Persona> personaList = ((UI) getOwner()).getApplication().getPersonas();

        Object[][] objects = new Object[personaList.size()][4];

        for(int i = 0; i < objects.length; i++){
            objects[i] = new Object[]{personaList.get(i).getId(),
                    personaList.get(i).getUbicacion().getCalle().getNombre() + " " + personaList.get(i).getUbicacion().getCalle().getId() + " al " + personaList.get(i).getUbicacion().getValor(),
                    (personaList.get(i).getDestino() != null) ? personaList.get(i).getDestino().getCalle().getNombre() + " " + personaList.get(i).getDestino().getCalle().getId() + " al " + personaList.get(i).getDestino().getValor() : null,
                    (Boolean) personaList.get(i).isEnAuto()
            };
        }
        return objects;
    }

    private Object[][] createListAutos(){
        List<Auto> autoList = ((UI) getOwner()).getApplication().getAutos();

        Object[][] objects = new Object[autoList.size()][4];

        for(int i = 0; i < objects.length; i++){
            objects[i] = new Object[]{autoList.get(i).getId(),
                    autoList.get(i).getUbicacion().getCalle().getNombre() + " " + autoList.get(i).getUbicacion().getCalle().getId() + " al " + autoList.get(i).getUbicacion().getValor(),
                    (autoList.get(i).getDestino() != null) ? autoList.get(i).getDestino().getCalle().getNombre() + " " + autoList.get(i).getDestino().getCalle().getId() + " al " + autoList.get(i).getDestino().getValor() : null,
                    (Boolean) autoList.get(i).isEnRuta()
            };
        }
        return objects;
    }

    private Object[][] createListCalles(){
        List<Calle> calleList = ((UI) getOwner()).getApplication().getCiudad().getListaDeCalles();

        Object[][] objects = new Object[calleList.size()][3];

        for(int i = 0; i < objects.length; i++){
            objects[i] = new Object[]{
                    calleList.get(i).getId(),
                    calleList.get(i).getOrientacion(),
                    calleList.get(i).getSentido()
            };
        }
        return objects;
    }

    private class EventoBoton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String ac = e.getActionCommand();
            if(ac.equals("cmd_gen")) {
                            }
            else if(ac.equals("cmd_salir")) {
                dispose();
            }
        }
    }
}
