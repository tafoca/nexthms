/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.util;

import java.util.LinkedList;
import main.beans.Utilisateur;

/**
 *
 * @author cabrel
 */
public class Transformlist {
    LinkedList<UtilisateurToDisplay> l2;
    LinkedList<Utilisateur> l1;
    public Transformlist() {
    }

    public LinkedList<UtilisateurToDisplay> getL2() {
        return l2;
    }

    public LinkedList<Utilisateur> getL1() {
        return l1;
    }

    public void setL2(LinkedList<UtilisateurToDisplay> l2) {
        this.l2 = l2;
    }

    public void setL1(LinkedList<Utilisateur> l1) {
        this.l1 = l1;
    }

    public void addListe(Utilisateur u1,UtilisateurToDisplay u2){
        if(u1==null && u2!=null){
            l2.add(u2);
        }else if(u2==null && u1!=null){
                      l1.add(u1);

        }
    }

    public LinkedList<UtilisateurToDisplay> changelist( LinkedList<UtilisateurToDisplay> l2, LinkedList<Utilisateur> l1){

        int n1=l1.size();
        for(int i=0;i<n1;i++){
         l2.add(new UtilisateurToDisplay("", ""));
         }

      int n2=l2.size();
        if(n1==n2){
           for(int i=0;i<n1;i++){
               l2.get(i).setId_usr(l1.get(i).getNum_utilisateur());
               l2.get(i).setLogin_usr(l1.get(i).getLogin());
               l2.get(i).setMail_usr(l1.get(i).getMail());
               l2.get(i).setNom_usr(l1.get(i).getNom());
               l2.get(i).setPass_usr(l1.get(i).getMot_de_passe());
               l2.get(i).setPhone_usr((int) l1.get(i).getPhone());
               l2.get(i).setPrenom_usr(l1.get(i).getPrenom());
               l2.get(i).setProfil_usr(l1.get(i).getProil());
           }
            setL2(l2);
            return this.l2 ;
        }else{
            return null;
        }
    }

    public static void main(String[] args) {
        Transformlist t=new Transformlist();
        UtilisateurToDisplay u2=new UtilisateurToDisplay("cabrel", "cabrel","cabrel", "cabrel","Admin","L",67890338,Long.valueOf("4677"),"cabrel@mail.com","ACTIVE","bjn");
        Utilisateur u1=new Utilisateur(Long.valueOf("4677"),"nom","prenom","login","mot_de_passe","statut",'L',657789000,"mail@.nj"," proil","bjn") ;
LinkedList<Utilisateur> l1=new LinkedList<>();
        LinkedList<UtilisateurToDisplay> l2=new LinkedList<>();

    l1.add(u1);
    l1.add(u1);
    l1.add(u1);
    l1.add(u1);
    t.setL1(l1);
    for (int j=0;j< l1.size();j++){
        l2.add(u2);
    }
        LinkedList<UtilisateurToDisplay> change = t.changelist(l2, l1);
         System.out.printf("nom = %s ,prenom = %s",t.getL2().get(0).getNom_usr(),t.getL2().get(0).getPrenom_usr()).println();
    }
}
