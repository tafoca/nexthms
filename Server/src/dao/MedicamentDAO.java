/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import main.beans.Medicament;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Adrien MOMO
 */
public class MedicamentDAO extends DAO<Medicament>{

    @Override
    public Medicament find(long id) {
        Medicament med = new Medicament();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM medicament WHERE id= "+id);
            if(result.first()){
                med = new Medicament(id, result.getString("code"), result.getString("nom"), result.getString("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return med;
    }

    @Override
    public Medicament create(Medicament obj) {
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT NEXTVAL('medicament_id_seq') as id");
            if(result.first()){
                long id = result.getLong("id");
                PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO medicament (id,code,nom,description) VALUES(?,?,?,?)");
                prepare.setLong(1, id);
                prepare.setString(2, obj.getCode());
                prepare.setString(3, obj.getNom());
                prepare.setString(4, obj.getDescription());

                prepare.executeUpdate();
                obj = this.find(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Medicament update(Medicament obj) {
        try {
            ResultSet result = connect.createStatement().executeQuery("UPDATE medicament SET "
                    + "code ="+obj.getCode()
                    +",nom = "+obj.getNom()
                    +",description = "+obj.getDescription()
                    +" WHERE id="
                    +obj.getId()

            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        obj = this.find(obj.getId());
        return obj;
    }

    @Override
    public void delete(Medicament obj) {
        try {
            ResultSet result = connect.createStatement().executeQuery("DELETE FROM medicament WHERE id= "+obj.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
