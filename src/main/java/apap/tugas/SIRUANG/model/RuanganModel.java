package apap.tugas.SIRUANG.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="ruangan")
public class RuanganModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(max=200)
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "kapasitas", nullable = false)
    private int kapasitas;

    @OneToMany(mappedBy = "ruangan", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<FasilitasModel> listFasilitas;

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param kapasitas the kapasitas to set
     */
    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    /**
     * @param listFasilitas the listFasilitas to set
     */
    public void setListFasilitas(List<FasilitasModel> listFasilitas) {
        this.listFasilitas = listFasilitas;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the kapasitas
     */
    public int getKapasitas() {
        return kapasitas;
    }

    /**
     * @return the listFasilitas
     */
    public List<FasilitasModel> getListFasilitas() {
        return listFasilitas;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    @OneToMany(mappedBy = "ruangan", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PeminjamanRuanganModel> listPeminjamanRuangan;

    /**
     * @param listPeminjamanRuangan the listPeminjamanRuangan to set
     */
    public void setListPeminjamanRuangan(List<PeminjamanRuanganModel> listPeminjamanRuangan) {
        this.listPeminjamanRuangan = listPeminjamanRuangan;
    }

    /**
     * @return the listPeminjamanRuangan
     */
    public List<PeminjamanRuanganModel> getListPeminjamanRuangan() {
        return listPeminjamanRuangan;
    }
}