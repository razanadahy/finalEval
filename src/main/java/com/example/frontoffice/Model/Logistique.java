package com.example.frontoffice.Model;


import com.example.frontoffice.Base.FonctionBase;

import java.util.Vector;

public class Logistique {
    int idLogistique;
    String nomLogistique,nomType;
    double tarif;

    public Logistique(int idLogistique, double tarif) {
        this.idLogistique = idLogistique;
        this.tarif = tarif;
    }

    public Logistique(int idLogistique, String nomLogistique, String nomType, double tarif) {
        this.idLogistique = idLogistique;
        this.nomLogistique = nomLogistique;
        this.nomType = nomType;
        this.tarif = tarif;
    }

    public int getIdLogistique() {
        return idLogistique;
    }

    public void setIdLogistique(int idLogistique) {
        this.idLogistique = idLogistique;
    }

    public String getNomLogistique() {
        return nomLogistique;
    }

    public void setNomLogistique(String nomLogistique) {
        this.nomLogistique = nomLogistique;
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

    public static Vector<Logistique> listLogistique() throws Exception {
        Vector<Logistique>sonorisations=new Vector<>();
        String sql="select idTarifLogistique,nomLogistique,nomType,tarif from logTarif";
        Vector[]list= FonctionBase.select(sql);
        for (int i = 0; i <list[0].size() ; i++) {
            double tar=0;
            String nom="--";
            if (list[3].elementAt(i)!=null){
                tar=(double)list[3].elementAt(i);
                nom=(String)list[2].elementAt(i);
            }
            Logistique sono=new Logistique((int)list[0].elementAt(i),(String) list[1].elementAt(i),nom,tar);
            sonorisations.add(sono);
        }
        return sonorisations;
    }
    public static Vector<Logistique> listLogistique(int idSpectacle) throws Exception {
        Vector<Logistique>sonorisations=new Vector<>();
        String sql="select idTarifLogistique,nomLogistique,nomType,tarif*logSpect.dure from logSpect where idSpectacle="+idSpectacle;
        System.out.println(sql);
        Vector[]list= FonctionBase.select(sql);
        for (int i = 0; i <list[0].size() ; i++) {
            double tar=0;
            String nom="--";
            if (list[3].elementAt(i)!=null){
                tar=(double)list[3].elementAt(i);
                nom=(String)list[2].elementAt(i);
            }
            Logistique sono=new Logistique((int)list[0].elementAt(i),(String) list[1].elementAt(i),nom,tar);
            sonorisations.add(sono);
        }
        return sonorisations;
    }
}
