/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import main.beans.Ordonance;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author JOB
 */
public class OrdonanceDAO extends DAO<Ordonance>{

    @Override
    public Ordonance find(long id) {
        Ordonance ord = new Ordonance();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM ordonance WHERE num_ordonance = "+id);
            if(result.first()){
                ord = new Ordonance(result.getLong("num_ordonance"), result.getDate("date"),result.getString("nom_medicament"),result.getString("famille_medicament"),result.getString("forme_medicament"),result.getString("posologie"),result.getString("observation"),result.getBoolean("nouveau_traitement"), result.getLong("id_medecin"),result.getLong("id_patient"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ord;
    }
    public ArrayList findAllByIdPatient(long id) {
        ArrayList<Ordonance> ord = new ArrayList();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM ordonance WHERE id_patient = "+id);
            while(result.next()){
                ord.add(new Ordonance(result.getLong("num_ordonance"), result.getDate("date"),result.getString("nom_medicament"),result.getString("famille_medicament"),result.getString("forme_medicament"),result.getString("posologie"),result.getString("observation"),result.getBoolean("nouveau_traitement"), result.getLong("id_medecin"),result.getLong("id_patient")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ord;
    }

    @Override
    public Ordonance create(Ordonance obj) {
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT NEXTVAL('ordonance_num_ordonance_seq') as id");
            if(result.first()){
                long id = result.getLong("id");
                PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO ordonance (num_ordonance,date,nom_medicament,famille_medicament,forme_medicament,posologie,observation,nouveau_traitement,id_medecin,id_patient) VALUES(?,?,?,?,?,?,?,?,?,?)");
                prepare.setLong(1, id);
                prepare.setDate(2, (java.sql.Date)obj.getDate());
                prepare.setString(3, obj.getNom_medicament());
                prepare.setString(4, obj.getFamille_medicament());
                prepare.setString(5, obj.getForme_medicament());
                prepare.setString(6, obj.getPosologie());
                prepare.setString(7, obj.getObservation());
                prepare.setBoolean(8, obj.isNouveauTraitement());
                prepare.setLong(9, obj.getId_medecin());
                prepare.setLong(10, obj.getId_patient());

                prepare.executeUpdate();
                obj = this.find(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Ordonance update(Ordonance obj) {
        try {
            ResultSet result = connect.createStatement().executeQuery("UPDATE ordonance SET "
                    +",date = "+obj.getDate()
                    +",id_medecin = "+obj.getId_medecin()
                    +" WHERE num_ordonance="
                    +obj.getNum_ordonance()

            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        obj = this.find(obj.getNum_ordonance());
        return obj;
    }

    @Override
    public void delete(Ordonance obj) {
        try {
            ResultSet result = connect.createStatement().executeQuery("DELETE FROM ordonance WHERE num_ordonance = "+obj.getNum_ordonance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
