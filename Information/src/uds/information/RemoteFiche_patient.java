package uds.information;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import main.beans.Fiche_patient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public interface RemoteFiche_patient extends Remote{
    public Object getFiche_patientById(long id)  throws RemoteException;
    public Object getFiche_patientByNom(String nom)  throws RemoteException;
    public Object getFiche_patientByNom(String nom,String Prenom)  throws RemoteException;
    public Object getFiche_patientByCni(long cni)  throws RemoteException;
    public ArrayList<Fiche_patient> getAllFiche_patient()  throws RemoteException;
    public ArrayList<Fiche_patient> getAllFiche_patientMalade()  throws RemoteException;

    public Object createFiche_patient(Fiche_patient f) throws RemoteException;

    public Object updateFiche_patient(Fiche_patient f) throws RemoteException;

    public String getSms()  throws RemoteException;
}
