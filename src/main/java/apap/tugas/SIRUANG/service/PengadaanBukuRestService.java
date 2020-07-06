package apap.tugas.SIRUANG.service;

import apap.tugas.SIRUANG.rest.PengadaanBukuDetail;
import apap.tugas.SIRUANG.rest.PengadaanBukuDetailResponse;
import reactor.core.publisher.Mono;

public interface PengadaanBukuRestService {
	Mono<PengadaanBukuDetailResponse> addBuku(PengadaanBukuDetail buku);
}
