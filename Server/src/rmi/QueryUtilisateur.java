/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import dao.UtilisateurDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import main.beans.Utilisateur;
import uds.information.RemoteUtilisateur;

/**
 *
 * @author user
 */
public class QueryUtilisateur extends UnicastRemoteObject implements RemoteUtilisateur {

    UtilisateurDAO utilisateurDao = new UtilisateurDAO();



    protected QueryUtilisateur() throws RemoteException {
        super();
    }

    @Override
    public Object getUtilisateur(long id) throws RemoteException {
        System.out.println("________________________________________________");
        System.out.println("Id := " + id);
        System.out.println(utilisateurDao.find(id).toString());
        return (utilisateurDao.find(id));
    }

    @Override
    public String texteTest() throws RemoteException {
        return "bla bla bla";
    }

    @Override
    public Object getAllUtilisateur() throws RemoteException {
        return (utilisateurDao.sellectAll());
    }

    @Override
    public Object getUtilisateurByLogin(String login) throws RemoteException {
        System.out.println("________________________________________________");
        System.out.println("Loggin := " + login);
        System.out.println(utilisateurDao.findByLogin(login).toString());
        return (utilisateurDao.findByLogin(login));
    }

    @Override
    public Utilisateur AjouterUtilisateur(Utilisateur obj) throws RemoteException {
        return utilisateurDao.create(obj);
    }

    @Override
    public Utilisateur UpdateUtilisateur(Utilisateur obj) throws RemoteException {
        return utilisateurDao.update(obj);
    }

}
