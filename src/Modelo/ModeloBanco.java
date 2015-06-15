/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author Fernando
 */
public class ModeloBanco {

    private Date desde;
    private Date hasta;
    private int idcuenta;
    private int mes1;
    private int mes2;
    private int per1;
    private int per2;
    private String nombremes1;
    private String nombremes2;
    private String cuenta;
    private final int[] arrCheck = new int[17];
    private String INbancos;
    private String Query;
    private String queryreport;
    private String nombrepdf;
    private String inBancoquery;
    private int posicionCol;
    /**
     * @return the desde
     */
    public Date getDesde() {
        return desde;
    }

    /**
     * @param desde the desde to set
     */
    public void setDesde(Date desde) {
        this.desde = desde;
    }

    /**
     * @return the hasta
     */
    public Date getHasta() {
        return hasta;
    }

    /**
     * @param hasta the hasta to set
     */
    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public int getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(int idcuenta) {
        this.idcuenta = idcuenta;
    }

    /**
     * @return the mes1
     */
    public int getMes1() {
        return mes1;
    }

    /**
     * @param mes1 the mes1 to set
     */
    public void setMes1(int mes1) {
        this.mes1 = mes1;
    }

    /**
     * @return the mes2
     */
    public int getMes2() {
        return mes2;
    }

    /**
     * @param mes2 the mes2 to set
     */
    public void setMes2(int mes2) {
        this.mes2 = mes2;
    }

    /**
     * @return the per1
     */
    public int getPer1() {
        return per1;
    }

    /**
     * @param per1 the per1 to set
     */
    public void setPer1(int per1) {
        this.per1 = per1;
    }

    /**
     * @return the per2
     */
    public int getPer2() {
        return per2;
    }

    /**
     * @param per2 the per2 to set
     */
    public void setPer2(int per2) {
        this.per2 = per2;
    }

    /**
     * @return the nombremes1
     */
    public String getNombremes1() {
        return nombremes1;
    }

    /**
     * @param nombremes1 the nombremes1 to set
     */
    public void setNombremes1(String nombremes1) {
        this.nombremes1 = nombremes1;
    }

    /**
     * @return the nombremes2
     */
    public String getNombremes2() {
        return nombremes2;
    }

    /**
     * @param nombremes2 the nombremes2 to set
     */
    public void setNombremes2(String nombremes2) {
        this.nombremes2 = nombremes2;
    }

    /**
     * @return the cuenta
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @param ind
     * @return the arrCheck
     */
    public int getArrCheck(int ind) {
        return arrCheck[ind];
    }

    
    public void setArrCheck(int ind, int valor) {
        this.arrCheck[ind]=valor;
    }

    /**
     * @return the INbancos
     */
    public String getINbancos() {
        return INbancos;
    }

    /**
     * @param INbancos the INbancos to set
     */
    public void setINbancos(String INbancos) {
        this.INbancos = INbancos;
    }

    /**
     * @return the Query
     */
    public String getQuery() {
        return Query;
    }

    /**
     * @param Query the Query to set
     */
    public void setQuery(String Query) {
        this.Query = Query;
    }

    /**
     * @return the queryreport
     */
    public String getQueryreport() {
        return queryreport;
    }

    /**
     * @param queryreport the queryreport to set
     */
    public void setQueryreport(String queryreport) {
        this.queryreport = queryreport;
    }

    /**
     * @return the nombrepdf
     */
    public String getNombrepdf() {
        return nombrepdf;
    }

    /**
     * @param nombrepdf the nombrepdf to set
     */
    public void setNombrepdf(String nombrepdf) {
        this.nombrepdf = nombrepdf;
    }

    /**
     * @return the inBancoquery
     */
    public String getInBancoquery() {
        return inBancoquery;
    }

    /**
     * @param inBancoquery the inBancoquery to set
     */
    public void setInBancoquery(String inBancoquery) {
        this.inBancoquery = inBancoquery;
    }

    /**
     * @return the posicionCol
     */
    public int getPosicionCol() {
        return posicionCol;
    }

    /**
     * @param posicionCol the posicionCol to set
     */
    public void setPosicionCol(int posicionCol) {
        this.posicionCol = posicionCol;
    }

}
