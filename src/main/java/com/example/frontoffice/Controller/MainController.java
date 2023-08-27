package com.example.frontoffice.Controller;

import com.example.frontoffice.Model.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

@Controller
@RequestMapping("/")
public class MainController {
    static Spectacle spectacle=new Spectacle();
    public static Vector<Artiste>list=new Vector<>();
    public static Vector<Autre>autre=new Vector<>();
    public static Vector<Sonorisation>sonorisations=new Vector<>();
    public static Vector<Logistique>logistiques=new Vector<>();
    @GetMapping("/")
    public String index(){
        return "Login";
    }

    @GetMapping("/list")
    public String acceuil(Model model,HttpServletRequest request) throws Exception {
        HttpSession session=request.getSession();
        Employe emp= (Employe) session.getAttribute("emp");
        if (emp==null){
            return "redirect:/";
        }
        model.addAttribute("list", Spectacle.listSpectal(emp.getIdEmploye()));
        return "ListSpectacle";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request) throws Exception {
        Employe emp=Employe.login(request.getParameter("user"),request.getParameter("mdp"));
        if (emp.getIdEmploye()>=1){
            HttpSession session= request.getSession();
            session.setAttribute("emp",emp);
            return "redirect:/list";
        }
        return "redirect:/";
    }
    public static Date stringToDate(String date){
        String formatPattern = "yyyy-MM-dd";

        DateFormat dateFormat = new SimpleDateFormat(formatPattern);
        try {
            Date dat = dateFormat.parse(date);
            return dat;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("/cree")
    public String create(Model model){
        return "Creation";
    }
    @PostMapping("/nextArtiste")
    public String nextPage(HttpServletRequest request) throws Exception {
        HttpSession session=request.getSession();
        Employe emp= (Employe) session.getAttribute("emp");
        if (emp==null){
            return "redirect:/";
        }
        spectacle.setIdSpectacle((int)Spectacle.id());
        spectacle.setSpectacle(request.getParameter("client"));
        spectacle.setDateSpectacle(stringToDate(request.getParameter("date")));
        spectacle.setIdLieu(Integer.parseInt(request.getParameter("lieu")));
        spectacle.setHeure(Double.parseDouble(request.getParameter("dure")));
        spectacle.setiDEmploye(emp.getIdEmploye());
        spectacle.setLocation(Double.parseDouble(request.getParameter("location")));
        return "Artiste";
    }
    @PostMapping("/insertArtiste")
    public String insertArtiste(HttpServletRequest request){
        Artiste artiste=new Artiste(Integer.parseInt(request.getParameter("artiste")),Double.parseDouble(request.getParameter("dure")));
        list.add(artiste);
        return "Artiste";
    }

    @GetMapping("/autre")
    public String autreDepense(Model model) throws Exception {
        model.addAttribute("list",Autre.list());
        return "Autre";
    }
    @PostMapping("/insertAutre")
    public String insertAutre(HttpServletRequest request){
        Autre artiste=new Autre(Integer.parseInt(request.getParameter("artiste")),Double.parseDouble(request.getParameter("montant")));
        autre.add(artiste);
        return "redirect:/autre";
    }
    @GetMapping("/insertSono")
    public String viewSono(){
        return "CreationSono";
    }
    @PostMapping("/insertSonoVect")
    public String insertSono(HttpServletRequest request){
        Sonorisation sono=new Sonorisation(Integer.parseInt(request.getParameter("sono")),Double.parseDouble(request.getParameter("dureSono")));
        sonorisations.add(sono);
        return "redirect:/insertSono";
    }
    @GetMapping("/nexLog")
    public String nexLog(){
        return "CreationLogistique";
    }
    @PostMapping("/loginsert")
    public String insertLog(HttpServletRequest request){
        Logistique logis=new Logistique(Integer.parseInt(request.getParameter("log")),Double.parseDouble(request.getParameter("nombre")));
        logistiques.add(logis);
        return "redirect:/nexLog";
    }

    @GetMapping("/insertAll")
    public String insertAll() throws Exception {
        spectacle.insert();
        spectacle.setArtistes(list);
        spectacle.setDepenses(autre);
        spectacle.setSonorisations(sonorisations);
        spectacle.setLogistiques(logistiques);
        spectacle.insertAll();
        return "redirect:/list";
    }

    @GetMapping("/modifCreation")
    public String modifCreation(HttpServletRequest request){
        spectacle.setIdSpectacle(Integer.parseInt(request.getParameter("idSpectacle")));
        return "ModifSpectacle";
    }

    @PostMapping("/validerModifCreation")
    public String validerModifCreation(HttpServletRequest request) throws Exception {
        spectacle.setDateSpectacle(stringToDate(request.getParameter("daty")));
        spectacle.setHeure(Double.parseDouble(request.getParameter("dure")));
        spectacle.setIdLieu(Integer.parseInt(request.getParameter("lieu")));
        spectacle.setLocation(Double.parseDouble(request.getParameter("location")));
        spectacle.modif();
        return "redirect:/list";
    }

    @GetMapping("/modifArtiste")
    public String modifArtiste(HttpServletRequest request){
        spectacle.setIdSpectacle(Integer.parseInt(request.getParameter("idSpectacle")));
        list=new Vector<>();
        return "ModifArtiste";
    }

    @PostMapping("/insertArtistModif")
    public String modifArtisteModif(HttpServletRequest request) throws Exception {
        Artiste artiste=new Artiste(Integer.parseInt(request.getParameter("artiste")),Double.parseDouble(request.getParameter("dure")));
        list.add(artiste);
        return "ModifArtiste";
    }

    @GetMapping("/valider")
    public String validerArtiste() throws Exception {
        spectacle.setArtistes(list);
        spectacle.modifArtist();
        return "redirect:/list";
    }

    @GetMapping("/modifAutre")
    public String modifAutre(HttpServletRequest request){
        spectacle.setIdSpectacle(Integer.parseInt(request.getParameter("idSpectacle")));
        autre=new Vector<>();
        return "ModifAutre";
    }

    @PostMapping("/modifAutre")
    public String insertAutreModif(HttpServletRequest request){
        Autre artiste=new Autre(Integer.parseInt(request.getParameter("artiste")),Double.parseDouble(request.getParameter("montant")));
        autre.add(artiste);
        return "ModifAutre";
    }

    @GetMapping("/validerModif")
    public String modifAutre() throws Exception {
        spectacle.setDepenses(autre);
        spectacle.modifAutre();
        return "redirect:/list";
    }

    @GetMapping("/place")
    public String viewPlace(Model model,HttpServletRequest request) throws Exception {
        HttpSession session=request.getSession();
        Employe emp= (Employe) session.getAttribute("emp");
        if (emp==null){
            return "redirect:/";
        }
        model.addAttribute("list",Spectacle.listSpectal(emp.getIdEmploye()));
        return "Place";
    }
    @PostMapping("/insertPlace")
    public String insertPlace(HttpServletRequest request) throws Exception {
        int idSpectacle=Integer.parseInt(request.getParameter("spectacle"));
        int idCategorie=Integer.parseInt(request.getParameter("cat"));
        double montant=Double.parseDouble(request.getParameter("nom"));
        Place.insertPlace(idSpectacle,idCategorie,montant);
        return "redirect:/place";
    }
    @PostMapping("/updatePlace")
    public String updatePlace(HttpServletRequest request) throws Exception {
        int id=Integer.parseInt(request.getParameter("idPrixPlace"));
        double montant=Double.parseDouble(request.getParameter("nom"));
        Place.updatePlace(id,montant);
        return "redirect:/place";
    }

    @GetMapping("/modifSono")
    public String modifSono(HttpServletRequest request){
        spectacle.setIdSpectacle(Integer.parseInt(request.getParameter("idSpectacle")));
        sonorisations=new Vector<>();
        return "ModifSono";
    }

    @PostMapping("/insertSonoModif")
    public String insertModifSono(HttpServletRequest request){
        Sonorisation sono=new Sonorisation(Integer.parseInt(request.getParameter("sono")),Double.parseDouble(request.getParameter("dureSono")));
        sonorisations.add(sono);
        return "ModifSono";
    }
    @GetMapping("/validerSonorisation")
    public String validerSono() throws Exception {
        spectacle.setSonorisations(sonorisations);
        spectacle.modifSonorisation();
        return "redirect:/list";
    }

    @GetMapping("/modifLogistique")
    public String modifLogistique(HttpServletRequest request){
        spectacle.setIdSpectacle(Integer.parseInt(request.getParameter("idSpectacle")));
        logistiques=new Vector<>();
        return "ModifLogistique";
    }

    @PostMapping("/insertLogistiqueModif")
    public String insertModifLogistique(HttpServletRequest request){
        Logistique sono=new Logistique(Integer.parseInt(request.getParameter("log")),Double.parseDouble(request.getParameter("nombre")));
        logistiques.add(sono);
        return "ModifLogistique";
    }
    @GetMapping("/validerLogistique")
    public String validerLogistique() throws Exception {
        spectacle.setLogistiques(logistiques);
        spectacle.modifLogistique();
        return "redirect:/list";
    }

    @GetMapping("/toPdf")
    public String toPDF(Model model,HttpServletRequest request) throws Exception {
        model.addAttribute("spectacle",Spectacle.toPdfSpectacle(Integer.parseInt(request.getParameter("id"))));
        model.addAttribute("place",Place.listPlace(Integer.parseInt(request.getParameter("id"))));
        return "Affiche";
    }

    @GetMapping("/export-pdf")
    public void exportPdf(HttpServletRequest request, HttpServletResponse response) {
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument("http://localhost:8761/toPdf?id="+request.getParameter("id"));
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

    @GetMapping("/placeVendu")
    public String placeVendu(Model model,HttpServletRequest request) throws Exception {
        HttpSession session=request.getSession();
        Employe emp= (Employe) session.getAttribute("emp");
        if (emp==null){
            return "redirect:/";
        }
        model.addAttribute("list",Spectacle.listSpectal(emp.getIdEmploye()));
        return "PlaceVendu";
    }

    @PostMapping("/insertPlaceVendu")
    public String placeVendu(HttpServletRequest request,Model model) throws Exception {
        double nombre=Double.parseDouble(request.getParameter("nom"));
        int idSpectacle=Integer.parseInt(request.getParameter("spectacle"));
        int idCategorie=Integer.parseInt(request.getParameter("cat"));
        double place=Place.place(idCategorie,idSpectacle);
        if (place<nombre){
            HttpSession session=request.getSession();
            Employe emp= (Employe) session.getAttribute("emp");
            if (emp==null){
                return "redirect:/";
            }
            model.addAttribute("list",Spectacle.listSpectal(emp.getIdEmploye()));
            model.addAttribute("erreur",place);
            return "PlaceVendu";
        }
        Place.insertPlaceVendu(idSpectacle,idCategorie,nombre);
        return "redirect:/placeVendu";
    }

    @GetMapping("/statistique")
    public String statistique(HttpServletRequest request,Model model) throws Exception {
        HttpSession session=request.getSession();
        Employe emp= (Employe) session.getAttribute("emp");
        if (emp==null){
            return "redirect:/";
        }
        model.addAttribute("list", Spectacle.listSpectal(emp.getIdEmploye()));
        return "Statistique";
    }


    public static JFreeChart createBarChart(Spectacle spectacles){
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Autre depence",spectacles.getAutre());
        dataset.setValue("Logistique",spectacles.getLog());
        dataset.setValue("Location",spectacles.getLocation());
        dataset.setValue("Sonorisation",spectacles.getSono());
        dataset.setValue("Artiste",spectacles.getArtiste());
        JFreeChart chart = ChartFactory.createPieChart(spectacles.getSpectacle(),
                dataset, true, true, false);
        return chart;
    }

    @PostMapping("/duplicate")
    public String dupicate(HttpServletRequest request) throws Exception {
        Vector<Spectacle>spect=Spectacle.listSpectal(1);
        int id=Integer.parseInt(request.getParameter("id"));
        for (int i = 0; i <spect.size() ; i++) {
            if (spect.elementAt(i).getIdSpectacle()==id){
                Vector<Artiste>ar=new Vector<>();
                for (Artiste au : spect.elementAt(i).getArtistes()){
                    Artiste at=new Artiste(au.getIdArtiste(),au.getNomArtiste(),3000000.0);
                    ar.add(at);
                }
                Vector<Logistique>l=new Vector<>();
                Vector<Sonorisation>sono=new Vector<>();
                for (Logistique log : spect.elementAt(i).getLogistiques()){

                    Logistique al=new Logistique(log.getIdLogistique(),log.getNomLogistique(),log.getNomType(),75000.0);
                    l.add(al);
                }
                for (Sonorisation log : spect.elementAt(i).getSonorisations()){

                    Sonorisation al=new Sonorisation(log.getIdSono(),log.getNomSono(),log.getNomType(),log.getTarif());
                    sono.add(al);
                }
                Spectacle spectacle=new Spectacle();

                spectacle.setIdLieu(spect.elementAt(i).getIdLieu());
                spectacle.setiDEmploye(spect.elementAt(i).getiDEmploye());
                spectacle.setImage(spect.elementAt(i).getImage());
                //spectacle.setSpectacle();
                spectacle.setNomLieu(spect.elementAt(i).getNomLieu());

                spectacle.setIdSpectacle((int)Spectacle.id());
                spectacle.setDateSpectacle(stringToDate(request.getParameter("nom")));
                spectacle.setHeure(Double.parseDouble(request.getParameter("heur")));
                spectacle.setSpectacle(spectacle.getNomLieu()+"bis");
                spectacle.insert();
                spectacle.setDepenses(spect.elementAt(i).getDepenses());
                spectacle.setLogistiques(l);
                spectacle.setArtistes(Artiste.listArtisteSpectacle(id));
                spectacle.setSonorisations(sono);
                spectacle.insertAll();
                break;
            }
        }
        return "redirect:/list";
    }
}
