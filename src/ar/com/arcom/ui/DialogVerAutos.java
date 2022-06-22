package ar.com.arcom.ui;

import ar.com.arcom.bin.Auto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DialogVerAutos extends JDialog {
    private final JPanel contentPane;
    private JTable table;
    private DefaultTableModel defaultTableModel;

    public DialogVerAutos(Frame owner) {
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

        table = new JTable();

        table.setAutoCreateRowSorter(true);

        defaultTableModel = new DefaultTableModel(
                createListAutos(),
                new String[] {
                        "ID", "UBICACION", "DESTINO", "EN RUTA"
                }
        );

        table.setModel(defaultTableModel);

        table.getColumnModel().getColumn(0).setPreferredWidth(25);
        table.getColumnModel().getColumn(1).setPreferredWidth(69);
        table.getColumnModel().getColumn(2).setPreferredWidth(206);
        table.getColumnModel().getColumn(3).setPreferredWidth(40);

        JScrollPane scrollPane = new JScrollPane(table);
        //scrollPane.setPreferredSize(new Dimension(470, 300));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(scrollPane,BorderLayout.CENTER);
    }

    public DefaultTableModel getDefaultTableModel() {
        return defaultTableModel;
    }

    public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
        this.defaultTableModel = defaultTableModel;
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
