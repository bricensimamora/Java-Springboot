package apap.tugas.SIRUANG.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tugas.SIRUANG.model.PeminjamanRuanganModel;
import apap.tugas.SIRUANG.model.UserModel;
import apap.tugas.SIRUANG.repository.PeminjamanRuanganDb;
import apap.tugas.SIRUANG.repository.UserDb;
import apap.tugas.SIRUANG.rest.Setting;
import apap.tugas.SIRUANG.rest.SuratDetail;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class SuratRestServiceImpl implements SuratRestService {
	
	@Autowired
	PeminjamanRuanganDb peminjamanRuanganDb;
	
	@Autowired
	UserDb userDb;
	
	private final WebClient webClient;
	
	public SuratRestServiceImpl(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl(Setting.situUrl).build();
	}
	
	@Override
	public Mono<SuratDetail> getSuratDetail(int nomor_surat) {
		return this.webClient.get().uri("/api/v1/surat/" + nomor_surat)
				.retrieve().bodyToMono(SuratDetail.class);
	}

	@Override
	public Optional<PeminjamanRuanganModel> getPeminjamanRuanganById(int id) {
		return peminjamanRuanganDb.findById(id);
	}

	@Override
	public Optional<UserModel> getUserByUuid(String uuid) {
		return userDb.findByUuid(uuid);
	}
}
