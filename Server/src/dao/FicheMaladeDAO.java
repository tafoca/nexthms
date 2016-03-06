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

import java.sql.Date;
import main.beans.FicheMalade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import main.beans.FicheMalade;
import main.beans.Fiche_patient;

/**
 *
 * @author Adrien MOMO
 */
public class FicheMaladeDAO extends DAO<FicheMalade>{

    public FicheMalade findByIdPatient(long id) {
        FicheMalade exam = new FicheMalade();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM fiche_malade WHERE id_patient = "+id);
            if(result.first()){
                exam = new FicheMalade(result.getDate("date_arrive"),result.getDate("date_sortie"),result.getBoolean("est_clos"),result.getLong("id_patient"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exam;
    }

    public ArrayList<FicheMalade> findAllByIdPatient(long id) {
        ArrayList<FicheMalade> exam = new ArrayList();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM fiche_malade WHERE num_patient = "+id);
            while(result.next()){
                exam.add(new FicheMalade(result.getDate("date_arrive"),result.getDate("date_sortie"),result.getBoolean("est_clos"),result.getLong("id_patient")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exam;
    }

    public FicheMalade create(Fiche_patient obj) {
        try {
                PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO fiche_malade (date_arrive,date_sortie,est_clos,id_patient) VALUES(?,?,?,?)");
                prepare.setDate(1, Date.valueOf(LocalDate.now()));
                prepare.setDate(2, null);
                prepare.setBoolean(3, false);
                prepare.setLong(4, obj.getNum_fiche());

                prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FicheMalade f= this.findByIdPatient(obj.getNum_fiche());
        return f;
    }

    @Override
    public FicheMalade update(FicheMalade obj) {
        try {
            ResultSet result = connect.createStatement().executeQuery("UPDATE fiche_malade SET "
                    +" WHERE numero="
                    +obj.getId_patient()

            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        obj = this.findByIdPatient(obj.getId_patient());
        return obj;
    }

    @Override
    public void delete(FicheMalade obj) {
        try {
            connect.createStatement().executeUpdate("DELETE FROM fiche_malade WHERE id_patient = "+obj.getId_patient());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public FicheMalade find(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FicheMalade create(FicheMalade obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

