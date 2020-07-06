package apap.tugas.SIRUANG.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PeminjamanRuanganDetail {
    private String status;

    @JsonProperty("waktu_mulai")
    private String waktu_mulai;

    @JsonProperty("waktu_selesai")
    private String waktu_selesai;

    @JsonProperty("tanggal_mulai")
    private Date tanggal_mulai;

    @JsonProperty("tanggal_mulai")
    private Date tanggal_selesai;

    @JsonProperty("tujuan")
    private String tujuan;

    @JsonProperty("keterangan")
    private String keterangan;

    @JsonProperty("jumlah_peserta")
    private Integer jumlah_peserta;

}
