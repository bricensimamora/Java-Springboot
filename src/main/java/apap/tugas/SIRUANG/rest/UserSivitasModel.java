package apap.tugas.SIRUANG.rest;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

public class UserSivitasModel implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idUSer;

    @NotNull
    @Size(max=50)
    @Column(name="username", nullable = false)
    private String username;

    @NotNull
    @Lob
    @Column(name="password", nullable = false)
    private String password;

    @NotNull
    @Size(max=50)
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name="alamat", nullable = false)
    private String alamat;

    @NotNull
    @Column(name="ni", nullable = false)
    private String ni;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="tanggalLahir", nullable = false)
    private Date tanggalLahir;

    @NotNull
    @Column(name="tempatLahir", nullable = false)
    private String tempatLahir;

    @NotNull
    @Column(name="telepon", nullable = false)
    private String telepon;

    @NotNull
    @Column(name="role", nullable = false)
    private long idRole;

    /**
     * @param alamat the alamat to set
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    /**
     * @param idRole the idRole to set
     */
    public void setIdRole(long idRole) {
        this.idRole = idRole;
    }

    /**
     * @param idUSer the idUSer to set
     */
    public void setIdUSer(String idUSer) {
        this.idUSer = idUSer;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @param ni the ni to set
     */
    public void setNi(String ni) {
        this.ni = ni;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param tanggalLahir the tanggalLahir to set
     */
    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    /**
     * @param telepon the telepon to set
     */
    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    /**
     * @param tempatLahir the tempatLahir to set
     */
    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the alamat
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     * @return the idRole
     */
    public long getIdRole() {
        return idRole;
    }

    /**
     * @return the idUSer
     */
    public String getIdUSer() {
        return idUSer;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @return the ni
     */
    public String getNi() {
        return ni;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the tanggalLahir
     */
    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    /**
     * @return the telepon
     */
    public String getTelepon() {
        return telepon;
    }

    /**
     * @return the tempatLahir
     */
    public String getTempatLahir() {
        return tempatLahir;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    public String getStringTanggalLahir(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(tanggalLahir);
    }
}