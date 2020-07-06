package apap.tugas.SIRUANG.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.SIRUANG.model.RuanganModel;

import java.util.Optional;

@Repository
public interface RuanganDb extends JpaRepository<RuanganModel, Long> {



	RuanganModel findById(int id);


	RuanganModel findByNama(String nama);
	RuanganModel findRuanganModelById(int id);
}