/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author FedeXavier
 */
public class ModeloReport {
    private int[] cuenta;
    private String[] titulos;
    private String nombreJasper;
    private String query;
    private String tit;
    private String nombrePDF;

    /**
     * @return the cuenta
     */
    public int[] getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(int[] cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the nombreJasper
     */
    public String getNombreJasper() {
        return nombreJasper;
    }

    /**
     * @param nombreJasper the nombreJasper to set
     */
    public void setNombreJasper(String nombreJasper) {
        this.nombreJasper = nombreJasper;
    }

    /**
     * @return the titulos
     */
    public String[] getTitulos() {
        return titulos;
    }

    /**
     * @param titulos the titulos to set
     */
    public void setTitulos(String[] titulos) {
        this.titulos = titulos;
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * @return the tit
     */
    public String getTit() {
        return tit;
    }

    /**
     * @param tit the tit to set
     */
    public void setTit(String tit) {
        this.tit = tit;
    }

    /**
     * @return the nombrePDF
     */
    public String getNombrePDF() {
        return nombrePDF;
    }

    /**
     * @param nombrePDF the nombrePDF to set
     */
    public void setNombrePDF(String nombrePDF) {
        this.nombrePDF = nombrePDF;
    }
    
}
