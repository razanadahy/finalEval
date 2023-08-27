package com.example.frontoffice.Controller;

import com.example.frontoffice.CliniqueModel.*;
import com.example.frontoffice.Function.Function;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControlleur {
    @GetMapping("/")
    public String patient(Model model) throws Exception {
        model.addAttribute("patients", Patient.list());
        return "Admin/Patient";
    }
    @PostMapping("/patient")
    public String insertPatient(HttpServletRequest request) throws Exception {
        Patient.insert(request.getParameter("nom"),Integer.parseInt(request.getParameter("genre")),MainController.stringToDate(request.getParameter("date")),request.getParameter("rem"));
        return "redirect:/admin/";
    }
    @PostMapping("/patientModif")
    public String modifpatient(HttpServletRequest request) throws Exception {
        Patient.modif(Integer.parseInt(request.getParameter("id")),request.getParameter("nom"),Integer.parseInt(request.getParameter("genre")),MainController.stringToDate(request.getParameter("date")),request.getParameter("rem"));
        return "redirect:/admin/";
    }

    @GetMapping("/acte")
    public String acte(Model model) throws Exception {
        model.addAttribute("actes", Acte.list());
        return "Admin/Acte";
    }
    @PostMapping("/acte")
    public String acte(HttpServletRequest request) throws Exception {
        Acte.insert(request.getParameter("nom"),Double.parseDouble(request.getParameter("montant")),request.getParameter("ref"));
        return "redirect:/admin/acte";
    }

    @GetMapping("/depense")
    public String dept(Model model) throws Exception {
        model.addAttribute("depts", Depense.list());
        return "Admin/Depense";
    }
    @PostMapping("/depense")
    public String dept(HttpServletRequest request) throws Exception {
        Depense.insert(request.getParameter("nom"),request.getParameter("ref"),Double.parseDouble(request.getParameter("montant")));
        return "redirect:/admin/depense";
    }
    @GetMapping("/budgetaire")
    public String budge(Model model,HttpServletRequest request) throws Exception {
        int mois=0,anne=0;
        try {
            mois=Integer.parseInt(request.getParameter("mois"));
            anne=Integer.parseInt(request.getParameter("anne"));
        }catch (Exception e){
            mois=0;
            anne=0;
        }
        model.addAttribute("date","mois : "+ Function.mois[mois]+" --  année : "+anne);
        model.addAttribute("budgetaire", BudgetJour2.budgetJour2(mois,anne));
        return "Admin/Budgetaire";
    }
}
