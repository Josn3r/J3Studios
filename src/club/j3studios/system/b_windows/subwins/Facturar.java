/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package club.j3studios.system.b_windows.subwins;

import club.j3studios.system.b_windows.MainMenu;
import club.j3studios.system.b_windows.dialogs.AddProductDialog;
import club.j3studios.system.b_windows.dialogs.CobrarDialog;
import club.j3studios.system.control.Ctrl_Producto;
import club.j3studios.system.database.SQL;
import club.j3studios.system.model.DetalleVenta;
import club.j3studios.system.utils.Tools;
import club.j3studios.system.utils.jtable.ButtonRenderer;
import club.j3studios.system.utils.jtable.GestionEncabezadoTabla;
import club.j3studios.system.utils.jtable.TableActionCellEditor;
import club.j3studios.system.utils.jtable.TableActionCellRenderer;
import club.j3studios.system.utils.jtable.TableActionEvent;
import club.j3studios.system.b_windows.dialogs.DeleteItemVentaDialog;
import club.j3studios.system.b_windows.dialogs.EditItemVentaDialog;
import club.j3studios.system.b_windows.dialogs.SearchProductDialog;
import club.j3studios.system.b_windows.dialogs.asignClientFactura;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author josne
 */
public class Facturar extends javax.swing.JPanel {

    private MainMenu principal;
    private Facturar facturar;
    
    public MainMenu getMainMenu() {
        return principal;
    }
    
