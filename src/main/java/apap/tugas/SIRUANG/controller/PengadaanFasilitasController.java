package apap.tugas.SIRUANG.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import apap.tugas.SIRUANG.model.PengadaanFasilitasModel;
import apap.tugas.SIRUANG.model.UserModel;
import apap.tugas.SIRUANG.service.PengadaanFasilitasService;
import apap.tugas.SIRUANG.service.UserService;

@Controller
public class PengadaanFasilitasController{
    @Autowired
    @Qualifier("pengadaanFasilitasServiceImpl")
    private PengadaanFasilitasService pengadaanFasilitasService;
    
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;
    
	@RequestMapping(value="/pengadaan-fasilitas/tambah", method = RequestMethod.GET)
	public String addPengadaanFasilitasForm(Model model) {
		PengadaanFasilitasModel newPengadaanFasilitas = new PengadaanFasilitasModel();
		UserModel user = new UserModel();
		
		newPengadaanFasilitas.setUser(user);
		
		model.addAttribute("pengadaanFasilitas", newPengadaanFasilitas);
		model.addAttribute("userUuid", user.getUuid());
		
		return "form-pengadaan-fasilitas";
	}
	
	@RequestMapping(value="/pengadaan-fasilitas/tambah", method = RequestMethod.POST)
	public String addPengadaanFasilitasSubmit(PengadaanFasilitasModel pengadaanFasilitas, Model model) {
		UserModel user_peminjam = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		user_peminjam.setListPengadaanFasilitas(new ArrayList<>());
		
		pengadaanFasilitas.setUser(user_peminjam);
		if(pengadaanFasilitas.getUser().getRole().getNama().equals("Guru")) {
			pengadaanFasilitas.setStatus(1);
		}
		else {
			pengadaanFasilitas.setStatus(0);
		}
		pengadaanFasilitasService.addPengadaanFasilitas(pengadaanFasilitas);
		
		model.addAttribute("namaFasilitas", pengadaanFasilitas.getNama());
		model.addAttribute("userUuid", user_peminjam.getUuid());
		model.addAttribute("userRole", user_peminjam.getRole().getIdRole());
		
		return "pengadaan-fasilitas";
	}
	
	@RequestMapping(value="pengadaan-fasilitas/daftar", method = RequestMethod.GET)
	public String viewAllPengadaanFasilitas(Model model) {
		String userRole = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getRole().getNama();
		List<PengadaanFasilitasModel> listPengadaanFasilitas = new ArrayList<>();
		
		if(userRole.equalsIgnoreCase("GURU")) {
			listPengadaanFasilitas = pengadaanFasilitasService.getPengadaanFasilitasByUserUuidUser(userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getUuid());
		} else if(userRole.equalsIgnoreCase("ADMIN TU")) {
			listPengadaanFasilitas = pengadaanFasilitasService.getPengadaanFasilitasList();
		}
		
		model.addAttribute("listPengadaanFasilitas", listPengadaanFasilitas);
//		for(GrantedAuthority a : auth.getAuthorities()) {
//			if (a.getAuthority().equals("ROLE_Guru")) {
//				List<PengadaanFasilitasModel> listPengadaanFasilitasUser = pengadaanFasilitasService.getPengadaanFasilitasByUserUuidUser(user_peminjam.getUuid());
//				
//				model.addAttribute("listPengadaanFasilitas", listPengadaanFasilitasUser);
//				/*
//				 * for (PengadaanFasilitasModel listPengadaan : listPengadaanFasilitas) { if
//				 * (listPengadaan.getUser()==user_peminjam) {
//				 * listPengadaanFasilitasBaru.add(listPengadaan); } }
//				 */
//			} else {
//				model.addAttribute("listPengadaanFasilitas", listPengadaanFasilitas);
//			}
//		}	
		
		return "daftar-pengajuan-pengadaan-fasilitas";
	}
	
	@RequestMapping(value="/pengadaan-fasilitas/hapus/{id}", method = RequestMethod.POST)
	public String hapusPengadaanFasilitas(@PathVariable int id, Model model) {
		PengadaanFasilitasModel pengadaanFasilitas = pengadaanFasilitasService.getPengadaanFasilitasById(id).get();

		model.addAttribute("namaFasilitas", pengadaanFasilitas.getNama());
		
		pengadaanFasilitasService.deletePengadaanFasilitas(pengadaanFasilitas);
		
		
		return "redirect:/pengadaan-fasilitas/daftar";
	}
}