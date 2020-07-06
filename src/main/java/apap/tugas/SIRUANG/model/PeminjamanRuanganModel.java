package apap.tugas.SIRUANG.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "peminjaman_ruangan")
public class PeminjamanRuanganModel implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(max = 200)
    @Column(name = "waktu_mulai", nullable = false)
    private String waktu_mulai;

    @NotNull
    @Size(max = 200)
    @Column(name = "waktu_selesai", nullable = false)
    private String waktu_selesai;

    @DateTimeFormat(iso=ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "tanggal_mulai", nullable = false)
    private Date tanggal_mulai;

    @DateTimeFormat(iso=ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "tanggal_selesai", nullable = false)
    private Date tanggal_selesai;

    @NotNull
    @Size(max = 200)
    @Column(name = "tujuan", nullable = false)
    private String tujuan;

    @NotNull
    @Size(max = 200)
    @Column(name = "keterangan", nullable = false)
    private String keterangan;

    @NotNull
    @Column(name = "jumlah_peserta", nullable = false)
    private int jumlah_peserta;

    @NotNull
    @Column(name = "is_disetujui", nullable = false)
    private boolean is_disetujui = false;
    
    

	public boolean isIs_disetujui() {
		return is_disetujui;
	}

	/**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param is_disetujui the is_disetujui to set
     */
    public void setIs_disetujui(boolean is_disetujui) {
        this.is_disetujui = is_disetujui;
    }

    /**
     * @param jumlah_peserta the jumlah_peserta to set
     */
    public void setJumlah_peserta(int jumlah_peserta) {
        this.jumlah_peserta = jumlah_peserta;
    }

    /**
     * @param keterangan the keterangan to set
     */
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    /**
     * @param tanggal_mulai the tanggal_mulai to set
     */
    public void setTanggal_mulai(Date tanggal_mulai) {
        this.tanggal_mulai = tanggal_mulai;
    }

    /**
     * @param tanggal_selesai the tanggal_selesai to set
     */
    public void setTanggal_selesai(Date tanggal_selesai) {
        this.tanggal_selesai = tanggal_selesai;
    }

    /**
     * @param tujuan the tujuan to set
     */
    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    /**
     * @param waktu_mulai the waktu_mulai to set
     */
    public void setWaktu_mulai(String waktu_mulai) {
        this.waktu_mulai = waktu_mulai;
    }

    /**
     * @param waktu_selesai the waktu_selesai to set
     */
    public void setWaktu_selesai(String waktu_selesai) {
        this.waktu_selesai = waktu_selesai;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the jumlah_peserta
     */
    public int getJumlah_peserta() {
        return jumlah_peserta;
    }

    /**
     * @return the keterangan
     */
    public String getKeterangan() {
        return keterangan;
    }

    /**
     * @return the tanggal_mulai
     */
    public Date getTanggal_mulai() {
        return tanggal_mulai;
    }

    /**
     * @return the tanggal_selesai
     */
    public Date getTanggal_selesai() {
        return tanggal_selesai;
    }

    /**
     * @return the tujuan
     */
    public String getTujuan() {
        return tujuan;
    }

    /**
     * @return the waktu_mulai
     */
    public String getWaktu_mulai() {
        return waktu_mulai;
    }

    /**
     * @return the waktu_selesai
     */
    public String getWaktu_selesai() {
        return waktu_selesai;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ruanganId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonBackReference
    private RuanganModel ruangan;

    /**
     * @param ruangan the ruangan to set
     */
    public void setRuangan(RuanganModel ruangan) {
        this.ruangan = ruangan;
    }

    /**
     * @return the ruangan
     */
    public RuanganModel getRuangan() {
        return ruangan;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "userPeminjamUuid", referencedColumnName = "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonBackReference
    @JsonIgnore
    private UserModel userPeminjam;

    /**
     * @param user_peminjam the user_peminjam to set
     */
    public void setUser_peminjam(UserModel user_peminjam) {
        this.userPeminjam = userPeminjam;
    }

    /**
     * @return the user_peminjam
     */
    public UserModel getUser_peminjam() {
        return userPeminjam;
    }



    @ManyToOne(fetch = FetchType.EAGER, optional = true, cascade=CascadeType.ALL)
    @JoinColumn(name = "userPenyetujuUuid", referencedColumnName = "uuid", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonBackReference
    @JsonIgnore
    private UserModel user_penyetuju=null;

    /**
     * @param user_penyetuju the user_penyetuju to set
     */
    public void setUser_penyetuju(UserModel user_penyetuju) {
        this.user_penyetuju = user_penyetuju;
    }

    /**
     * @return the user_penyetuju
     */
    public UserModel getUser_penyetuju() {
        return user_penyetuju;
    }
}