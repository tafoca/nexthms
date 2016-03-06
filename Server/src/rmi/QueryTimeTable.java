/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import dao.emploi_de_tempsDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.util.LinkedList;
import main.beans.emploi_de_temps;
import uds.information.RemoteEmploiTemps;

/**
 *
 * @author cabrel
 */
public class QueryTimeTable extends UnicastRemoteObject implements RemoteEmploiTemps {

    public QueryTimeTable() throws RemoteException {
        super();
    }

    @Override
    public emploi_de_temps getemploi_de_temps(long id) throws RemoteException {
        emploi_de_tempsDAO emploitemps = new emploi_de_tempsDAO();
        return emploitemps.find(id);
    }

    @Override
    public emploi_de_temps getemploi_de_tempsByLogin(String login) throws RemoteException {
        return null;
    }

    @Override
    public emploi_de_temps Ajouteremploi_de_temps(emploi_de_temps obj) throws RemoteException {
        emploi_de_tempsDAO emploitemps = new emploi_de_tempsDAO();
        return emploitemps.create(obj);
    }

    @Override
    public emploi_de_temps Updateemploi_de_temps(emploi_de_temps obj) throws RemoteException {
        emploi_de_tempsDAO emploitemps = new emploi_de_tempsDAO();
        return emploitemps.update(obj);
    }

    @Override
    public LinkedList<emploi_de_temps> sellectAlluser(long id) throws RemoteException {
        emploi_de_tempsDAO emploitemps = new emploi_de_tempsDAO();
        return  (LinkedList<emploi_de_temps>)emploitemps.sellectAll(id);
    }



}
