package apap.tugas.SIRUANG.service;

import java.util.List;
import java.util.Optional;

import apap.tugas.SIRUANG.model.PeminjamanRuanganModel;;

public interface PeminjamanRuanganService {
	void addPeminjamanRuangan (PeminjamanRuanganModel peminjamanRuangan);


	List<PeminjamanRuanganModel> getPeminjamanRuanganList();
	Optional<PeminjamanRuanganModel> getPeminjamanRuanganById(int id);
	void ubahPersetujuanTerima (PeminjamanRuanganModel peminjamanRuanganModel);
	void ubahPersetujuanTolak (PeminjamanRuanganModel peminjamanRuanganModel);

}