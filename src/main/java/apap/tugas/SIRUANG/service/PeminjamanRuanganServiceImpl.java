package apap.tugas.SIRUANG.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.SIRUANG.model.PeminjamanRuanganModel;
import apap.tugas.SIRUANG.repository.PeminjamanRuanganDb;


@Service
@Transactional
public class PeminjamanRuanganServiceImpl implements PeminjamanRuanganService {

    @Autowired
    private PeminjamanRuanganDb peminjamanRuanganDb;

	@Override
	public void addPeminjamanRuangan(PeminjamanRuanganModel peminjamanRuangan) {
		peminjamanRuanganDb.save(peminjamanRuangan);
	}


	@Override
	public List<PeminjamanRuanganModel> getPeminjamanRuanganList() {
		return peminjamanRuanganDb.findAll();
	}

	@Override
	public Optional<PeminjamanRuanganModel> getPeminjamanRuanganById(int id) {
		return peminjamanRuanganDb.findById(id);
	}


	@Override
	public void ubahPersetujuanTerima(PeminjamanRuanganModel peminjamanRuanganModel) {
		boolean peminjamanDisetujui = true;
		peminjamanRuanganModel.setIs_disetujui(peminjamanDisetujui);
		peminjamanRuanganDb.save(peminjamanRuanganModel);
		
	}

	@Override
	public void ubahPersetujuanTolak(PeminjamanRuanganModel peminjamanRuanganModel) {
		boolean peminjamanDitolak = false;
		peminjamanRuanganModel.setIs_disetujui(peminjamanDitolak);
		peminjamanRuanganDb.save(peminjamanRuanganModel);		
	}
}