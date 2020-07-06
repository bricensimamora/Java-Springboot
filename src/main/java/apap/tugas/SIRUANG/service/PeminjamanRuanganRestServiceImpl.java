package apap.tugas.SIRUANG.service;

import apap.tugas.SIRUANG.model.PeminjamanRuanganModel;
import apap.tugas.SIRUANG.repository.PeminjamanRuanganDb;
import apap.tugas.SIRUANG.rest.PeminjamanRuanganDetail;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class PeminjamanRuanganRestServiceImpl implements PeminjamanRuanganRestService {
    private WebClient webClient;

    @Autowired
    PeminjamanRuanganDb peminjamanRuanganDb;

    @Override
    public PeminjamanRuanganModel createPeminjamanRuangan(PeminjamanRuanganModel peminjamanRuangan){
        peminjamanRuangan.setUser_peminjam(peminjamanRuanganDb.findByUserPeminjam("SI-KOPERASI"));
        return peminjamanRuanganDb.save(peminjamanRuangan);
    }
}
