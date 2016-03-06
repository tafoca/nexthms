package uds.information;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import main.beans.Allergie;
import main.beans.Ordonance;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public interface RemoteOrdonance extends Remote{
    public ArrayList getAllOrdonanceByIdPatient(long id)  throws RemoteException;
    public Object saveOrdonance(Ordonance a) throws RemoteException;
    public void remove(Ordonance c) throws RemoteException;
}
