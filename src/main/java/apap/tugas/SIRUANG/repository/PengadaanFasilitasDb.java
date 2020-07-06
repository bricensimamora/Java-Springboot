package apap.tugas.SIRUANG.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.SIRUANG.model.PengadaanFasilitasModel;

@Repository
public interface PengadaanFasilitasDb extends JpaRepository<PengadaanFasilitasModel, Long> {

	Optional<PengadaanFasilitasModel> findPengadaanFasilitasById(int id);
	
	List<PengadaanFasilitasModel> findPengadaanFasilitasByUserUuid(String uuid);
    
}