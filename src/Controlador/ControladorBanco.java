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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fernando
 */
public class ControladorBanco {

    Connection c = Conexion.getCon();
    Cargar load = new Cargar();
    Control control = new Control();
    ObtenerNumero on = new ObtenerNumero();
    ObtenerFecha of = new ObtenerFecha();

    public void cuentabalance(ModeloBanco MB, JTable jTable1) {
        try {
            PreparedStatement pstmt = c.prepareStatement(MB.getQuery());
            pstmt.setInt(1, MB.getIdcuenta());
            //System.out.println(pstmt.toString());
            ResultSet rs = pstmt.executeQuery();
            jTable1 = load.cargar_corrido(jTable1, rs);
           // load.poner_puntos_concoma(jTable1, 2);
            //this.diferencia(jTable1);
            //int ancho[] = {400, 50, 50, 100};
            //load.ancho(jTable1, ancho);
            pstmt.close();
        } catch (SQLException ex) {
            control.mensaje_error(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void balanceMes_Mes(ModeloBanco MB, JTable jTable1) {
        try {
            String query = "SELECT e.denominacion, c.descripcion,\n"
                    + "SUM(CASE WHEN  (b.mes) =? AND periodo=? AND b.idcuenta=?  \n"
                    + "THEN b.moneda_local+b.moneda_extranjera ELSE 0 END) AS 'mes1',\n"
                    + "SUM(CASE WHEN  (b.mes) =? AND periodo=? AND b.idcuenta=?  \n"
                    + "THEN b.moneda_local+b.moneda_extranjera ELSE 0 END) AS 'mes2'\n"
                    + "FROM balance_general b\n"
                    + "INNER JOIN entidades e\n"
                    + "ON b.identidades=e.identidades\n"
                    + "INNER JOIN cuenta_balance c\n"
                    + "ON  b.idcuenta=c.idcuenta_balance\n"
                    + "WHERE b.idcuenta=? \n"
                    + "GROUP BY b.identidades,idcuenta\n"
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
            jTable1 = load.cargar(jTable1, rs);
            load.poner_puntos_concoma(jTable1, 2);
            load.poner_puntos_concoma(jTable1, 3);
            this.comparativo(jTable1);
            int ancho[] = {400, 100, 50, 50, 50,};
            load.ancho(jTable1, ancho);
            jTable1.getColumn("denominacion").setHeaderValue("Banco");
            jTable1.getColumn("descripcion").setHeaderValue("Cuenta");
            jTable1.getColumn("mes1").setHeaderValue(MB.getNombremes1());
            jTable1.getColumn("mes2").setHeaderValue(MB.getNombremes2());
            pstmt.close();
        } catch (SQLException ex) {
            control.mensaje_error(ex.getMessage());
        }
    }

    public void diferencia(JTable tabla) {
//        //valores en un arreglo con nombres de personas
        int cant_datos = tabla.getRowCount();
        //creo un modelo para manejar la jTable
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.addColumn("Diferencia");

        //Agrego las filas recorriendo el arreglo valores
        for (int x = 1; x < cant_datos; x++) {
            String mes1 = (String) tabla.getValueAt(x, 2);
            String mes2 = (String) tabla.getValueAt(x - 1, 2);
            int mesuno = on.point_to_number(mes1);
            int mesdos = on.point_to_number(mes2);
            int resul = mesuno - mesdos;
            String resultado = on.getNumero(resul);
            //System.out.println(mesdos+" d "+mesuno);
            if (tabla.getValueAt(x, 0).equals(tabla.getValueAt(x - 1, 0))) {
                tabla.setValueAt(resultado, x, 3);
            }
        }
    }

    public void comparativo(JTable tabla) {
//        //valores en un arreglo con nombres de personas
        int cant_datos = tabla.getRowCount();
        //creo un modelo para manejar la jTable
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.addColumn("Comparativo");

        //Agrego las filas recorriendo el arreglo valores
        for (int x = 0; x < cant_datos; x++) {
            String mes1 = (String) tabla.getValueAt(x, 2);
            String mes2 = (String) tabla.getValueAt(x, 3);
            int mesuno = on.point_to_number(mes1);
            int mesdos = on.point_to_number(mes2);
            int resul = mesuno - mesdos;
            String resultado = on.getNumero(resul);
            tabla.setValueAt(resultado, x, 4);
        }
    }
    
    public void descripcionCuenta(ModeloBanco MB) {
        try {
            String query = "SELECT descripcion FROM cuenta_balance where idcuenta_balance=?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1, MB.getIdcuenta());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                MB.setCuenta(rs.getObject(1).toString());
            }
            pstmt.close();
        } catch (SQLException ex) {
            control.mensaje_error(ex.getMessage());
        }
    }
    
    public void descripcionInforma(ModeloBanco MB) {
        try {
            String query = "SELECT descripcion FROM cuenta_informacion where idcuenta_informacion=?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1, MB.getIdcuenta());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                MB.setCuenta(rs.getObject(1).toString());
            }
            pstmt.close();
        } catch (SQLException ex) {
            control.mensaje_error(ex.getMessage());
        }
    }

}
