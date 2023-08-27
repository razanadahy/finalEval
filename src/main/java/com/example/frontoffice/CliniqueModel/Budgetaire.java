package com.example.frontoffice.CliniqueModel;

import com.example.frontoffice.Base.FonctionBase;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Budgetaire {
    int anne,mois;
    double recette,depence;

    public int getAnne() {
        return anne;
    }

    public void setAnne(int anne) {
        this.anne = anne;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public double getRecette() {
        return recette;
    }

    public void setRecette(double recette) {
        this.recette = recette;
    }

    public double getDepence() {
        return depence;
    }

    public void setDepence(double depence) {
        this.depence = depence;
    }

    public Budgetaire(int anne, int mois, double recette, double depence) {
        this.anne = anne;
        this.mois = mois;
        this.recette = recette;
        this.depence = depence;
    }
    public static Vector<Budgetaire> list() throws Exception {
        Vector<Budgetaire>valiny=new Vector<>();
        try(Connection connection=FonctionBase.connect()) {
            Date min=getDateMin(connection);
            Date max=getDateMax(connection);
            if (min==null){
                return valiny;
            }

            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar1.setTime(min);
            calendar2.setTime(max);
            int diffMonths = (calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR)) * 12
                    + calendar2.get(Calendar.MONTH) - calendar1.get(Calendar.MONTH);

            for (int i = 0; i <diffMonths+1 ; i++) {
                Budgetaire vente=budgMoisAnne(min.getYear()+1900,min.getMonth()+1,connection);
                min.setMonth(min.getMonth()+1);
                valiny.add(vente);
            }
        }
        return valiny;
    }


    public static Date getDateMin(Connection connection) throws Exception {
        String sql="select min(date) from dateMovement";
        Vector valiny= FonctionBase.all(sql,connection)[0];
        if (valiny!=null && valiny.size()>0){
            return (Date) valiny.elementAt(0);
        }
        return new Date();
    }
    public static Date getDateMax(Connection connection) throws Exception {
        String sql="select max(date) from dateMovement";
        Vector valiny=FonctionBase.all(sql,connection)[0];
        if (valiny!=null && valiny.size()>0){
            return (Date) valiny.elementAt(0);
        }
        return new Date();
    }
    public static double dept(int anne,int mois,Connection connection) throws Exception {
        String sql="select case when sum(montant) is null then 0 else sum(montant) end as vola from mouvementDepense  where extract(year from date)=%s and extract(month from date)=%s";
        sql=String.format(sql,anne,mois);
        Vector valiny= FonctionBase.all(sql,connection)[0];
        return (double) valiny.elementAt(0);
    }
    public static double rec(int anne,int mois,Connection connection) throws Exception {
        String sql="select case when sum(montant) is null then 0 else sum(montant) end as vola from actePatient join facture f on f.idFacture = actePatient.idFacture where extract(year from date)=%s and extract(month from date)=%s";
        sql=String.format(sql,anne,mois);
        Vector valiny= FonctionBase.all(sql,connection)[0];
        return (double) valiny.elementAt(0);
    }
    public static Budgetaire budgMoisAnne(int anne,int mois,Connection connection) throws Exception {
        return new Budgetaire(anne,mois,rec(anne,mois,connection),dept(anne,mois,connection));
    }
}
