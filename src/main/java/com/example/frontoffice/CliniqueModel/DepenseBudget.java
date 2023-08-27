package com.example.frontoffice.CliniqueModel;

import com.example.frontoffice.Base.FonctionBase;

import java.util.Vector;

public class DepenseBudget {
    String type;
    double reel;
    double budget;
    double pourcentage;

    public DepenseBudget(String type, double reel, double budget, double pourcentage) {
        this.type = type;
        this.reel = reel;
        this.budget = budget;
        this.pourcentage = pourcentage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getReel() {
        return reel;
    }

    public void setReel(double reel) {
        this.reel = reel;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }
    public static Vector<DepenseBudget> acte(int mois, int anne) throws Exception {
        Vector<DepenseBudget>acte=new Vector<>();
        String sql="select vola,typeDepense,budget,(vola/budget)*100 as realisation,idDepense from tdepense where (extract(year from date)=%s and extract(month from date)=%s) or date is null";
        //String sql="select vola,typeDepense,cast(budget as decimal(10,2))as budget,(vola/budget)*100 as realisation,idDepense from tdepense where (extract(year from date)=%s and extract(month from date)=%s) or date is null";
        sql=String.format(sql,anne,mois);
        Vector[]valiny= FonctionBase.select(sql);
        Vector<Depense>actes=Depense.list();
        int size= valiny[0].size();
        Vector<Integer>idActe=new Vector<>();
        for (int i = 0; i <size ; i++) {
            idActe.add((int)valiny[4].elementAt(i));
        }
        for (Depense a : actes){
                DepenseBudget s=new DepenseBudget(a.getType(),0,a.getBudget()/12,0);
                acte.add(s);
        }
        for (int i = 0; i <valiny[0].size() ; i++) {

            for (DepenseBudget t : acte){
                if (t.getType().equalsIgnoreCase((String) valiny[1].elementAt(i))){
                    t.setReel(t.getReel()+(double) valiny[0].elementAt(i));
                    t.setPourcentage(BudgetJour2.pourcentage(t.getReel(),t.getBudget()));
                }
            }
            /*DepenseBudget s=new DepenseBudget((String) valiny[1].elementAt(i),(double) valiny[0].elementAt(i),(double) valiny[2].elementAt(i),(double) valiny[3].elementAt(i));
            acte.add(s);*/
        }
        return acte;
    }
}
