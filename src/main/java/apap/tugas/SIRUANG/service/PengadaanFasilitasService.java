package apap.tugas.SIRUANG.service;

import java.util.List;
import java.util.Optional;

import apap.tugas.SIRUANG.model.PengadaanFasilitasModel;
import apap.tugas.SIRUANG.model.UserModel;;

public interface PengadaanFasilitasService {
	void addPengadaanFasilitas(PengadaanFasilitasModel pengadaanFasilitas);
	
	List<PengadaanFasilitasModel> getPengadaanFasilitasList();
	
	void deletePengadaanFasilitas(PengadaanFasilitasModel pengadaanFasilitas);
	
	Optional<PengadaanFasilitasModel> getPengadaanFasilitasById(int id);
	
	List<PengadaanFasilitasModel> getPengadaanFasilitasByUserUuidUser (String uuid);
}