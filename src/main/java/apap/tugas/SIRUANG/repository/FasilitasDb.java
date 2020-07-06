package apap.tugas.SIRUANG.repository;

import apap.tugas.SIRUANG.model.RuanganModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.SIRUANG.model.FasilitasModel;

@Repository
public interface FasilitasDb extends JpaRepository<FasilitasModel, Long> {
    FasilitasModel findByRuangan(RuanganModel ruangan);
    FasilitasModel findById(int id);
}