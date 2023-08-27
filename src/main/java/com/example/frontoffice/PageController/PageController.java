package com.example.frontoffice.PageController;

import com.example.frontoffice.CliniqueModel.Acte;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {
    @GetMapping("/acte")
    public String acte(Model model) throws Exception {
        model.addAttribute("actes", Acte.list());
        return "Page/ActePage";
    }
}
