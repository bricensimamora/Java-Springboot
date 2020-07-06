package apap.tugas.SIRUANG.service;

import java.util.List;
import java.util.Optional;

import apap.tugas.SIRUANG.model.FasilitasModel;
import apap.tugas.SIRUANG.model.RuanganModel;;

public interface RuanganService {

	RuanganModel getRuanganById (int id);

    void addRuangan(RuanganModel ruangan);

    List<RuanganModel> getRuanganList();

    RuanganModel getRuanganByNama(String nama);

    void removeRuangan(RuanganModel ruangan);

    void updateRuangan(RuanganModel ruanganModel);

    FasilitasModel sameFasilitas(RuanganModel ruanganModel, FasilitasModel fasilitasModel);
}