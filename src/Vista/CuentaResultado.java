/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Conexion.Cargar;
import Conexion.Control;
import Conexion.ExportExcel;
import Conexion.MergePDF;
import Conexion.ObtenerFecha;
import Conexion.ObtenerNumero;
import Controlador.ControladorBanco;
import Controlador.ControladorReporte;
import Modelo.ModeloBanco;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author xavier
 */
public class CuentaResultado extends javax.swing.JFrame {

    /**
     * Creates new form CuentaResultado
     */
    public CuentaResultado() {
        initComponents();
        load.LlenarList(jListCuenResul, "SELECT descripcion FROM cuenta_estado;");
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
        if (jCheckBox17.isSelected()) {
            MB.setArrCheck(16, 19);
        }

        for (int i = 0; i < 16; i++) {
            if (MB.getArrCheck(i) != 0) {
                MB.setINbancos(MB.getINbancos() + "," + MB.getArrCheck(i));
            }
        }

//        if (MB.getINbancos() == null) {
//            control.mensaje_error("Debe seleccionar un banco!");
//        } else {
//            MB.setINbancos(MB.getINbancos().substring(5));
//        }
    }

    //arma el query de acuerdo a lo seleccionado
    public void querybuscar(ModeloBanco MB) {
        String inbanco = "";
        this.desde_hasta(MB);
        if (jComboBox4.getSelectedIndex() == 1) {
            this.impCheckSel(MB);
            if (MB.getINbancos() != null) {
                MB.setINbancos(MB.getINbancos().substring(5));
                inbanco = "and e.identidades IN(" + MB.getINbancos() + ")\n";
            }
        }

        MB.setQuery("SELECT e.alias, c.descripcion, concat(LEFT(m.nombremes,3),'-', i.periodo) as Periodo, i.cantidad\n"
                + "FROM 5dias.informacion_adicional i, 5dias.cuenta_informacion c, 5dias.entidades e,  5dias.meses m\n"
                + "where i.idcuenta_informacion=c.idcuenta_informacion\n"
                + "and i.identidades=e.identidades and i.mes=m.codmes\n"
                + "and i.idcuenta_informacion=?\n"
                + "and i.fecha between '" + MB.getDesde() + "' and '" + MB.getHasta() + "'\n"
                + inbanco
                + " order by i.fecha, e.alias");
        MB.setQueryreport(MB.getQuery().substring(326));

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
        jCheckBox17 = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListCuenResul = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jDialogBancos.setTitle(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jDialogBancos.title")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jLabel2.text")); // NOI18N

        jCheckBox1.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jCheckBox1.text")); // NOI18N

        jCheckBox2.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jCheckBox2.text")); // NOI18N

        jCheckBox3.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jCheckBox3.text")); // NOI18N

        jCheckBox4.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jCheckBox4.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jLabel3.text")); // NOI18N

        jCheckBox5.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jCheckBox5.text")); // NOI18N

        jButton1.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox6.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jCheckBox6.text")); // NOI18N

        jCheckBox7.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jCheckBox7.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jLabel4.text")); // NOI18N

        jCheckBox8.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jCheckBox8.text")); // NOI18N

        jCheckBox9.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jCheckBox9.text")); // NOI18N

        jCheckBox10.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jCheckBox10.text")); // NOI18N

        jCheckBox11.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jCheckBox11.text")); // NOI18N

        jCheckBox12.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jCheckBox12.text")); // NOI18N

        jCheckBox13.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jCheckBox13.text")); // NOI18N

        jCheckBox14.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jCheckBox14.text")); // NOI18N

        jCheckBox15.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jCheckBox15.text")); // NOI18N

        jLabel8.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jLabel8.text")); // NOI18N

        jCheckBox16.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jCheckBox16.text")); // NOI18N

        jCheckBox17.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jCheckBox17.text")); // NOI18N

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
                                        .addComponent(jCheckBox13)
                                        .addComponent(jCheckBox14)
                                        .addComponent(jCheckBox17)
                                        .addComponent(jCheckBox8)
                                        .addComponent(jCheckBox16)
                                        .addComponent(jCheckBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jCheckBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox17)
                .addGap(15, 15, 15)
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
                    .addContainerGap(618, Short.MAX_VALUE)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        on.solonumero(jTextField5);
        on.solonumero(jTextField6);
        on.solonumero(jTextField7);
        on.solonumero(jTextField8);

