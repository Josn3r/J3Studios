/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package club.j3studios.system.b_windows.dialogs;

import club.j3studios.system.b_windows.subwins.Facturar;
import club.j3studios.system.control.Ctrl_Producto;
import club.j3studios.system.database.SQL;
import club.j3studios.system.utils.SearchOption;
import club.j3studios.system.utils.Tools;
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
import java.sql.Statement;
import java.text.DecimalFormat;
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
public class SearchProductDialog extends javax.swing.JDialog {

    private final Facturar principal;
    private final JFrame fram;
    private Animator animator;
    private Glass glass;
    private boolean show;
    private MessageType messageType = MessageType.CANCEL;
    
    public SearchProductDialog(JFrame fram, Facturar principal) {
        super(fram, true);
        this.fram = fram;
        this.principal = principal;
        initComponents();
        init();
        
        txtBuscador.addEventOptionSelected((SearchOption option, int index) -> {
            txtBuscador.setHint("Buscar por el " + option.getName() + "...");
        });
        txtBuscador.addOption(new SearchOption("Descripción", new ImageIcon(getClass().getResource("/images/etiqueta.png"))));
	txtBuscador.addOption(new SearchOption("Código", new ImageIcon(getClass().getResource("/images/escaner.png")))); 
	txtBuscador.setSelectedIndex(0);
        
        JTableHeader jtableHeader = table.getTableHeader();
        jtableHeader.setDefaultRenderer(new GestionEncabezadoTabla());
        table.setTableHeader(jtableHeader);
	    
        table.setModel(new DefaultTableModel(new Object [][] {}, new String [] {
            "Código", 
            "Descripción", 
            "Categoría", 
            "Marca", 
            "Stock", 
            "Unidad Medida", 
            "Precio", 
            "IVA", 
            "Total"
            }){
                private static final long serialVersionUID = 1L;
                boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false, false, false};
	           
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
        table.setRowHeight(30);
        
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
    
    public void showMessage(String title) {
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
        buttonCustom1 = new club.j3studios.system.utils.visuals.ButtonCustom();
        jLabel1 = new javax.swing.JLabel();

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

        buttonCustom1.setBackground(new java.awt.Color(255, 0, 0));
        buttonCustom1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonCustom1.setText("CANCELAR");
        buttonCustom1.setColorHover(new java.awt.Color(255, 51, 51));
        buttonCustom1.setColorPressed(new java.awt.Color(153, 0, 0));
        buttonCustom1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonCustom1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCustom1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(buttonCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deletingIcon.png"))); // NOI18N

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            	loadData("where " + SQL.PRODUCTOS_DESC + " like ? ", text);
            } else {
            	loadData("where " + SQL.PRODUCTOS_CODIGO + " like ? ", text);
            }
        }
    }//GEN-LAST:event_txtBuscadorKeyReleased

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (table.getSelectedRow() != -1) {
                Ctrl_Producto ctrlProducto = new Ctrl_Producto();
                String codigo = (String)table.getModel().getValueAt(table.getSelectedRow(), 0);
                
                if (ctrlProducto.existProductoByCode(codigo)) {
                    Integer idPro = ctrlProducto.getIdProductoByCode(codigo);
                    principal.agregarProducto(idPro);
                    messageType = MessageType.OK;
                    closeMessage();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un reglón primero!");
            }
        }
    }//GEN-LAST:event_tableKeyPressed

    private void buttonCustom1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCustom1ActionPerformed
        messageType = MessageType.CANCEL;
        closeMessage();
    }//GEN-LAST:event_buttonCustom1ActionPerformed
    //
    
    private void loadData (String where, Object... search) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
			
            Connection con = SQL.conectar();
            String sql = "select * from " + SQL.PRODUCTOS_TABLE + " " + where;
            
            PreparedStatement ps = con.prepareStatement(sql);
            for (int i = 0; i < search.length; i++) {
                ps.setObject(i+1, search[i]);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString(SQL.PRODUCTOS_CODIGO);
                String nombre = rs.getString(SQL.PRODUCTOS_DESC);
                Integer categoria = rs.getInt(SQL.PRODUCTOS_IDCATEGORIA);
                Integer marca = rs.getInt(SQL.PRODUCTOS_IDMARCA);
                Integer cantidad = rs.getInt(SQL.PRODUCTOS_STOCK_ACTUAL);
                String tipoVenta = rs.getString(SQL.PRODUCTOS_UNIDADMEDIDA);
                Double precioVenta = rs.getDouble(SQL.PRODUCTOS_VENTA);
                Boolean exento = rs.getBoolean(SQL.PRODUCTOS_EXENTO);
                Double porcentajeIva = 16.0;
				
                Double calcIva = 0.0;
                if (!exento) {
                    calcIva = new Tools().calcPercent(precioVenta, porcentajeIva);
                }
                
                Double precioTotal = (precioVenta + calcIva);
                
                DecimalFormat formato = new DecimalFormat("###,###,##0.00");
                String precioVentaForm = formato.format(precioVenta);
                String ivaForm = (exento ? "Exento" : "$"+formato.format(calcIva));
                String totalForm = formato.format(precioTotal);
                
                model.addRow(new Object[] {
                    codigo, 
                    nombre, 
                    getCategoria(categoria),
                    "Ninguna",
                    (cantidad <= 0 ? "Ilimitado" : cantidad), 
                    tipoVenta, 
                    "$" + precioVentaForm, 
                    ivaForm, 
                    "$" + totalForm});
			                
                table.getColumnModel().getColumn(0).setPreferredWidth(40);
                table.getColumnModel().getColumn(1).setPreferredWidth(240);
                table.getColumnModel().getColumn(2).setPreferredWidth(100);
                table.getColumnModel().getColumn(3).setPreferredWidth(100);
                table.getColumnModel().getColumn(4).setPreferredWidth(80);
                table.getColumnModel().getColumn(5).setPreferredWidth(100);
                table.getColumnModel().getColumn(6).setPreferredWidth(70);
                table.getColumnModel().getColumn(7).setPreferredWidth(70);
                table.getColumnModel().getColumn(8).setPreferredWidth(70);
            }
	        
            con.close();
        } catch (SQLException e) {
            System.out.print("Error al llenar la tabla de productos.");
            System.out.print(e);
        }
    }
    
    private String getCategoria(int idCategoria) {
        String sql = "select " + SQL.CATEGORIA_DESC + " from " + SQL.CATEGORIA_TABLE + " where " + SQL.CATEGORIA_ID +" = '" + idCategoria + "'";
        Statement st;
        try {
            Connection cn = SQL.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                return rs.getString(SQL.CATEGORIA_DESC);
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener el id de la categoria!");
        }
        return "";
    }
    
    public static enum MessageType {
        CANCEL, OK;
    }
    
    public MessageType getMessageType() {
        return messageType;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private club.j3studios.system.utils.visuals.Background background1;
    private club.j3studios.system.utils.visuals.ButtonCustom buttonCustom1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable table;
    public club.j3studios.system.utils.TextFieldSearchOption txtBuscador;
    // End of variables declaration//GEN-END:variables
}
