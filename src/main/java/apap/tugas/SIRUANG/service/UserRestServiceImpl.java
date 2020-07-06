package apap.tugas.SIRUANG.service;

import java.util.Optional;

import javax.transaction.Transactional;

import apap.tugas.SIRUANG.rest.*;
import reactor.core.publisher.Mono;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;

import apap.tugas.SIRUANG.model.UserModel;
import apap.tugas.SIRUANG.repository.UserDb;
import apap.tugas.SIRUANG.rest.Setting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService {
    private final WebClient webClient;
    private final WebClient webClient1;

    @Autowired
    private UserDb userDb;

    @Autowired
    private UserService userService;

    @Override
    public UserModel getUserById(String id) {
        return userDb.findByUuid(id).get();
    }
    
    @Override
    public UserModel createUser(UserModel user) {
        return userDb.save(user);
    }

    // Consumer Service
    public UserRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.userUrl).build();
        this.webClient1 = webClientBuilder.baseUrl(Setting.sivitasUrl).build();
    }

    @Override
    public Mono<SiswaDetailResponse> addSiswa(UserModel user, SiswaDetail siswa) throws JSONException {
        JSONObject data= new JSONObject();
        data.put("idUser", user.getUuid());
        data.put("nis", siswa.getNis());
        data.put("nama",siswa.getNama());
        data.put("tempatLahir", siswa.getTempatLahir());
        data.put("tanggalLahir", siswa.getTanggalLahir());
        data.put("alamat",siswa.getAlamat());
        data.put("telepon",siswa.getTelepon());
        System.out.println(data);
        return this.webClient1.post()
                .uri("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(data.toString())
                .retrieve()
                .bodyToMono(SiswaDetailResponse.class);
    }

    @Override
    public Mono<GuruDetailResponse> addGuru(UserModel user, GuruDetail guru) throws JSONException {
        JSONObject data= new JSONObject();
        data.put("idUser", user.getUuid());
        data.put("nig", guru.getNig());
        data.put("nama",guru.getNama());
        data.put("tempatLahir", guru.getTempatLahir());
        data.put("tanggalLahir", guru.getTanggalLahir());
        data.put("alamat",guru.getAlamat());
        data.put("telepon",guru.getTelepon());
        return this.webClient1.post()
                .uri("/api/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(data.toString())
                .retrieve()
                .bodyToMono(GuruDetailResponse.class);
    }

    @Override
    public Mono<PegawaiDetailResponse> addPegawai(UserModel user, PegawaiDetail pegawai) throws JSONException {
        JSONObject data= new JSONObject();
        data.put("idUser", user.getUuid());
        data.put("nip", pegawai.getNip());
        data.put("nama",pegawai.getNama());
        data.put("tempatLahir", pegawai.getTempatLahir());
        data.put("tanggalLahir", pegawai.getTanggalLahir());
        data.put("alamat",pegawai.getAlamat());
        data.put("telepon",pegawai.getTelepon());
        return this.webClient1.post()
                .uri("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(data.toString())
                .retrieve()
                .bodyToMono(PegawaiDetailResponse.class);
    }


    @Override
    public Mono<String> getGuru(String uuid) {
        return this.webClient.get().uri("/api/teachers/" + uuid).retrieve().bodyToMono(String.class);
    }

    @Override
    public Mono<String> getSiswa(String uuid) {
        return this.webClient.get().uri("/api/students/" + uuid).retrieve().bodyToMono(String.class);
    }

    @Override
    public Mono<String> getPegawai(String uuid) {
        return this.webClient.get().uri("/api/employees/" + uuid).retrieve().bodyToMono(String.class);
    }

    @Override
    public Optional<UserModel> getByUuid(String uuid) {
        return userDb.findByUuid(uuid);
    }

    @Override
    public UserModel getByUsername(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mono<BaseResponse> getUserData(String uuid) {
        UserModel user = userService.getByUuid(uuid);
        Long userRoleId = user.getRole().getIdRole();
        if (userRoleId == 2 | userRoleId == 5 | userRoleId == 6 | userRoleId == 7) {
            String uri = "/api/employees/" + uuid;
            return this.webClient1.get().uri(uri).retrieve().bodyToMono(BaseResponse.class);
        } else if (userRoleId == 3 || userRoleId == 1) {
            String uri = "/api/teachers/" + uuid;
            return this.webClient1.get().uri(uri).retrieve().bodyToMono(BaseResponse.class);
        } else {
            String uri = "/api/students/" + uuid;
            return this.webClient1.get().uri(uri).retrieve().bodyToMono(BaseResponse.class);
        }
    }
}