/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import dao.Fiche_patientDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import main.beans.Fiche_patient;
import uds.information.RemoteFiche_patient;

/**
 *
 * @author user
 */
public class QueryFiche_patient extends UnicastRemoteObject implements RemoteFiche_patient{

    Fiche_patientDAO fice_patientDao = new Fiche_patientDAO();;

    protected QueryFiche_patient() throws RemoteException{
        super();
    }

    @Override
    public Object getFiche_patientById(long id) throws RemoteException {
        return (Object)fice_patientDao.find(id);
    }

    @Override
    public Object getFiche_patientByNom(String nom) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getFiche_patientByCni(long cni) throws RemoteException {
        return (Object)fice_patientDao.findByCni(cni);
    }

    @Override
    public String getSms() throws RemoteException {
        return "Yellow.";
    }

    @Override
    public Object createFiche_patient(Fiche_patient f) throws RemoteException {
        return (Object)fice_patientDao.create(f);
    }

    @Override
    public Object getFiche_patientByNom(String nom, String prenom) throws RemoteException {
        return (Object) fice_patientDao.findByNom(nom,prenom);
    }

    @Override
    public ArrayList<Fiche_patient> getAllFiche_patient() throws RemoteException {
        return fice_patientDao.findAll();
    }

    @Override
    public Object updateFiche_patient(Fiche_patient f) throws RemoteException {
        return fice_patientDao.update(f);
    }

    @Override
    public ArrayList<Fiche_patient> getAllFiche_patientMalade() throws RemoteException {
        return fice_patientDao.findAllMalade();
    }


}
