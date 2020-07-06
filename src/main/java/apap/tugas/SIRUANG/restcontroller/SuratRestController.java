package apap.tugas.SIRUANG.restcontroller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import apap.tugas.SIRUANG.model.PeminjamanRuanganModel;
import apap.tugas.SIRUANG.model.RoleModel;
import apap.tugas.SIRUANG.model.RuanganModel;
import apap.tugas.SIRUANG.model.UserModel;
import apap.tugas.SIRUANG.repository.PeminjamanRuanganDb;
import apap.tugas.SIRUANG.repository.UserDb;
import apap.tugas.SIRUANG.rest.SuratDetail;
import apap.tugas.SIRUANG.service.RoleServiceImpl;
import apap.tugas.SIRUANG.service.SuratRestService;
import apap.tugas.SIRUANG.service.UserServiceImpl;
import reactor.core.publisher.Mono;

@Controller
@SessionAttributes("peminjamanRuangan")
public class SuratRestController {
	@Autowired
    SuratRestService suratRestService;
	
	@Autowired 
	UserServiceImpl userService;
	
	@Autowired 
	PeminjamanRuanganDb peminjamanRuanganDb;
	
	@Autowired 
	UserDb userDb;
	
	
	@Autowired
	RoleServiceImpl roleService;
	
	@PostMapping("/surat/{id}")
	public String suratRedirect(
			@RequestParam("nomor_surat") String nomorSurat, @PathVariable int id, Model model
	) {
		model.addAttribute("peminjamanRuangan", suratRestService.getPeminjamanRuanganById(id).get());
		
		return "redirect:/surat/" + nomorSurat;
	}
	
	@GetMapping(value="/surat/{nomor_surat}")
	//@ResponseBody
	private String getSuratDetail(@PathVariable int nomor_surat, Model model, @ModelAttribute("peminjamanRuangan") PeminjamanRuanganModel peminjamanRuangan) {
		RoleModel roleModel = roleService.getRoleByIdRole(Long.valueOf(2));
		System.out.print(roleModel.getNama());
		Mono<SuratDetail> hasil = suratRestService.getSuratDetail(nomor_surat);	
		SuratDetail suratDetail = hasil.block();
		LinkedHashMap<String, String> results = (LinkedHashMap<String, String>) suratDetail.getResult();
		String keterangan = results.get("keterangan");
		String jenisSurat = results.get("jenisSurat");
		String status = results.get("status");
		String uuidUser = results.get("uuidUser");
		UserModel addUserPenyetuju = new UserModel();
		addUserPenyetuju.setUuid(uuidUser);
		addUserPenyetuju.setUsername("AdminTU");
		addUserPenyetuju.setPassword("AdminTU");
		addUserPenyetuju.setRole(roleModel);
		
		userService.addUser(addUserPenyetuju);

		
		if(status.equalsIgnoreCase("Disetujui")||status.equalsIgnoreCase("Diproses")||status.equalsIgnoreCase("Selesai")) {
			peminjamanRuangan.setIs_disetujui(true);
		}
		if(!uuidUser.isEmpty()) {
			peminjamanRuangan.setUser_penyetuju(addUserPenyetuju);
			addUserPenyetuju.setListPeminjamanRuanganUserPeminjam(new ArrayList<>());
		}
		peminjamanRuanganDb.save(peminjamanRuangan);
		model.addAttribute("keterangan", keterangan);
		model.addAttribute("jenisSurat", jenisSurat);
		model.addAttribute("status", status);
		model.addAttribute("uuidUser", uuidUser);
		return "detailSurat";
	}		
}
