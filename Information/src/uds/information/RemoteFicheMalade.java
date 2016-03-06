package uds.information;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import main.beans.Allergie;
import main.beans.FicheMalade;
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
public interface RemoteFicheMalade extends Remote{
    public Object saveFicheMalade(Fiche_patient f) throws RemoteException;
    public Object getFicheMalade(Fiche_patient f) throws RemoteException;
}
