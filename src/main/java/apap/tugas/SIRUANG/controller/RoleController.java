package apap.tugas.SIRUANG.controller;

import apap.tugas.SIRUANG.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
