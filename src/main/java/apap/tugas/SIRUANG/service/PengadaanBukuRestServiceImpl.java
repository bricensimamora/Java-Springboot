package apap.tugas.SIRUANG.service;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tugas.SIRUANG.rest.PengadaanBukuDetail;
import apap.tugas.SIRUANG.rest.PengadaanBukuDetailResponse;
import apap.tugas.SIRUANG.rest.Setting;
import reactor.core.publisher.Mono;

@Service
public class PengadaanBukuRestServiceImpl implements PengadaanBukuRestService {
	private final WebClient webClient;
	
	public PengadaanBukuRestServiceImpl(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl(Setting.pengadaanBukuUrl).build();
	}
	
	@Override
	public Mono<PengadaanBukuDetailResponse> addBuku(PengadaanBukuDetail buku) {
		MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
		data.add("judul", buku.getJudul());
		data.add("pengarang", buku.getPengarang());
		data.add("penerbit", buku.getPenerbit());
		data.add("pengarang", buku.getPengarang());
		data.add("jumlah", String.valueOf(buku.getJumlah()));
		data.add("harga", String.valueOf(buku.getHarga()));
		return this.webClient.post().uri("/api/add/pengadaan")
				.syncBody(data)
				.retrieve()
				.bodyToMono(PengadaanBukuDetailResponse.class);
	}

}
