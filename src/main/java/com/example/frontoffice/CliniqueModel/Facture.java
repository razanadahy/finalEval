package com.example.frontoffice.CliniqueModel;

import com.example.frontoffice.Base.FonctionBase;

import java.sql.Connection;
import java.util.Date;
import java.util.Vector;

public class Facture {
    String reference,nom;
    Date date;
    Vector<Acte>actes;
    int idFacture;

    public Facture(String reference, String nom, Date date, Vector<Acte> actes, int idFacture) {
        this.reference = reference;
        this.nom = nom;
        this.date = date;
        this.actes = actes;
        this.idFacture = idFacture;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Vector<Acte> getActes() {
        return actes;
    }

    public void setActes(Vector<Acte> actes) {
        this.actes = actes;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public static Vector<Facture>list() throws Exception {
        Vector<Facture>factures=new Vector<>();
        String sql="select reference,date,nom,idFacture from facturePatient";
        try(Connection connection=FonctionBase.connect()) {
            Vector[]fact=FonctionBase.all(sql,connection);
            for (int i = 0; i <fact[0].size() ; i++) {
                Facture facture=new Facture((String)fact[0].elementAt(i),(String)fact[2].elementAt(i),(Date) fact[1].elementAt(i),acteFacture((int)fact[3].elementAt(i),connection),(int)fact[3].elementAt(i));
                factures.add(facture);
            }
        }
        return factures;
    }
    public static Vector<Acte>acteFacture(int idFacture, Connection connection) throws Exception {
        Vector<Acte>valiny=new Vector<>();
        String sql="select sum(montant),idActe,typeActe from acteFacture where idFacture=%s group by idActe,typeActe";
        sql=String.format(sql,idFacture);
        Vector[]list= FonctionBase.all(sql,connection);
        for (int i = 0; i <list[0].size() ; i++) {
            Acte acte=new Acte((int)list[1].elementAt(i),(String) list[2].elementAt(i));
            acte.setMontant((double) list[0].elementAt(i));
            valiny.add(acte);
        }
        return valiny;
    }
    public static void insert(String reference,int idPatient,Date date) throws Exception {
        String sql="insert into facture (reference, idPatient, date) VALUES ('%s','%s','%s')";
        sql=String.format(sql,reference,idPatient,date);
        FonctionBase.modifBase(sql);
    }
    public static void insertActe(double montant,int idFacture,int idActe) throws Exception {
        String sql="insert into actePatient(idFacture, idActe, montant) VALUES ('%s','%s','%s')";
        if (Patient.estRembourser(idFacture)) {
            String rembourchement = "insert into actePatient(idFacture, idActe, montant) VALUES ('%s','%s','%s')";
            rembourchement=String.format(rembourchement,idFacture,5,(montant*20/100));
            System.out.println("mande atoooooooo");
            FonctionBase.modifBase(rembourchement);
        }
        sql=String.format(sql,idFacture,idActe,montant);
        FonctionBase.modifBase(sql);
    }
    public static Facture once(int idFacture) throws Exception {
        Vector<Facture>f=list();
        for (Facture lf : f){
            if (lf.getIdFacture()==idFacture){
                return lf;
            }
        }
        return null;
    }
    public double montant(){
        double valiny=0;
        for (Acte a: actes){
            valiny+=a.getMontant();
        }
        return valiny;
    }
}
