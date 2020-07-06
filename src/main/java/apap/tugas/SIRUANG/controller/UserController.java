package apap.tugas.SIRUANG.controller;

import apap.tugas.SIRUANG.model.RoleModel;
import apap.tugas.SIRUANG.model.UserModel;
import apap.tugas.SIRUANG.rest.*;
import apap.tugas.SIRUANG.service.RoleService;
import apap.tugas.SIRUANG.service.UserRestService;
import apap.tugas.SIRUANG.service.RuanganService;
import apap.tugas.SIRUANG.service.UserService;
import reactor.core.publisher.Mono;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @Qualifier("roleServiceImpl")
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRestService userRestService;

    // @RequestMapping(value = "/add", method = RequestMethod.GET)
    // private String addUserFormPage(Model model){
    //     UserModel newUser = new UserModel();
    //     model.addAttribute("user", newUser);
    //     model.addAttribute("userRole", roleService.findAll());
    //     return "form-add-user";
    // }

    // @RequestMapping(value = "/add", method = RequestMethod.POST)
    // private String addUserSubmit(@ModelAttribute UserModel user, Model model){
    //     userService.addUser(user);
    //     model.addAttribute("userName", user.getUsername());
    //     return "add-user";

    @Autowired
    private RuanganService ruanganService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserModel user, Model model){

        // if(!userService.passwordValidation(user.getPassword())){
        //     model.addAttribute("listRole", roleService.findAll());
        //     model.addAttribute("eror", "\nPassword harus mengandung huruf, angka, dan minimal terdiri dari 8 karakter!");
        //     model.addAttribute("role", "admin");
        //     return "home";
        // }else {
            userService.addUser(user);
            userService.addUser(user);

        // }

        // return "redirect:/";
        model.addAttribute("listRuangan", ruanganService.getRuanganList());
        model.addAttribute("listRole", roleService.findAll());
        return "home";
    }

    @RequestMapping(value = "/user/tambah", method = RequestMethod.GET)
    public String addAnggotaFormPage(Model model) throws JSONException {
        UserModel newUser = new UserModel();
        GuruDetail guruDetail = new GuruDetail();
        model.addAttribute("listRole", roleService.findAll());
        model.addAttribute("user", newUser);
        model.addAttribute("guruDetail", guruDetail);
        return "form-tambah-user";
    }

    @RequestMapping(value = "/user/tambah", method = RequestMethod.POST)
    public String addUserFormSubmit(@ModelAttribute UserModel userModel, @ModelAttribute GuruDetail guruDetail,
     Model model) throws ParseException, JSONException {
        // if (userService.checkUsername(userModel.getUsername())) {
            if(userModel.getRole().getNama().equals("Guru")) {
                UserModel newUser = userService.addUser(userModel);
                String nig = userService.generateNIG(newUser, LocalDate.parse(guruDetail.getTanggalLahir()));
                guruDetail.setNig(nig);
                
                if (userRestService.addGuru(newUser, guruDetail).block().getStatus().equals("200")) {
                    model.addAttribute("user", userModel);
                    return "tambah-user";
                };
            }
            else if(userModel.getRole().getNama().equals("Siswa")) {
                UserModel newUser = userService.addUser(userModel);
                SiswaDetail siswaDetail = new SiswaDetail();
                siswaDetail.setAlamat(guruDetail.getAlamat());
                siswaDetail.setNama(guruDetail.getNama());
                siswaDetail.setTanggalLahir(guruDetail.getTanggalLahir());
                siswaDetail.setTelepon(guruDetail.getTelepon());
                siswaDetail.setTempatLahir(guruDetail.getTempatLahir());
                String nis = userService.generateNIS(newUser, LocalDate.parse(guruDetail.getTanggalLahir()));
                siswaDetail.setNis(nis);
                if (userRestService.addSiswa(newUser, siswaDetail).block().getStatus().equals("200")) {
                    model.addAttribute("user", userModel);
                    return "tambah-user";
                };
            }
            else if(userModel.getRole().getNama().equals("Admin TU")) {
                UserModel newUser = userService.addUser(userModel);
                PegawaiDetail pegawaiDetail = new PegawaiDetail();
                pegawaiDetail.setAlamat(guruDetail.getAlamat());
                pegawaiDetail.setNama(guruDetail.getNama());
                pegawaiDetail.setTanggalLahir(guruDetail.getTanggalLahir());
                pegawaiDetail.setTelepon(guruDetail.getTelepon());
                pegawaiDetail.setTempatLahir(guruDetail.getTempatLahir());
                String nip = userService.generateNIP(newUser, LocalDate.parse(guruDetail.getTanggalLahir()));
                pegawaiDetail.setNip(nip);
                if (userRestService.addPegawai(newUser, pegawaiDetail).block().getStatus().equals("200")) {
                    model.addAttribute("user", userModel);
                    return "tambah-user";
                }
                ;
            }
        // }
        String namaUser = userModel.getUsername();
        String roleUser = userModel.getRole().getNama();
        model.addAttribute("namaUser", namaUser);
        model.addAttribute("roleUser", roleUser);
        return "tambah-user-gagal";
    }

    @RequestMapping(value = "/display/user", method = RequestMethod.GET)
    public String displayUser() {
        return "display-user";
    }

    // =====================

    @RequestMapping("/profile")
        public String getUserProfile (Model model) throws ParseException {
            UserModel user = userService.getUserCurrentLoggedIn();
            
            model.addAttribute("role" ,user.getRole());
            model.addAttribute("user1", user);

            if (user.getRole().getIdRole() < 5) {
                UserSivitasModel userModel = new UserSivitasModel();
                Mono<BaseResponse> respon = userRestService.getUserData(user.getUuid());
                LinkedHashMap<String, String> data = (LinkedHashMap<String, String>) Objects.requireNonNull(respon.block()).getResult();

                userModel.setAlamat(data.get("alamat"));
                userModel.setNama(data.get("nama"));
                String tanggal = data.get("tanggalLahir");
                userModel.setTanggalLahir(new SimpleDateFormat("yyyy-MM-dd").parse(tanggal));
                userModel.setTelepon(data.get("telepon"));
                userModel.setTempatLahir(data.get("tempatLahir"));
                model.addAttribute("idUser", data.get("idUser"));

                if (user.getRole().getIdRole() == 4) {
                    userModel.setNi(data.get("nis"));
                } else if (user.getRole().getIdRole() == 1 | user.getRole().getIdRole() == 3) {
                    userModel.setNi(data.get("nig"));
                } else {
                    userModel.setNi(data.get("nip"));
                }
                model.addAttribute("user", userModel);
            }
            return "profile";
        }
}
