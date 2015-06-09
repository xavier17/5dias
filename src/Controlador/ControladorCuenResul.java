/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexion.Cargar;
import Conexion.Conexion;
import Conexion.Control;
import Conexion.ObtenerFecha;
import Conexion.ObtenerNumero;
import Modelo.ModeloBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author FedeXavier
 */
public class ControladorCuenResul {

    Connection c = Conexion.getCon();
    Cargar load = new Cargar();
    Control control = new Control();
    ObtenerNumero on = new ObtenerNumero();
    ObtenerFecha of = new ObtenerFecha();

    public void cuentaResulDesdeHasta(ModeloBanco MB, JTable jTable1) {
        try {
            PreparedStatement pstmt = c.prepareStatement(MB.getQuery());
            ResultSet rs = pstmt.executeQuery();
            System.out.println(pstmt.toString());
            jTable1 = load.cargar_corrido(jTable1, rs);
            pstmt.close();
        } catch (SQLException ex) {
            control.mensaje_error(ex.getMessage());
        }
    }

    public void cuentaResulSistema(ModeloBanco MB, JTable jTable1) {
        try {
            String query = "SELECT c.descripcion,\n"
                    + "SUM(CASE WHEN  (b.mes) =? AND periodo=? AND b.idcuenta_estado=? \n"
                    + "THEN b.moneda_local+b.moneda_extranjera ELSE 0 END) AS 'mes1',\n"
                    + "SUM(CASE WHEN  (b.mes) =? AND periodo=? AND b.idcuenta_estado=?\n"
                    + "THEN b.moneda_local+b.moneda_extranjera ELSE 0 END) AS 'mes2'\n"
                    + "FROM 5dias.estado_ganancia_perdidas b\n"
                    + "INNER JOIN 5dias.entidades e\n"
                    + "ON b.identidades=e.identidades\n"
                    + "INNER JOIN 5dias.cuenta_estado c\n"
                    + "ON  b.idcuenta_estado=c.idcuenta_estado\n"
                    + "WHERE b.idcuenta_estado=?\n"
                    + "GROUP BY b.idcuenta_estado\n"
                    + "ORDER BY e.denominacion";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1, MB.getMes1());
            pstmt.setInt(2, MB.getPer1());
            pstmt.setInt(3, MB.getIdcuenta());
            pstmt.setInt(4, MB.getMes2());
            pstmt.setInt(5, MB.getPer2());
            pstmt.setInt(6, MB.getIdcuenta());
            pstmt.setInt(7, MB.getIdcuenta());
            ResultSet rs = pstmt.executeQuery();
            System.out.println(pstmt.toString());
            jTable1 = load.cargar_corrido(jTable1, rs);
            pstmt.close();
        } catch (SQLException ex) {
            control.mensaje_error(ex.getMessage());
        }
    }

    public void variacion(JTable tabla) {
//        //valores en un arreglo con nombres de personas
        int cant_datos = tabla.getRowCount();
        //creo un modelo para manejar la jTable
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.addColumn("Variacion");
        String resultado;
        DecimalFormat twoDForm = new DecimalFormat("#");

        //Agrego las filas recorriendo el arreglo valores
        for (int x = 0; x < cant_datos; x++) {
            String mes1 = (String) tabla.getValueAt(x, 1);
            String mes2 = (String) tabla.getValueAt(x, 2);
            double mesuno = Double.valueOf(mes1);
            double mesdos = Double.valueOf(mes2);
            double resul = (mesdos / mesuno - 1) * 100;
            System.out.println("resul" + resul);
            resultado = twoDForm.format(resul) + "%";
            tabla.setValueAt(resultado, x, 3);
        }
    }

    public void ModJtable(JTable tabla) {
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(250);
    }

}
