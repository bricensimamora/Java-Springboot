package apap.tugas.SIRUANG.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.SIRUANG.model.PengadaanFasilitasModel;
import apap.tugas.SIRUANG.model.UserModel;
import apap.tugas.SIRUANG.repository.PengadaanFasilitasDb;


@Service
@Transactional
public class PengadaanFasilitasServiceImpl implements PengadaanFasilitasService {

    @Autowired
    private PengadaanFasilitasDb pengadaanFasilitasDb;

	@Override
	public void addPengadaanFasilitas(PengadaanFasilitasModel pengadaanFasilitas) {
		pengadaanFasilitasDb.save(pengadaanFasilitas);
	}

	@Override
	public List<PengadaanFasilitasModel> getPengadaanFasilitasList() {
		return pengadaanFasilitasDb.findAll();
	}

	@Override
	public void deletePengadaanFasilitas(PengadaanFasilitasModel pengadaanFasilitas) {
		pengadaanFasilitasDb.delete(pengadaanFasilitas);
	}

	@Override
	public Optional<PengadaanFasilitasModel> getPengadaanFasilitasById(int id) {
		return pengadaanFasilitasDb.findPengadaanFasilitasById(id);
	}

	@Override
	public List<PengadaanFasilitasModel> getPengadaanFasilitasByUserUuidUser(String uuid) {
		return pengadaanFasilitasDb.findPengadaanFasilitasByUserUuid(uuid);
	}
}