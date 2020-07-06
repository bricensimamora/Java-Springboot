package apap.tugas.SIRUANG.service;

import apap.tugas.SIRUANG.model.UserModel;
import apap.tugas.SIRUANG.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDb userDb;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String pasword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode((pasword));
        return hashedPassword;
    }

    @Override
    public UserModel getUserByUsername(String username) {
        return userDb.findByUsername(username);
    }

    @Override
    public boolean passwordValidation(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,}$");
    }

    @Override
    public boolean checkUsername(String username) {
        for (UserModel user : userDb.findAll()) {
            if (username.equals(user.getUsername())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String generateNIG(UserModel user, LocalDate tanggalLahir) throws ParseException {
        String nig= "";

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(tanggalLahir.toString());
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String tanggal = dateFormat.format(date);

        Random randomString = new Random();
        String a = String.valueOf((char) (randomString.nextInt(26) + 'a'));
        String b = String.valueOf((char) (randomString.nextInt(26) + 'a'));
        String twoRandomCase= (a+b).toUpperCase();

        Random randomInt = new Random();
        int intRandom = randomInt.nextInt(999);
        String stringRandomNumber= String.format("%03d", intRandom);

        nig = "G" + tanggal + twoRandomCase + stringRandomNumber + user.getUuid();
        return nig;
    }

    @Override
    public String generateNIS(UserModel user, LocalDate tanggalLahir) throws ParseException {
        String nis= "";
        
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(tanggalLahir.toString());
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String tanggal = dateFormat.format(date);

        Random randomString = new Random();
        String a = String.valueOf((char) (randomString.nextInt(26) + 'a'));
        String b = String.valueOf((char) (randomString.nextInt(26) + 'a'));
        String twoRandomCase= (a+b).toUpperCase();

        Random randomInt = new Random();
        int intRandom = randomInt.nextInt(999);
        String stringRandomNumber= String.format("%03d", intRandom);

        nis = "S" + tanggal + twoRandomCase + stringRandomNumber + user.getUuid();
        return nis;
    }

    @Override
    public String generateNIP(UserModel user, LocalDate tanggalLahir) throws ParseException {
        String nip= "";

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(tanggalLahir.toString());
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String tanggal = dateFormat.format(date);

        Random randomString = new Random();
        String a = String.valueOf((char) (randomString.nextInt(26) + 'a'));
        String b = String.valueOf((char) (randomString.nextInt(26) + 'a'));
        String twoRandomCase= (a+b).toUpperCase();

        Random randomInt = new Random();
        int intRandom = randomInt.nextInt(999);
        String stringRandomNumber= String.format("%03d", intRandom);

        nip = "P" + tanggal + twoRandomCase + stringRandomNumber + user.getUuid();
        return nip;
    }

    @Override
    public UserModel getUserCurrentLoggedIn() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";

        if (principal instanceof UserDetails) { 
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
    return userDb.findByUsername(username);
    }

    @Override
    public UserModel getByUuid(String uuid) {
        return userDb.findByUuid(uuid).get();
    }

    @Override
    public UserModel getSIKoperasiUser(String username) {
        return userDb.findByUsername(username);
    }
}
