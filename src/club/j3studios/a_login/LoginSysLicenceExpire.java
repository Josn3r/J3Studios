package club.j3studios.a_login;

import club.j3studios.system.database.SQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author josne
 */
public class LoginSysLicenceExpire extends javax.swing.JFrame {

    private Integer idCuenta=0;
    
    public LoginSysLicenceExpire(Integer idCuenta) {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);        
        this.idCuenta = idCuenta;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt = new club.j3studios.system.utils.TextField();
        btnConfirm = new club.j3studios.system.utils.Button();
        btnCancel = new club.j3studios.system.utils.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("J3Studios - Licencia");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LICENCIA DEL PROGRAMA");

        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("¡Tu licencia ha expirado!");

        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Adquiere una licencia nueva y registrala aquí");

        txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnConfirm.setBackground(new java.awt.Color(153, 255, 153));
        btnConfirm.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirm.setText("CONFIRMAR");
        btnConfirm.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(255, 102, 102));
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("CANCELAR");
        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        String key = txt.getText().trim();
        
        Boolean keyFound = false;
        Boolean yearly = false;
        
        if (this.checkLicenseKeyUsing(key)) {
            JOptionPane.showMessageDialog(null, "La licencia proporcionada ya fué canjeada por otro usuario!");
            return;
        }
        
        if (this.checkLicenseExists(key, LoginSysLicenceExpire.DataType.YEARLY)) {
            keyFound = true;
            yearly = true;
        } 
        if (!keyFound) {
            if (this.checkLicenseExists(key, LoginSysLicenceExpire.DataType.MONTHLY)) {
                keyFound = true;
            }
        }
        
        if (keyFound) {
            Calendar cal = Calendar.getInstance(); 
            if (yearly) {
                cal.add(Calendar.YEAR, 1);
            } else {
                cal.add(Calendar.MONTH, 1);
            }
            Date vencimiento = cal.getTime();
            Date date = new Date();
            String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(date);
            String fechaVencimiento = new SimpleDateFormat("dd/MM/yyyy").format(vencimiento); 
            
            diferenciaFechas(fechaActual, fechaVencimiento);
            
            registerNewLicense(key, fechaActual, fechaVencimiento, idCuenta);
            updateUserLicense(idCuenta, getLicenseID(key));
            deleteLicenseUse(key, (yearly == true ? LoginSysLicenceExpire.DataType.YEARLY : LoginSysLicenceExpire.DataType.MONTHLY));
            
            JOptionPane.showMessageDialog(null, "Haz canjeado correctamente tu licencia!\n\nLicencia: " + key + "\nMembresía: " + (yearly ? "ANUAL" : "MENSUAL") + "\nFecha: " + fechaActual + "\nVencimiento: " + fechaVencimiento);
        } else {
            JOptionPane.showMessageDialog(null, "La licencia proporcionada es inválida o no existe.\nAdquiere tu licencia de uso en\nWWW.J3STUDIOS.STORE");
            return;
        }
        dispose();
    }//GEN-LAST:event_btnConfirmActionPerformed
    
    public boolean checkLicenseExists (String key, LoginSysLicenceExpire.DataType data) {
        boolean response = false;        
        Connection con = SQL.conectar2();
        String sql = "SELECT idKey FROM keys_monthly WHERE llave = ? limit 1";		
	if (data == LoginSysLicenceExpire.DataType.YEARLY) {
            sql = "SELECT idKey FROM keys_yearly WHERE llave = ? limit 1";
        }        
        try {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, key);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.first()) {
                        response = true;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }
    
    public boolean checkLicenseKeyUsing(String key) {		
        boolean response = false;        
        Connection con = SQL.conectar2();        
        String sql = "select licenseKey from tb_licencias where licenseKey = '" + key + "'";		
	Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                response = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }
    
    public boolean deleteLicenseUse(String key, LoginSysLicenceExpire.DataType data) {
        boolean respuesta = false;
        Connection cn = SQL.conectar2();
        String sql = "DELETE FROM keys_monthly WHERE llave = ? limit 1";		
	if (data == LoginSysLicenceExpire.DataType.YEARLY) {
            sql = "DELETE FROM keys_yearly WHERE llave = ? limit 1";
        } 
        try {
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, key);
                if (ps.executeUpdate() > 0) {
                    respuesta = true;
                }
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar una llave de licencia: " + e);
        }
        return respuesta;
    }
    
    private static void diferenciaFechas(String fechaInicial, String fechaFinal){        
    
         String[] fechaI = fechaInicial.split("/");
         String[] fechaF = fechaFinal.split("/");
         
         Calendar cal = Calendar.getInstance();

           cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(fechaI[0]));
           cal.set(Calendar.MONTH, Integer.parseInt(fechaI[1]));
           cal.set(Calendar.YEAR, Integer.parseInt(fechaI[2]));
           Date firstDate = cal.getTime();

           cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(fechaF[0]));
           cal.set(Calendar.MONTH, Integer.parseInt(fechaF[1]));
           cal.set(Calendar.YEAR, Integer.parseInt(fechaF[2]));
           Date secondDate = cal.getTime();
    
           long diferencia = secondDate.getTime() - firstDate.getTime();

           System.out.println ("Diferencia en dias: " + diferencia/1000/60/60/24);
    }
    
    private void updateUserLicense(Integer idCuenta, Integer licenseId) {
        Connection cn = SQL.conectar2();
        try {
            PreparedStatement consulta = cn.prepareStatement("update tb_cuentas set idLicense = ? where idCuenta ='" + idCuenta + "'");
            consulta.setInt(1, licenseId);
            consulta.executeUpdate();
            
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario: " + e);
        }
    }
    
    private void registerNewLicense(String license, String activeDate, String expireDate, Integer claimedBy) {
        Connection cn = SQL.conectar2();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_licencias values(?, ?, ?, ?, ?)");
            consulta.setInt(1, 0);//id
            consulta.setString(2, license);
            consulta.setString(3, activeDate);
            consulta.setString(4, expireDate);
            consulta.setInt(5, claimedBy);   
            consulta.executeUpdate();
            
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar la licencia: " + e);
        }
    }
    
    public Integer getLicenseID (String key) {
        Connection con = SQL.conectar2();        
        String sql = "select * from tb_licencias where licenseKey = '" + key + "'";		
	Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt("idLicense");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    public static enum DataType {
        MONTHLY, YEARLY;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private club.j3studios.system.utils.Button btnCancel;
    private club.j3studios.system.utils.Button btnConfirm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public club.j3studios.system.utils.TextField txt;
    // End of variables declaration//GEN-END:variables
}
