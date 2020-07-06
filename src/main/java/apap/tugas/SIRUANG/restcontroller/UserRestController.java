package apap.tugas.SIRUANG.restcontroller;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import apap.tugas.SIRUANG.rest.PegawaiDetail;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import apap.tugas.SIRUANG.model.UserModel;
import apap.tugas.SIRUANG.rest.GuruDetail;
import apap.tugas.SIRUANG.rest.SiswaDetail;
import apap.tugas.SIRUANG.service.UserRestService;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {
    @Autowired
    UserRestService userRestService;

    @PostMapping(value = "/adduser")
    private UserModel createUser(@Valid @RequestBody UserModel user, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            return userRestService.createUser(user);
        }
    }

    @PostMapping(value = "/user/tambah")
    private UserModel tambahUser(@Valid @RequestBody UserModel userModel, @Valid @RequestBody GuruDetail guruDetail,
                                 @Valid @RequestBody SiswaDetail siswaDetail, @Valid @RequestBody PegawaiDetail pegawaiDetail,
                                 BindingResult bindingResult) throws JSONException {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request Body has invalid type or missing field"
            );
        }
        else {
            userRestService.addGuru(userModel, guruDetail);
            userRestService.addSiswa(userModel, siswaDetail);
            userRestService.addPegawai(userModel, pegawaiDetail);
            return userRestService.createUser(userModel);
        }
    }
}


// @GetMapping(value = "/detail/{username}")
// @ResponseBody
// private Map<String, Object> detailUserByUsername(@PathVariable("username") String username) {
//     UserModel user = userRestService.getByUsername(username);
//     if (user == null) {
//         Map<String, Object> attributes = new HashMap<>();
//         // attributes.put("timestamp", "200");
//         attributes.put("status", "404");
//         attributes.put("error", "Not Found");
//         attributes.put("messages", "username in request body is invalid");
//         return attributes;
//     }
//     else {
//         Map<String, Object> attributes = new HashMap<>();
//         // Mono<String> dataPegawai = userRestService.getPegawai("53338ba8258241989aaec882270795c6");
//         attributes.put("status", "200");
//         attributes.put("messages", "success");
//         attributes.put("result", user);
//         attributes.put("role", user.getRole());
//         // attributes.put("pegawaiData", dataPegawai.block());
//         // System.out.println(dataPegawai);
//         return attributes;
//     }
// }

// @GetMapping(value = "/detail/pegawai/{uuid}")
// @ResponseBody
// private Map<String, Object> detailPegawaiUserByUuid(@PathVariable("uuid") String uuid) {
//     // UserModel user = userRestService.getByUsername(username);
//     UserModel user = userRestService.getByUuid(uuid).get();
//     if (user == null) {
//         Map<String, Object> attributes = new HashMap<>();
//         // attributes.put("timestamp", "200");
//         attributes.put("status", "404");
//         attributes.put("error", "Not Found");
//         attributes.put("messages", "username in request body is invalid");
//         return attributes;
//     }
//     else {
//         Map<String, Object> attributes = new HashMap<>();
//         Mono<String> dataPegawai = userRestService.getPegawai(uuid);
//         attributes.put("status", "200");
//         attributes.put("messages", "success");
//         attributes.put("result", user);
//         attributes.put("role", user.getRole());
//         attributes.put("pegawaiData", dataPegawai.block());
//         System.out.println(dataPegawai);
//         return attributes;
//     }
// }

// @GetMapping(value = "/detail/siswa/{uuid}")
// @ResponseBody
// private Map<String, Object> detailSiswaUserByUuid(@PathVariable("uuid") String uuid) {
//     // UserModel user = userRestService.getByUsername(username);
//     UserModel user = userRestService.getByUuid(uuid).get();
//     if (user == null) {
//         Map<String, Object> attributes = new HashMap<>();
//         // attributes.put("timestamp", "200");
//         attributes.put("status", "404");
//         attributes.put("error", "Not Found");
//         attributes.put("messages", "username in request body is invalid");
//         return attributes;
//     }
//     else {
//         Map<String, Object> attributes = new HashMap<>();
//         Mono<String> dataPegawai = userRestService.getSiswa(uuid);
//         attributes.put("status", "200");
//         attributes.put("messages", "success");
//         attributes.put("result", user);
//         attributes.put("role", user.getRole());
//         attributes.put("pegawaiData", dataPegawai.block());
//         System.out.println(dataPegawai);
//         return attributes;
//     }
// }

// @GetMapping(value = "/detail/guru/{uuid}")
// @ResponseBody
// private Map<String, Object> detailGuruUserByUuid(@PathVariable("uuid") String uuid) {
//     // UserModel user = userRestService.getByUsername(username);
//     UserModel user = userRestService.getByUuid(uuid).get();
//     if (user == null) {
//         Map<String, Object> attributes = new HashMap<>();
//         // attributes.put("timestamp", "200");
//         attributes.put("status", "404");
//         attributes.put("error", "Not Found");
//         attributes.put("messages", "username in request body is invalid");
//         return attributes;
//     }
//     else {
//         Map<String, Object> attributes = new HashMap<>();
//         Mono<String> dataPegawai = userRestService.getGuru(uuid);
//         attributes.put("status", "200");
//         attributes.put("messages", "success");
//         attributes.put("result", user);
//         attributes.put("role", user.getRole());
//         attributes.put("pegawaiData", dataPegawai.block());
//         System.out.println(dataPegawai);
//         return attributes;
//     }
// }

// @GetMapping(value = "/sivitas-detail/pegawai/{username}")
// @ResponseBody
// private Mono<String> recipeExcludeIngredients(@PathVariable("username") String username) {
//     UserModel user = userRestService.getByUsername(username);
//     if (user.getRole().getIdRole() == 2 || user.getRole().getIdRole() == 5 ||
//     user.getRole().getIdRole() == 6 || user.getRole().getIdRole() == 7) {
//         return userRestService.getPegawai("53338ba8258241989aaec882270795c6");
//     }
//     else if (user.getRole().getIdRole() == 1 || user.getRole().getIdRole() == 3) {//GURU
//         return userRestService.getGuru("4b9d357ef11548c698feacaa43a0da2c");
//     }
//     else { //SISWA
//         return userRestService.getSiswa("21c19e51e38f47b9930f9c6a3cee2a30");
//     }
// }
