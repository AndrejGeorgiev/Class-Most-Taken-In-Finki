package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) {

        HashMap<String,Integer> golema_mapa=new HashMap<>();
        for (int i=4600;i<=5300;i++){ //ENTER FROM WHAT INDEX TO WHAT INDEX TO EXPLORE
            System.out.println(i);
            Document doc = null;
            try {
                doc = Jsoup.connect("http://courses.finki.ukim.mk/user/profile.php?id="+i+"&showallcourses=1/").cookie("MoodleSession","ENTER YOUR SESSION ID HERE").get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println(doc);
            Elements content = doc.select(".profile_tree li a");
            HashMap<String,Integer> mala_mapa=new HashMap<>();
            for (Element a : content){
                String predmet=a.text().split("20|-| -")[0];
                System.out.println("PREDMET "+predmet);
                if (mala_mapa.get(predmet)==null){
                    mala_mapa.put(predmet,1);
                }
                else
                {
                    int vrednost=mala_mapa.get(predmet);
                    vrednost++;
                    mala_mapa.put(predmet,vrednost);
                }



            }
            System.out.println(mala_mapa);
            mala_mapa.entrySet().stream().forEach(entri->{

                if(golema_mapa.get(entri.getKey())==null){

                    golema_mapa.put(entri.getKey(),entri.getValue()-1);
                }
                else
                {
                    int vred=golema_mapa.get(entri.getKey());
                    golema_mapa.put(entri.getKey(),vred+entri.getValue()-1);
                }

            });










        }

        System.out.println("%%%%%%%%%%%%%%%%%%%%%");
        golema_mapa.entrySet().stream()
                .sorted(Comparator.comparing(entri->entri.getValue()))
                .forEach(entri-> System.out.println(entri.getKey() +" "+ entri.getValue()));
        System.out.println(golema_mapa);











       /* for(Element x:content){
            System.out.println("ASD" +x);
        }*/
    }
}
