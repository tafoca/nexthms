/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import dao.AllergieDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import main.beans.Allergie;
import uds.information.RemoteAllergie;

/**
 *
 * @author user
 */
public class QueryAllergie extends UnicastRemoteObject implements RemoteAllergie{

    AllergieDAO allergieDao = new AllergieDAO();

    protected QueryAllergie() throws RemoteException{
        super();
    }

    @Override
    public ArrayList getAllAllergieByIdPatient(long id) throws RemoteException {
        return allergieDao.findAllByIdPatient(id);
    }

    @Override
    public Object saveAllergie(Allergie a) throws RemoteException {
        return allergieDao.create(a);
    }


}
