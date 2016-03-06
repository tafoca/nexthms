/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uds.information;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;
import main.beans.emploi_de_temps;

/**
 *
 * @author cabrel
 */
public interface RemoteEmploiTemps extends Remote {
     // public Object getAllemploi_de_temps()  throws RemoteException;
    public emploi_de_temps getemploi_de_temps(long id)  throws RemoteException;
    public emploi_de_temps getemploi_de_tempsByLogin(String login)  throws RemoteException;
    public emploi_de_temps Ajouteremploi_de_temps(emploi_de_temps obj)throws RemoteException;
    public  emploi_de_temps Updateemploi_de_temps(emploi_de_temps obj)throws RemoteException;
     public LinkedList<emploi_de_temps> sellectAlluser(long id)throws RemoteException;

}
