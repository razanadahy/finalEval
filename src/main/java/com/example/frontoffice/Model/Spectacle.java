package com.example.frontoffice.Model;

import com.example.frontoffice.Base.FonctionBase;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Spectacle {
    int idLieu,idSpectacle,iDEmploye;
    String spectacle,nomLieu,image;
    Date dateSpectacle;
    double heure,location;

    Vector<Artiste>artistes=new Vector<>();
    Vector<Autre>depenses=new Vector<>();
    Vector<Sonorisation>sonorisations=new Vector<>();
    Vector<Logistique>logistiques=new Vector<>();

    public static Vector<Spectacle>listSpectal(int iDEmploye) throws Exception {
        String sql="select idSpectacle,idLieu,iDEmploye,client,nomLieu,dateSpectacle,dureSpectacle,location,image from lieuSpectacle where iDEmploye="+iDEmploye;
        Vector<Spectacle> spectacles=new Vector<>();
        Vector[]list=FonctionBase.select(sql);
        for (int i = 0; i <list[0].size() ; i++) {
            Spectacle spectacle=new Spectacle();
            spectacle.setIdSpectacle((int)list[0].elementAt(i));
            spectacle.setIdLieu((int)list[1].elementAt(i));
            spectacle.setiDEmploye((int)list[2].elementAt(i));
            spectacle.setImage((String) list[8].elementAt(i));
            spectacle.setSpectacle((String)list[3].elementAt(i));
            spectacle.setNomLieu((String)list[4].elementAt(i));

            spectacle.setDateSpectacle((Date) list[5].elementAt(i));

            spectacle.setHeure((double) list[6].elementAt(i));
            spectacle.setLocation((double) list[7].elementAt(i));
            spectacle.setSonorisations(Sonorisation.listSono(spectacle.getIdSpectacle()));
            spectacle.setDepenses(Autre.listAutreSpect(spectacle.getIdSpectacle()));
            spectacle.setArtistes(Artiste.listArtisteSpectacle(spectacle.getIdSpectacle()));
            spectacle.setLogistiques(Logistique.listLogistique(spectacle.idSpectacle));
            spectacles.add(spectacle);
        }
        return spectacles;
    }

    public static Spectacle toPdfSpectacle(int idSpectacle) throws Exception {
        String sql="select idSpectacle,idLieu,iDEmploye,client,nomLieu,dateSpectacle,dureSpectacle,location,image from lieuSpectacle where idSpectacle="+idSpectacle;
        Vector<Spectacle> spectacles=new Vector<>();
        Vector[]list=FonctionBase.select(sql);
        for (int i = 0; i <list[0].size() ; i++) {
            Spectacle spectacle=new Spectacle();
            spectacle.setIdSpectacle((int)list[0].elementAt(i));
            spectacle.setIdLieu((int)list[1].elementAt(i));
            spectacle.setiDEmploye((int)list[2].elementAt(i));
            spectacle.setImage((String) list[8].elementAt(i));
            spectacle.setSpectacle((String)list[3].elementAt(i));
            spectacle.setNomLieu((String)list[4].elementAt(i));

            spectacle.setDateSpectacle((Date) list[5].elementAt(i));

            spectacle.setHeure((double) list[6].elementAt(i));
            spectacle.setLocation((double) list[7].elementAt(i));
            spectacle.setSonorisations(Sonorisation.listSono(spectacle.getIdSpectacle()));
            spectacle.setDepenses(Autre.listAutreSpect(spectacle.getIdSpectacle()));
            spectacle.setArtistes(Artiste.listArtisteSpectacle(spectacle.getIdSpectacle()));
            spectacle.setLogistiques(Logistique.listLogistique(spectacle.idSpectacle));
            return spectacle;
            //spectacles.add(spectacle);
        }
        return new Spectacle();
    }

    public Spectacle() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(int idLieu) {
        this.idLieu = idLieu;
    }

    public int getIdSpectacle() {
        return idSpectacle;
    }

    public void setIdSpectacle(int idSpectacle) {
        this.idSpectacle = idSpectacle;
    }

    public int getiDEmploye() {
        return iDEmploye;
    }

    public void setiDEmploye(int iDEmploye) {
        this.iDEmploye = iDEmploye;
    }

    public String getSpectacle() {
        return spectacle;
    }

    public void setSpectacle(String spectacle) {
        this.spectacle = spectacle;
    }

    public String getNomLieu() {
        return nomLieu;
    }

    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }


    public Date getDateSpectacle() {
        return dateSpectacle;
    }

    public void setDateSpectacle(Date dateSpectacle) {
        this.dateSpectacle = dateSpectacle;
    }

    public double getHeure() {
        return heure;
    }

    public void setHeure(double heure) {
        this.heure = heure;
    }

    public double getLocation() {
        return location;
    }

    public void setLocation(double location) {
        this.location = location;
    }

    public Vector<Artiste> getArtistes() {
        return artistes;
    }

    public void setArtistes(Vector<Artiste> artistes) {
        this.artistes = artistes;
    }

    public Vector<Autre> getDepenses() {
        return depenses;
    }

    public void setDepenses(Vector<Autre> depenses) {
        this.depenses = depenses;
    }

    public Vector<Sonorisation> getSonorisations() {
        return sonorisations;
    }

    public void setSonorisations(Vector<Sonorisation> sonorisations) {
        this.sonorisations = sonorisations;
    }

    public Vector<Logistique> getLogistiques() {
        return logistiques;
    }

    public void setLogistiques(Vector<Logistique> logistiques) {
        this.logistiques = logistiques;
    }

    public double getAutre(){
        double valiny=0;
        for(Autre au : depenses){
            valiny+=au.getMontant();
        }
        return valiny;
    }
    public double getArtiste(){
        double valiny=0;
        for (Artiste ar:artistes){
            valiny+=ar.getTarif();
        }
        return valiny;
    }
    public double getSono(){
        double valiny=0;
        for (Sonorisation sono : sonorisations){
            valiny+=sono.getTarif();
        }
        return valiny;
    }
    public double getLog(){
        double valiny=0;
        for (Logistique log : logistiques){
            valiny+=log.getTarif();
        }
        return valiny;
    }

    public static String dateToDate(Date date){
        String[]mn=new String[]{
                "Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"
        };
        int month=date.getMonth();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int anne=date.getYear()+1900;
        calendar.setTime(date);
        return day+"/"+mn[month]+"/"+anne;
    }

    public static void main(String[] args) {
        dateToDate(new Date());
    }

    public double getTotal(){
        double valiny=0;
        for (Artiste ar:artistes){
            valiny+=ar.getTarif();
        }
        for(Autre au : depenses){
            valiny+=au.getMontant();
        }
        for (Sonorisation sono : sonorisations){
            valiny+=sono.getTarif();
        }
        for (Logistique log : logistiques){
            valiny+=log.getTarif();
        }
        return valiny+location;
    }


    public static long id() throws Exception {
        String sql="select nextval('seqSpectacle')";
        Vector d=FonctionBase.select(sql)[0];
        return (long) d.elementAt(0);
    }
    public void insert() throws Exception {
        String sql="insert into spectacle(idSpectacle, dateSpectacle, dureSpectacle, idLieu, client, iDEmploye,location) VALUES ('%s','%s','%s','%s','%s','%s','%s')";
        sql=String.format(sql,idSpectacle,dateSpectacle,heure,idLieu,spectacle,iDEmploye,location);
        FonctionBase.modifBase(sql);
    }
    public void insertArtist() throws Exception {
        for (Artiste ar : artistes) {
            String sql = "insert into spectacleArtiste (idSpectacle, idArtiste, duree) VALUES (%s,%s,%s)";
            sql = String.format(sql, idSpectacle,ar.getIdArtiste(),ar.getTarif());
            FonctionBase.modifBase(sql);
        }
    }
    public void insertAutre() throws Exception {
        for (Autre au : depenses){
            String sql="insert into spectacleAutreDepense(idSpectacle, idAutreDepense, montant) VALUES (%s,%s,%s)";
            sql=String.format(sql,idSpectacle,au.getIdAutre(),au.getMontant());
            FonctionBase.modifBase(sql);
        }
    }
    public void insertSono() throws Exception {
        for (Sonorisation sono : sonorisations){
            String sql="insert into spectacleSono(idSpectacle, idTarifSono, dure) VALUES ('%s','%s','%s')";
            sql=String.format(sql,idSpectacle,sono.getIdSono(),sono.getTarif());
            FonctionBase.modifBase(sql);
        }
    }
    public void insertLog() throws Exception {
        for (Logistique log : logistiques){
            String sql="insert into spectacleLogistique(idSpectacle, idTarifLogistique, dure) VALUES ('%s','%s','%s')";
            sql=String.format(sql,idSpectacle,log.getIdLogistique(),log.getTarif());
            FonctionBase.modifBase(sql);
        }
    }
    public void insertAll() throws Exception {
       insertArtist();
       insertAutre();
       insertLog();
       insertSono();
    }
    public void modif() throws Exception {
        String sql="update spectacle set dateSpectacle='%s' , dureSpectacle='%s' , idLieu='%s' , location='%s' where idSpectacle=%s";
        sql=String.format(sql,dateSpectacle,heure,idLieu,location,idSpectacle);
       FonctionBase.modifBase(sql);
    }
    public void modifArtist() throws Exception {
        String sql="delete from spectacleArtiste where idSpectacle="+idSpectacle;
        FonctionBase.modifBase(sql);
        insertArtist();
    }
    public void modifAutre() throws Exception {
        String sql="delete from spectacleAutreDepense where idSpectacle="+idSpectacle;
        FonctionBase.modifBase(sql);
        insertAutre();
    }


    public double getRecette() throws Exception {
        String sql="select case when sum(nombreplace*prix) is null then 0 else sum(nombreplace*prix) end from prixPlace where idSpectacle="+idSpectacle;
        Vector valiny=FonctionBase.select(sql)[0];
        if (valiny.size()>0){
            return (double)valiny.elementAt(0);
        }
        return 0;
    }

    public double getRealRecette() throws Exception {
        String sql="select case when sum(nombrePlaceVendu*prix) is null then 0 else sum(nombrePlaceVendu*prix) end from recette where idSpectacle="+idSpectacle;
        Vector valiny=FonctionBase.select(sql)[0];
        if (valiny.size()>0){
            return (double)valiny.elementAt(0);
        }
        return 0;
    }




    public void modifSonorisation() throws Exception {
        String sql="delete from spectacleSono where idSpectacle="+idSpectacle;
        FonctionBase.modifBase(sql);
        insertSono();
    }
    public void modifLogistique() throws Exception {
        String sql="delete from spectacleLogistique where idSpectacle="+idSpectacle;
        FonctionBase.modifBase(sql);
        insertLog();
    }
    public static String heur(double heure){
        int hours = (int) heure;
        int minutes = (int) ((heure - hours) * 60);

        String hourString = String.format("%02d", hours);
        String minuteString = String.format("%02d", minutes);

        String time = hourString + " : " + minuteString;
        return time;
    }
}
