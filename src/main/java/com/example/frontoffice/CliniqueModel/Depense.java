package com.example.frontoffice.CliniqueModel;

import com.example.frontoffice.Base.FonctionBase;

import java.sql.Connection;
import java.util.Date;
import java.util.Vector;

public class Depense {
    int id;
    String type;
    double montant;
    Date date;
    String ref;
    double budget;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Depense(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
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

    public static Vector<Depense>list() throws Exception {
        Vector<Depense> lists=new Vector<>();
        String sql="select idDepense,typeDepense,ref,budget from depense";
        Vector[]val=FonctionBase.select(sql);
        for (int i = 0; i <val[0].size() ; i++) {
            Depense dept=new Depense((int)val[0].elementAt(i),(String) val[1].elementAt(i));
            dept.setRef((String) val[2].elementAt(i));
            dept.setBudget((double) val[3].elementAt(i));
            lists.add(dept);
        }
        return lists;
    }
    public static void insert(String nom,String ref,double budget) throws Exception {
        String sql="insert into depense(typeDepense,ref,budget) values ('%s','%s','%s')";
        sql=String.format(sql,nom,ref,budget);
        FonctionBase.modifBase(sql);
    }
    public static Vector<Depense>listWithMontant() throws Exception {
        Vector<Depense> lists=new Vector<>();
        String sql="select montant,date,idDepense,typeDepense from dept";
        Vector[]val=FonctionBase.select(sql);
        for (int i = 0; i <val[0].size() ; i++) {
            Depense dept=new Depense((int)val[2].elementAt(i),(String) val[3].elementAt(i));
            dept.setMontant((double)val[0].elementAt(i) );
            dept.setDate((Date) val[1].elementAt(i) );
            lists.add(dept);
        }
        return lists;
    }
    public static void insertMontant(int idDepense,Date dte,double montant) throws Exception {
        String sql="insert into mouvementDepense(montant, idDepense, date) VALUES ('%s','%s','%s')";
        sql=String.format(sql,montant,idDepense,dte);
        FonctionBase.modifBase(sql);
    }

    public static int idDepense(String code, Connection connection) throws Exception {
        String sql="select idDepense from depense where upper(ref)=upper('%s')";
        sql=String.format(sql,code);
        Vector valiny= FonctionBase.all(sql,connection)[0];
        if (valiny.isEmpty()){
            return -100;
        }
        return (int) valiny.elementAt(0);
    }
    public static void insertFile(String date,String code,String montant,Connection connection) throws Exception {
        String sql="insert into mouvementDepense(montant, idDepense, date) VALUES ('%s','%s','%s')";
        sql=String.format(sql,montant,idDepense(code,connection),date);
        FonctionBase.execute(sql,connection);
    }
}
