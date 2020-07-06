package apap.tugas.SIRUANG.service;

import java.util.List;
import java.util.Optional;

import apap.tugas.SIRUANG.model.RuanganModel;
import apap.tugas.SIRUANG.model.FasilitasModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.SIRUANG.model.RuanganModel;
import apap.tugas.SIRUANG.repository.RuanganDb;;

@Service
public class RuanganServiceImpl implements RuanganService {

    @Autowired
    private RuanganDb ruanganDb;


	@Override
	public RuanganModel getRuanganById(int id) {
		return ruanganDb.findById(id);
	}

    @Override
    public void addRuangan(RuanganModel ruangan) {
        ruanganDb.save(ruangan);
    }

    @Override
    public List<RuanganModel> getRuanganList() {
        return ruanganDb.findAll();
    }

    @Override
    public RuanganModel getRuanganByNama(String nama) {
        return ruanganDb.findByNama(nama);
    }

    @Override
    public void removeRuangan(RuanganModel ruangan) {
        ruanganDb.delete(ruangan);
    }

    @Override
    public void updateRuangan(RuanganModel ruanganModel) {
        RuanganModel targetRuangan = ruanganDb.findById(ruanganModel.getId());
        targetRuangan.setListFasilitas(ruanganModel.getListFasilitas());
        ruanganDb.save(targetRuangan);
    }

    @Override
    public FasilitasModel sameFasilitas(RuanganModel ruanganModel, FasilitasModel fasilitasModel) {
        for (FasilitasModel fasilitas: ruanganModel.getListFasilitas()) {
            if(fasilitas.getNama().equalsIgnoreCase(fasilitasModel.getNama())){
                return fasilitas;
            }
        }
        return null;
    }

}