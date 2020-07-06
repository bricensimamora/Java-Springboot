package apap.tugas.SIRUANG.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugas.SIRUANG.model.RuanganModel;
import apap.tugas.SIRUANG.model.UserModel;
import apap.tugas.SIRUANG.repository.RuanganDb;

@Service
@Transactional
public class RuanganRestServiceImpl implements RuanganRestService {
    @Autowired
    private RuanganDb ruangaDb;

	@Override
	public RuanganModel getByNama(String nama) {
		return ruangaDb.findByNama(nama);
	}
}