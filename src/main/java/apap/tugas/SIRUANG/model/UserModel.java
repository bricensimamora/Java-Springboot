package apap.tugas.SIRUANG.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="user")
public class UserModel implements Serializable{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Size(max=200)
    private String uuid;

    @NotNull
    @Size(max = 200)
    @Column(name = "username",  nullable = false)
    private String username;

    @NotNull
    @Lob
    @Size(max = 200)
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role", referencedColumnName = "idRole", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private RoleModel role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PengadaanFasilitasModel> listPengadaanFasilitas;

    /**
     * @param listPengadaanFasilitas the listPengadaanFasilitas to set
     */
    public void setListPengadaanFasilitas(List<PengadaanFasilitasModel> listPengadaanFasilitas) {
        this.listPengadaanFasilitas = listPengadaanFasilitas;
    }

    /**
     * @return the listPengadaanFasilitas
     */
    public List<PengadaanFasilitasModel> getListPengadaanFasilitas() {
        return listPengadaanFasilitas;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param role the role to set
     */
    public void setRole(RoleModel role) {
        this.role = role;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the role
     */
    public RoleModel getRole() {
        return role;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    @OneToMany(mappedBy = "user_penyetuju", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PeminjamanRuanganModel> listPeminjamanRuanganUserPenyetuju;

    /**
     * @param listPeminjamanRuanganUserPenyetuju the listPeminjamanRuanganUserPenyetuju to set
     */
    public void setListPeminjamanRuanganUserPenyetuju(List<PeminjamanRuanganModel> listPeminjamanRuanganUserPenyetuju) {
        this.listPeminjamanRuanganUserPenyetuju = listPeminjamanRuanganUserPenyetuju;
    }

    /**
     * @return the listPeminjamanRuanganUserPenyetuju
     */
    public List<PeminjamanRuanganModel> getListPeminjamanRuanganUserPenyetuju() {
        return listPeminjamanRuanganUserPenyetuju;
    }


    @OneToMany(mappedBy = "user_peminjam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PeminjamanRuanganModel> listPeminjamanRuanganUserPeminjam;

    /**
     * @param listPeminjamanRuanganUserPeminjam the listPeminjamanRuanganUserPeminjam to set
     */
    public void setListPeminjamanRuanganUserPeminjam(List<PeminjamanRuanganModel> listPeminjamanRuanganUserPeminjam) {
        this.listPeminjamanRuanganUserPeminjam = listPeminjamanRuanganUserPeminjam;
    }
    
    /**
     * @return the listPeminjamanRuanganUserPeminjam
     */
    public List<PeminjamanRuanganModel> getListPeminjamanRuanganUserPeminjam() {
        return listPeminjamanRuanganUserPeminjam;
    }
}