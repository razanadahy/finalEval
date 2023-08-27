package com.example.frontoffice.Function;

import com.example.frontoffice.Base.FonctionBase;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

public class Function {

    public static int calculateAge(Date date) {
        //Date date=new Date();
        LocalDate birthDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
    public static String affichageDouble(double valiny){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(valiny);
    }
    public static int arround(double valeur){
        return (int) Math.round(valeur);
    }
    public static String[]mois=new String[]{
            "--","Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"
    };

    public static double valeur() throws Exception {
        String sql="select sum(budget/12) from acte";
        Vector valiny= FonctionBase.select(sql)[0];
        if (!valiny.isEmpty()){
            try{
                return (double) valiny.elementAt(0);
            }catch (Exception e){
                return 0;
            }
        }
        return 0;
    }
    public static double valeur2() throws Exception {
        String sql="select sum(budget/12) from depense";
        Vector valiny= FonctionBase.select(sql)[0];
        if (!valiny.isEmpty()){
            try{
                return (double) valiny.elementAt(0);
            }catch (Exception e){
                return 0;
            }
        }
        return 0;
    }
    public static boolean valide(String date) throws ParseException {
        /*Date date1=new Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime());
        String valid=date1.toString();
        System.out.println(valid);
        return date.compareTo(valid) == 0;*/
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
        sd.setLenient(false);
        try {
            Date s=sd.parse(date);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public static void main(String[] args) throws Exception {
        System.out.println(valeur());
        System.out.println(valide("2022-02-31"));
    }

}
