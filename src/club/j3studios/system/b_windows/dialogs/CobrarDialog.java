/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package club.j3studios.system.b_windows.dialogs;

import club.j3studios.system.b_windows.subwins.Facturar;
import club.j3studios.system.control.Ctrl_RegistrarVenta;
import club.j3studios.system.control.VentaPDF;
import club.j3studios.system.model.CabeceraVenta;
import club.j3studios.system.model.DetalleVenta;
import club.j3studios.system.utils.Tools;
import club.j3studios.system.utils.visuals.Glass;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author josne
 */
public class CobrarDialog extends javax.swing.JDialog {

    private final Facturar principal;
    private final JFrame fram;
    private Animator animator;
    private Glass glass;
    private boolean show;
    private MessageType messageType = MessageType.CANCEL;
    
    public Double totalValue = 0.0;
    public Double restante = 0.0;

    private Double efectivo = 0.0;
    private Double dolares = 0.0;
    private Double tarjeta = 0.0;
    private Double pagomovil = 0.0;
    
    private Double valorDolares = 0.0;
    
    
    
    public CobrarDialog(JFrame fram, Facturar principal) {
        super(fram, true);
        this.fram = fram;
        this.principal = principal;
        initComponents();
        init();
        setValues();
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
    
    public void setValues() {
		String str = principal.labelTotal.getText().trim();	
		
		str = str.replace(".", "");
		str = str.replace(",", ".");
		str = str.substring(3, str.length());
		
		Double value = Double.valueOf(str);		
		String dolarStr = principal.textDolar.getText().trim();
		for (int i = 0; i < dolarStr.length(); i++) {
			if (dolarStr.charAt(i) == ',') {
				String valorNuevo01 = dolarStr.replace(",", ".");
				if (new Tools().isDouble(valorNuevo01)) {
					valorDolares = Double.valueOf(valorNuevo01);
				}
			} else {
				valorDolares = Double.valueOf(dolarStr);
			}
		}
		
		totalValue = value;
		restante = totalValue;
		
		titulo.setText("Bs. " + new DecimalFormat("###,###,##0.00").format(totalValue));
		
		Double calcularBsToDolarRes = restante/valorDolares;		
		restanteBs.setText("Bs. " + new DecimalFormat("###,###,##0.00").format(restante));
		restanteUsd.setText("$. " + new DecimalFormat("###,###,##0.00").format(calcularBsToDolarRes));
		
		restanteBs.setForeground(new Color(255, 80, 114));
		restanteUsd.setForeground(new Color(255, 80, 114));		
	}
    
    public void calcularRestante() {
		restante = totalValue;
		
		String efectivoStr = txtEfectivo.getText().trim();
		for (int i = 0; i < efectivoStr.length(); i++) {
			if (efectivoStr.charAt(i) == ',') {
				String valorNuevo = efectivoStr.replace(",", ".");
				if (new Tools().isDouble(valorNuevo)) {
					efectivo = Double.valueOf(valorNuevo);
				}
			} else {
				efectivo = Double.valueOf(efectivoStr);
			}
		}
		
		String dolaresStr = txtDolares.getText().trim();
		for (int i = 0; i < dolaresStr.length(); i++) {
			if (dolaresStr.charAt(i) == ',') {
				String valorNuevo = dolaresStr.replace(",", ".");
				if (new Tools().isDouble(valorNuevo)) {
					dolares = Double.valueOf(valorNuevo);
				}
			} else {
				dolares = Double.valueOf(dolaresStr);
			}
		}
		
		String tarjetaStr = txtTarjetas.getText().trim();
		for (int i = 0; i < tarjetaStr.length(); i++) {
			if (tarjetaStr.charAt(i) == ',') {
				String valorNuevo = tarjetaStr.replace(",", ".");
				if (new Tools().isDouble(valorNuevo)) {
					tarjeta = Double.valueOf(valorNuevo);
				}
			} else {
				tarjeta = Double.valueOf(tarjetaStr);
			}
		}
                
                String pagoMovilStr = txtPagoMovil.getText().trim();
		for (int i = 0; i < pagoMovilStr.length(); i++) {
			if (pagoMovilStr.charAt(i) == ',') {
				String valorNuevo = pagoMovilStr.replace(",", ".");
				if (new Tools().isDouble(valorNuevo)) {
					pagomovil = Double.valueOf(valorNuevo);
				}
			} else {
				pagomovil = Double.valueOf(pagoMovilStr);
			}
		}
		
		String dolarStr = principal.textDolar.getText().trim();
		for (int i = 0; i < dolarStr.length(); i++) {
			if (dolarStr.charAt(i) == ',') {
				String valorNuevo01 = dolarStr.replace(",", ".");
				if (new Tools().isDouble(valorNuevo01)) {
					valorDolares = Double.valueOf(valorNuevo01);
				}
			} else {
				valorDolares = Double.valueOf(dolarStr);
			}
		}
		Double calcularDolaresToBs = valorDolares * dolares;
		
		restante = (restante - efectivo - calcularDolaresToBs - tarjeta - pagomovil);
		Double calcularBsToDolarRes = restante/valorDolares;
		
		restanteBs.setText("Bs. " + new DecimalFormat("###,###,##0.00").format(restante));
		restanteUsd.setText("$" + new DecimalFormat("###,###,##0.00").format(calcularBsToDolarRes));
		
		if (restante > 0.00) {
			vueltoBs.setText("Bs. 0.00");
			vueltoUsd.setText("$0.00");
			restanteBs.setForeground(new Color(255, 80, 114));
			restanteUsd.setForeground(new Color(255, 80, 114));
		} else {
			Double positiveRestante = Math.abs(restante);
			Double calcularBsToDolar = positiveRestante/valorDolares;
			
			vueltoBs.setText("Bs. " + new DecimalFormat("###,###,##0.00").format(positiveRestante));
			vueltoUsd.setText("$" + new DecimalFormat("###,###,##0.00").format(calcularBsToDolar));			
			
			restanteBs.setText("Bs. " + new DecimalFormat("###,###,##0.00").format(0.0));
			restanteUsd.setText("$" + new DecimalFormat("###,###,##0.00").format(0.0));
		}
	}
    
    public void cobrar (boolean imprimir) {
        new Tools().debug("Cobrando...");           
        
        CabeceraVenta cabeceraVenta = new CabeceraVenta();
        DetalleVenta detalleVenta = new DetalleVenta();
        Ctrl_RegistrarVenta controlVenta = new Ctrl_RegistrarVenta();

        String fechaActual = "";
        Date date = new Date();
        fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);

        if (!principal.listaProductos.isEmpty()) {            
            //registrar cabecera
            cabeceraVenta.setIdCabeceraventa(0);
            cabeceraVenta.setIdCaja(principal.getMainMenu().caja.getIdCaja());
            cabeceraVenta.setIdUsuario(principal.getMainMenu().caja.getIdUsuario());
            cabeceraVenta.setIdCliente(principal.idCliente);
            cabeceraVenta.setValorPagar(totalValue);
            cabeceraVenta.setFechaVenta(fechaActual);
            cabeceraVenta.setEstado(1);

            if (controlVenta.guardar(cabeceraVenta)) {
                JOptionPane.showMessageDialog(null, "¡Venta Registrada!");

                VentaPDF pdf = new VentaPDF(principal);
                if (imprimir) {
                    pdf.generarFacturaPDF(principal.getMainMenu().caja);
                }
                
                //guardar detalle
                for (DetalleVenta elemento : principal.listaProductos) {
                    detalleVenta.setIdDetalleVenta(0);
                    detalleVenta.setIdCabeceraVenta(0);
                    detalleVenta.setIdProducto(elemento.getIdProducto());
                    detalleVenta.setCantidad(elemento.getCantidad());
                    detalleVenta.setCodigo(elemento.getCodigo());
                    detalleVenta.setNombre(elemento.getNombre());
                    detalleVenta.setPrecioVenta(elemento.getPrecioVenta());
                    detalleVenta.setSubTotal(elemento.getSubTotal());
                    detalleVenta.setIva(elemento.getIva());
                    detalleVenta.setTotalPagarUsd(elemento.getTotalPagarUsd());
                    detalleVenta.setTotalPagarBs(elemento.getTotalPagarBs());
                    
                    if (controlVenta.guardarDetalle(detalleVenta)) {
                        principal.labelTotal.setText("Bs. 0.00");
                        principal.labelTotalUSD.setText("$0.00");
                        principal.label_Iva.setText("Bs. 0.00");
                        principal.labelSubTotal.setText("Bs. 0.00");
                        principal.auxIdDetalle = 1;

                        //principal.RestarStockProductos(elemento.getIdProducto(), elemento.getCantidad());
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "¡Error al guardar detalle de venta!");
                    }
                }
                //vaciamos la lista
                principal.clear();
            } else {
                JOptionPane.showMessageDialog(null, "¡Error al guardar cabecera de venta!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "¡Seleccione un producto!");
        }
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
        cmdConfirm = new club.j3studios.system.utils.visuals.ButtonCustom();
        cmdCancel = new club.j3studios.system.utils.visuals.ButtonCustom();
        jLabel1 = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        cmdConfirmNoFactura = new club.j3studios.system.utils.visuals.ButtonCustom();
        txtEfectivo = new club.j3studios.system.utils.FormattedTextField();
        txtDolares = new club.j3studios.system.utils.FormattedTextField();
        txtPagoMovil = new club.j3studios.system.utils.FormattedTextField();
        txtTarjetas = new club.j3studios.system.utils.FormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        restanteUsd = new javax.swing.JLabel();
        vueltoBs = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        restanteBs = new javax.swing.JLabel();
        vueltoUsd = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        background1.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));

