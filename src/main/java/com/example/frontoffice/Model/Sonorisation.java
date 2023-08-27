package com.example.frontoffice.Model;


import com.example.frontoffice.Base.FonctionBase;

import java.util.Vector;

public class Sonorisation {
    int idSono;
    String nomSono,nomType;
    double tarif;

    public Sonorisation(int idSono, double tarif) {
        this.idSono = idSono;
        this.tarif = tarif;
    }

    public Sonorisation(int idSono, String nomSono, String nomType, double tarif) {
        this.idSono = idSono;
        this.nomSono = nomSono;
        this.nomType = nomType;
        this.tarif = tarif;
    }

    public int getIdSono() {
        return idSono;
    }

    public void setIdSono(int idSono) {
        this.idSono = idSono;
    }

    public String getNomSono() {
        return nomSono;
    }

    public void setNomSono(String nomSono) {
        this.nomSono = nomSono;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public static Vector<Sonorisation>listSono() throws Exception {
        Vector<Sonorisation>sonorisations=new Vector<>();
        String sql="select idTarifSono,nomSono,nomType,tarif from sonorisation";
        Vector[]list= FonctionBase.select(sql);
        for (int i = 0; i <list[0].size() ; i++) {
            double tar=0;
            String nom="--";
            if (list[3].elementAt(i)!=null){
                tar=(double)list[3].elementAt(i);
                nom=(String)list[2].elementAt(i);
            }
            Sonorisation sono=new Sonorisation((int)list[0].elementAt(i),(String) list[1].elementAt(i),nom,tar);
            sonorisations.add(sono);
        }
        return sonorisations;
    }

    public static Vector<Sonorisation>listSono(int idSpectacle) throws Exception {
        Vector<Sonorisation>sonorisations=new Vector<>();
        String sql="select idTarifSono,nomSono,nomType,tarif*sonoSpectacle.dure as montant from sonoSpectacle where idSpectacle="+idSpectacle;
        Vector[]list= FonctionBase.select(sql);
        for (int i = 0; i <list[0].size() ; i++) {
            double tar=0;
            String nom="--";
            if (list[3].elementAt(i)!=null){
                tar=(double)list[3].elementAt(i);
                nom=(String)list[2].elementAt(i);
            }
            Sonorisation sono=new Sonorisation((int)list[0].elementAt(i),(String) list[1].elementAt(i),nom,tar);
            sonorisations.add(sono);
        }
        return sonorisations;
    }
}
