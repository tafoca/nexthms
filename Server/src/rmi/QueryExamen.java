/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import dao.ExamenDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import main.beans.Examen;
import uds.information.RemoteExamen;

/**
 *
 * @author user
 */
public class QueryExamen extends UnicastRemoteObject implements RemoteExamen{

    ExamenDAO examenDao = new ExamenDAO();

    protected QueryExamen() throws RemoteException{
        super();
    }

    @Override
    public ArrayList getAllExamenByIdPatient(long id) throws RemoteException {
        return examenDao.findAllByIdPatient(id);
    }

    @Override
    public Object saveExamen(Examen e) throws RemoteException {
        return examenDao.create(e);
    }

    @Override
    public void remove(Examen e) throws RemoteException {
        examenDao.delete(e);
    }

    @Override
    public ArrayList getAllExamen() throws RemoteException {
        return examenDao.findAll();
    }

    @Override
    public Object updateExamen(Examen e) throws RemoteException {
        return examenDao.update(e);
    }

}
