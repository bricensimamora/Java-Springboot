package apap.tugas.SIRUANG.controller;

import apap.tugas.SIRUANG.model.FasilitasModel;
import apap.tugas.SIRUANG.model.RuanganModel;
import apap.tugas.SIRUANG.service.RuanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import apap.tugas.SIRUANG.service.FasilitasService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FasilitasController{
    @Qualifier("fasilitasServiceImpl")
    @Autowired
    FasilitasService fasilitasService;

    @Autowired
    RuanganService ruanganService;

    @RequestMapping(value = "/fasilitas/tambah", method = RequestMethod.GET)
    public String tambahFasilitas(Model model){
        FasilitasModel fasilitas = new FasilitasModel();
        RuanganModel ruangan = new RuanganModel();
        fasilitas.setRuangan(ruangan);
        List<RuanganModel> listRuangan = ruanganService.getRuanganList();
        model.addAttribute("fasilitas", fasilitas);
        model.addAttribute("listRuangan", listRuangan);
        return "form-tambah-fasilitas";
    }

    @RequestMapping(value = "/fasilitas/tambah", method = RequestMethod.POST)
    public String tambahFasilitasSubmit(@ModelAttribute FasilitasModel fasilitas, Model model){
        RuanganModel ruangan = ruanganService.getRuanganById(fasilitas.getRuangan().getId());
        if (ruangan.getListFasilitas().size()==0 && ruangan.getKapasitas()>fasilitas.getJumlah()){
            fasilitasService.addFasilitas(fasilitas);
            ruangan.getListFasilitas().add(fasilitas);
            ruanganService.updateRuangan(ruangan);
            model.addAttribute("fasilitas", fasilitas);
            model.addAttribute("ruangan", ruangan.getNama());
            return "tambah-fasilitas";
        }
        if (ruanganService.sameFasilitas(ruangan,fasilitas) != null){
            FasilitasModel sameFasilitas = ruanganService.sameFasilitas(ruangan,fasilitas);
            sameFasilitas.setJumlah(sameFasilitas.getJumlah()+fasilitas.getJumlah());
            ruanganService.updateRuangan(ruangan);
        }
        else {
            fasilitasService.addFasilitas(fasilitas);
            ruangan.getListFasilitas().add(fasilitas);
            ruanganService.updateRuangan(ruangan);
        }
        model.addAttribute("fasilitas", fasilitas);
        model.addAttribute("ruangan", ruangan.getNama());
        System.out.println(fasilitas);
        System.out.println(fasilitas.getRuangan());
        return "tambah-fasilitas";
    }

    @RequestMapping("/fasilitas/delete/{id}")
    public String deleteFasilitas(@PathVariable(value = "id") int id, @ModelAttribute FasilitasModel fasilitas, Model model){
        FasilitasModel fasilitasDel = fasilitasService.getFasilitasById(id);
        model.addAttribute("ruangan", fasilitasDel.getRuangan());
        fasilitasService.deleteFasilitas(fasilitasDel);
        model.addAttribute("fasilitas", fasilitasDel);
        return "delete-fasilitas";
    }

    @RequestMapping(value = "fasilitas/ubah/{id}", method = RequestMethod.GET)
    public String ubahFasilitas(@PathVariable(value = "id") int id, Model model){
        FasilitasModel existingFasilitas = fasilitasService.getFasilitasById(id);
        model.addAttribute("fasilitas", existingFasilitas);
        model.addAttribute("ruangan", existingFasilitas.getRuangan());
        return "form-ubah-jumlah-fasilitas";
    }

    @RequestMapping(value = "/fasilitas/ubah/{id}", method = RequestMethod.POST)
    public String ubahFasilitasSubmit(@PathVariable(value = "id") int id, @ModelAttribute FasilitasModel fasilitas, Model model){
        FasilitasModel newFasilitas = fasilitasService.ubahJumlahFasilitas(fasilitas);
        model.addAttribute("fasilitas", newFasilitas);
        return "ubah-jumlah-fasilitas";
    }
}