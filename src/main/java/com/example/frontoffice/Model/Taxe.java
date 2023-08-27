package com.example.frontoffice.Model;


import com.example.frontoffice.Base.FonctionBase;

import java.util.Vector;

public class Taxe {
    public static double getTaxe() throws Exception {
        String sql="select case when montant is null then 1 else montant end from taxe order by idTaxe desc ";
        Vector taxe= FonctionBase.select(sql)[0];
        if (taxe.size()>0){
            return (double) taxe.elementAt(0);
        }
        return 0;
    }
    public static void insert(double montant) throws Exception {
        String sql="insert into taxe(montant) values (%s)";
        FonctionBase.modifBase(String.format(sql,montant));
    }
}
