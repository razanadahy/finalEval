package com.example.frontoffice.Controller;

import com.example.frontoffice.Base.FonctionBase;
import com.example.frontoffice.CliniqueModel.*;
import com.example.frontoffice.Function.Function;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Vector;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {
    @GetMapping("/")
    public String facture(Model model) throws Exception {
        model.addAttribute("patients", Patient.list());
        model.addAttribute("factures", Facture.list());
        model.addAttribute("actes", Acte.list());
        return "Utilisateur/SaisirActe";
    }
    @PostMapping("/")
    public String facture(HttpServletRequest request) throws Exception {
        Facture.insert(request.getParameter("nom"),Integer.parseInt(request.getParameter("idPatient")),MainController.stringToDate(request.getParameter("date")));
        return "redirect:/utilisateur/";
    }
    @PostMapping("/ajouterActe")
    public String ajouterActe(HttpServletRequest request) throws Exception {
        Facture.insertActe(Double.parseDouble(request.getParameter("montant")),Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("acte")));
        return "redirect:/utilisateur/";
    }
    @GetMapping("/to-export")
    public String export(Model model,HttpServletRequest request) throws Exception {
        model.addAttribute("facture",Facture.once(Integer.parseInt(request.getParameter("id"))));
        return "Utilisateur/Facture";
    }
    @GetMapping("/export-pdf")
    public void exportPdf(HttpServletRequest request, HttpServletResponse response) {
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument("http://localhost:8080/utilisateur/to-export?id="+request.getParameter("id"));
            renderer.layout();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"export.pdf\"");
            OutputStream outputStream = response.getOutputStream();
            renderer.createPDF(outputStream);
            outputStream.close();
        } catch (Exception e) {
            System.out.println("Erreur : "+e.getMessage() );
        }
    }

    @GetMapping("/dept")
    public String depense(Model model) throws Exception {
        model.addAttribute("dept", Depense.list());
        model.addAttribute("deptMontant",Depense.listWithMontant());
        return "Utilisateur/SaisirDepense";
    }
    @PostMapping("/dept")
    public String depense(HttpServletRequest request) throws Exception {
        Depense.insertMontant(Integer.parseInt(request.getParameter("dept")),MainController.stringToDate(request.getParameter("date")),Double.parseDouble(request.getParameter("montant")));
        return "redirect:/utilisateur/dept";
    }

    @PostMapping("/multiple")
    public String insertMultiple(HttpServletRequest request,Model model) throws Exception {
        Vector<String>diso=new Vector<>();
        for (String mois : request.getParameterValues("mois")){
            String date=request.getParameter("anne")+"-"+mois+"-"+request.getParameter("jour");
            if (!Function.valide(date)){
                /*model.addAttribute("dept", Depense.list());
                model.addAttribute("deptMontant",Depense.listWithMontant());
                model.addAttribute("diso","verifier votre date");
                return "Utilisateur/SaisirDepense";*/
                diso.add("Il existe des date non valide  : "+date);
            }
        }
        int idCode=-1;
        try(Connection connection=FonctionBase.connect()) {
            idCode=Depense.idDepense(request.getParameter("dept"),connection);
            if (idCode<0){
                diso.add("ce code n'existe pas"+request.getParameter("dept"));
            }
        }
        try{
            double montant=Double.parseDouble(request.getParameter("montant"));
            if (montant<0){
                diso.add("montant doit superieur à 0");
            }
        }catch (Exception e){
            diso.add("montant invalide");
        }


        if (diso.size()>0){
            model.addAttribute("dept", Depense.list());
            model.addAttribute("deptMontant",Depense.listWithMontant());
            model.addAttribute("diso",diso);
            return "Utilisateur/SaisirDepense";
        }
        for (String mois : request.getParameterValues("mois")){
            String date=request.getParameter("anne")+"-"+mois+"-"+request.getParameter("jour");
            Depense.insertMontant(idCode,MainController.stringToDate(date),Double.parseDouble(request.getParameter("montant")));
        }
        return "redirect:/utilisateur/dept";
    }
    @PostMapping("/file")
    public String insertFile(@RequestParam("file") MultipartFile file,Model model) throws Exception {
        if (file.isEmpty()) {
            return "redirect:/utilisateur/dept";
        }
        Vector<String>erreur=new Vector<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[]insert=line.split(";");
                if (insert.length!=3){
                    erreur.add("il y a des donnée incomplete");
                }else {
                    try(Connection connection=FonctionBase.connect()){
                        try{
                            Depense.insertFile(insert[0],insert[1],insert[2],connection);
                        }catch (Exception e){
                            erreur.add(e.getMessage());
                        }
                    }
                }
            }
        }
        if (erreur.size()!=0){
            model.addAttribute("dept", Depense.list());
            model.addAttribute("deptMontant",Depense.listWithMontant());
            model.addAttribute("erreur",erreur);
            return "Utilisateur/SaisirDepense";
        }
        return "redirect:/utilisateur/dept";
    }

}
