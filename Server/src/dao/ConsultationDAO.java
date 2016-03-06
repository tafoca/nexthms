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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import main.beans.Consultation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JOB DESIRE NGOUNOU
 */
public class ConsultationDAO extends DAO<Consultation>{

    @Override
    public Consultation find(long id) {
        Consultation con = new Consultation();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM consultation WHERE num_consultation = "+id);
            if(result.first()){
                con = new Consultation(result.getLong("num_consultation"), result.getString("motif"), result.getDate("date"),result.getInt("poids"),result.getInt("temperature"),result.getInt("tention"), result.getString("diagnostic"), result.getLong("id_medecin"),result.getLong("id_patient"),true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
    public ArrayList<Consultation> findAllByIdPatient(long id) {
        ArrayList<Consultation> con = new ArrayList();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM consultation WHERE id_patient = "+id+" AND nouveau_traitement = true");
            while(result.next()){
                con.add(new Consultation(result.getLong("num_consultation"), result.getString("motif"), result.getDate("date"),result.getInt("poids"),result.getInt("temperature"),result.getInt("tention"), result.getString("diagnostic"), result.getLong("id_medecin"),result.getLong("id_patient"),result.getBoolean("nouveau_traitement")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    @Override
    public Consultation create(Consultation obj) {
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT NEXTVAL('consultation_num_consultation_seq') as id");
            if(result.first()){
                long id = result.getLong("id");
                PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO consultation (num_consultation,motif,date,poids,temperature,tention,diagnostic,id_medecin,id_patient,nouveau_traitement) VALUES(?,?,?,?,?,?,?,?,?,true)");
                prepare.setLong(1, id);
                prepare.setString(2, obj.getMotif());
                prepare.setDate(3, (java.sql.Date)obj.getDate());
                prepare.setInt(4, obj.getPoids());
                prepare.setInt(5, obj.getTemperature());
                prepare.setInt(6, obj.getTention());
                prepare.setString(7, obj.getDiagnostic());
                prepare.setLong(8, obj.getId_medecin());
                prepare.setLong(9, obj.getId_patient());

                prepare.executeUpdate();
                obj = this.find(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Consultation update(Consultation obj) {
        try {
            ResultSet result = connect.createStatement().executeQuery("UPDATE consultation SET "
                    +",motif = "+obj.getMotif()
                    +",date = "+obj.getDate()
                    +",diagnostic= "+obj.getDiagnostic()
                    +",id_medecin = "+obj.getId_medecin()
                    +" WHERE num_consultation="
                    +obj.getNum_consultation()

            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        obj = this.find(obj.getNum_consultation());
        return obj;
    }

    @Override
    public void delete(Consultation obj) {
        try {
            ResultSet result = connect.createStatement().executeQuery("DELETE FROM consultation WHERE num_consultation = "+obj.getNum_consultation());
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e.getMessage(), "notification of delete",JOptionPane.DEFAULT_OPTION);
        }
    }

}


