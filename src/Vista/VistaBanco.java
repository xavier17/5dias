/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Conexion.Cargar;
import Conexion.Control;
import Conexion.MergePDF;
import Conexion.ObtenerFecha;
import Conexion.ObtenerNumero;
import Controlador.ControladorBanco;
import Controlador.ControladorReporte;
import Modelo.ModeloBanco;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author xavier
 */
public class VistaBanco extends javax.swing.JPanel {

    /**
     * Creates new form Tab
     */
    public VistaBanco() {
        initComponents();
        load.LlenarList(jListBalanceGral, "SELECT descripcion FROM cuenta_balance;");
         //load.LlenarList(jListBalanceGral, "SELECT descripcion FROM diasticocuenta_balance;");
    }
    Cargar load = new Cargar();
    ObtenerNumero on = new ObtenerNumero();
    ObtenerFecha of = new ObtenerFecha();
    ControladorBanco CB = new ControladorBanco();
    Control control = new Control();
    MergePDF pdf = new MergePDF();

    public void desde_hasta(ModeloBanco MB) {
        try {
            MB.setMes1(Integer.valueOf(jTextField5.getText()));
            MB.setPer1(Integer.valueOf(jTextField6.getText()));
            MB.setMes2(Integer.valueOf(jTextField8.getText()));
            MB.setPer2(Integer.valueOf(jTextField7.getText()));
            MB.setNombremes1(of.NombreMes(MB.getMes1()));
            MB.setNombremes2(of.NombreMes(MB.getMes2()));
            int mesdesde = Integer.valueOf(jTextField5.getText());
            int meshasta = Integer.valueOf(jTextField8.getText());
            int añodesde = Integer.valueOf(jTextField6.getText());
            int añohasta = Integer.valueOf(jTextField7.getText());
            String desde = añodesde + "-" + mesdesde + "-01";
            Date hasta = of.findemes(meshasta, añohasta);
            //MB.setCuenta(String.valueOf(jComboBox2.getSelectedItem()));
            //MB.setIdcuenta(jComboBox2.getSelectedIndex() + 1);
            MB.setDesde(of.de_String_a_java(desde));
            MB.setHasta(of.de_java_a_sql(hasta));
        } catch (Exception e) {
            control.mensaje_error("Debe ingresar una fecha correcta");
        }
    }

    public void impCheckSel(ModeloBanco MB) {
        if (jCheckBox16.isSelected()) {
            MB.setArrCheck(0, 16);
        }
        if (jCheckBox5.isSelected()) {
            MB.setArrCheck(1, 1);
        }
        if (jCheckBox1.isSelected()) {
            MB.setArrCheck(2, 2);
        }
        if (jCheckBox3.isSelected()) {
            MB.setArrCheck(3, 3);
        }
        if (jCheckBox4.isSelected()) {
            MB.setArrCheck(4, 4);
        }
        if (jCheckBox2.isSelected()) {
            MB.setArrCheck(5, 5);
        }
        if (jCheckBox6.isSelected()) {
            MB.setArrCheck(6, 6);
        }
        if (jCheckBox7.isSelected()) {
            MB.setArrCheck(7, 7);
        }
        if (jCheckBox8.isSelected()) {
            MB.setArrCheck(8, 8);
        }
        if (jCheckBox12.isSelected()) {
            MB.setArrCheck(9, 9);
        }
        if (jCheckBox9.isSelected()) {
            MB.setArrCheck(10, 10);
        }
        if (jCheckBox10.isSelected()) {
            MB.setArrCheck(11, 11);
        }
        if (jCheckBox11.isSelected()) {
            MB.setArrCheck(12, 12);
        }
        if (jCheckBox15.isSelected()) {
            MB.setArrCheck(13, 13);
        }
        if (jCheckBox13.isSelected()) {
            MB.setArrCheck(14, 14);
        }
        if (jCheckBox14.isSelected()) {
            MB.setArrCheck(15, 15);
        }
        for (int i = 0; i < 16; i++) {
            if (MB.getArrCheck(i) != 0) {
                MB.setINbancos(MB.getINbancos() + "," + MB.getArrCheck(i));
            }
        }

        if (MB.getINbancos() == null) {
            control.mensaje_error("Debe seleccionar un banco!");
        } else {
            MB.setINbancos(MB.getINbancos().substring(5));
        }

    }