        jButton5.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jButton5.text")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jButton6.text")); // NOI18N
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

        jLabel6.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jLabel6.text")); // NOI18N

        jTextField5.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jTextField5.text")); // NOI18N
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField5FocusGained(evt);
            }
        });

        jTextField6.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jTextField6.text")); // NOI18N
        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField6FocusGained(evt);
            }
        });

        jTextField7.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jTextField7.text")); // NOI18N
        jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField7FocusGained(evt);
            }
        });

        jTextField8.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jTextField8.text")); // NOI18N
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField8FocusGained(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sistema", "Seleccionar Banco" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jLabel7.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jLabel7.text")); // NOI18N

        jLabel9.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jLabel9.text")); // NOI18N

        jButton7.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jButton7.text")); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(7, 7, 7)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5)
                        .addComponent(jButton6)
                        .addComponent(jButton7))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addGap(15, 15, 15))
        );

        jScrollPane4.setViewportView(jListCuenResul);

        jTable1.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel8.setBackground(new java.awt.Color(153, 0, 0));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText(org.openide.util.NbBundle.getMessage(CuentaResultado.class, "CuentaResultado.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(727, 727, 727))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // Busqueda Cuenta Resultado
        ModeloBanco mb = new ModeloBanco();
        
        
        
//        if (jTable1.getRowCount() <= 0) {
//            control.mensaje_error("Tabla Vacia");
//            return;
//        }
//        String directorio_actual = System.getProperty("user.dir");
//        String separador = System.getProperty("file.separator");
//        String ruta1 = directorio_actual + separador + "Reportes" + separador + "Report1.pdf";
//        String ruta2 = directorio_actual + separador + "Reportes" + separador + "Report2.pdf";
//        String ruta3 = directorio_actual + separador + "Reportes" + separador + "Report3.pdf";
//
//        try {
//            ControladorReporte CR = new ControladorReporte();
//            int[] cuenta = jListCuenResul.getSelectedIndices();
//            //String[] descripcion = (String[]) jListBalanceGral.getSelectedValues();
//            for (int i = 0; i < cuenta.length; i++) {
//                ModeloBanco mb = new ModeloBanco();
//                mb.setIdcuenta(cuenta[i] + 1);
//                CB.descripcionCuenta(mb);
//                this.querybuscar(mb);
//                mb.setQueryreport(mb.getIdcuenta() + mb.getQueryreport());
//
//                if (i == 1) {
//                    mb.setNombrepdf("Report2.pdf");
//                } else {
//                    mb.setNombrepdf("Report1.pdf");
//                }
//
////                CR.ejecutarReporte_deposito("pruebaquery.jasper", mb);
//                System.out.println("\n\nQueryReport:" + mb.getQueryreport() + "idcuentaBal" + mb.getIdcuenta());
//
//                if (i == 1) {
//                    pdf.MergePDF(ruta1, ruta2, ruta3);
//                } else {
//                    if (i > 1) {
//                        File fichero = new File(ruta2);
//                        fichero.delete();
//                        File r1 = new File(ruta1);
//                        File r2 = new File(ruta2);
//                        File r3 = new File(ruta3);
//                        r1.renameTo(r2);
//                        r3.renameTo(r1);
//                        pdf.MergePDF(ruta1, ruta2, ruta3);
//                    }
//                }
//            }
//            if (cuenta.length == 1) {
//                pdf.openPDF(ruta1);
//            } else {
//                pdf.openPDF(ruta3);
//            }
//
//        } catch (Exception ex) {
//            control.mensaje_error(ex.getMessage());
//        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // BT bucar
        ModeloBanco mb = new ModeloBanco();
        int[] cuenta = jListCuenResul.getSelectedIndices();
        desde_hasta(mb);
        load.limpiar(jTable1);
        //this.querybuscar(mb);
        for (int i = 0; i < cuenta.length; i++) {
            mb.setIdcuenta(cuenta[i] + 1);
            if (jComboBox4.getSelectedIndex() == 0) {
                CB.cuentaResulSistema(mb, jTable1);
            }
            //System.out.println("Cuentaid:"+mb.getIdcuenta());
        }
        
        if (jComboBox4.getSelectedIndex() == 0) {
            CB.variacion(jTable1);
            load.poner_puntos(jTable1, 1);
            load.poner_puntos(jTable1, 2);
            int ancho[] = {200, 50, 50, 50,};
            load.ancho(jTable1, ancho);
            String col[] = {"Cuenta", mb.getNombremes1() + "-" + mb.getPer1(), mb.getNombremes2() + "-" + mb.getPer2(), "Variacion"};
            load.nombreCol(jTable1, col);
        }
        // load.poner_puntos_concoma(jTable1, 3);
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
            jDialogBancos.setModal(true);
            jDialogBancos.setSize(560, 700);
            jDialogBancos.setLocationRelativeTo(null);
            jDialogBancos.setResizable(false);
            jDialogBancos.setVisible(true);
        }
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // BT export excel
        if (jTable1.getRowCount() <= 0) {
            control.mensaje_error("Tabla Vacia");
            return;
        }

        JFileChooser dialog = new JFileChooser();
        int opcion = dialog.showSaveDialog(this);

        if (opcion == JFileChooser.APPROVE_OPTION) {
            File dir = dialog.getSelectedFile();
            String fl = dir.toString();

            try {
                List<JTable> tb = new ArrayList<JTable>();
                tb.add(jTable1);
                //-------------------
                ExportExcel excelExporter = new ExportExcel(tb, new File(fl + ".xls"));
                if (excelExporter.export()) {
                    JOptionPane.showMessageDialog(null, "TABLAS EXPORTADOS CON EXITOS!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // BT ok seleccionar Bancos
        jDialogBancos.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CuentaResultado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CuentaResultado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CuentaResultado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CuentaResultado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CuentaResultado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JDialog jDialogBancos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jListCuenResul;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
