package com.example.frontoffice.CliniqueModel;

import com.example.frontoffice.Function.Function;

import java.util.Vector;

public class BudgetJour2 {
    Vector<ActeBudget>actes;
    Vector<DepenseBudget>depenses;

    public BudgetJour2(Vector<ActeBudget> actes, Vector<DepenseBudget> depenses) {
        this.actes = actes;
        this.depenses = depenses;
    }

    public Vector<ActeBudget> getActes() {
        return actes;
    }

    public Vector<DepenseBudget> getDepenses() {
        return depenses;
    }

    public double getRecette() {
        double valiny=0;
        for (ActeBudget p : actes){
            valiny+=p.getReel();
        }
        return valiny;
    }

    public double getDepense() {
        double valiny=0;
        for (DepenseBudget p : depenses){
            valiny+=p.getReel();
        }
        return valiny;
    }
    public double getActBudget() throws Exception {
        /*double valiny=0;
        for (ActeBudget p : actes){
            valiny+=p.getBudget();
        }
        if (actes.size()==5){
            valiny+=-250000;;
        }
        return valiny;*/
        return Function.valeur();
    }
    public double getDeptBudget() throws Exception {
        return Function.valeur2();
    }
    public static double pourcentage(double reel,double budget){
        if (budget==0){
            return 0;
        }
        return (reel/budget)*100;
    }
    public static BudgetJour2 budgetJour2(int mois,int anne) throws Exception {
        return new BudgetJour2(ActeBudget.acte(mois, anne),DepenseBudget.acte(mois, anne));
    }
}