    //arma el query de acuerdo a lo seleccionado
    public void querybuscar(ModeloBanco MB) {
        String inbanco = "";
        this.desde_hasta(MB);
        if (jComboBox4.getSelectedIndex() == 1) {
            this.impCheckSel(MB);
            inbanco = "and e.identidades IN(" + MB.getINbancos() + ") \n";
        }

        MB.setQuery("SELECT e.alias as Banco,c.descripcion, concat(LEFT(m.nombremes,3),'-', b.periodo) as Periodo, b.moneda_local +b.moneda_extranjera as Total\n"
                + "FROM balance_general b, cuenta_balance c, entidades e,  meses m \n"
                + "where c.idcuenta_balance=b.idcuenta and b.identidades=e.identidades and b.mes=m.codmes \n"
                + "and idcuenta_balance=?"
                + " and b.fecha between '" + MB.getDesde() + "' and '" + MB.getHasta() + "'\n"
                + inbanco
                + "order by b.fecha, e.alias");
        MB.setQueryreport(MB.getQuery().substring(314));
    }

    public void VerjDialog(JDialog ventana, int with, int heigt) {
        ventana.setModal(true);
        ventana.setSize(with, heigt);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setVisible(true);
    }
    
    public void ventana(JPanel tab) {
        tab.setSize(jTabbedPane1.getSize());
        jTabbedPane1.removeAll();
        jTabbedPane1.add(tab);
        jTabbedPane1.revalidate();
        jTabbedPane1.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogBancos = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jCheckBox5 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox15 = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jCheckBox16 = new javax.swing.JCheckBox();
        jDialogResultado = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jTextField9 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListBalanceGral = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        jLabel2.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jLabel2.text")); // NOI18N

        jCheckBox1.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jCheckBox1.text")); // NOI18N

        jCheckBox2.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jCheckBox2.text")); // NOI18N

        jCheckBox3.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jCheckBox3.text")); // NOI18N

        jCheckBox4.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jCheckBox4.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jLabel3.text")); // NOI18N

        jCheckBox5.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jCheckBox5.text")); // NOI18N

        jButton1.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox6.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jCheckBox6.text")); // NOI18N

        jCheckBox7.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jCheckBox7.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jLabel4.text")); // NOI18N

        jCheckBox8.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jCheckBox8.text")); // NOI18N

        jCheckBox9.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jCheckBox9.text")); // NOI18N

        jCheckBox10.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jCheckBox10.text")); // NOI18N

        jCheckBox11.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jCheckBox11.text")); // NOI18N

        jCheckBox12.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jCheckBox12.text")); // NOI18N

        jCheckBox13.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jCheckBox13.text")); // NOI18N

        jCheckBox14.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jCheckBox14.text")); // NOI18N

        jCheckBox15.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jCheckBox15.text")); // NOI18N

        jLabel8.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jLabel8.text")); // NOI18N

        jCheckBox16.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jCheckBox16.text")); // NOI18N

        javax.swing.GroupLayout jDialogBancosLayout = new javax.swing.GroupLayout(jDialogBancos.getContentPane());
        jDialogBancos.getContentPane().setLayout(jDialogBancosLayout);
        jDialogBancosLayout.setHorizontalGroup(
            jDialogBancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogBancosLayout.createSequentialGroup()
                .addGroup(jDialogBancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogBancosLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jDialogBancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jDialogBancosLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jDialogBancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addGroup(jDialogBancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addGroup(jDialogBancosLayout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addGroup(jDialogBancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jDialogBancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogBancosLayout.createSequentialGroup()
                                                .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(68, 68, 68))
                                            .addComponent(jCheckBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jCheckBox6)
                                            .addComponent(jCheckBox7))
                                        .addComponent(jCheckBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jCheckBox10)
                                        .addComponent(jCheckBox11)
                                        .addGroup(jDialogBancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogBancosLayout.createSequentialGroup()
                                                .addComponent(jCheckBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(68, 68, 68))
                                            .addComponent(jCheckBox13)
                                            .addComponent(jCheckBox14))
                                        .addComponent(jCheckBox8)
                                        .addComponent(jCheckBox16)
                                        .addComponent(jCheckBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(jDialogBancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialogBancosLayout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addGroup(jDialogBancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(42, Short.MAX_VALUE)))
        );
        jDialogBancosLayout.setVerticalGroup(
            jDialogBancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogBancosLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jCheckBox5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox3)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox7)
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox14)
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox16)
                .addGap(24, 24, 24)
                .addComponent(jButton1)
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(jDialogBancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialogBancosLayout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(600, Short.MAX_VALUE)))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cuenta", "Mes 1", "Mes 2", "Diferencia", "Variacion"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField9.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jTextField9.text")); // NOI18N
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField9FocusGained(evt);
            }
        });

        jTextField12.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jTextField12.text")); // NOI18N
        jTextField12.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField12FocusGained(evt);
            }
        });

        jButton2.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jButton2.text")); // NOI18N

        jButton3.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jButton3.text")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(332, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(153, 0, 0));

        jLabel10.setFont(new java.awt.Font("Simplified Arabic", 0, 30)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jLabel10.text")); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel10)
                .addGap(10, 10, 10))
        );

        jScrollPane3.setViewportView(jList1);

        javax.swing.GroupLayout jDialogResultadoLayout = new javax.swing.GroupLayout(jDialogResultado.getContentPane());
        jDialogResultado.getContentPane().setLayout(jDialogResultadoLayout);
        jDialogResultadoLayout.setHorizontalGroup(
            jDialogResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogResultadoLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jDialogResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jDialogResultadoLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jDialogResultadoLayout.setVerticalGroup(
            jDialogResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogResultadoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialogResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        on.solonumero(jTextField5);
        on.solonumero(jTextField6);
        on.solonumero(jTextField7);
        on.solonumero(jTextField8);

        jButton5.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jButton5.text")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jButton6.text")); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jButton6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton6KeyReleased(evt);
            }
        });

        jLabel6.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jLabel6.text")); // NOI18N

        jTextField5.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jTextField5.text")); // NOI18N
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField5FocusGained(evt);
            }
        });

        jTextField6.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jTextField6.text")); // NOI18N
        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField6FocusGained(evt);
            }
        });

        jTextField7.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jTextField7.text")); // NOI18N
        jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField7FocusGained(evt);
            }
        });

        jTextField8.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jTextField8.text")); // NOI18N
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField8FocusGained(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hasta", "con" }));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos los Bancos", "Seleccionar Banco" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jLabel7.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jLabel7.text")); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel7)
                .addGap(7, 7, 7)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(220, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5)
                        .addComponent(jButton6))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addGap(15, 15, 15))
        );

        jScrollPane4.setViewportView(jListBalanceGral);

        jTable1.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel8.setBackground(new java.awt.Color(153, 0, 0));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jLabel5.text")); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jLabel14.text")); // NOI18N
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(35, 35, 35)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(369, 369, 369))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1235, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1235, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 673, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1235, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 673, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(VistaBanco.class, "VistaBanco.jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
//        ControladorReporte CR = new ControladorReporte();
//            ModeloBanco mb = new ModeloBanco();
//            mb.setCuenta(String.valueOf(jComboBox2.getItemAt(1)));
//            mb.setIdcuenta(1);
//            this.querybuscar(mb);
//            System.out.print(mb.getQueryreport());
        if (jTable1.getRowCount() <= 0) {
            control.mensaje_error("Tabla Vacia");
            return;
        }
        String directorio_actual = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");
        String ruta1 = directorio_actual + separador + "Reportes" + separador + "Report1.pdf";
        String ruta2 = directorio_actual + separador + "Reportes" + separador + "Report2.pdf";
        String ruta3 = directorio_actual + separador + "Reportes" + separador + "Report3.pdf";

        try {
            int[] cuenta = jListBalanceGral.getSelectedIndices();
            //String[] descripcion = (String[]) jListBalanceGral.getSelectedValues();
            for (int i = 0; i < cuenta.length; i++) {
                ControladorReporte CR = new ControladorReporte();
                ModeloBanco mb = new ModeloBanco();
                
                //mb.setIdcuenta(i + 1);
                mb.setIdcuenta(cuenta[i] + 1);
                CB.descripcionCuenta(mb);
                this.querybuscar(mb);
                mb.setQueryreport(mb.getIdcuenta()+mb.getQueryreport());
                
                if (i == 1) {
                    mb.setNombrepdf("Report2.pdf");
                } else {
                    mb.setNombrepdf("Report1.pdf");
                }
                
                
                if (jComboBox3.getSelectedIndex() == 0) {
                    CR.ejecutarReporte_deposito("pruebaquery.jasper", mb);
                    System.out.println("\n\nQueryReport:"+mb.getQueryreport() + "idcuentaBal"+mb.getIdcuenta());
                } else {

                }

                if(i==1){
                    pdf.MergePDF(ruta1, ruta2, ruta3);
                } else {
                    if (i > 1) {
                        File fichero = new File(ruta2);
                        fichero.delete();
                        File r1 = new File(ruta1);
                        File r2 = new File(ruta2);
                        File r3 = new File(ruta3);
                        r1.renameTo(r2);
                        r3.renameTo(r1);
                        pdf.MergePDF(ruta1, ruta2, ruta3);
                    }
                }
            }
            if(cuenta.length==1){
                pdf.openPDF(ruta1);
            }else{
                pdf.openPDF(ruta3);
            }
            
        }catch(Exception ex){
            control.mensaje_error(ex.getMessage());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // BT bucar
        ModeloBanco mb = new ModeloBanco();
        int[] cuenta = jListBalanceGral.getSelectedIndices();
        load.limpiar(jTable1);
        this.querybuscar(mb);
        for (int i = 0; i < cuenta.length; i++) {
            mb.setIdcuenta(cuenta[i]+1);
            if (jComboBox3.getSelectedIndex() == 0) {
                CB.cuentabalance(mb, jTable1);
            } else {
                CB.balanceMes_Mes(mb, jTable1);
            }
            //System.out.println("Cuentaid:"+mb.getIdcuenta());
        }
        load.poner_puntos_concoma(jTable1, 3);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton6ActionPerformed(null);
        }
    }//GEN-LAST:event_jButton6KeyReleased

    private void jTextField5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusGained
        // TODO add your handling code here:
        jTextField5.selectAll();
    }//GEN-LAST:event_jTextField5FocusGained

    private void jTextField6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusGained
        // TODO add your handling code here:
        jTextField6.selectAll();
    }//GEN-LAST:event_jTextField6FocusGained

    private void jTextField7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusGained
        // TODO add your handling code here:
        jTextField7.selectAll();
    }//GEN-LAST:event_jTextField7FocusGained

    private void jTextField8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusGained
        // TODO add your handling code here:
        jTextField8.selectAll();
    }//GEN-LAST:event_jTextField8FocusGained

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
        if (jComboBox4.getSelectedIndex() == 1) {
            VerjDialog(jDialogBancos, 560, 700);
        }
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // BT ok seleccionar Bancos
        jDialogBancos.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusGained
        // Anho1
        jTextField9.selectAll();
    }//GEN-LAST:event_jTextField9FocusGained

    private void jTextField12FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField12FocusGained
        // JT mes1
        jTextField12.selectAll();
    }//GEN-LAST:event_jTextField12FocusGained

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        VerjDialog(jDialogResultado, 700, 550);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // MouseClick Informacion Adicional
        InformacionAdicional IF = new InformacionAdicional();
        IF.setVisible(true);
    }//GEN-LAST:event_jLabel14MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JDialog jDialogBancos;
    private javax.swing.JDialog jDialogResultado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList jList1;
    private javax.swing.JList jListBalanceGral;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

}
