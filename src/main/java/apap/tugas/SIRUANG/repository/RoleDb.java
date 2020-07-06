package apap.tugas.SIRUANG.repository;

import apap.tugas.SIRUANG.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleDb extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByIdRole(Long idRole);
    //Optional<RoleModel> findByNama(String nama);
    List<RoleModel> findAllByOrderByNamaAsc();

}
