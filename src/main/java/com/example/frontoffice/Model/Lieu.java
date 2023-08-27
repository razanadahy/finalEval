package com.example.frontoffice.Model;


import com.example.frontoffice.Base.FonctionBase;

import java.util.Vector;

public class Lieu {
    int idLieu;
    String nomLieu,nomType;
    int nombre;

    public Lieu(int idLieu, String nomLieu, String nomType, int nombre) {
        this.idLieu = idLieu;
        this.nomLieu = nomLieu;
        this.nomType = nomType;
        this.nombre = nombre;
    }

    public int getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(int idLieu) {
        this.idLieu = idLieu;
    }

    public String getNomLieu() {
        return nomLieu;
    }

    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
    public static Vector<Lieu>listLieu() throws Exception {
        Vector<Lieu>lieus=new Vector<>();
        String sql="select idLieu,nomLieu,nomType,nombre from lieuType";
        Vector[]list= FonctionBase.select(sql);
        for (int i = 0; i <list[0].size() ; i++) {
            Lieu sono=new Lieu((int)list[0].elementAt(i),(String) list[1].elementAt(i),(String)list[2].elementAt(i),(int)list[3].elementAt(i));
            lieus.add(sono);
        }
        return lieus;
    }
}
