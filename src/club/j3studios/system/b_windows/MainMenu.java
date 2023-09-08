/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package club.j3studios.system.b_windows;

import club.j3studios.system.LoginUser;
import club.j3studios.system.b_windows.dialogs.cajaApertura;
import club.j3studios.system.b_windows.subwins.Clientes;
import club.j3studios.system.b_windows.subwins.Facturar;
import club.j3studios.system.b_windows.subwins.Productos;
import club.j3studios.system.control.Ctrl_Cajas;
import club.j3studios.system.control.Ctrl_Usuario;
import club.j3studios.system.model.Cajas;
import club.j3studios.system.model.Usuario;
import club.j3studios.system.utils.GradientDropdownMenu;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import org.json.JSONObject;

/**
 *
 * @author josne
 */
public class MainMenu extends javax.swing.JFrame {

    public Usuario usuario;
    public Cajas caja;
    
    public Ctrl_Usuario ctrlUser;
    public Ctrl_Cajas ctrlCajas;
    
    public String dolarValue;
    
    // VENTANAS
    private MainMenu principal;
    
    private Facturar facturar;
    private Clientes clientes;
    private Productos productos;
    
    //
        
    public static final String VERSION_PROGRAM = "1.07.09.23";
    
        
    
    /**
     * Creates new form MainMenu
     */
    public MainMenu(Usuario usuario, Cajas caja) {
        this.principal = this;
        
        initComponents();
        // Add a WindowAdapter to the window.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ctrlUser.updateStatus(usuario, false);
                ctrlCajas.updateStatus(caja, 0);
            }
        });
        
        this.usuario = usuario;
        this.caja = caja;
        
        this.ctrlUser = new Ctrl_Usuario();
        this.ctrlCajas = new Ctrl_Cajas();
        
        this.facturar = new Facturar(this);
        this.clientes = new Clientes(this);
        this.productos = new Productos(this);
        
        setSize(new Dimension(1360, 768));
	//setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setTitle("SISTEMA DE VENTAS - J3Studios.net");
        GradientDropdownMenu menu = new GradientDropdownMenu();
        menu.setMenuHeight(40);
        menu.setHeaderGradient(false);
        menu.setGradientColor(new Color(80,80,80), new Color(60,60,60));
        menu.addItem(caja.getNombre().toUpperCase(), "Agregar Efectivo", "Retirar Efectivo");
        menu.addItem("Usuarios");
        menu.addItem("Facturar [F1]");
        menu.addItem("Productos");
        menu.addItem("Clientes");
        menu.addItem("Proveedores");
        menu.addItem("Reportes", "» Productos", "» Clientes", "» Proveedores", "» Ventas");
        menu.addItem("Historiales");
        menu.addItem("Opciones", "Configuración", "Cambiar cuenta", "Cerrar sesion");
        menu.setFont(new java.awt.Font("sansserif", 1, 14));
        menu.applay(this);
        
        menu.addEvent((int index, int subIndex, boolean menuItem) -> {
            KeyStroke keyStroke = KeyStroke.getKeyStroke("F1");
            Action accion = new AbstractAction("f1") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showForm(facturar);
                }
            };
            String keyF1 = "f1";
            menu.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, keyF1);
            menu.getActionMap().put(keyF1, accion);
            
            if (index == 2 && subIndex == 0) {
                showForm(facturar);
                return;
            }
            if (index == 3 && subIndex == 0) {
                showForm(productos);
                return;
            }
            if (index == 4 && subIndex == 0) {
                showForm(clientes);
                return;
            }
            if (index == 8 && subIndex == 2) {
                ctrlUser.updateStatus(usuario, false);
                ctrlCajas.updateStatus(caja, 0);
                new LoginUser().setVisible(true);
                dispose();
                return;
            }
            if (index == 8 && subIndex == 3) {
                ctrlUser.updateStatus(usuario, false);
                ctrlCajas.updateStatus(caja, 0);
                System.exit(0);
                return;
            }
            if (menuItem) {
                showForm(new Form(menu.getMenuNameAt(index, subIndex).trim()));
            }
        });
        
        showForm(facturar);
                
        facturar.updateDolarValue.setText("Cargando el precio del dolar...");	
        /*new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                
            }
        }, 10); */
        
        try {
            obtenerPrecioDolarBCV();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    cajaApertura dialog = new cajaApertura(principal);
                    dialog.showMessage("");
                    if (dialog.getMessageType() == cajaApertura.MessageType.ABRIR) {
                        ctrlCajas.openCaja(caja, dialog.bolivares, dialog.dolares);
                    }   
                }
            }, 500); 
        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showForm(Component com) {
        panel.removeAll();
        panel.add(com);
        panel.repaint();
        panel.revalidate();
    }
    
    public void obtenerPrecioDolarBCV() throws IOException {
        URL url = new URL("https://pydolarvenezuela-api.vercel.app/api/v1/dollar/bcv_oficial");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int statusCode = connection.getResponseCode();

        if (statusCode == 200) {
            InputStream inputStream = connection.getInputStream();
            Scanner scanner = new Scanner(new InputStreamReader(inputStream));
            String json = scanner.nextLine();            
            
            JSONObject jsonObject = new JSONObject(json);
            JSONObject bcvObject = jsonObject.getJSONObject("bcv");
            String lastUpdate = bcvObject.getString("last_update");
            String price = bcvObject.getString("price");

            facturar.textDolar.setText(price);
            facturar.updateDolarValue.setText("El Último cambio de precio del dolar BCV fue " + lastUpdate + ".");	
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    facturar.updateDolarValue.setText("");
                }
            }, 2000);
        } else {
            System.out.println("La solicitud no se realizó correctamente. El código de estado es " + statusCode);
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

        panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setToolTipText("");
        panel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