        cmdConfirm.setText("FACTURAR e IMPRIMIR");
        cmdConfirm.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cobrar.png"))); // NOI18N

        titulo.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 51, 51));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Bs. 0.00");

        cmdConfirmNoFactura.setText("FACTURAR SIN IMPRIMIR");
        cmdConfirmNoFactura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmdConfirmNoFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdConfirmNoFacturaActionPerformed(evt);
            }
        });

        txtEfectivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEfectivo.setText("0.00");
        txtEfectivo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtEfectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEfectivoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEfectivoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEfectivoKeyTyped(evt);
            }
        });

        txtDolares.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDolares.setText("0.00");
        txtDolares.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtDolares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDolaresActionPerformed(evt);
            }
        });
        txtDolares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDolaresKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDolaresKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDolaresKeyTyped(evt);
            }
        });

        txtPagoMovil.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPagoMovil.setText("0.00");
        txtPagoMovil.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtPagoMovil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPagoMovilActionPerformed(evt);
            }
        });
        txtPagoMovil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPagoMovilKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPagoMovilKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPagoMovilKeyTyped(evt);
            }
        });

        txtTarjetas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTarjetas.setText("0.00");
        txtTarjetas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTarjetas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTarjetasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTarjetasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTarjetasKeyTyped(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("______________________________________________________________________________________________________");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("  PAGO MÓVIL:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dinero1.png"))); // NOI18N
        jLabel4.setText(" EFECTIVO:");
        jLabel4.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dolares1.png"))); // NOI18N
        jLabel5.setText("  DÓLARES:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tarjeta1.png"))); // NOI18N
        jLabel6.setText("  TARJETAS:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("VUELTO:  ");

        restanteUsd.setText("$0.00");

        vueltoBs.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        vueltoBs.setText("Bs. 0.00");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("RESTANTE:  ");

        restanteBs.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        restanteBs.setText("Bs. 0.00");

        vueltoUsd.setText("$0.00");

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(titulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(cmdCancel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, background1Layout.createSequentialGroup()
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(background1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEfectivo, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(txtDolares, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTarjetas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPagoMovil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, background1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(cmdConfirmNoFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
                            .addGroup(background1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(restanteUsd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(background1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(restanteBs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, background1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vueltoUsd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(background1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vueltoBs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDolares, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTarjetas, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPagoMovil, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(restanteBs, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(restanteUsd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(vueltoBs, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(vueltoUsd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdConfirmNoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void txtDolaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDolaresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDolaresActionPerformed

    private void cmdConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdConfirmActionPerformed
        messageType = MessageType.IMPRIMIR;
        
        Double restanteFixed = 0.0;
        String resT = restanteBs.getText().trim();
        resT = resT.substring(4, resT.length());
        if (!new Tools().isDouble(resT)) {
            String valorNuevo01 = resT.replace(",", ".");
            restanteFixed = Double.valueOf(valorNuevo01);
        } else {
            restanteFixed = Double.valueOf(resT);
        }
        
        if (restanteFixed > 0.00) {
            new Tools().debug("Confirm with Factura error = Restante > 0.00 (" + restanteFixed + ")");
            JOptionPane.showMessageDialog(null, "No puedes cobrar si el monto restante todavía es mayor a 0,00.\n                     ¡Asegurate de cobrar cada centavo!");
            return;
        }
        cobrar(true);
        
        closeMessage();
    }//GEN-LAST:event_cmdConfirmActionPerformed

    private void cmdConfirmNoFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdConfirmNoFacturaActionPerformed
        messageType = MessageType.NO_IMPRIMIR;
        
        Double restanteFixed = 0.0;
        String resT = restanteBs.getText().trim();
        resT = resT.substring(4, resT.length());
        if (!new Tools().isDouble(resT)) {
            String valorNuevo01 = resT.replace(",", ".");
            restanteFixed = Double.valueOf(valorNuevo01);
        } else {
            restanteFixed = Double.valueOf(resT);
        }
        
        if (restanteFixed > 0.00) {
            new Tools().debug("Confirm without Factura error = Restante > 0.00 (" + restanteFixed + ")");
            JOptionPane.showMessageDialog(null, "No puedes cobrar si el monto restante todavía es mayor a 0,00.\n                     ¡Asegurate de cobrar cada centavo!");
            return;
        }
        cobrar(true);
        
        closeMessage();
    }//GEN-LAST:event_cmdConfirmNoFacturaActionPerformed

    private void txtPagoMovilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPagoMovilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPagoMovilActionPerformed

    //
    private void txtEfectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyTyped
        char c = evt.getKeyChar();
        if((Character.isDigit(c))||(c==KeyEvent.VK_PERIOD)||(c==KeyEvent.VK_BACK_SPACE)){
            if(c==KeyEvent.VK_PERIOD) { 
                String s = txtEfectivo.getText();
                int dot = s.indexOf('.');
                if( dot != -1){
                    getToolkit().beep();
                    evt.consume();
                }
            }
        } else {
            evt.consume();
        }	
        if (txtEfectivo.getText().isEmpty()) {
            return;
        }        
        if (new Tools().isDouble(txtEfectivo.getText().trim())) {
            efectivo = Double.valueOf(txtEfectivo.getText().trim());
        }
        calcularRestante();
    }//GEN-LAST:event_txtEfectivoKeyTyped

    private void txtDolaresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDolaresKeyTyped
        char c = evt.getKeyChar();
        if((Character.isDigit(c))||(c==KeyEvent.VK_PERIOD)||(c==KeyEvent.VK_BACK_SPACE)){
            if(c==KeyEvent.VK_PERIOD) { 
                String s = txtDolares.getText();
                int dot = s.indexOf('.');
                if( dot != -1){
                    getToolkit().beep();
                    evt.consume();
                }
            }
        } else {
            evt.consume();
        }	
        if (txtDolares.getText().isEmpty()) {
            return;
        }        
        if (new Tools().isDouble(txtDolares.getText().trim())) {
            dolares = Double.valueOf(txtDolares.getText().trim());
        }        
        calcularRestante();
    }//GEN-LAST:event_txtDolaresKeyTyped

    private void txtTarjetasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarjetasKeyTyped
        char c = evt.getKeyChar();
        if((Character.isDigit(c))||(c==KeyEvent.VK_PERIOD)||(c==KeyEvent.VK_BACK_SPACE)){
            if(c==KeyEvent.VK_PERIOD) { 
                String s = txtTarjetas.getText();
                int dot = s.indexOf('.');
                if( dot != -1){
                    getToolkit().beep();
                    evt.consume();
                }
            }
        } else {
            evt.consume();
        }	
        if (txtTarjetas.getText().isEmpty()) {
            return;
        }
        if (new Tools().isDouble(txtTarjetas.getText().trim())) {
            tarjeta = Double.valueOf(txtTarjetas.getText().trim());
        }
        calcularRestante();
    }//GEN-LAST:event_txtTarjetasKeyTyped

    private void txtPagoMovilKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagoMovilKeyTyped
        char c = evt.getKeyChar();
        if((Character.isDigit(c))||(c==KeyEvent.VK_PERIOD)||(c==KeyEvent.VK_BACK_SPACE)){
            if(c==KeyEvent.VK_PERIOD) { 
                String s = txtPagoMovil.getText();
                int dot = s.indexOf('.');
                if( dot != -1){
                    getToolkit().beep();
                    evt.consume();
                }
            }
        } else {
            evt.consume();
        }	
        if (txtPagoMovil.getText().isEmpty()) {
            return;
        }        
        if (new Tools().isDouble(txtPagoMovil.getText().trim())) {
            pagomovil = Double.valueOf(txtPagoMovil.getText().trim());
        }        
        calcularRestante();
    }//GEN-LAST:event_txtPagoMovilKeyTyped

    private void txtEfectivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyPressed
        if (txtEfectivo.getText().isEmpty()) {
            return;
        }        
        if (new Tools().isDouble(txtEfectivo.getText().trim())) {
            efectivo = Double.valueOf(txtEfectivo.getText().trim());
        }
        calcularRestante();
    }//GEN-LAST:event_txtEfectivoKeyPressed

    private void txtEfectivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyReleased
        if (txtEfectivo.getText().isEmpty()) {
            return;
        }        
        if (new Tools().isDouble(txtEfectivo.getText().trim())) {
            efectivo = Double.valueOf(txtEfectivo.getText().trim());
        }
        calcularRestante();
    }//GEN-LAST:event_txtEfectivoKeyReleased

    private void txtDolaresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDolaresKeyPressed
        if (txtDolares.getText().isEmpty()) {
            return;
        }        
        if (new Tools().isDouble(txtDolares.getText().trim())) {
            dolares = Double.valueOf(txtDolares.getText().trim());
        }
        calcularRestante();
    }//GEN-LAST:event_txtDolaresKeyPressed

    private void txtDolaresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDolaresKeyReleased
        if (txtDolares.getText().isEmpty()) {
            return;
        }        
        if (new Tools().isDouble(txtDolares.getText().trim())) {
            dolares = Double.valueOf(txtDolares.getText().trim());
        }
        calcularRestante();
    }//GEN-LAST:event_txtDolaresKeyReleased

    private void txtTarjetasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarjetasKeyPressed
        if (txtTarjetas.getText().isEmpty()) {
            return;
        }        
        if (new Tools().isDouble(txtTarjetas.getText().trim())) {
            tarjeta = Double.valueOf(txtTarjetas.getText().trim());
        }
        calcularRestante();
    }//GEN-LAST:event_txtTarjetasKeyPressed

    private void txtTarjetasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarjetasKeyReleased
        if (txtTarjetas.getText().isEmpty()) {
            return;
        }        
        if (new Tools().isDouble(txtTarjetas.getText().trim())) {
            tarjeta = Double.valueOf(txtTarjetas.getText().trim());
        }
        calcularRestante();
    }//GEN-LAST:event_txtTarjetasKeyReleased

    private void txtPagoMovilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagoMovilKeyPressed
        if (txtPagoMovil.getText().isEmpty()) {
            return;
        }        
        if (new Tools().isDouble(txtPagoMovil.getText().trim())) {
            pagomovil = Double.valueOf(txtPagoMovil.getText().trim());
        }
        calcularRestante();
    }//GEN-LAST:event_txtPagoMovilKeyPressed

    private void txtPagoMovilKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagoMovilKeyReleased
        if (txtPagoMovil.getText().isEmpty()) {
            return;
        }        
        if (new Tools().isDouble(txtPagoMovil.getText().trim())) {
            pagomovil = Double.valueOf(txtPagoMovil.getText().trim());
        }
        calcularRestante();
    }//GEN-LAST:event_txtPagoMovilKeyReleased
    //
    
    public static enum MessageType {
        CANCEL, IMPRIMIR, NO_IMPRIMIR;
    }
    
    public MessageType getMessageType() {
        return messageType;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private club.j3studios.system.utils.visuals.Background background1;
    private club.j3studios.system.utils.visuals.ButtonCustom cmdCancel;
    private club.j3studios.system.utils.visuals.ButtonCustom cmdConfirm;
    private club.j3studios.system.utils.visuals.ButtonCustom cmdConfirmNoFactura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel restanteBs;
    private javax.swing.JLabel restanteUsd;
    private javax.swing.JLabel titulo;
    private club.j3studios.system.utils.FormattedTextField txtDolares;
    private club.j3studios.system.utils.FormattedTextField txtEfectivo;
    private club.j3studios.system.utils.FormattedTextField txtPagoMovil;
    private club.j3studios.system.utils.FormattedTextField txtTarjetas;
    private javax.swing.JLabel vueltoBs;
    private javax.swing.JLabel vueltoUsd;
    // End of variables declaration//GEN-END:variables
}
