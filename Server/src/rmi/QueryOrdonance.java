/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import dao.OrdonanceDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import main.beans.Ordonance;
import uds.information.RemoteOrdonance;

/**
 *
 * @author user
 */
public class QueryOrdonance extends UnicastRemoteObject implements RemoteOrdonance{


    OrdonanceDAO ordonanceDao = new OrdonanceDAO();

    protected QueryOrdonance() throws RemoteException{
        super();
    }

    @Override
    public ArrayList getAllOrdonanceByIdPatient(long id) throws RemoteException {
        return ordonanceDao.findAllByIdPatient(id);
    }

    @Override
    public Object saveOrdonance(Ordonance a) throws RemoteException {
        return ordonanceDao.create(a);
    }

    @Override
    public void remove(Ordonance c) throws RemoteException {
        ordonanceDao.delete(c);
    }


}
