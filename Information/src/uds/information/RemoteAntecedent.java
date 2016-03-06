package uds.information;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import main.beans.Antecedent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public interface RemoteAntecedent extends Remote{
    public ArrayList getAllAntecedentByIdPatient(long id)  throws RemoteException;
    public Object saveAntecedent(Antecedent a) throws RemoteException;
}
