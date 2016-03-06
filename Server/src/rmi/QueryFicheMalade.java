/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import dao.FicheMaladeDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import main.beans.Allergie;
import main.beans.FicheMalade;
import main.beans.Fiche_patient;
import uds.information.RemoteAllergie;
import uds.information.RemoteFicheMalade;

/**
 *
 * @author user
 */
public class QueryFicheMalade extends UnicastRemoteObject implements RemoteFicheMalade{

    FicheMaladeDAO ficheMaladeDao = new FicheMaladeDAO();

    protected QueryFicheMalade() throws RemoteException{
        super();
    }

    @Override
    public Object saveFicheMalade(Fiche_patient f) throws RemoteException {
        return ficheMaladeDao.create(f);
    }

    @Override
    public Object getFicheMalade(Fiche_patient f) throws RemoteException {
        FicheMalade fiche = ficheMaladeDao.findByIdPatient(f.getNum_fiche());
        System.out.println(fiche.toString());
        ficheMaladeDao.delete(fiche);
        return fiche;
    }

}
