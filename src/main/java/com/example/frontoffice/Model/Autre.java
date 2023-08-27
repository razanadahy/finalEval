package com.example.frontoffice.Model;

import com.example.frontoffice.Base.FonctionBase;

import java.util.Vector;

public class Autre {
    int idAutre ;
    String nomAutre;
    double  montant;

    public Autre(int idAutre, double montant) {
        this.idAutre = idAutre;
        this.montant = montant;
    }

    public Autre(int idAutre, String nomAutre, double montant) {
        this.idAutre = idAutre;
        this.nomAutre = nomAutre;
        this.montant = montant;
    }

    public Autre(int idAutre, String nomAutre) {
        this.idAutre = idAutre;
        this.nomAutre = nomAutre;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getIdAutre() {
        return idAutre;
    }

    public void setIdAutre(int idAutre) {
        this.idAutre = idAutre;
    }

    public String getNomAutre() {
        return nomAutre;
    }

    public void setNomAutre(String nomAutre) {
        this.nomAutre = nomAutre;
    }

    public static Vector<Autre> list() throws Exception {
        Vector<Autre>autres=new Vector<>();
        String sql="select idAutre,nomAutre from autreDepense";
        Vector[]list= FonctionBase.select(sql);
        for (int i = 0; i <list[0].size() ; i++) {
            Autre sono=new Autre((int)list[0].elementAt(i),(String) list[1].elementAt(i));
            autres.add(sono);
        }
        return autres;
    }
    public static Vector<Autre>listAutreSpect(int idSpect) throws Exception {
        Vector<Autre>autres=new Vector<>();
        String sql="select idAutre,nomAutre,montant from spectAutre where idSpectacle="+idSpect;
        Vector[]list= FonctionBase.select(sql);
        for (int i = 0; i <list[0].size() ; i++) {
            Autre sono=new Autre((int)list[0].elementAt(i),(String) list[1].elementAt(i),(double)list[2].elementAt(i));
            autres.add(sono);
        }
        return autres;
    }
}
