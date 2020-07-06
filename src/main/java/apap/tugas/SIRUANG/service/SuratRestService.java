package apap.tugas.SIRUANG.service;

import java.util.Optional;

import apap.tugas.SIRUANG.model.PeminjamanRuanganModel;
import apap.tugas.SIRUANG.model.UserModel;
import apap.tugas.SIRUANG.rest.SuratDetail;
import reactor.core.publisher.Mono;

public interface SuratRestService {
	
	Optional<PeminjamanRuanganModel> getPeminjamanRuanganById(int id);

	Mono<SuratDetail> getSuratDetail(int nomor_surat);
	
	Optional<UserModel> getUserByUuid(String uuid);
}
