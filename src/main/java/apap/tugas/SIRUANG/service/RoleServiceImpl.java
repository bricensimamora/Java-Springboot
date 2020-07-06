package apap.tugas.SIRUANG.service;

import apap.tugas.SIRUANG.model.RoleModel;
import apap.tugas.SIRUANG.repository.RoleDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDb roleDb;

    @Override
    public List<RoleModel> findAll(){
        return roleDb.findAll();
    }

    @Override
    public List<RoleModel> getRoleList() {
        return roleDb.findAllByOrderByNamaAsc();
    }

//    @Override
//    public Optional<RoleModel> getRoleByName(String nama) {
//        return roleDb.findByNama(nama);
//    }

    @Override
    public RoleModel getRoleByIdRole(Long idRole) {
        Optional<RoleModel> role = roleDb.findByIdRole(idRole);
        if (role.isPresent()){
            return role.get();
        }else {
            throw new NoSuchElementException();
        }
    }

}
