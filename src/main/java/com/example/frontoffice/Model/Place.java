package com.example.frontoffice.Model;

import com.example.frontoffice.Base.FonctionBase;

import java.util.Vector;

public class Place {
    String nomLieu,nomCategorie,client;
    double prix;
    int idPrixPlace;

    public Place(String nomLieu, String nomCategorie, String client, double prix) {
        this.nomLieu = nomLieu;
        this.nomCategorie = nomCategorie;
        this.client = client;
        this.prix = prix;
    }

    public String getNomLieu() {
        return nomLieu;
    }

    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }

    public int getIdPrixPlace() {
        return idPrixPlace;
    }

    public void setIdPrixPlace(int idPrixPlace) {
        this.idPrixPlace = idPrixPlace;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    public static Vector<Place>list() throws Exception {
        Vector<Place>places=new Vector<>();
        String sql="select nomLieu,nomCategorie,client,prix,idPrixPlace from place order by idSpectacle";
        Vector[]list= FonctionBase.select(sql);
        for (int i = 0; i < list[0].size(); i++) {
            Place place=new Place((String)list[0].elementAt(i),(String)list[1].elementAt(i),(String)list[2].elementAt(i),(double)list[3].elementAt(i));
            place.setIdPrixPlace((int)list[4].elementAt(i));
            places.add(place);
        }
        return places;
    }
    public static void insertPlace(int idSpectacle,int idCategorie,double montant) throws Exception {
        String sql="insert into prixPlaceSpectacle(idSpectacle, idCategorie, prix) VALUES (%s,%s,%s)";
        sql=String.format(sql,idSpectacle,idCategorie,montant);
        FonctionBase.modifBase(sql);
    }
    public static void updatePlace(int id,double montant) throws Exception {
        String sql="update prixPlaceSpectacle set prix='%s' where idPrixPlace=%s";
        sql=String.format(sql,montant,id);
        FonctionBase.modifBase(sql);
    }
    public static Vector<Place>listPlace(int idSpectacle) throws Exception {
        Vector<Place>places=new Vector<>();
        String sql="select nomLieu,nomCategorie,client,prix,idPrixPlace from place where idSpectacle="+idSpectacle;
        Vector[]list= FonctionBase.select(sql);
        for (int i = 0; i < list[0].size(); i++) {
            Place place=new Place((String)list[0].elementAt(i),(String)list[1].elementAt(i),(String)list[2].elementAt(i),(double)list[3].elementAt(i));
            place.setIdPrixPlace((int)list[4].elementAt(i));
            places.add(place);
        }
        return places;
    }

    public static Vector<Place>listPlaceVendu() throws Exception {
        Vector<Place>places=new Vector<>();
        String sql="select nomLieu,nomCategorie,client,nombrePlaceVendu,idPlaceSpectacle from nombrePlaceVendu";
        Vector[]list= FonctionBase.select(sql);
        for (int i = 0; i < list[0].size(); i++) {
            Place place=new Place((String)list[0].elementAt(i),(String)list[1].elementAt(i),(String)list[2].elementAt(i),(double)list[3].elementAt(i));
            place.setIdPrixPlace((int)list[4].elementAt(i));
            places.add(place);
        }
        return places;
    }

    public static double place(int categorie,int idSpectacle) throws Exception {
        String sql="select nombreplace,idCategorie from placeLieu join lieu l on l.idLieu = placeLieu.idLieu join spectacle s on l.idLieu = s.idLieu where idspectacle=%s and idCategorie=%s";
        sql=String.format(sql,idSpectacle,categorie);
        Vector valiny=FonctionBase.select(sql)[0];
        return (int)valiny.elementAt(0);
    }

    public static void insertPlaceVendu(int idSpectacle,int categorie,double nombre) throws Exception {
        String sql="insert into placeSpectacle(idSpectacle, idCategorie, nombrePlaceVendu) VALUES (%s,%s,%s)";

        FonctionBase.modifBase(String.format(sql,idSpectacle,categorie,nombre));
    }

}
