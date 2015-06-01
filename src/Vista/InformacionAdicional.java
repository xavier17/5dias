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
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author xavier
 */
public class InformacionAdicional extends javax.swing.JFrame {

    /**
     * Creates new form InformacionAdicional
     */
    public InformacionAdicional() {
        initComponents();
        load.LlenarList(jListCuentaInformacion, "SELECT descripcion FROM cuenta_informacion;");
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
            MB.setMes1(Integer.valueOf(jTextField15.getText()));
            MB.setPer1(Integer.valueOf(jTextField16.getText()));
            MB.setMes2(Integer.valueOf(jTextField18.getText()));
            MB.setPer2(Integer.valueOf(jTextField17.getText()));
            MB.setNombremes1(of.NombreMes(MB.getMes1()));
            MB.setNombremes2(of.NombreMes(MB.getMes2()));
            int mesdesde = Integer.valueOf(jTextField15.getText());
            int meshasta = Integer.valueOf(jTextField18.getText());
            int añodesde = Integer.valueOf(jTextField16.getText());
            int añohasta = Integer.valueOf(jTextField17.getText());
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
        if (jComboBox8.getSelectedIndex() == 1) {
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
        jPanelInforAd1 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jComboBox8 = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jListCuentaInformacion = new javax.swing.JList();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();

        jLabel2.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jLabel2.text")); // NOI18N

        jCheckBox1.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jCheckBox1.text")); // NOI18N

        jCheckBox2.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jCheckBox2.text")); // NOI18N

        jCheckBox3.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jCheckBox3.text")); // NOI18N

        jCheckBox4.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jCheckBox4.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jLabel3.text")); // NOI18N

        jCheckBox5.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jCheckBox5.text")); // NOI18N

        jButton1.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox6.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jCheckBox6.text")); // NOI18N

        jCheckBox7.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jCheckBox7.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jLabel4.text")); // NOI18N

        jCheckBox8.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jCheckBox8.text")); // NOI18N

        jCheckBox9.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jCheckBox9.text")); // NOI18N

        jCheckBox10.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jCheckBox10.text")); // NOI18N

        jCheckBox11.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jCheckBox11.text")); // NOI18N

        jCheckBox12.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jCheckBox12.text")); // NOI18N

        jCheckBox13.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jCheckBox13.text")); // NOI18N

        jCheckBox14.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jCheckBox14.text")); // NOI18N

        jCheckBox15.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jCheckBox15.text")); // NOI18N

        jLabel8.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jLabel8.text")); // NOI18N

        jCheckBox16.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jCheckBox16.text")); // NOI18N

        jCheckBox17.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jCheckBox17.text")); // NOI18N

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

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        on.solonumero(jTextField15);
        on.solonumero(jTextField16);
        on.solonumero(jTextField17);
        on.solonumero(jTextField18);

        jButton9.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jButton9.text")); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jButton10.text")); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jButton10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton10KeyReleased(evt);
            }
        });

        jLabel16.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jLabel16.text")); // NOI18N

        jTextField15.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jTextField15.text")); // NOI18N
        jTextField15.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField15FocusGained(evt);
            }
        });

        jTextField16.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jTextField16.text")); // NOI18N
        jTextField16.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField16FocusGained(evt);
            }
        });

        jTextField17.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jTextField17.text")); // NOI18N
        jTextField17.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField17FocusGained(evt);
            }
        });

        jTextField18.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jTextField18.text")); // NOI18N
        jTextField18.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField18FocusGained(evt);
            }
        });

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos los Bancos", "Seleccionar Banco" }));
        jComboBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox8ActionPerformed(evt);
            }
        });

        jLabel17.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jLabel17.text")); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel17)
                .addGap(7, 7, 7)
                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton9)
                        .addComponent(jButton10))
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)))
                .addGap(15, 15, 15))
        );

        jScrollPane7.setViewportView(jListCuentaInformacion);

        jTable4.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(jTable4);

        jPanel15.setBackground(new java.awt.Color(153, 0, 0));

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText(org.openide.util.NbBundle.getMessage(InformacionAdicional.class, "InformacionAdicional.jLabel18.text")); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(748, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel18)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanelInforAd1Layout = new javax.swing.GroupLayout(jPanelInforAd1);
        jPanelInforAd1.setLayout(jPanelInforAd1Layout);
        jPanelInforAd1Layout.setHorizontalGroup(
            jPanelInforAd1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInforAd1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelInforAd1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelInforAd1Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelInforAd1Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelInforAd1Layout.setVerticalGroup(
            jPanelInforAd1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInforAd1Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInforAd1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                    .addComponent(jScrollPane7))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInforAd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanelInforAd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // jButon Reporte
        if (jTable4.getRowCount() <= 0) {
            control.mensaje_error("Tabla Vacia");
            return;
        }
        String directorio_actual = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");
        String ruta1 = directorio_actual + separador + "Reportes" + separador + "Report1.pdf";
        String ruta2 = directorio_actual + separador + "Reportes" + separador + "Report2.pdf";
        String ruta3 = directorio_actual + separador + "Reportes" + separador + "Report3.pdf";

        try {
            int[] cuenta = jListCuentaInformacion.getSelectedIndices();
            //String[] descripcion = (String[]) jListBalanceGral.getSelectedValues();
            for (int i = 0; i < cuenta.length; i++) {
                ControladorReporte CR = new ControladorReporte();
                ModeloBanco mb = new ModeloBanco();

                mb.setIdcuenta(cuenta[i] + 1);
                CB.descripcionInforma(mb);
                this.querybuscar(mb);
                mb.setQueryreport(mb.getIdcuenta() + mb.getQueryreport());

                if (i == 1) {
                    mb.setNombrepdf("Report2.pdf");
                } else {
                    mb.setNombrepdf("Report1.pdf");
                }

                CR.ejecutarReporte_deposito("informacionAdicional.jasper", mb);
                System.out.println("\n\nQueryReport:" + mb.getQueryreport() + "idcuentaInf" + mb.getIdcuenta());

                if (i == 1) {
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
            if (cuenta.length == 1) {
                pdf.openPDF(ruta1);
            } else {
                pdf.openPDF(ruta3);
            }

        } catch (Exception ex) {
            control.mensaje_error(ex.getMessage());
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // jButton Buscar
        ModeloBanco mb = new ModeloBanco();
        int[] cuenta = jListCuentaInformacion.getSelectedIndices();
        load.limpiar(jTable4);
        this.querybuscar(mb);
        for (int i = 0; i < cuenta.length; i++) {
            mb.setIdcuenta(cuenta[i] + 1);
            //System.out.println("Cuentaid:"+mb.getIdcuenta());
            CB.cuentabalance(mb, jTable4);
        }
        load.poner_puntos_concoma(jTable4, 3);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton10KeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton10ActionPerformed(null);
        }
    }//GEN-LAST:event_jButton10KeyReleased

    private void jTextField15FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField15FocusGained
        // TODO add your handling code here:
        jTextField15.selectAll();
    }//GEN-LAST:event_jTextField15FocusGained

    private void jTextField16FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField16FocusGained
        // TODO add your handling code here:
        jTextField16.selectAll();
    }//GEN-LAST:event_jTextField16FocusGained

    private void jTextField17FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField17FocusGained
        // TODO add your handling code here:
        jTextField17.selectAll();
    }//GEN-LAST:event_jTextField17FocusGained

    private void jTextField18FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField18FocusGained
        // TODO add your handling code here:
        jTextField18.selectAll();
    }//GEN-LAST:event_jTextField18FocusGained

    private void jComboBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox8ActionPerformed
        // TODO add your handling code here:
        if (jComboBox8.getSelectedIndex() == 1) {
            jDialogBancos.setModal(true);
            jDialogBancos.setSize(560, 700);
            jDialogBancos.setLocationRelativeTo(null);
            jDialogBancos.setResizable(false);
            jDialogBancos.setVisible(true);
        }
    }//GEN-LAST:event_jComboBox8ActionPerformed

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
            java.util.logging.Logger.getLogger(InformacionAdicional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformacionAdicional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformacionAdicional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformacionAdicional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformacionAdicional().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JComboBox jComboBox8;
    private javax.swing.JDialog jDialogBancos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList jListCuentaInformacion;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanelInforAd1;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    // End of variables declaration//GEN-END:variables
}
