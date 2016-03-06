/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import dao.ConsultationDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import main.beans.Allergie;
import main.beans.Consultation;
import uds.information.RemoteConsultation;

/**
 *
 * @author user
 */
public class QueryConsultation extends UnicastRemoteObject implements RemoteConsultation{


    ConsultationDAO consultationDao = new ConsultationDAO();

    protected QueryConsultation() throws RemoteException{
        super();
    }

    @Override
    public ArrayList getAllConsultationByIdPatient(long id) throws RemoteException {
        System.out.println("Dans getAllConsultationByIdPatient");
        return consultationDao.findAllByIdPatient(id);
    }


    @Override
    public Consultation saveConsultation(Consultation a) throws RemoteException {
        System.out.println("Dans saveConsultation");
        return consultationDao.create(a);
    }

    @Override
    public void remove(Consultation c) throws RemoteException {
        consultationDao.delete(c);
    }


}
