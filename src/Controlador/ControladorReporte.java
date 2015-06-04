/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexion.Conexion;
import Conexion.MergePDF;
import Modelo.ModeloBanco;
import Modelo.ModeloReport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Fernando
 */
public class ControladorReporte {
    
    MergePDF pdf = new MergePDF();

    // public void ejecutarReporte_deposito(String nombreReport, ModeloBanco MB) {
    //String url = "";
    public void ejecutarReporte_deposito(ModeloReport MR) {
        try {
            
            String directorio_actual = System.getProperty("user.dir");
            String separador = System.getProperty("file.separator");
            // String master = directorio_actual + separador + "src" + separador + "Reportes" + separador + nombreReport;
            // String master = "C:/Users/Federico Lopez/Documents/NetBeansProjects/LaPelle01/src/Reportes/" + nombreReport;
            String master = directorio_actual + separador + "src" + separador + "Reportes" + separador + MR.getNombreJasper();
            // URL master= getClass().getResource("/reportes/registro_mensual.jasper");
            // System.out.println(master);

            //  File file = new File(System.getProperty("user.dir") + "/jarname.jar");
            File file = new File(master);
            if (master == null) {
                JOptionPane.showMessageDialog(null, "Error Cargando el master", "Error", JOptionPane.ERROR_MESSAGE);
                //System.exit(2);
            }
            System.err.println(file);
            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject(file);
            } catch (JRException e) {
                System.out.println("Error cargando el reporte maestro: " + e.getMessage());
            }
            
            String imagen = directorio_actual + separador + "src" + separador + "images" + separador + "logo.png";
            //String imagen = directorio_actual + separador + "Images" + separador + "logo.jpg";
            //este es el parámetro, se pueden agregar más parámetros
            //basta con poner mas parametro.put
            // System.out.println(imagen);
            Map parametro = new HashMap();
            parametro.put("query", MR.getQuery());
            parametro.put("titulo", MR.getTit());
            parametro.put("logo", imagen);
            //parametro.put("hasta", MB.getHasta());
            //parametro.put("idcuenta", MB.getIdcuenta());
            //parametro.put("total", total);
            //parametro.put("imagen", imagen);
            //  parametro.put("fecha", fecha);

            Connection c = Conexion.getCon();
            // String ruta = "C:/Users/Administrador/Documents/Reportes/" + nombreReport + ".pdf";
            //Reporte diseñado y compilado con iReport

            try {
                JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, c);
                // String ruta = directorio_actual + separador + "Reportes" + separador + MR.getNombrepdf();
                String ruta = directorio_actual + separador + "Reportes" + separador + MR.getNombrePDF();
                System.out.print("Ruta " + ruta);
                JasperExportManager.exportReportToPdfFile(jasperPrint, ruta);
                //st.open(ruta);
//                JasperViewer jviewer = new JasperViewer(jasperPrint, false);
//                jviewer.setTitle(nombreReport);
//                jviewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
//                jviewer.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            //Se lanza el Viewer de Jasper, no termina aplicación al salir
//            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
//            jviewer.setTitle(nombreReport);
//            jviewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
//            jviewer.setVisible(true);
        } catch (Exception j) {
            JOptionPane.showMessageDialog(null, j.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void imprimirReporte(ModeloReport mr, ModeloBanco mb) {
        try {
            String directorio_actual = System.getProperty("user.dir");
            String separador = System.getProperty("file.separator");
            String merge = directorio_actual + separador + "Reportes" + separador + "Merge.pdf";
            
            int cuentas[] = mr.getCuenta();
            String[] titulo = mr.getTitulos();
            List<InputStream> pdfs = new ArrayList<InputStream>();
            
            for (int i = 0; i < cuentas.length; i++) {
                mb.setIdcuenta(cuentas[i] + 1);
                mr.setTit(titulo[i]);
                mr.setQuery(mb.getIdcuenta() + mb.getQueryreport());
                mr.setNombrePDF("Report" + i + ".pdf");
                ejecutarReporte_deposito(mr);
                String ruta1 = directorio_actual + separador + "Reportes" + separador + mr.getNombrePDF();
                pdfs.add(new FileInputStream(ruta1));
                System.out.print("Query" + mb.getQueryreport());
            }
            
            OutputStream output = new FileOutputStream(merge);
            pdf.concatPDFs(pdfs, output, true);
            pdf.openPDF(merge);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void deleteReport(String directorio) {
        try {
            System.out.print(directorio);
            File dir = new File(directorio);
            File[] files;
            files = dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    //return name.startsWith("R") && name.toLowerCase().endsWith(".pdf");
                    return name.startsWith("R");
                }
            });
            System.out.print("File" + dir);
            for (File file : files) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
