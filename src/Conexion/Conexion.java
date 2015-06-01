/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Federico Lopez
 */
public class Conexion {

    private static Connection con;

    public static Connection conectar(String usuario, String contraseña) throws SQLException,
            ClassNotFoundException {
        String driverName = "com.mysql.jdbc.Driver";
        //String url = "jdbc:mysql://www.5diasti.com:3306/diastico_5dias";
        String url = "jdbc:mysql://localhost/5dias";
        String username = usuario;
        String pasword = contraseña;
        boolean ban = true;
        Class.forName(driverName);
        try {
            con = DriverManager.getConnection(url, username, pasword);
            //JOptionPane.showMessageDialog(null, "Conexion Exitosa");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ban = false;
        }

//        if (ban == false) {
//            while (ban == false) {
//                try {
//                    String ip=JOptionPane.showInputDialog("Ingrese el ultimo digito de su direccion IP", "");
//                    url = "jdbc:postgresql://192.168.1." + ip + ":5432/elantiguo";
//                    System.out.println(url);
//                    con = DriverManager.getConnection(url, username, pasword);
//                    JOptionPane.showMessageDialog(null, "Conexion Exitosa");
//                    ban = true;
//                    break;
//                } catch (Exception ex) {
//                    System.out.println(ex.getMessage());
//                    ban = false;
//                }
//            }
//        }
        return con;
    }

    public static Connection getCon() {
        return con;
    }
}
