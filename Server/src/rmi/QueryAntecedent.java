/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import dao.AntecedentDAO;
import dao.UtilisateurDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import main.beans.Antecedent;
import uds.information.RemoteAntecedent;

/**
 *
 * @author user
 */
public class QueryAntecedent extends UnicastRemoteObject implements RemoteAntecedent{

    AntecedentDAO antecedentDao = new AntecedentDAO();

    protected QueryAntecedent() throws RemoteException{
        super();
    }

    @Override
    public ArrayList getAllAntecedentByIdPatient(long id) throws RemoteException {
        return antecedentDao.findAllByIdPatient(id);
    }

    @Override
    public Object saveAntecedent(Antecedent a) throws RemoteException {
        return antecedentDao.create(a);
    }


}
