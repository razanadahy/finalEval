package com.example.frontoffice.CliniqueModel;

import com.example.frontoffice.Base.FonctionBase;

import java.util.Date;
import java.util.Vector;

public class Patient {
    int idPatient ;
    String idGenre ;
    String nom ;
    Date naissance ;
    boolean rembourche ;

    public Patient(int idPatient, String idGenre, String nom, Date naissance, boolean rembourche) {
        this.idPatient = idPatient;
        this.idGenre = idGenre;
        this.nom = nom;
        this.naissance = naissance;
        this.rembourche = rembourche;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public String getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(String idGenre) {
        this.idGenre = idGenre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    public boolean isRembourche() {
        return rembourche;
    }

    public void setRembourche(boolean rembourche) {
        this.rembourche = rembourche;
    }

    public static Vector<Patient> list() throws Exception {
        Vector<Patient> patients=new Vector<>();
        String sql="select idPatient , nom,naissance,rembourche,genre from genrePatient";
        Vector[]valiny= FonctionBase.select(sql);
        for (int i = 0; i <valiny[0].size() ; i++) {
            Patient patient=new Patient((int)valiny[0].elementAt(i),(String) valiny[4].elementAt(i),(String) valiny[1].elementAt(i),(Date) valiny[2].elementAt(i),(boolean)valiny[3].elementAt(i));
            patients.add(patient);
        }
        return patients;
    }

    public static void insert(String nom,int genre,Date naissance,String isRembourche) throws Exception {
        String sql="insert into patient(nom, naissance, idGenre, rembourche) VALUES ('%s','%s','%s','%s')";
        sql=String.format(sql,nom,naissance,genre,isRembourche);
        FonctionBase.modifBase(sql);
    }
    public static void modif(int id,String nom,int genre,Date naissance,String isRem) throws Exception {
        String sql="update patient set nom='%s',naissance='%s',idGenre='%s',rembourche='%s' where idPatient='%s'";
        sql=String.format(sql,nom,naissance,genre,isRem,id);
        FonctionBase.modifBase(sql);
    }
    public static boolean estRembourser(int idFacture) throws Exception {
        String sql="select rembourche from facture join patient p on p.idPatient = facture.idPatient where idFacture="+idFacture;
        Vector valiny=FonctionBase.select(sql)[0];
        if (!valiny.isEmpty()){
            return (boolean) valiny.elementAt(0);
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(estRembourser(6));
    }
}
