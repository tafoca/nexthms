/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import main.beans.Antecedent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author JOB
 */
public class AntecedentDAO extends DAO<Antecedent> {

    @Override
    public Antecedent find(long id) {
        Antecedent ant = new Antecedent();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM antecedent WHERE num_antecedent = "+id);
            if(result.first()){
                ant = new Antecedent(id, result.getString("categorie"),  result.getString("description"),result.getDate("date"), result.getString("pronostic"), result.getLong("id_patient"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ant;
    }

    public ArrayList findAllByIdPatient(long id) {
        ArrayList<Antecedent> ant = new ArrayList();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM antecedent WHERE id_patient = "+id);
            while(result.next()){
                ant.add(new Antecedent(id, result.getString("categorie"),  result.getString("description"),result.getDate("date"), result.getString("pronostic"), result.getLong("id_patient")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ant;
    }
    @Override
    public Antecedent create(Antecedent obj) {
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT NEXTVAL('antecedent_num_antecedent_seq') as id");
            if(result.first()){
                long id = result.getLong("id");
                PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO antecedent (num_antecedent,categorie,date,description,pronostic,id_patient) VALUES(?,?,?,?,?,?)");
                prepare.setLong(1, id);
                prepare.setString(2, obj.getCategorie());
                prepare.setDate(3, obj.getDate());
                prepare.setString(4, obj.getDescription());
                prepare.setString(5, obj.getPronostique());
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
    public Antecedent update(Antecedent obj) {
        try {
            ResultSet result = connect.createStatement().executeQuery("UPDATE antecedent SET "
                    +"categorie = "+obj.getCategorie()
                    +",date = "+obj.getDate()
                    +",description = "+obj.getDescription()
                    +",pronostic = "+obj.getPronostique()
                    +",id_patient = "+obj.getId_patient()
                    +" WHERE num_antecedent="
                    +obj.getNum_antecedent()

            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        obj = this.find(obj.getNum_antecedent());
        return obj;
    }

    @Override
    public void delete(Antecedent obj) {
        try {
            ResultSet result = connect.createStatement().executeQuery("DELETE FROM antecedent WHERE num_antecedent = "+obj.getNum_antecedent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
