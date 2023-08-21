/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package club.j3studios.system.b_windows.dialogs;

import club.j3studios.system.b_windows.subwins.Facturar;
import club.j3studios.system.control.Ctrl_Cliente;
import club.j3studios.system.control.Ctrl_Producto;
import club.j3studios.system.database.SQL;
import club.j3studios.system.model.Cliente;
import club.j3studios.system.utils.SearchOption;
import club.j3studios.system.utils.jtable.GestionEncabezadoTabla;
import club.j3studios.system.utils.visuals.Glass;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author josne
 */
public class asignClientFactura extends javax.swing.JDialog {

    private final Facturar principal;
    private final JFrame fram;
    private Animator animator;
    private Glass glass;
    private boolean show;
    private MessageType messageType = MessageType.CANCEL;
    
    public asignClientFactura(JFrame fram, Facturar principal) {
        super(fram, true);
        this.fram = fram;
        this.principal = principal;
        initComponents();
        init();
        
        txtBuscador.addEventOptionSelected((SearchOption option, int index) -> {
            txtBuscador.setHint("Buscar por el " + option.getName() + "...");
        });
        txtBuscador.addOption(new SearchOption("Cédula", new ImageIcon(getClass().getResource("/images/etiqueta.png"))));
	txtBuscador.addOption(new SearchOption("Nombre", new ImageIcon(getClass().getResource("/images/escaner.png")))); 
	txtBuscador.setSelectedIndex(0);
        
        JTableHeader jtableHeader = table.getTableHeader();
        jtableHeader.setDefaultRenderer(new GestionEncabezadoTabla());
        table.setTableHeader(jtableHeader);
	    
        table.setModel(new DefaultTableModel(new Object [][] {}, new String [] {
            "Cédula", 
            "Nombre y Apellido", 
            "Género", 
            "Teléfono", 
            "Dirección", 
            "Correo Electrónico" }){
                private static final long serialVersionUID = 1L;
                boolean[] canEdit = new boolean [] {false, false, false, false, false, false};
	           
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
        
        for (int i = 0; i<table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;
                @Override
                public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                    setHorizontalAlignment(SwingConstants.CENTER);
                    return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
                }
            });
        }
        
        table.setRowHeight(30);
        
