package com.example.frontoffice.CliniqueModel;

import com.example.frontoffice.Base.FonctionBase;

import java.util.Vector;

public class Utilisateur {
    String nom,mdp;
    int type,id;

    public Utilisateur(String nom, String mdp, int type, int id) {
        this.nom = nom;
        this.mdp = mdp;
        this.type = type;
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getMdp() {
        return mdp;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Utilisateur login(String nom, String mdp, int type) throws Exception {
        int id=-100;
        Utilisateur utilisateur=new Utilisateur(nom,mdp,type,id);
        String sql="";
        if (type==1){
            sql="select idAdmin from adminUtilisateur where nom='%s' and mdp='%s'";
        }else {
            sql="select idUtilisateur from utilisateur where nom='%s' and mdp='%s'";
        }
        sql=String.format(sql,nom,mdp);
        System.out.println(sql);
        Vector<Object>idPutilisateur= FonctionBase.select(sql)[0];
        if (!idPutilisateur.isEmpty()){
            utilisateur.setId((Integer) idPutilisateur.elementAt(0));
        }
        return utilisateur;
    }
}
