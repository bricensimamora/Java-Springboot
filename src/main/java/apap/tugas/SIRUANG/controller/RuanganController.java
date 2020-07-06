package apap.tugas.SIRUANG.controller;

import apap.tugas.SIRUANG.model.FasilitasModel;
import apap.tugas.SIRUANG.model.RuanganModel;
import apap.tugas.SIRUANG.service.FasilitasService;
import apap.tugas.SIRUANG.service.RuanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ruangan")
public class RuanganController {
    @Autowired
    RuanganService ruanganService;

    @Autowired
    FasilitasService fasilitasService;

    @RequestMapping(value="/view", method=RequestMethod.GET)
    public String viewRuangan(
        @RequestParam(value = "nama") String nama, Model model) {
            if (ruanganService.getRuanganByNama(nama) == null) {
                model.addAttribute("nama", nama);
                model.addAttribute("pageTitle", "Ruangan Tidak Ditemukan");
                return "ruangan-tidak-ditemukan";
            }
            RuanganModel ruangan = ruanganService.getRuanganByNama(nama);
            List<FasilitasModel> listFasilitas = ruangan.getListFasilitas();

            model.addAttribute("ruangan", ruangan);
            model.addAttribute("listFasilitas", listFasilitas);
            return "detail-ruangan";
    }

    @RequestMapping(value="/viewRuangan/{id}", method=RequestMethod.GET)
    public String viewRuangan(
        @PathVariable("id") int id, Model model) {
            if (ruanganService.getRuanganById(id) == null) {
                model.addAttribute("pageTitle", "Ruangan Tidak Ditemukan");
                return "ruangan-tidak-ditemukan";
            }
            RuanganModel ruangan = ruanganService.getRuanganById(id);
            List<FasilitasModel> listFasilitas = ruangan.getListFasilitas();
            
            model.addAttribute("ruangan", ruangan);
            model.addAttribute("listFasilitas", listFasilitas);
            return "detail-ruangan";
    }
}