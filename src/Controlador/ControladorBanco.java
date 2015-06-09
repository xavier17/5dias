/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexion.Cargar;
import Conexion.Conexion;
import Conexion.Control;
import Conexion.ExporterXLS;
import Conexion.ObtenerFecha;
import Conexion.ObtenerNumero;
import Modelo.ModeloBanco;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
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
            if (rs.next()) {
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
            if (rs.next()) {
                MB.setCuenta(rs.getObject(1).toString());
            }
            pstmt.close();
        } catch (SQLException ex) {
            control.mensaje_error(ex.getMessage());
        }
    }

    public void exportExcel(JTable tabla, String nomHoja) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            List<JTable> tb = new ArrayList<JTable>();
            List<String> nom = new ArrayList<String>();
            tb.add(tabla);
            nom.add(nomHoja);
            String file = chooser.getSelectedFile().toString().concat(".xls");
            try {
                ExporterXLS e = new ExporterXLS(new File(file), tb, nom);
                //Conexion.ExportExcel e = new ExportExcel(tb, new File(file));
                if (e.export()) {
                    JOptionPane.showMessageDialog(null, "Los datos fueron exportados a excel en el directorio seleccionado", "Mensaje de Informacion", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Hubo un error " + e.getMessage(), " Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
