package uds.information;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import main.beans.Consultation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public interface RemoteConsultation extends Remote{
    public ArrayList getAllConsultationByIdPatient(long id)  throws RemoteException;
    public Consultation saveConsultation(Consultation a) throws RemoteException;
    public void remove(Consultation c) throws RemoteException;
}
