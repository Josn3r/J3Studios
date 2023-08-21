package club.j3studios.system.b_windows.dialogs;

import club.j3studios.system.b_windows.subwins.Clientes;
import club.j3studios.system.b_windows.subwins.Productos;
import club.j3studios.system.database.SQL;
import club.j3studios.system.utils.Tools;
import club.j3studios.system.utils.visuals.Glass;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

/**
 *
 * @author josne
 */
public class newProduct extends javax.swing.JDialog {

    private final Productos principal;
    private final JFrame fram;
    private Animator animator;
    private Glass glass;
    private boolean show;
    private MessageType messageType = MessageType.CANCEL;
    
    public newProduct(JFrame fram, Productos principal) {
        super(fram, true);
        this.fram = fram;
        this.principal = principal;
        initComponents();
        this.loadAllCategories();
        init();
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
    
    //
    //
    //
    
    
    @SuppressWarnings("unchecked")
    public void loadAllCategories() {
        Connection con = SQL.conectar();
        String sql = "select * from tb_categorias";
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                boxCategoria.addItem(rs.getString("descripcion"));
            }            
            con.close();
        } catch (SQLException e) {
            System.out.print("[J3Studios] » Error al cargar las categorias.");
        }
    }
    
    
    //
    //
    //
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new club.j3studios.system.utils.visuals.Background();
        cmdConfirm = new club.j3studios.system.utils.visuals.ButtonCustom();
        cmdCancel = new club.j3studios.system.utils.visuals.ButtonCustom();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDesc = new club.j3studios.system.utils.TextField();
        txtCode = new club.j3studios.system.utils.TextField();
        boxCategoria = new club.j3studios.system.utils.ComboBoxSuggestion();
        boxMarca = new club.j3studios.system.utils.ComboBoxSuggestion();
        jLabel8 = new javax.swing.JLabel();
        txtCompra = new club.j3studios.system.utils.TextField();
        jLabel3 = new javax.swing.JLabel();
        txtGanancia = new club.j3studios.system.utils.TextField();
        jLabel6 = new javax.swing.JLabel();
        txtVenta = new club.j3studios.system.utils.TextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtStockActual = new club.j3studios.system.utils.TextField();
        txtStockMin = new club.j3studios.system.utils.TextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtStockMax = new club.j3studios.system.utils.TextField();
        jLabel12 = new javax.swing.JLabel();
        checkControl = new club.j3studios.system.utils.JCheckBoxCustom();
        jLabel13 = new javax.swing.JLabel();
        boxMedida = new club.j3studios.system.utils.ComboBoxSuggestion();
        checkExento = new club.j3studios.system.utils.JCheckBoxCustom();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        background1.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));

        cmdConfirm.setText("CONFIRMAR");
        cmdConfirm.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cmdConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdConfirmActionPerformed(evt);
            }
        });

        cmdCancel.setBackground(new java.awt.Color(255, 51, 51));
        cmdCancel.setText("CANCELAR");
        cmdCancel.setColorHover(new java.awt.Color(255, 51, 51));
        cmdCancel.setColorPressed(new java.awt.Color(204, 0, 51));
        cmdCancel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nuevo-cliente.png"))); // NOI18N

        jLabel2.setText("CÓDIGO");

        jLabel4.setText("DESCRIPCIÓN:");

        jLabel5.setText("CATEGORÍA:");

        boxMarca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ninguna" }));
        boxMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxMarcaActionPerformed(evt);
            }
        });

        jLabel8.setText("MARCA:");

        txtCompra.setText("0.00");
        txtCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCompraKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCompraKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCompraKeyTyped(evt);
            }
        });

        jLabel3.setText("PRECIO DE COMPRA:");

        txtGanancia.setText("20");
        txtGanancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGananciaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGananciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGananciaKeyTyped(evt);
            }
        });

        jLabel6.setText("GANANCIAS (%):");

        txtVenta.setText("0.00");
        txtVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVentaKeyTyped(evt);
            }
        });

        jLabel7.setText("PRECIO DE VENTA:");

        jLabel9.setText("STOCK ACTUAL:");

        txtStockActual.setText("0");

        txtStockMin.setText("0");
        txtStockMin.setEnabled(false);

        jLabel10.setText("STOCK MIN:");

        jLabel11.setText("STOCK MÁX:");

        txtStockMax.setText("0");
        txtStockMax.setEnabled(false);

        jLabel12.setText("CONTROL DE STOCK:");

        checkControl.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                checkControlStateChanged(evt);
            }
        });
        checkControl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkControlActionPerformed(evt);
            }
        });

        jLabel13.setText("UNIDAD DE MEDIDA:");

        boxMedida.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Unidades", "Kilogramos", "Metros", "Litros" }));

        checkExento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkExentoActionPerformed(evt);
            }
        });

        jLabel14.setText("PRODUCTO EXENTO AL IVA: ");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(102, 102, 102));
        jButton1.setText("Nuevo");
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setForeground(new java.awt.Color(102, 102, 102));
        jButton2.setText("Editar");
        jButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setForeground(new java.awt.Color(102, 102, 102));
        jButton3.setText("Editar");
        jButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setForeground(new java.awt.Color(102, 102, 102));
        jButton4.setText("Nuevo");
        jButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 0, 0));

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(background1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, background1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cmdCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(background1Layout.createSequentialGroup()
                                .addComponent(txtCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGanancia, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(background1Layout.createSequentialGroup()
                                .addComponent(checkControl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(background1Layout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(checkExento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(background1Layout.createSequentialGroup()
                                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(background1Layout.createSequentialGroup()
                                                .addComponent(txtStockActual, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(background1Layout.createSequentialGroup()
                                                .addComponent(txtStockMin, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtStockMax, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(background1Layout.createSequentialGroup()
                                                .addComponent(boxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)))))))))
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(background1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(background1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(boxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(boxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGanancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtStockMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtStockActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(checkControl, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtStockMax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(boxMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(checkExento, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        messageType = MessageType.CANCEL;
        closeMessage();
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void cmdConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdConfirmActionPerformed
        messageType = MessageType.OK;
        closeMessage();
    }//GEN-LAST:event_cmdConfirmActionPerformed

    private void checkControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkControlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkControlActionPerformed

    private void checkExentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkExentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkExentoActionPerformed

    private void checkControlStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkControlStateChanged
        if (checkControl.isSelected()) {
            txtStockMin.setEnabled(true);
            txtStockMax.setEnabled(true);
        } else {
            txtStockMin.setEnabled(false);
            txtStockMax.setEnabled(false);
            txtStockMin.setText("0");
            txtStockMax.setText("0");
        }
    }//GEN-LAST:event_checkControlStateChanged

    private void txtCompraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompraKeyPressed
        if (txtCompra.getText().isEmpty()) {
            return;
        }
        calcularPrecioVenta();
    }//GEN-LAST:event_txtCompraKeyPressed

    private void txtCompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompraKeyReleased
        if (txtCompra.getText().isEmpty()) {
            return;
        }
        calcularPrecioVenta();
    }//GEN-LAST:event_txtCompraKeyReleased

    private void txtCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompraKeyTyped
        char c = evt.getKeyChar();
        if((Character.isDigit(c))||(c==KeyEvent.VK_PERIOD)||(c==KeyEvent.VK_BACK_SPACE)){
            if(c==KeyEvent.VK_PERIOD) { 
                String s = txtCompra.getText();
                int dot = s.indexOf('.');
                if( dot != -1){
                    getToolkit().beep();
                    evt.consume();
                }
            }
        } else {
            evt.consume();
        }	
        if (txtCompra.getText().isEmpty()) {
            return;
        }
        calcularPrecioVenta();
    }//GEN-LAST:event_txtCompraKeyTyped

    private void txtGananciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaKeyPressed
        if (txtGanancia.getText().isEmpty()) {
            return;
        }
        calcularPrecioVenta();
    }//GEN-LAST:event_txtGananciaKeyPressed

    private void txtGananciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaKeyReleased
        if (txtGanancia.getText().isEmpty()) {
            return;
        }
        calcularPrecioVenta();
    }//GEN-LAST:event_txtGananciaKeyReleased

    private void txtGananciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaKeyTyped
        char c = evt.getKeyChar();
        if((Character.isDigit(c))||(c==KeyEvent.VK_PERIOD)||(c==KeyEvent.VK_BACK_SPACE)){
            if(c==KeyEvent.VK_PERIOD) { 
                String s = txtGanancia.getText();
                int dot = s.indexOf('.');
                if( dot != -1){
                    getToolkit().beep();
                    evt.consume();
                }
            }
        } else {
            evt.consume();
        }	
        if (txtGanancia.getText().isEmpty()) {
            return;
        }
        calcularPrecioVenta();
    }//GEN-LAST:event_txtGananciaKeyTyped

    private void txtVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVentaKeyTyped
        char c = evt.getKeyChar();
        if((Character.isDigit(c))||(c==KeyEvent.VK_PERIOD)||(c==KeyEvent.VK_BACK_SPACE)){
            if(c==KeyEvent.VK_PERIOD) { 
                String s = txtVenta.getText();
                int dot = s.indexOf('.');
                if( dot != -1){
                    getToolkit().beep();
                    evt.consume();
                }
            }
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_txtVentaKeyTyped

    private void boxMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxMarcaActionPerformed
    
    public void calcularPrecioVenta() {
        Double priceCosto = 0.0;
        Double ganancias = 0.0;
        
        if (new Tools().isDouble(txtCompra.getText().trim())) {
            priceCosto = Double.valueOf(txtCompra.getText().trim());
        }
        if (new Tools().isDouble(txtGanancia.getText().trim())) {
            ganancias = Double.valueOf(txtGanancia.getText().trim());
        }
        
        Double porcentaje = new Tools().calcPercent(priceCosto, ganancias);
        Double precioVenta = priceCosto + porcentaje;
        
        DecimalFormat format = new DecimalFormat("###,###,##0.00");
        String precioForVenta = format.format(precioVenta);
        txtVenta.setText(precioForVenta);
    }
    
    //
    
    public static enum MessageType {
        CANCEL, OK;
    }
    
    public MessageType getMessageType() {
        return messageType;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private club.j3studios.system.utils.visuals.Background background1;
    public club.j3studios.system.utils.ComboBoxSuggestion boxCategoria;
    public club.j3studios.system.utils.ComboBoxSuggestion boxMarca;
    public club.j3studios.system.utils.ComboBoxSuggestion boxMedida;
    public club.j3studios.system.utils.JCheckBoxCustom checkControl;
    public club.j3studios.system.utils.JCheckBoxCustom checkExento;
    private club.j3studios.system.utils.visuals.ButtonCustom cmdCancel;
    private club.j3studios.system.utils.visuals.ButtonCustom cmdConfirm;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public club.j3studios.system.utils.TextField txtCode;
    public club.j3studios.system.utils.TextField txtCompra;
    public club.j3studios.system.utils.TextField txtDesc;
    public club.j3studios.system.utils.TextField txtGanancia;
    public club.j3studios.system.utils.TextField txtStockActual;
    public club.j3studios.system.utils.TextField txtStockMax;
    public club.j3studios.system.utils.TextField txtStockMin;
    public club.j3studios.system.utils.TextField txtVenta;
    // End of variables declaration//GEN-END:variables
}
