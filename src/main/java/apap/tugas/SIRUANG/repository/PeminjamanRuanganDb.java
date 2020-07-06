package apap.tugas.SIRUANG.repository;

import java.util.Optional;

import apap.tugas.SIRUANG.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.SIRUANG.model.PeminjamanRuanganModel;

@Repository
public interface PeminjamanRuanganDb extends JpaRepository<PeminjamanRuanganModel, Long> {

	Optional<PeminjamanRuanganModel> findById(int id);
	UserModel findByUserPeminjam(String peminjam);
    
}