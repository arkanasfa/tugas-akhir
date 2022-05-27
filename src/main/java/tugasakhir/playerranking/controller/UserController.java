package tugasakhir.playerranking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tugasakhir.playerranking.model.RoleModel;
import tugasakhir.playerranking.model.UserModel;
import tugasakhir.playerranking.service.RoleService;
import tugasakhir.playerranking.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value ="/register")
    private String register(Model model){
        List<RoleModel> listRole = roleService.findAll();
        UserModel user = new UserModel();
        model.addAttribute("listRole",listRole);
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping(value ="/register")
    public String registerSubmit(@ModelAttribute UserModel user, @RequestParam("roleId") Long roleId, Model model){
        userService.addUser(user,roleId);
        model.addAttribute("user", user);
        return "login";
    }
}
