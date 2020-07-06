package apap.tugas.SIRUANG.controller;

import apap.tugas.SIRUANG.service.RoleService;
import apap.tugas.SIRUANG.service.RuanganService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @Autowired
    RoleService roleService;

    @Autowired
    RuanganService ruanganService;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("listRuangan", ruanganService.getRuanganList());
        model.addAttribute("listRole", roleService.findAll());
        return "home";
    }

    @RequestMapping("/home-dev")
    public String homeDev(Model model){
        model.addAttribute("listRuangan", ruanganService.getRuanganList());
        return "home-dev";
    }

    @RequestMapping("/login-dev")
    public String login(){
        return "login-dev";
    }
}
