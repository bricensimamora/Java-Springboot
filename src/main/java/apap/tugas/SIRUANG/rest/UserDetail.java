package apap.tugas.SIRUANG.rest;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetail {
    @JsonProperty("idUser")
    private String uuid_user;

    @JsonProperty("ni")
    private String ni;

    @JsonProperty("nama")
    private String nama;

    @JsonProperty("tempatLahir")
    private String tempatLahir;

    @JsonProperty("tanggalLahir")
    private String tanggalLahir;

    @JsonProperty("alamat")
    private String alamat;

    @JsonProperty("telepon")
    private String telepon;

    /**
     * @param alamat the alamat to set
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
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
     * @param tanggalLahir the tanggalLahir to set
     */
    public void setTanggalLahir(String tanggalLahir) {
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
     * @return the alamat
     */
    public String getAlamat() {
        return alamat;
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
     * @return the tanggalLahir
     */
    public String getTanggalLahir() {
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
     * @return the uuid_user
     */
    public String getUuid_user() {
        return uuid_user;
    }
}