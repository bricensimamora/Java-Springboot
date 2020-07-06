package apap.tugas.SIRUANG.service;

import apap.tugas.SIRUANG.model.FasilitasModel;

public interface FasilitasService {
    void addFasilitas(FasilitasModel fasilitas);
    void deleteFasilitas(FasilitasModel fasilitas);
    FasilitasModel getFasilitasById(int id);
    FasilitasModel ubahJumlahFasilitas(FasilitasModel fasilitas);
}