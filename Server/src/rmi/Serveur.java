/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import dao.AntecedentDAO;
import dao.Fiche_patientDAO;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.beans.Antecedent;
import main.beans.Fiche_patient;
import static sun.net.www.http.HttpClient.New;

/**
 *
 * @author user
 */
public class Serveur {

    public Serveur(){
//        System.setSecurityManager(new SecurityManager());
/*        Fiche_patientDAO fiche=new Fiche_patientDAO();
        Fiche_patient f = new Fiche_patient(1, "MOMO", "Adrien", 111111, new Date(00,00,00), 674000407, "etudiant", 'M');
        fiche.create(f);
        AntecedentDAO an = new AntecedentDAO();
        Antecedent a= new Antecedent(0, "Accident", "Rupture du tibia", new Date(2010, 2, 14),"Mancho a vie", 4);
        an.create(a);
*/
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            QueryUtilisateur utilisateur = new QueryUtilisateur();
            QueryFiche_patient patient = new QueryFiche_patient();
            QueryAntecedent antecedent = new QueryAntecedent();
            QueryAllergie allergie =new QueryAllergie();
            QueryConsultation consultation = new QueryConsultation();
            QueryOrdonance ordonance = new QueryOrdonance();
            QueryExamen examen = new QueryExamen();
            QueryFicheMalade ficheMalade = new QueryFicheMalade();
            QueryTimeTable timetable=new QueryTimeTable();

            Naming.rebind("rmi://127.0.0.1/serveurHospital/utilisateur", utilisateur);
            Naming.rebind("rmi://127.0.0.1/serveurHospital/fiche_patient", patient);
            Naming.rebind("rmi://127.0.0.1/serveurHospital/antecedent", antecedent);
            Naming.rebind("rmi://127.0.0.1/serveurHospital/allergie", allergie);
            Naming.rebind("rmi://127.0.0.1/serveurHospital/consultation", consultation);
            Naming.rebind("rmi://127.0.0.1/serveurHospital/ordonance", ordonance);
            Naming.rebind("rmi://127.0.0.1/serveurHospital/examen", examen);
            Naming.rebind("rmi://127.0.0.1/serveurHospital/ficheMalade", ficheMalade);
            Naming.rebind("rmi://127.0.0.1/serveurHospital/emploi_de_temps",timetable);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
