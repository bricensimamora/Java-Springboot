package apap.tugas.SIRUANG.restcontroller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import apap.tugas.SIRUANG.model.RuanganModel;
import apap.tugas.SIRUANG.service.RuanganRestService;


@RestController
@RequestMapping("/api/v1")
public class RuanganRestController {
    
    @Autowired
    RuanganRestService ruanganRestService;

    @GetMapping(value = "ruangan/detail/{nama}")
    private Map<String, Object> detailRuangan(@PathVariable("nama") String nama) {
        RuanganModel ruanganModel = ruanganRestService.getByNama(nama);
        if (ruanganModel == null) {
            Map<String, Object> attributes = new HashMap<>();
            // attributes.put("timestamp", "200");
            attributes.put("status", "404");
            attributes.put("error", "Not Found");
            attributes.put("messages", "nama in request body is invalid");
            return attributes;
        }
        else {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("status", "200");
            attributes.put("messages", "success");
            attributes.put("result", ruanganModel.getListFasilitas());
            return attributes;
        }
    }
}