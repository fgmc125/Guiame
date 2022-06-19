package ar.com.arcom.handlers;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/*
Pasos para usar JDBC:
1. Crear una instancia del JDBC Driver.
2. Especificar la url y credenciales de la BDD.
3. Establecer una conexión usando el driver que crea el objeto Connection.
4. Crear un objeto Statement, usando Connection.
5. Armar el postulado SQL y enviarlo a ejecución usando el Statement.
6. Recibir los resultados en el objeto ResultSet.
*/


public class HelperHandler {
    private String JDBC_DRIVER;
    private String DB_URL;
    private String USER;
    private String PASSWORD;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private boolean openConnection;

    public HelperHandler() {
        connection = null;
        statement = null;
        resultSet = null;

        openConnection = false;

        setDefaultDataBaseInfo();
    }

    public void setDefaultDataBaseInfo(){
        JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost:3306/supermark";
        USER = "root";
        PASSWORD = "1234567890f";
    }

    public void setDataBaseURL(String DB_URL){
        this.DB_URL = DB_URL;
    }

    public void setUSER_PASWORD(String USER, String PASSWORD){
        this.USER = USER;
        this.PASSWORD = PASSWORD;
    }

    /*private void registerJDBCDriver(){
        //PASO 2: Registrar JDBC driver
        try{
            Class.forName(JDBC_DRIVER);
        }catch(Exception e){
            // Resolver errores para Class.forName
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }*/

    public void openConection(){
        //PASO 1: Abrir una Conexion
        System.out.println("Connecting to database");
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            openConnection = true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
            openConnection = false;
            clean();
        }
    }

    public ArrayList<String> runQuery(){
        //PASO 2: Ejecutar una consulta SQL
        System.out.println("Creating statement...");
        ArrayList<String> arrayList = new ArrayList<>();
        if (openConnection){
            try {
                statement = connection.createStatement();
                String sql;
                sql = "SELECT " + "user, password FROM `supermark`.`users_db`";
                resultSet = statement.executeQuery(sql);
                arrayList = extractData();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                clean();
            }
        }
        return arrayList;
    }


    public ArrayList<String> runQuery(String from, String labelData, String labels){
        //PASO 2: Ejecutar una consulta SQL
        System.out.println("Creating statement...");
        ArrayList<String> arrayList = new ArrayList<>();
        if (openConnection){
            try {
                statement = connection.createStatement();
                String sql;
                sql = "SELECT " + labels + " FROM `supermark`.`users_db`";

                System.out.println(sql);
                resultSet = statement.executeQuery(sql);

                ArrayList<String> aux = new ArrayList<>();
                String str = labels;
                int indice2 = str.indexOf(",");
                while(indice2 != (-1)){
                    aux.add(str.substring(0,indice2));
                    str = str.substring(indice2+2);
                    indice2 = str.indexOf(",");
                }
                aux.add(str);
                arrayList = extractData(aux);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                clean();
            }
        }
        return arrayList;
    }

    public boolean compareQuery(String from, String label, String labelData){
        //PASO 2: Ejecutar una consulta SQL
        boolean aux = false;
        System.out.println("Creating statement...");
        if (openConnection){
            try {
                statement = connection.createStatement();
                String sql;
                sql = "SELECT * FROM " + from + " WHERE " + label + " = " + labelData;
                resultSet = statement.executeQuery(sql);
                if(resultSet.next()) aux = true;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                clean();
            }
        }
        System.out.println(aux);
        return aux;
    }

    private ArrayList<String> extractData(){
        //PASO 3: Extraer datos de un ResulSet
        ArrayList<String> arrayList = new ArrayList<>();
        String user = null, password = null;
        try {
            while(resultSet.next()){
                //Recivir por tipo de columna
                password = resultSet.getString("password");
                user = resultSet.getString("user");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        arrayList.add(user);
        arrayList.add(password);
        return arrayList;
    }

    private ArrayList<String> extractData(ArrayList<String> labels){
        //PASO 3: Extraer datos de un ResulSet
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            while(resultSet.next()){
                //Recibir por tipo de columna
                for (String string : labels) arrayList.add(resultSet.getString(string));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return arrayList;
    }

    private void clean(){
        //PASO4: Entorno de Limpieza
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            // Bloque finalmente utilizado para cerrar recursos
            try{
                if(statement!=null)
                    statement.close();
            }catch(SQLException se2) {
                se2.printStackTrace();
                JOptionPane.showMessageDialog(null, se2.getMessage());
            }// Nada que podamos hacer
            try{
                if(connection!=null) connection.close();
            }catch(SQLException se){
                se.printStackTrace();
                JOptionPane.showMessageDialog(null, se.getMessage());
            }
        } //cierra finally try
    }
}