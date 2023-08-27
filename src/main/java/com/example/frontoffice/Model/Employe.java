package com.example.frontoffice.Model;

import com.example.frontoffice.Base.FonctionBase;

import java.util.Vector;

public class Employe {
    int idEmploye ;
    String nomEmploye ,mdp ;

    public Employe(int idEmploye, String nomEmploye, String mdp) {
        this.idEmploye = idEmploye;
        this.nomEmploye = nomEmploye;
        this.mdp = mdp;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNomEmploye() {
        return nomEmploye;
    }

    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public static Employe login(String user,String mdp) throws Exception {
        String sql="select idEmploye from employe where nomEmploye='%s' and mdp='%s'";
        sql=String.format(sql,user,mdp);
        Vector<Object> list= FonctionBase.select(sql)[0];
        int id=-10;
        if (list!=null){
            if (list.size()==1){
                id= (int) list.elementAt(0);
            }
        }
        return new Employe(id,user,mdp);
    }
}
