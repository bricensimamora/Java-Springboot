package apap.tugas.SIRUANG.restcontroller;

import apap.tugas.SIRUANG.model.PeminjamanRuanganModel;
import apap.tugas.SIRUANG.rest.BaseResponse;
import apap.tugas.SIRUANG.rest.PeminjamanRuanganDetail;
import apap.tugas.SIRUANG.service.PeminjamanRuanganRestService;
import apap.tugas.SIRUANG.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PeminjamanRuanganRestController {
    @Autowired
    private PeminjamanRuanganRestService peminjamanRuanganRestService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/ruangan")
    private PeminjamanRuanganModel createPeminjamanRuangan(@Valid @RequestBody PeminjamanRuanganModel peminjamanRuangan, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        } else {
            peminjamanRuangan.setUser_peminjam(userService.getSIKoperasiUser("SI-KOPERASI"));
            return peminjamanRuanganRestService.createPeminjamanRuangan(peminjamanRuangan);
        }
    }
}
