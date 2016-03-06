/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.util;

import java.util.LinkedList;
import main.beans.emploi_de_temps;

/**
 *
 * @author cabrel
 */
public class TransformListe {
 LinkedList<TimeTableToDisplay> l2;
 LinkedList<emploi_de_temps> l1;

    public TransformListe() {
    }

    public LinkedList<TimeTableToDisplay> getL2() {
        return l2;
    }

    public LinkedList<emploi_de_temps> getL1() {
        return l1;
    }

    public void setL2(LinkedList<TimeTableToDisplay> l2) {
        this.l2 = l2;
    }

    public void setL1(LinkedList<emploi_de_temps> l1) {
        this.l1 = l1;
    }

    public LinkedList<TimeTableToDisplay> changelist( LinkedList<TimeTableToDisplay> l2, LinkedList<emploi_de_temps> l1){

        int n1=l1.size();
        for(int i=0;i<n1;i++){
         l2.add(new TimeTableToDisplay(null, "", "", "", "", "", ""));
         }

      int n2=l2.size();
        if(n1==n2){
           for(int i=0;i<n1;i++){
               l2.get(i).setLundi(l1.get(i).getLundi());
               l2.get(i).setMardi(l1.get(i).getMardi());
               l2.get(i).setMercredi(l1.get(i).getMercredi());
               l2.get(i).setJeudi(l1.get(i).getJeudi());
               l2.get(i).setVendredi(l1.get(i).getVendredi());
               l2.get(i).setSamedi(l1.get(i).getSamedi());
               l2.get(i).setHoraire(l1.get(i).getHoraire());
             //  l2.get(i).setProfil_usr(l1.get(i).getProil());
           }
            setL2(l2);
            return this.l2 ;
        }else{
            return null;
        }
    }




}
