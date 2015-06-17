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
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

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

    public void variacion(JTable tabla, ModeloBanco mb) {
//        //valores en un arreglo con nombres de personas
        int cant_datos = tabla.getRowCount();
        //creo un modelo para manejar la jTable
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.addColumn("Variacion");
        String resultado;
        DecimalFormat twoDForm = new DecimalFormat("#");

        //Agrego las filas recorriendo el arreglo valores
        for (int x = 0; x < cant_datos; x++) {
            String mes1 = (String) tabla.getValueAt(x, mb.getPosicionCol());
            String mes2 = (String) tabla.getValueAt(x, mb.getPosicionCol() + 1);
            double mesuno = Double.valueOf(mes1);
            double mesdos = Double.valueOf(mes2);
            double resul = (mesdos / mesuno - 1) * 100;
            System.out.println("resul" + resul);
            resultado = twoDForm.format(resul) + "%";
            tabla.setValueAt(resultado, x, mb.getPosicionCol() + 2);
        }

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        load.poner_puntos(tabla, mb.getPosicionCol());
        load.poner_puntos(tabla, mb.getPosicionCol() + 1);

    }

    public void ModJtable(JTable tabla) {
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(250);
    }

    public void cuenResul(JTable tabla) {
        int filas = tabla.getRowCount();
        int col = tabla.getColumnCount() - 3;
        int iteCol = 3;
        if (tabla.getColumnName(2).toLowerCase().equals("enero")) {
            col = tabla.getColumnCount() - 2;
            iteCol = 2;
        }
        int[][] resultado = new int[filas][col];

        for (int i = iteCol; i < tabla.getColumnCount(); i++) {
            for (int j = 0; j < tabla.getRowCount(); j++) {
                if (!(tabla.getColumnName(i).toLowerCase().equals("enero"))) {
                    String numero1 = String.valueOf(tabla.getValueAt(j, i - 1));
                    String numero2 = String.valueOf(tabla.getValueAt(j, i));
                    int resta = on.point_to_number(numero2) - on.point_to_number(numero1);
                    //System.out.println("numero1 " + numero1 + " numero2 " + numero2 + " resta " + resta);
                    //String res = on.getNumero(resta);
//                        System.out.println("resta" +  res);
                    resultado[j][i - iteCol] = resta;
                } else {
                    String numero1 = String.valueOf(tabla.getValueAt(j, i));
                    resultado[j][i - iteCol] = on.point_to_number(numero1);
                }
            }
        }

        //Se borra la primera columna si no comienza con enero
        if (!(tabla.getColumnName(2).toLowerCase().equals("enero"))) {
            TableColumnModel tcm = tabla.getColumnModel();
            TableColumn columnaABorrar = tcm.getColumn(2);
            tabla.removeColumn(columnaABorrar);
        }

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("  fila[" + i + " ] columna[ " + j + " ] = " + resultado[i][j]);
                String numero = on.getNumero(resultado[i][j]);
                tabla.setValueAt(numero, i, j+2);
            }
            System.out.print("\n");
        }
    }

}
