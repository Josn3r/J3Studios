package club.j3studios.a_login;

public class ModelUser {

    private int userID;
    
    private String email;
    private String password;
    private String nombre;
    private String razonSocial;
    private String verifyCode;
    private String status;
    private String licenseKey;
    private String onlineStatus;
    
    public ModelUser(int userID, String email, String password, String nombre, String razonSocial, String verifyCode) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.razonSocial = razonSocial;
        this.verifyCode = verifyCode;
    }

    public ModelUser(int userID, String email, String password, String nombre, String razonSocial) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.razonSocial = razonSocial;
    }

    public ModelUser() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    
    
    
}