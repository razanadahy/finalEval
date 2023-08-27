package com.example.frontoffice.CliniqueModel;

import com.example.frontoffice.Base.FonctionBase;

import java.util.Vector;

public class ActeBudget {
    String type;
    double reel;
    double budget;
    double pourcentage;

    public ActeBudget(String type, double reel, double budget, double pourcentage) {
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
    public static Vector<ActeBudget>acte(int mois,int anne) throws Exception {
        Vector<ActeBudget>acte=new Vector<>();
        String sql="select case when sum(vola) is null then 0 else  sum(vola) end ,typeActe,budget,(vola/tActe.budget)*100 as realisation, idActe from tActe where (extract(year from date)=%s and extract(month from date)=%s) or date is null group by typeActe, budget, vola, idActe";
        sql=String.format(sql,anne,mois);
        Vector[]valiny= FonctionBase.select(sql);
        Vector<Acte>actes=Acte.list();
        int size= valiny[0].size();
        Vector<Integer>idActe=new Vector<>();
        for (int i = 0; i <size ; i++) {
            idActe.add((int)valiny[4].elementAt(i));
        }
        for (Acte a : actes){
                ActeBudget s=new ActeBudget(a.getType(),0,a.getBudget()/12,0);
                acte.add(s);

        }
        for (int i = 0; i <valiny[0].size() ; i++) {

            for (ActeBudget t : acte){
                if (t.getType().equalsIgnoreCase((String) valiny[1].elementAt(i))){
                    t.setReel(t.getReel()+(double) valiny[0].elementAt(i));
                    t.setPourcentage(BudgetJour2.pourcentage(t.getReel(),t.getBudget()));
                }
            }

            /*ActeBudget s=new ActeBudget((String) valiny[1].elementAt(i),(double) valiny[0].elementAt(i),(double) valiny[2].elementAt(i),(double) valiny[3].elementAt(i));
            acte.add(s);*/
        }
        return acte;
    }
}
