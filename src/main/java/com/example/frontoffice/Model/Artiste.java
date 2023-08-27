package com.example.frontoffice.Model;

import com.example.frontoffice.Base.FonctionBase;

import java.util.Vector;

public class Artiste {
    int idArtiste ;
    String nomArtiste ;
    double tarif;
    String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Artiste(int idArtiste, String nomArtiste, double tarif) {
        this.idArtiste = idArtiste;
        this.nomArtiste = nomArtiste;
        this.tarif = tarif;
    }

    public Artiste(int idArtiste, double tarif) {
        this.idArtiste = idArtiste;
        this.tarif = tarif;
    }

    public int getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(int idArtiste) {
        this.idArtiste = idArtiste;
    }

    public String getNomArtiste() {
        return nomArtiste;
    }

    public void setNomArtiste(String nomArtiste) {
        this.nomArtiste = nomArtiste;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public static Vector<Artiste> listeArtiste() throws Exception {
        Vector<Artiste>artistes=new Vector<>();
        String sql="select idArtiste,nomArtiste,tarif from artiste";
        Vector[]list=FonctionBase.select(sql);
        for (int i = 0; i <list[0].size() ; i++) {
            Artiste artiste=new Artiste((int)list[0].elementAt(i),(String)list[1].elementAt(i),(double)list[2].elementAt(i));
            artistes.add(artiste);
        }
        return artistes;
    }

    public static Vector<Artiste>listArtisteSpectacle(int idSpectacle) throws Exception {
        Vector<Artiste>artistes=new Vector<>();
        String sql="select idArtiste, nomArtiste,montant,photo from spectArt where idSpectacle="+idSpectacle;
        Vector[]list=FonctionBase.select(sql);
        for (int i = 0; i <list[0].size() ; i++) {
            Artiste artiste=new Artiste((int)list[0].elementAt(i),(String)list[1].elementAt(i),(double)list[2].elementAt(i));
            artiste.setPhoto((String)list[3].elementAt(i));
            artistes.add(artiste);
        }
        return artistes;
    }
}
