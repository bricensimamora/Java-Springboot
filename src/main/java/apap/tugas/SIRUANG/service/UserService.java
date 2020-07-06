package apap.tugas.SIRUANG.service;

import java.text.ParseException;
import java.time.LocalDate;

import apap.tugas.SIRUANG.model.UserModel;


public interface UserService {

    UserModel addUser(UserModel user);
    public String encrypt(String pasword);
    public UserModel getUserByUsername(String username);
    public boolean passwordValidation(String password);
    boolean checkUsername(String username);
    
    public String generateNIG(UserModel user, LocalDate tanggalLahir) throws ParseException;
    public String generateNIS(UserModel user, LocalDate tanggalLahir) throws ParseException;
    public String generateNIP(UserModel user, LocalDate tanggalLahir) throws ParseException;

    public UserModel getUserCurrentLoggedIn();
	UserModel getByUuid(String uuid);
	UserModel getSIKoperasiUser(String username);
}

