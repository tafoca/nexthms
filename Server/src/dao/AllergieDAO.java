/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import main.beans.Allergie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author JOB
 */
public class AllergieDAO extends DAO<Allergie> {

    @Override
    public Allergie find(long id) {
        Allergie ant = new Allergie();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM allergie WHERE num_allergie = "+id);
            if(result.first()){
                ant = new Allergie(id, result.getString("type"), result.getString("nom"),  result.getString("description"),result.getLong("id_patient"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ant;
    }

    public ArrayList findAllByIdPatient(long id) {
        ArrayList<Allergie> ant = new ArrayList();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM allergie WHERE id_patient = "+id);
            while(result.next()){
                ant.add(new Allergie(result.getLong("num_allergie"), result.getString("type"), result.getString("nom"),  result.getString("description"),id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ant;
    }
    @Override
    public Allergie create(Allergie obj) {
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT NEXTVAL('antecedent_num_antecedent_seq') as id");
            if(result.first()){
                long id = result.getLong("id");
                PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO allergie (num_allergie,type,description,id_patient) VALUES(?,?,?,?)");
                prepare.setLong(1, id);
                prepare.setString(2, obj.getType());
                prepare.setString(3, obj.getDescription());
                prepare.setLong(4, obj.getId_patient());


                prepare.executeUpdate();
                obj = this.find(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Allergie update(Allergie obj) {
        try {
            ResultSet result = connect.createStatement().executeQuery("UPDATE allergie SET "
                    +",type = "+obj.getType()
                    +",description = "+obj.getDescription()
                    +",id_patient = "+obj.getId_patient()
                    +" WHERE num_allergie="
                    +obj.getNum_allergie()

            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        obj = this.find(obj.getNum_allergie());
        return obj;
    }

    @Override
    public void delete(Allergie obj) {
        try {
            ResultSet result = connect.createStatement().executeQuery("DELETE FROM allergie WHERE num_antecedent = "+obj.getNum_allergie());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
