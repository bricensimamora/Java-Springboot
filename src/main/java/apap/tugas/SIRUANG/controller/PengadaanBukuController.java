package apap.tugas.SIRUANG.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import apap.tugas.SIRUANG.rest.PengadaanBukuDetail;
import apap.tugas.SIRUANG.rest.PengadaanBukuDetailResponse;
import apap.tugas.SIRUANG.service.PengadaanBukuRestService;

@Controller
public class PengadaanBukuController {
	@Autowired
	private PengadaanBukuRestService pengadaanBukuRestService;
	
	@RequestMapping(value="/pengadaan-buku")
	public String pengadaanBukuFormPage(Model model) {
		PengadaanBukuDetail buku = new PengadaanBukuDetail();
		
		model.addAttribute("buku", buku);
		
		return "form-pengadaan-buku";
	}
	
	@PostMapping("/pengadaan-buku")
	public String pengadaanBukuSubmit(PengadaanBukuDetail buku, Model model) {
		PengadaanBukuDetailResponse bukuResponse = pengadaanBukuRestService.addBuku(buku).block();
		
		model.addAttribute("response", bukuResponse);
		
		return "pengadaan-buku";
	}
}
