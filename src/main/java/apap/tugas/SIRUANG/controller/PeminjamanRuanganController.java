package apap.tugas.SIRUANG.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugas.SIRUANG.model.PeminjamanRuanganModel;
import apap.tugas.SIRUANG.model.RuanganModel;
import apap.tugas.SIRUANG.model.UserModel;
import apap.tugas.SIRUANG.repository.PeminjamanRuanganDb;
import apap.tugas.SIRUANG.service.PeminjamanRuanganService;
import apap.tugas.SIRUANG.service.RuanganService;
import apap.tugas.SIRUANG.service.UserService;

@Controller
@RequestMapping("/peminjaman-ruangan")
public class PeminjamanRuanganController {
	@Autowired
	@Qualifier("peminjamanRuanganServiceImpl")
	PeminjamanRuanganService peminjamanRuanganService;
	
	@Autowired 
	@Qualifier("ruanganServiceImpl")
	RuanganService ruanganService;
	
	@Autowired 
	@Qualifier("userServiceImpl")
	UserService userService;
	
	@Autowired
	PeminjamanRuanganDb peminjamanRuanganDb;
	
	@RequestMapping(value="/daftar", method=RequestMethod.GET)
	public String viewPeminjamanRuanganList(Model model) {
		List<PeminjamanRuanganModel> listPeminjamanRuangan = peminjamanRuanganService.getPeminjamanRuanganList();
		model.addAttribute("listPeminjamanRuangan", listPeminjamanRuangan);
		return "daftar-peminjaman-ruangan";
	}
	
	@RequestMapping(value="/tambah/{id}", method=RequestMethod.GET)
	public String addPeminjamanRuanganPage(@PathVariable int id, Model model) {
		RuanganModel ruangan = ruanganService.getRuanganById(id);
		PeminjamanRuanganModel peminjamanRuangan = new PeminjamanRuanganModel();
		
		
		model.addAttribute("peminjamanRuangan", peminjamanRuangan);
		model.addAttribute("ruangan", ruangan);
		return "form-tambah-peminjaman-ruangan";
	}
	
	@RequestMapping(value="/tambah/{id}", method = RequestMethod.POST)
	public String addPeminjamanRuanganSubmit(@PathVariable int id,Authentication auth, @ModelAttribute PeminjamanRuanganModel peminjamanRuangan, Model model) {
		UserModel user_peminjam = userService.getUserByUsername(auth.getName());
		RuanganModel ruangan = ruanganService.getRuanganById(id);
		peminjamanRuangan.setRuangan(ruangan);
		peminjamanRuangan.setUser_peminjam(user_peminjam);
		user_peminjam.setListPeminjamanRuanganUserPeminjam(new ArrayList<>());
		boolean checkAvailability = true;
		boolean checkKapasitas = false;
		String nama1 = peminjamanRuangan.getRuangan().getNama();
		Date tanggalMulai1 = peminjamanRuangan.getTanggal_mulai();
		Date tanggalSelesai1 = peminjamanRuangan.getTanggal_selesai();
		String waktuMulai1 = peminjamanRuangan.getWaktu_mulai();
		String waktuSelesai1 = peminjamanRuangan.getWaktu_selesai();
		
		List<PeminjamanRuanganModel> peminjamanDB = peminjamanRuanganDb.findAll();
		for (PeminjamanRuanganModel peminjamanRuanganDb : peminjamanDB) {
			String nama = peminjamanRuanganDb.getRuangan().getNama();
			Date tanggalMulai = peminjamanRuanganDb.getTanggal_mulai();
			Date tanggalSelesai = peminjamanRuanganDb.getTanggal_selesai();
			String waktuMulai = peminjamanRuanganDb.getWaktu_mulai();
			String waktuSelesai = peminjamanRuanganDb.getWaktu_selesai();
			
			boolean tanggal_mulai = !(tanggalMulai1.after(tanggalMulai)&&tanggalMulai1.before(tanggalSelesai));
			boolean tanggal_selesai = !(tanggalSelesai1.after(tanggalMulai)&&tanggalMulai1.before(tanggalSelesai));
			boolean waktu_mulai = !(waktuMulai1.compareTo(waktuMulai)>=0 && waktuMulai1.compareTo(waktuSelesai)<=0);
			boolean waktu_selesai = !(waktuSelesai1.compareTo(waktuMulai)>=0 && waktuSelesai1.compareTo(waktuSelesai)<=0);
			
			if(nama1.equals(nama)) {
				if(!(tanggal_mulai && tanggal_selesai)) {
					if(!(waktu_mulai && waktu_selesai)) {
						checkAvailability =  false;
					}
				}
			}
		}
			
		int kapasitas = peminjamanRuangan.getJumlah_peserta();
		if(kapasitas<=ruangan.getKapasitas()) {
			checkKapasitas = true;
		}
		if(checkAvailability==true) {
			if(checkKapasitas==true) {
				peminjamanRuanganService.addPeminjamanRuangan(peminjamanRuangan);
				model.addAttribute("tujuan",peminjamanRuangan.getTujuan());
				return "tambah-peminjaman-ruangan";
			}
			model.addAttribute("alasan","Kapasaitas Ruangan Tidak Mencukupi! Sila Pilih Ruangan Lain atau Kurangi Kapasitas.");
			return"tambah-peminjaman-ruangan-gagal";
		}
		model.addAttribute("alasan","Ruangan Dalam Proses Peminjaman, Sila Cek Daftar dan Pilih Waktu Lain atau Ruangan Lain.");
		return "tambah-peminjaman-ruangan-gagal";
	}
	
	@RequestMapping(value="/ubah-persetujuan-terima/{id}", method = RequestMethod.GET)
	public String changePersetujuanTerima(@PathVariable int id, Model model, Authentication auth) {		
		PeminjamanRuanganModel peminjamanRuangan = peminjamanRuanganService.getPeminjamanRuanganById(id).get();
		UserModel user_penyetuju = userService.getUserByUsername(auth.getName());
		peminjamanRuangan.setUser_penyetuju(user_penyetuju);
		user_penyetuju.setListPeminjamanRuanganUserPeminjam(new ArrayList<>());
		peminjamanRuanganService.ubahPersetujuanTerima(peminjamanRuangan);
		model.addAttribute("tujuan",peminjamanRuangan.getTujuan());
		return "redirect:/peminjaman-ruangan/daftar";
	}
	
	@RequestMapping(value="/ubah-persetujuan-tolak/{id}", method = RequestMethod.GET)
	public String changePersetujuanTolak(@PathVariable int id, Model model, Authentication auth) {
		PeminjamanRuanganModel peminjamanRuangan = peminjamanRuanganService.getPeminjamanRuanganById(id).get();
		UserModel user_penyetuju = userService.getUserByUsername(auth.getName());
		peminjamanRuangan.setUser_penyetuju(user_penyetuju);
		user_penyetuju.setListPeminjamanRuanganUserPeminjam(new ArrayList<>());
		peminjamanRuanganService.ubahPersetujuanTolak(peminjamanRuangan);
		model.addAttribute("tujuan",peminjamanRuangan.getTujuan());
		return "redirect:/peminjaman-ruangan/daftar";
	}

    
}