    public Facturar(MainMenu principal) {
        this.facturar = this;
        this.principal = principal;
        
        initComponents();
        
        Timer timer = new Timer();
        // Schedule a task to be executed every second
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	String fechaActual = "";
                Date date = new Date();
                fechaActual = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(date);            	
                labelFecha.setText(fechaActual);
            }
        }, 0, 1000);
        
        labelVersion.setText("Version " + MainMenu.VERSION_PROGRAM);
        
        inicializarTablaProducto();
        listaTablaProductos();
        
        labelUsuario.setText(principal.usuario.getNombre());
        labelRango.setText("Administrador");
        
        KeyStroke keyStrokeF10 = KeyStroke.getKeyStroke("F10");
            Action accionkeyF10 = new AbstractAction("f10") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idCliente <= 0) {
                    JOptionPane.showMessageDialog(null, "Debes asignar un cliente antes de continuar con la facturación.");
                    return;
                }
                SearchProductDialog dialog = new SearchProductDialog(principal, facturar);
                dialog.showMessage("CAJA");
            }
        };
        String keyF10 = "f10";
        btnSearchProduct.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeF10, keyF10);
        btnSearchProduct.getActionMap().put(keyF10, accionkeyF10);
        
        //
        
        KeyStroke keyStrokeF9 = KeyStroke.getKeyStroke("F9");
            Action accionkeyF9 = new AbstractAction("f9") {
            @Override
            public void actionPerformed(ActionEvent e) {
                asignClientFactura dialog = new asignClientFactura(principal, facturar);
                dialog.showMessage();
            }
        };
        String keyF9 = "f9";
        btnAsignClient.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeF9, keyF9);
        btnAsignClient.getActionMap().put(keyF9, accionkeyF9);
        
        //btnCobrar
        
        KeyStroke keyStrokeF2 = KeyStroke.getKeyStroke("F2");
            Action accionkeyF2 = new AbstractAction("f2") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaProductos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "¡No hay artículos en la lista para cobrar!\n      Debes agregar articulos primero.");
                    return;
                }
                if (idCliente <= 0) {
                    JOptionPane.showMessageDialog(null, "Debes asignar un cliente antes de continuar a la sección de cobrar.");
                    return;
                }
                CobrarDialog dialog = new CobrarDialog(principal, facturar);
                dialog.showMessage("CAJA");
            }
        };
        String keyF2 = "f2";
        btnAsignClient.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeF2, keyF2);
        btnAsignClient.getActionMap().put(keyF2, accionkeyF2);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtCedula = new javax.swing.JLabel();
        txtSearchByCode = new club.j3studios.system.utils.TextField();
        btnSearchProduct = new club.j3studios.system.utils.Button();
        btnAsignClient = new club.j3studios.system.utils.Button();
        jLabel2 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JLabel();
        txtNombre = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        contadorProductos = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        limpiarLista = new club.j3studios.system.utils.Button();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnUpdateDolar = new club.j3studios.system.utils.Button();
        checkDolarManual = new club.j3studios.system.utils.JCheckBoxCustom();
        updateDolarValue = new javax.swing.JLabel();
        textDolar = new club.j3studios.system.utils.FormattedTextField();
        labelTotal = new javax.swing.JLabel();
        jLabelContador = new javax.swing.JLabel();
        label_Iva = new javax.swing.JLabel();
        labelTotalUSD = new javax.swing.JLabel();
        labelSubTotal = new javax.swing.JLabel();
        jLabelContador1 = new javax.swing.JLabel();
        jLabelContador2 = new javax.swing.JLabel();
        jLabelContador3 = new javax.swing.JLabel();
        btnCobrar = new club.j3studios.system.utils.Button();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelVersion = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        labelU = new javax.swing.JLabel();
        labelRango = new javax.swing.JLabel();
        labelR = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setOpaque(false);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCedula.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtCedula.setForeground(new java.awt.Color(102, 102, 102));
        txtCedula.setText("CEDULA o RIF:");
        jPanel1.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 110, 340, 20));

        txtSearchByCode.setShadowColor(new java.awt.Color(0, 102, 204));
        txtSearchByCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchByCodeActionPerformed(evt);
            }
        });
        txtSearchByCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchByCodeKeyPressed(evt);
            }
        });
        jPanel1.add(txtSearchByCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 260, 50));

        btnSearchProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buscar.png"))); // NOI18N
        btnSearchProduct.setText("BUSCAR [F10]");
        btnSearchProduct.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearchProduct.setRippleColor(new java.awt.Color(0, 102, 255));
        btnSearchProduct.setShadowColor(new java.awt.Color(0, 102, 255));
        btnSearchProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchProductActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearchProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 150, 50));

        btnAsignClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user2.png"))); // NOI18N
        btnAsignClient.setText("ASIGNAR CLIENTE [F9]");
        btnAsignClient.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAsignClient.setRippleColor(new java.awt.Color(0, 102, 255));
        btnAsignClient.setShadowColor(new java.awt.Color(0, 102, 255));
        btnAsignClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignClientActionPerformed(evt);
            }
        });
        jPanel1.add(btnAsignClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 100, 210, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("CÓDIGO DEL PRODUCTO:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 150, 40));

        txtTelefono.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(102, 102, 102));
        txtTelefono.setText("TELÉFONO:");
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 130, 340, 20));

        txtNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(102, 102, 102));
        txtNombre.setText("NOMBRE Y APELLIDO:");
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 90, 340, 20));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPane.setViewportView(table);

        jPanel1.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 1360, 410));

        jPanel2.setBackground(new java.awt.Color(232, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        contadorProductos.setFont(new java.awt.Font("Trebuchet MS", 1, 28)); // NOI18N
        contadorProductos.setForeground(new java.awt.Color(0, 153, 204));
        contadorProductos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contadorProductos.setText("0");
        jPanel2.add(contadorProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 40, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("TASA DEL DOLAR OFICIAL (B.C.V)");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 250, 30));

        limpiarLista.setText("LIMPIAR");
        limpiarLista.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        limpiarLista.setRippleColor(new java.awt.Color(0, 102, 255));
        limpiarLista.setShadowColor(new java.awt.Color(0, 102, 255));
        limpiarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarListaActionPerformed(evt);
            }
        });
        jPanel2.add(limpiarLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 100, 50));

        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("_________________________________________________________________________________________________");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 510, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("PRODUCTOS REGISTRADOS EN LA LISTA.");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 250, 50));

        btnUpdateDolar.setText("ACTUALIZAR");
        btnUpdateDolar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateDolar.setRippleColor(new java.awt.Color(0, 102, 255));
        btnUpdateDolar.setShadowColor(new java.awt.Color(0, 102, 255));
        btnUpdateDolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateDolarActionPerformed(evt);
            }
        });
        jPanel2.add(btnUpdateDolar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 110, 40));

        checkDolarManual.setText("ACTUALIZACIÓN MANUAL");
        checkDolarManual.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                checkDolarManualStateChanged(evt);
            }
        });
        jPanel2.add(checkDolarManual, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 90, 170, -1));

        updateDolarValue.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        updateDolarValue.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(updateDolarValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 380, 40));

        textDolar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textDolar.setText("0.00");
        textDolar.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        textDolar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textDolarKeyTyped(evt);
            }
        });
        jPanel2.add(textDolar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 180, 70));

        labelTotal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelTotal.setForeground(new java.awt.Color(51, 153, 255));
        labelTotal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labelTotal.setText("Bs. 0.00");
        jPanel2.add(labelTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 100, 250, 50));

        jLabelContador.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelContador.setForeground(new java.awt.Color(51, 51, 51));
        jLabelContador.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelContador.setText("Total en Bs:");
        jPanel2.add(jLabelContador, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 100, 120, 50));

        label_Iva.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label_Iva.setForeground(new java.awt.Color(51, 51, 51));
        label_Iva.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_Iva.setText("Bs. 0.00");
        jPanel2.add(label_Iva, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 40, 250, 30));

        labelTotalUSD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelTotalUSD.setForeground(new java.awt.Color(51, 51, 51));
        labelTotalUSD.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labelTotalUSD.setText("$0.00");
        jPanel2.add(labelTotalUSD, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 70, 250, 30));

        labelSubTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelSubTotal.setForeground(new java.awt.Color(51, 51, 51));
        labelSubTotal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labelSubTotal.setText("Bs. 0.00");
        jPanel2.add(labelSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 10, 250, 30));

        jLabelContador1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelContador1.setForeground(new java.awt.Color(51, 51, 51));
        jLabelContador1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelContador1.setText("Sub Total:");
        jPanel2.add(jLabelContador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 10, 120, 30));

        jLabelContador2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelContador2.setForeground(new java.awt.Color(51, 51, 51));
        jLabelContador2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelContador2.setText("IVA:");
        jPanel2.add(jLabelContador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 40, 120, 30));

        jLabelContador3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelContador3.setForeground(new java.awt.Color(51, 51, 51));
        jLabelContador3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelContador3.setText("Total en USD:");
        jPanel2.add(jLabelContador3, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 70, 120, 30));

        btnCobrar.setBackground(new java.awt.Color(204, 255, 204));
        btnCobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cobrar.png"))); // NOI18N
        btnCobrar.setRippleColor(new java.awt.Color(102, 255, 102));
        btnCobrar.setShadowColor(new java.awt.Color(0, 153, 51));
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCobrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 160, 110));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("COBRAR - [F2]");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 6, 160, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 1360, 170));

        jPanel3.setBackground(new java.awt.Color(255, 255, 220));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelVersion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelVersion.setForeground(new java.awt.Color(102, 102, 102));
        labelVersion.setText("Version 1.13.08.23");
        jPanel3.add(labelVersion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 362, 20));

        labelFecha.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        labelFecha.setForeground(new java.awt.Color(102, 102, 102));
        labelFecha.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labelFecha.setText("13/08/2023 - 4:47:34");
        jPanel3.add(labelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, 362, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 740, 1360, 30));

        labelUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelUsuario.setForeground(new java.awt.Color(255, 255, 255));
        labelUsuario.setText("---");
        jPanel1.add(labelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 210, 20));

        labelU.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelU.setForeground(new java.awt.Color(255, 255, 255));
        labelU.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labelU.setText("VENDEDOR: ");
        jPanel1.add(labelU, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 90, 20));

        labelRango.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelRango.setForeground(new java.awt.Color(255, 255, 255));
        labelRango.setText("---");
        jPanel1.add(labelRango, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 210, 20));

        labelR.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelR.setForeground(new java.awt.Color(255, 255, 255));
        labelR.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labelR.setText("RANGO: ");
        jPanel1.add(labelR, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 90, 20));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Background1.png"))); // NOI18N
        background.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                backgroundKeyPressed(evt);
            }
        });
        jPanel1.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, 760));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backgroundKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_backgroundKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F10) {
            new Tools().debug("Presionaste la tecla F10");
        }
    }//GEN-LAST:event_backgroundKeyPressed

    private void btnSearchProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchProductActionPerformed
        if (idCliente <= 0) {
            JOptionPane.showMessageDialog(null, "Debes asignar un cliente antes de continuar con la facturación.");
            return;
        }
        SearchProductDialog dialog = new SearchProductDialog(principal, this);
        dialog.showMessage("CAJA");
    }//GEN-LAST:event_btnSearchProductActionPerformed

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F10) {
            new Tools().debug("Presionaste la tecla F10");
        }
    }//GEN-LAST:event_jPanel1KeyPressed

    private void txtSearchByCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchByCodeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (idCliente <= 0) {
                JOptionPane.showMessageDialog(null, "Debes asignar un cliente antes de continuar con la facturación.");
                return;
            }
            addItemToCart();
        }
    }//GEN-LAST:event_txtSearchByCodeKeyPressed

    private void txtSearchByCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchByCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchByCodeActionPerformed

    private void btnAsignClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignClientActionPerformed
        asignClientFactura dialog = new asignClientFactura(principal, this);
        dialog.showMessage();
    }//GEN-LAST:event_btnAsignClientActionPerformed

    private void limpiarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarListaActionPerformed
        clear();
    }//GEN-LAST:event_limpiarListaActionPerformed

    public void clear() {
        labelTotal.setText("Bs. 0.00");
        labelTotalUSD.setText("$0.00");
        label_Iva.setText("Bs. 0.00");
        labelSubTotal.setText("Bs. 0.00");
        
        txtCedula.setText("CÉDULA o RIF: ");
        txtNombre.setText("NOMBRE Y APELLIDO: ");
        txtTelefono.setText("TELÉFONO: ");
        idCliente = -1;
                
        listaProductos.clear();
        listaTablaProductos();
        this.CalcularTotalPagar();
    }
    
    private void checkDolarManualStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkDolarManualStateChanged
        if (checkDolarManual.isSelected()) {
            textDolar.setEditable(true);
        } else {
            textDolar.setEditable(false);
        }
    }//GEN-LAST:event_checkDolarManualStateChanged

    private void btnUpdateDolarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateDolarActionPerformed
        try {
            principal.obtenerPrecioDolarBCV();
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_btnUpdateDolarActionPerformed

    private void textDolarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textDolarKeyTyped
        char c=evt.getKeyChar();
        if((Character.isDigit(c))||(c==KeyEvent.VK_PERIOD)||(c==KeyEvent.VK_DECIMAL)||(c==KeyEvent.VK_BACK_SPACE)){
            if(c==KeyEvent.VK_PERIOD || c==KeyEvent.VK_DECIMAL) { 
                String s=textDolar.getText();
                int dot=s.indexOf('.');
                if(dot!=-1){
                    getToolkit().beep();
                    evt.consume();
                }
            }
        } else {
            getToolkit().beep();
            evt.consume();
        }
        
        if (textDolar.getText().isEmpty()) {
            textDolar.setText("0.00");
        }        
    }//GEN-LAST:event_textDolarKeyTyped

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        if (listaProductos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "¡No hay artículos en la lista para cobrar!\n      Debes agregar articulos primero.");
            return;
        }
        
        if (idCliente <= 0) {
            JOptionPane.showMessageDialog(null, "Debes asignar un cliente antes de continuar a la sección de cobrar.");
            return;
        }
        
        CobrarDialog dialog = new CobrarDialog(principal, this);
        dialog.showMessage("CAJA");
    }//GEN-LAST:event_btnCobrarActionPerformed
    
    private void inicializarTablaProducto() {
		table = new JTable();
		table.setDefaultRenderer(Object.class, new ButtonRenderer());
		
		modeloDatosProductos = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			
			boolean[] canEdit = new boolean [] {
					false, false, false, false, false, false, false, false, true
			};
			
                        @Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
            }						
		};
		//a�adir columnas
		modeloDatosProductos.addColumn("Nº Item");
		modeloDatosProductos.addColumn("Código");
		modeloDatosProductos.addColumn("Descripción");
		modeloDatosProductos.addColumn("Cantidad");
		modeloDatosProductos.addColumn("P. Unitario");
		modeloDatosProductos.addColumn("SubTotal");
		modeloDatosProductos.addColumn("Iva");
		modeloDatosProductos.addColumn("Total Pagar");
		modeloDatosProductos.addColumn(" ");
		
		table.setRowHeight(40);
				
		JTableHeader jtableHeader = table.getTableHeader();
	    jtableHeader.setDefaultRenderer(new GestionEncabezadoTabla());
	    table.setTableHeader(jtableHeader);
	    
		table.setModel(modeloDatosProductos);
		TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
            	EditItemVentaDialog obj = new EditItemVentaDialog(principal);
            	DetalleVenta detail = listaProductos.get(row);
            	obj.showMessage("Editar Cantidad", ""+detail.getCantidad());
            	if (obj.getMessageType() == EditItemVentaDialog.MessageType.OK) {
            		Integer cantidad = obj.getNewCantidad();
            		if (cantidad >= 1) {
            			subtotal = precioVenta * cantidad;
                    	iva = calcPercent(subtotal, Double.valueOf(porcentajeIva));
                    	totalPagarUsd = subtotal + iva;
                    	
                    	totalPagarBs = totalPagarUsd * Double.valueOf(textDolar.getText().trim());
                    	subtotal = subtotal * Double.valueOf(textDolar.getText().trim());
                    	iva = iva * Double.valueOf(textDolar.getText().trim());
                    	
                        subtotal = (double) Math.round(subtotal * 100) / 100;
                        iva = (double) Math.round(iva * 100) / 100;
                        totalPagarUsd = (double) Math.round(totalPagarUsd * 100) / 100;
                        totalPagarBs = (double) Math.round(totalPagarBs * 100) / 100;
                		
                		detail.setCantidad(obj.getNewCantidad());
                		detail.setSubTotal(subtotal);
                		detail.setIva(iva);
                		detail.setTotalPagarUsd(totalPagarUsd);
                		detail.setTotalPagarBs(totalPagarBs);
                		
                		CalcularTotalPagar();
    	                listaTablaProductos();
            		}            		
            	}
            }
            @Override
            public void onDelete(int row) {
            	DeleteItemVentaDialog obj = new DeleteItemVentaDialog(principal);
            	DetalleVenta detail = listaProductos.get(row);
            	DecimalFormat formato = new DecimalFormat("###,###,##0.00");
                
            	obj.showMessage("¿Eliminar Producto?", 
            			"Código: " + detail.getCodigo() + "\nNombre: " + detail.getNombre() + "\n" +
            			"Cantidad: " + detail.getCantidad() + " - Precio: $" + formato.format(detail.getTotalPagarUsd()));
            	if (obj.getMessageType() == DeleteItemVentaDialog.MessageType.OK) {
            		listaProductos.remove(row);
	            	CalcularTotalPagar();
	                listaTablaProductos();
            	}
            }
        };
        table.getColumnModel().getColumn(8).setCellRenderer(new TableActionCellRenderer());
        table.getColumnModel().getColumn(8).setCellEditor(new TableActionCellEditor(event));
        for (int i = 0; i<table.getColumnCount()-1; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;
                @Override
                public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                    setHorizontalAlignment(SwingConstants.CENTER);
                    return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
                }
            });
        }
        
        scrollPane.setViewportView(table);
    }
    
    public void addItemToCart() {
		Ctrl_Producto ctrlProducto = new Ctrl_Producto();
		String str = txtSearchByCode.getText().trim();
		
		if (!new Tools().isDouble(textDolar.getText().trim())) {
			JOptionPane.showMessageDialog(null, "Por favor, actualiza el precio del dolar!");
			return;
		}
		if (Double.valueOf(textDolar.getText().trim()) <= 0.0) {
			JOptionPane.showMessageDialog(null, "Por favor, actualiza el precio del dolar!");
			return;
		}
		
		if (str.startsWith("*") && str.contains(" ")) {
			Integer conteo = 0;
			for (int i1 = 0; i1 < str.length(); i1++) {
				if (str.charAt(i1) == ' ') {
					conteo = i1;
				}
			}
                        			
			Integer cantidad = Integer.valueOf(str.substring(1, conteo));
			String codeStr = str.substring(conteo+1, str.length());
			
			if (ctrlProducto.existProductoByCode(codeStr)) {
				Integer idPro = ctrlProducto.getIdProductoByCode(codeStr);
				agregarProducto(idPro, cantidad);
				txtSearchByCode.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "No existe un producto con ese código!");
				txtSearchByCode.setText("");
			}
			return;
		}
		
		if (ctrlProducto.existProductoByCode(str)) {
			Integer idPro = ctrlProducto.getIdProductoByCode(str);
			//agregarProducto(idPro, 1);
                        openDialogAddProducto(idPro);
			txtSearchByCode.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "No existe un producto con ese código!");
			txtSearchByCode.setText("");
		}
    }
    
    public void agregarProducto (Integer productoID) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                openDialogAddProducto(productoID);
            }
        }, 500);
    }
    
    public void openDialogAddProducto(Integer productoID){
        AddProductDialog dialog = new AddProductDialog(principal, this);
        dialog.showMessage("Especifica la cantidad que está agregando a la factura.");
        
        if (dialog.getMessageType() == AddProductDialog.MessageType.OK) {
            String value = dialog.txt.getText().trim();
            Boolean isDouble = false;
            if (value.contains(".")) {
                isDouble = true;
            }
            if (isDouble) {
                Double qty = Double.valueOf(value);
                if (qty <= 0.00) {
                    return;
                }
                agregarProducto(productoID, qty);
            } else {
                Integer qty = Integer.valueOf(value);
                if (qty <= 0) {
                    return;
                }
                agregarProducto(productoID, qty);
            }
        }
    }
    
    public void agregarProducto (Integer productoID, Double qty) {
		cantidadDouble = qty;
        this.DatosDelProducto(productoID);
        
        if (cantidadProductoBBDD == 0) {
        	subtotal = precioVenta * cantidadDouble;
        	iva = calcPercent(subtotal, Double.valueOf(porcentajeIva));
        	totalPagarUsd = subtotal + iva;
        	
        	totalPagarBs = totalPagarUsd * Double.valueOf(textDolar.getText().trim());

        	subtotal = subtotal * Double.valueOf(textDolar.getText().trim());
        	iva = iva * Double.valueOf(textDolar.getText().trim());
        	        	
            subtotal = (double) Math.round(subtotal * 100) / 100;
            iva = (double) Math.round(iva * 100) / 100;
            totalPagarUsd = (double) Math.round(totalPagarUsd * 100) / 100;
            totalPagarBs = (double) Math.round(totalPagarBs * 100) / 100;

            producto = new DetalleVenta(auxIdDetalle,
                    1,
                    idProducto,
                    codigo,
                    nombre,
                    cantidadDouble,
                    precioVenta,
                    subtotal,
                    iva,
                    totalPagarUsd,
                    totalPagarBs
            );
            
            listaProductos.add(producto);
            auxIdDetalle++;            
            this.CalcularTotalPagar();
        } else {
        	if (cantidadDouble <= cantidadProductoBBDD) {
            	subtotal = precioVenta * cantidadDouble;
            	iva = calcPercent(subtotal, Double.valueOf(porcentajeIva));
            	totalPagarUsd = subtotal + iva;
            	
            	totalPagarBs = totalPagarUsd * Double.valueOf(textDolar.getText().trim());
            	subtotal = subtotal * Double.valueOf(textDolar.getText().trim());
            	iva = iva * Double.valueOf(textDolar.getText().trim());
            	
                subtotal = (double) Math.round(subtotal * 100) / 100;
                iva = (double) Math.round(iva * 100) / 100;
                totalPagarUsd = (double) Math.round(totalPagarUsd * 100) / 100;
                totalPagarBs = (double) Math.round(totalPagarBs * 100) / 100;

                producto = new DetalleVenta(auxIdDetalle,
                        1,
                        idProducto,
                        codigo,
                        nombre,
                        cantidadDouble,
                        precioVenta,
                        subtotal,
                        iva,
                        totalPagarUsd,
                        totalPagarBs
                );
               
                listaProductos.add(producto);
                auxIdDetalle++;
                this.CalcularTotalPagar();
            }
        }
        
        //llamar al metodo
        this.listaTablaProductos();
    }
    
    public void agregarProducto (Integer productoID, Integer qty) {
		cantidadInt = qty;
        this.DatosDelProducto(productoID);
        
        if (cantidadProductoBBDD == 0) {
        	subtotal = precioVenta * cantidadInt;
        	iva = calcPercent(subtotal, Double.valueOf(porcentajeIva));
        	totalPagarUsd = subtotal + iva;
        	
        	totalPagarBs = totalPagarUsd * Double.valueOf(textDolar.getText().trim());

        	subtotal = subtotal * Double.valueOf(textDolar.getText().trim());
        	iva = iva * Double.valueOf(textDolar.getText().trim());
        	        	
            subtotal = (double) Math.round(subtotal * 100) / 100;
            iva = (double) Math.round(iva * 100) / 100;
            totalPagarUsd = (double) Math.round(totalPagarUsd * 100) / 100;
            totalPagarBs = (double) Math.round(totalPagarBs * 100) / 100;

            producto = new DetalleVenta(auxIdDetalle,
                    1,
                    idProducto,
                    codigo,
                    nombre,
                    Double.valueOf(cantidadInt),
                    precioVenta,
                    subtotal,
                    iva,
                    totalPagarUsd,
                    totalPagarBs
            );
            
            listaProductos.add(producto);
            auxIdDetalle++;            
            this.CalcularTotalPagar();
        } else {
        	if (cantidadInt <= cantidadProductoBBDD) {
            	subtotal = precioVenta * cantidadInt;
            	iva = calcPercent(subtotal, Double.valueOf(porcentajeIva));
            	totalPagarUsd = subtotal + iva;
            	
            	totalPagarBs = totalPagarUsd * Double.valueOf(textDolar.getText().trim());
            	subtotal = subtotal * Double.valueOf(textDolar.getText().trim());
            	iva = iva * Double.valueOf(textDolar.getText().trim());
            	
                subtotal = (double) Math.round(subtotal * 100) / 100;
                iva = (double) Math.round(iva * 100) / 100;
                totalPagarUsd = (double) Math.round(totalPagarUsd * 100) / 100;
                totalPagarBs = (double) Math.round(totalPagarBs * 100) / 100;

                producto = new DetalleVenta(auxIdDetalle,
                        1,
                        idProducto,
                        codigo,
                        nombre,
                        Double.valueOf(cantidadInt),
                        precioVenta,
                        subtotal,
                        iva,
                        totalPagarUsd,
                        totalPagarBs
                );
               
                listaProductos.add(producto);
                auxIdDetalle++;
                this.CalcularTotalPagar();
            }
        }
        
        //llamar al metodo
        this.listaTablaProductos();
    }
    
    
    public void listaTablaProductos() {
	DecimalFormat formato = new DecimalFormat("###,###,##0.00");
		
        this.modeloDatosProductos.setRowCount(listaProductos.size());
        for (int i = 0; i < listaProductos.size(); i++) {
            this.modeloDatosProductos.setValueAt(i + 1, i, 0);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getCodigo(), i, 1);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getNombre(), i, 2);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getCantidad(), i, 3);
            this.modeloDatosProductos.setValueAt("Bs. " + formato.format(listaProductos.get(i).getPrecioVenta() * Double.valueOf(textDolar.getText().trim())), i, 4);
            this.modeloDatosProductos.setValueAt("Bs. " + formato.format(listaProductos.get(i).getSubTotal()), i, 5);
            this.modeloDatosProductos.setValueAt("Bs. " + formato.format(listaProductos.get(i).getIva()), i, 6);
            this.modeloDatosProductos.setValueAt("Bs. " + formato.format(listaProductos.get(i).getTotalPagarBs()), i, 7);
        }        
        table.setModel(modeloDatosProductos);
       
        table.getColumnModel().getColumn(0).setPreferredWidth(2);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(2).setPreferredWidth(420);
        table.getColumnModel().getColumn(3).setPreferredWidth(10);
        table.getColumnModel().getColumn(4).setPreferredWidth(60);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
        table.getColumnModel().getColumn(6).setPreferredWidth(50);
        table.getColumnModel().getColumn(7).setPreferredWidth(50);
        table.getColumnModel().getColumn(8).setPreferredWidth(20);
        
        contadorProductos.setText(""+listaProductos.size());
    }
    //idCliente = idCliente;

    private void DatosDelProducto(Integer id) {
        try {
            String sql = "select * from " + SQL.PRODUCTOS_TABLE + " where " + SQL.PRODUCTOS_IDPRODUCT + " = '" + id + "'";
            Connection cn = SQL.conectar();
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            	codigo = rs.getString(SQL.PRODUCTOS_CODIGO);
                nombre = rs.getString(SQL.PRODUCTOS_DESC);
                cantidadProductoBBDD = rs.getInt(SQL.PRODUCTOS_STOCK_ACTUAL);
                precioVenta = rs.getDouble(SQL.PRODUCTOS_VENTA);
                exento = rs.getBoolean(SQL.PRODUCTOS_EXENTO);
                porcentajeIva = (exento == true ? 0 : 16);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener datos del producto, " + e);
        }
    }

    public Double calcPercent (Double value, Double percent) {
		double impuestos = -(100-percent);
    	double calculo = (value * (100 + (impuestos)) / 100);
    	return calculo;
	}
    
    /*
    Metodo para calcular el total a pagar de todos los productos agregados
     */
    public void CalcularTotalPagar() {
        subtotalGeneral = 0;
        ivaGeneral = 0;
        totalPagarGeneralUsd = 0;
        totalPagarGeneralBs = 0;
        
        for (DetalleVenta elemento : listaProductos) {
            subtotalGeneral += elemento.getSubTotal();
            ivaGeneral += elemento.getIva();
            totalPagarGeneralUsd += elemento.getTotalPagarUsd();
            totalPagarGeneralBs += elemento.getTotalPagarBs();
        } 
        //redondear decimales
        DecimalFormat formato = new DecimalFormat("###,###,##0.00");
        
        subtotalGeneral = (double) Math.round(subtotalGeneral * 100) / 100;
        ivaGeneral = (double) Math.round(ivaGeneral * 100) / 100;
        totalPagarGeneralUsd = (double) Math.round(totalPagarGeneralUsd * 100) / 100;
        totalPagarGeneralBs = (double) Math.round(totalPagarGeneralBs * 100) / 100;

        //enviar datos a la vista
        labelSubTotal.setText("Bs. "+formato.format(subtotalGeneral));
        label_Iva.setText("Bs. "+formato.format(ivaGeneral));
        labelTotalUSD.setText("$"+formato.format(totalPagarGeneralUsd));
        labelTotal.setText("Bs. "+formato.format(totalPagarGeneralBs));
    }
    
    
    
    public void updateCliente(String str) {
        try {
            String sql = "select * from " + SQL.CLIENTES_TABLE + " where " + SQL.CLIENTES_CEDULA + " = '" + str + "'";
            Connection cn = SQL.conectar();
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            	String cedula = rs.getString(SQL.CLIENTES_CEDULA);
            	String nombre1 = rs.getString(SQL.CLIENTES_NOMBRE);
            	String telefono = rs.getString(SQL.CLIENTES_TLF);
            	
            	
            	String formatTelefono = "";
            	String suffix = telefono.substring(0, 4);
            	String number = telefono.substring(4, telefono.length());
            	formatTelefono = "(" + suffix + ") " + number;
            	
            	txtCedula.setText("CÉDULA o RIF: " + cedula);
            	txtNombre.setText("NOMBRE Y APELLIDO: "+nombre1);
            	txtTelefono.setText("TELÉFONO: "+formatTelefono);
                
                idCliente = rs.getInt(SQL.CLIENTES_IDCLIENT);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener id del cliente, " + e);
        }
    }
    
    
    
    private DefaultTableModel modeloDatosProductos;
    //lista para el detalle de venta de los productos
    public ArrayList<DetalleVenta> listaProductos = new ArrayList<>();
    public int idCliente = -1;//id del cliente sleccionado
    
    private DetalleVenta producto;
    private int idProducto = 0;
    private String codigo = "";
    private String nombre = "";
    //private String tipoVenta = "";
    private int cantidadProductoBBDD = 0;
    private double precioVenta = 0.0;
    private boolean exento = true;
    private int porcentajeIva = 0;

    private int cantidadInt = 0;//cantidad de productos a comprar
    private double cantidadDouble = 0;//cantidad de productos a comprar
    private double subtotal = 0.0;//cantidad por precio
    private double iva = 0.0;
    private double totalPagarUsd = 0.0;
    private double totalPagarBs = 0.0;

    //variables para calculos globales
    private double subtotalGeneral = 0.0;
    private double ivaGeneral = 0.0;
    private double totalPagarGeneralUsd = 0.0;
    private double totalPagarGeneralBs = 0.0;
    //fin de variables de calculos globales

    public int auxIdDetalle = 1;//id del detalle de venta

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private club.j3studios.system.utils.Button btnAsignClient;
    private club.j3studios.system.utils.Button btnCobrar;
    private club.j3studios.system.utils.Button btnSearchProduct;
    private club.j3studios.system.utils.Button btnUpdateDolar;
    private club.j3studios.system.utils.JCheckBoxCustom checkDolarManual;
    private javax.swing.JLabel contadorProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelContador;
    private javax.swing.JLabel jLabelContador1;
    private javax.swing.JLabel jLabelContador2;
    private javax.swing.JLabel jLabelContador3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelR;
    private javax.swing.JLabel labelRango;
    public javax.swing.JLabel labelSubTotal;
    public javax.swing.JLabel labelTotal;
    public javax.swing.JLabel labelTotalUSD;
    private javax.swing.JLabel labelU;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JLabel labelVersion;
    public javax.swing.JLabel label_Iva;
    private club.j3studios.system.utils.Button limpiarLista;
    public javax.swing.JScrollPane scrollPane;
    public javax.swing.JTable table;
    public club.j3studios.system.utils.FormattedTextField textDolar;
    public javax.swing.JLabel txtCedula;
    public javax.swing.JLabel txtNombre;
    private club.j3studios.system.utils.TextField txtSearchByCode;
    public javax.swing.JLabel txtTelefono;
    public javax.swing.JLabel updateDolarValue;
    // End of variables declaration//GEN-END:variables
}
