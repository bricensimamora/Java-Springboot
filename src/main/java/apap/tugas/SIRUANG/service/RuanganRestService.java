package apap.tugas.SIRUANG.service;

import java.util.Optional;

import apap.tugas.SIRUANG.model.RuanganModel;

public interface RuanganRestService {
	RuanganModel getByNama(String nama);
}