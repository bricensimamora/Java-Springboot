package apap.tugas.SIRUANG.service;

import apap.tugas.SIRUANG.model.FasilitasModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.SIRUANG.repository.FasilitasDb;;


@Service
public class FasilitasServiceImpl implements FasilitasService {

    @Autowired
    private FasilitasDb fasilitasDb;

    public void addFasilitas(FasilitasModel fasilitas){
        fasilitasDb.save(fasilitas);
    }

    @Override
    public void deleteFasilitas(FasilitasModel fasilitas) {
        fasilitasDb.delete(fasilitas);
    }

    @Override
    public FasilitasModel getFasilitasById(int id) {
        return fasilitasDb.findById(id);
    }

    @Override
    public FasilitasModel ubahJumlahFasilitas(FasilitasModel fasilitas) {
        FasilitasModel targetFasilitas = fasilitasDb.findById(fasilitas.getId());
        targetFasilitas.setJumlah(fasilitas.getJumlah());
        fasilitasDb.save(targetFasilitas);
        return targetFasilitas;
    }
}