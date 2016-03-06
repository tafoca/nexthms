package uds.information;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import main.beans.Allergie;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public interface RemoteAllergie extends Remote{
    public ArrayList getAllAllergieByIdPatient(long id)  throws RemoteException;
    public Object saveAllergie(Allergie a) throws RemoteException;
}
