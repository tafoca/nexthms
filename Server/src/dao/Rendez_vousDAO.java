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

import main.beans.Rendez_vous;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author JOB DESIRE NGOUNOU
 */
public class Rendez_vousDAO extends DAO<Rendez_vous>{

    @Override
    public Rendez_vous find(long id) {
        Rendez_vous rdv = new Rendez_vous();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM rendez_vous WHERE num_rdv = "+id);
            if(result.first()){
                rdv = new Rendez_vous(result.getLong("num_rdv"), result.getTime("heure"), result.getDate("date"), result.getLong("id_medecin"), result.getLong("id_patient"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rdv;
    }

    @Override
    public Rendez_vous create(Rendez_vous obj) {
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT NEXTVAL('rendez_vous_id_seq') as id");
            if(result.first()){
                long id = result.getLong("id");
                PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO rendez_vous (num_rdv,heure,date,id_medecin,id_patient) VALUES(?,?,?,?,?)");
                prepare.setLong(1, id);
                prepare.setTime(3, (java.sql.Time)obj.getHeure());
                prepare.setDate(4, (java.sql.Date)obj.getDate());
                prepare.setLong(5, obj.getId_medecin());
                prepare.setLong(6, obj.getId_patient());

                prepare.executeUpdate();
                obj = this.find(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Rendez_vous update(Rendez_vous obj) {
        try {
            ResultSet result = connect.createStatement().executeQuery("UPDATE rendez_vous SET "
                    +",heure = "+obj.getHeure()
                    +",date = "+obj.getDate()
                    +",id_medecin = "+obj.getId_medecin()
                    +",id_patient= "+obj.getId_patient()

                    +" WHERE num_rdv="
                    +obj.getNum_rdv()

            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        obj = this.find(obj.getNum_rdv());
        return obj;
    }

    @Override
    public void delete(Rendez_vous obj) {
        try {
            ResultSet result = connect.createStatement().executeQuery("DELETE FROM rendez_vous WHERE num_rdv = "+obj.getNum_rdv());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

