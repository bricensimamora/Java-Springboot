package apap.tugas.SIRUANG.service;

import java.util.Optional;

import apap.tugas.SIRUANG.rest.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.server.ServerResponse;

import apap.tugas.SIRUANG.model.UserModel;
import reactor.core.publisher.Mono;
import org.json.JSONException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRestService {

	UserModel getByUsername(String username);

	UserModel getUserById(String uuid);
    UserModel createUser(UserModel user);

    Mono<SiswaDetailResponse> addSiswa(UserModel user, SiswaDetail siswa) throws JSONException;
    Mono<GuruDetailResponse> addGuru(UserModel user, GuruDetail guru) throws JSONException;
	Mono<PegawaiDetailResponse> addPegawai(UserModel user, PegawaiDetail pegawai) throws JSONException;


	Mono<String> getGuru(String uuid);

	Mono<String> getSiswa(String uuid);

	Mono<String> getPegawai(String string);

	Optional<UserModel> getByUuid(String uuid);

	Mono<BaseResponse> getUserData(String idUser);
}