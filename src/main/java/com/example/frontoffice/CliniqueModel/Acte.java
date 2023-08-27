package com.example.frontoffice.CliniqueModel;

import com.example.frontoffice.Base.FonctionBase;

import java.util.Vector;

public class Acte {
    int id;
    String type;
    double montant;
    double budget;
    String ref;

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Acte(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public static Vector<Acte>list() throws Exception {
        Vector<Acte>actes=new Vector<>();
        String sql="select idActe,typeActe,ref,budget from acte";
        Vector[]val=FonctionBase.select(sql);
        for (int i = 0; i <val[0].size() ; i++) {
            Acte acte=new Acte((int)val[0].elementAt(i),(String) val[1].elementAt(i));
            acte.setRef((String) val[2].elementAt(i));
            acte.setBudget((double) val[3].elementAt(i));
            actes.add(acte);
        }
        return actes;
    }
    public static void insert(String type,double budget,String ref) throws Exception {
        String sql="insert into acte(typeActe,ref,budget) values ('%s','%s','%s')";
        sql=String.format(sql,type,ref,budget);
        FonctionBase.modifBase(sql);
    }
}
