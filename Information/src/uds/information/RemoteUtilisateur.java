package uds.information;


import java.rmi.Remote;
import java.rmi.RemoteException;
import main.beans.Utilisateur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public interface RemoteUtilisateur extends Remote{
    public Object getAllUtilisateur()  throws RemoteException;
    public Object getUtilisateur(long id)  throws RemoteException;
    public Object getUtilisateurByLogin(String login)  throws RemoteException;
    public String texteTest() throws RemoteException;
    public Utilisateur AjouterUtilisateur(Utilisateur obj)throws RemoteException;
    public  Utilisateur UpdateUtilisateur(Utilisateur obj)throws RemoteException;
}
