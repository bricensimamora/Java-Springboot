package apap.tugas.SIRUANG.repository;

import apap.tugas.SIRUANG.model.UserModel;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDb extends JpaRepository<UserModel, Long> {

    UserModel findByUsername(String  username);

	Optional<UserModel> findByUuid(String uuid);
	
	List<UserModel> findAll();
}

