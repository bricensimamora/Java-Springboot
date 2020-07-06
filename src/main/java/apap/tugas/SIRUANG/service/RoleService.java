package apap.tugas.SIRUANG.service;

import apap.tugas.SIRUANG.model.RoleModel;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleModel> findAll();

    //Method untuk mendapatkan semua data Role yang tersimpan
    List<RoleModel> getRoleList();
//
//    Optional<RoleModel> getRoleByName(String nama);

    //Method untuk mendapatkan data sebuah Role berdasarkan idRole
    RoleModel getRoleByIdRole(Long idRole);

}
