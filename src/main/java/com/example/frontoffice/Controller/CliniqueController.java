package com.example.frontoffice.Controller;

import com.example.frontoffice.CliniqueModel.Utilisateur;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clinique")
public class CliniqueController {
    @GetMapping("/")
    public String index(){
        return "Login";
    }

    @PostMapping("/login")
    public String acceuil(HttpServletRequest request) throws Exception {
        Utilisateur utilisateur=Utilisateur.login(request.getParameter("user"),request.getParameter("mdp"),Integer.parseInt(request.getParameter("type")));
        System.out.println("id : "+utilisateur.getId());
        if(utilisateur.getId()<0){
            return "redirect:/clinique/";
        }
        if (utilisateur.getType()==1){
            return "redirect:/admin/";
        }
        return "redirect:/utilisateur/";
    }
}