        loadData("");
    }
    
    private void init() {
        setBackground(new Color(0, 0, 0, 0));
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeMessage();
            }
        });
        animator = new Animator(350, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                float f = show ? fraction : 1f - fraction;
                glass.setAlpha(f - f * 0.3f);
                setOpacity(f);
            }

            @Override
            public void end() {
                if (show == false) {
                    dispose();
                    glass.setVisible(false);
                }
            }
        });
        animator.setResolution(0);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
        setOpacity(0f);
        glass = new Glass();
    }

    private void startAnimator(boolean show) {
        if (animator.isRunning()) {
            float f = animator.getTimingFraction();
            animator.stop();
            animator.setStartFraction(1f - f);
        } else {
            animator.setStartFraction(0f);
        }
        this.show = show;
        animator.start();
    }
    
    public void showMessage() {
        fram.setGlassPane(glass);
        glass.setVisible(true);
        setLocationRelativeTo(fram);
        startAnimator(true);
        setVisible(true);
    }
    
    public void closeMessage() {
        startAnimator(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new club.j3studios.system.utils.visuals.Background();
        jPanel1 = new javax.swing.JPanel();
        txtBuscador = new club.j3studios.system.utils.TextFieldSearchOption();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnNewClient = new club.j3studios.system.utils.visuals.ButtonCustom();
        jLabel1 = new javax.swing.JLabel();
        btnClose = new club.j3studios.system.utils.visuals.ButtonCustom();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        background1.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));

        txtBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscadorKeyReleased(evt);
            }
        });

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
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        btnNewClient.setBackground(new java.awt.Color(51, 153, 255));
        btnNewClient.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnNewClient.setText("CLIENTE NUEVO");
        btnNewClient.setColorHover(new java.awt.Color(51, 153, 255));
        btnNewClient.setColorPressed(new java.awt.Color(153, 255, 255));
        btnNewClient.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNewClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewClientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnNewClient, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btnNewClient, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deletingIcon.png"))); // NOI18N

        btnClose.setBackground(new java.awt.Color(255, 0, 0));
        btnClose.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnClose.setText("CERRAR");
        btnClose.setColorHover(new java.awt.Color(255, 51, 51));
        btnClose.setColorPressed(new java.awt.Color(153, 0, 0));
        btnClose.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(271, 271, 271)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscadorKeyReleased
        if (txtBuscador.isSelected()) {
            int option = txtBuscador.getSelectedIndex();
            String text = "%"+txtBuscador.getText().trim()+"%";
            if (option==0) {
            	loadData("where " + SQL.CLIENTES_CEDULA + " like ? ", text);
            } else {
            	loadData("where " + SQL.CLIENTES_NOMBRE + " like ? ", text);
            }
        }
    }//GEN-LAST:event_txtBuscadorKeyReleased

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (table.getSelectedRow() != -1) {
                String cedula = table.getValueAt(table.getSelectedRow(), 0).toString();
                principal.updateCliente(cedula);
                closeMessage();                
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un reglón primero!");
            }
        }
    }//GEN-LAST:event_tableKeyPressed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        messageType = MessageType.CANCEL;
        closeMessage();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnNewClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewClientActionPerformed
        closeMessage();
        
        newClientDialogOnAsign dialog = new newClientDialogOnAsign(fram, principal);
        dialog.showMessage();
        if (dialog.getMessageType() == newClientDialogOnAsign.MessageType.OK) {
            saveClient(dialog);
            closeMessage();
            /*String cedula = dialog.txtCedula.getText().trim();
            principal.updateCliente(cedula);*/
        }
        if (dialog.getMessageType() == newClientDialogOnAsign.MessageType.CANCEL) {
            asignClientFactura client = this;
            showMessage();
        }
    }//GEN-LAST:event_btnNewClientActionPerformed
    //
    
    private void loadData (String where, Object... search) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
			
            Connection con = SQL.conectar();
            String sql = "select * from " + SQL.CLIENTES_TABLE + " " + where;
            
            PreparedStatement ps = con.prepareStatement(sql);
            for (int i = 0; i < search.length; i++) {
                ps.setObject(i+1, search[i]);
            }
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String cedula = rs.getString(SQL.CLIENTES_CEDULA);
                String nombre = rs.getString(SQL.CLIENTES_NOMBRE);
                String genero = rs.getString(SQL.CLIENTES_GENERO);
                String telefono = rs.getString(SQL.CLIENTES_TLF);
                String direccion = rs.getString(SQL.CLIENTES_DIRECCION);
                String correo = rs.getString(SQL.CLIENTES_CORREO);
                	
                model.addRow(new Object[] {
                    cedula, 
                    nombre, 
                    genero,
                    telefono,
                    direccion, 
                    correo});
			                
                table.getColumnModel().getColumn(0).setPreferredWidth(100);
                table.getColumnModel().getColumn(1).setPreferredWidth(100);
                table.getColumnModel().getColumn(2).setPreferredWidth(100);
                table.getColumnModel().getColumn(3).setPreferredWidth(100);
                table.getColumnModel().getColumn(4).setPreferredWidth(100);
                table.getColumnModel().getColumn(5).setPreferredWidth(100);
            }
	        
            con.close();
        } catch (SQLException e) {
            System.out.print("Error al llenar la tabla de productos.");
            System.out.print(e);
        }
    }
       
    private void saveClient(newClientDialogOnAsign dialog) {
        
        Cliente cliente = new Cliente();
        Ctrl_Cliente controlCliente = new Ctrl_Cliente();
        
        Date date = new Date();
        String fechaActual = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(date);
                
        if (!dialog.txtCedula.getText().isEmpty() && !dialog.txtNombre.getText().isEmpty() && 
                !dialog.txtTlf.getText().isEmpty()) {
            
            if (!controlCliente.existeCliente(dialog.txtCedula.getText().trim())) {
                cliente.setCedula(dialog.txtCedula.getText().trim());
                cliente.setNombre(dialog.txtNombre.getText().trim());
                cliente.setGenero(dialog.boxGenero.getSelectedItem().toString());
                cliente.setTelefono(dialog.txtTlf.getText().trim());
                cliente.setDireccion(dialog.txtDirection.getText().trim());
                cliente.setCorreo(dialog.txtCorreo.getText().trim());
                cliente.setCompras(0);
                cliente.setValorTotal(0.0);
                cliente.setFechaRegistro(fechaActual);
                cliente.setUltimaCompra("Sin Datos");
                if (controlCliente.guardar(cliente)) {
                    principal.updateCliente(cliente.getCedula());
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar el nuevo cliente.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El cliente ya esta registrado en la Base de Datos.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Completa todos los campos");
        }
        loadData("");
    }
    
    public static enum MessageType {
        CANCEL, OK;
    }
    
    public MessageType getMessageType() {
        return messageType;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private club.j3studios.system.utils.visuals.Background background1;
    private club.j3studios.system.utils.visuals.ButtonCustom btnClose;
    private club.j3studios.system.utils.visuals.ButtonCustom btnNewClient;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable table;
    public club.j3studios.system.utils.TextFieldSearchOption txtBuscador;
    // End of variables declaration//GEN-END:variables
}
