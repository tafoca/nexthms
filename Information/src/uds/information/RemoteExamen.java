package uds.information;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import main.beans.Allergie;
import main.beans.Examen;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public interface RemoteExamen extends Remote{
    public ArrayList getAllExamen()  throws RemoteException;
    public ArrayList getAllExamenByIdPatient(long id)  throws RemoteException;
    public Object saveExamen(Examen e) throws RemoteException;
    public Object updateExamen(Examen e) throws RemoteException;
    public void remove(Examen e) throws RemoteException;
}
