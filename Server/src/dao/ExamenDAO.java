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

import main.beans.Examen;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Adrien MOMO
 */
public class ExamenDAO extends DAO<Examen>{

    @Override
    public Examen find(long id) {
        Examen exam = new Examen();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM examen WHERE numero = "+id);
            if(result.first()){
                exam = new Examen(result.getLong("numero"),result.getString("type"),result.getLong("num_patient"),result.getString("nom"),result.getString("resulta"),result.getLong("id_medecin"),result.getBoolean("nouveau_traitement"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exam;
    }

    public ArrayList<Examen> findAllByIdPatient(long id) {
        ArrayList<Examen> exam = new ArrayList();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM examen WHERE num_patient = "+id);
            while(result.next()){
                exam.add(new Examen(result.getLong("numero"),result.getString("type"),result.getLong("num_patient"),result.getString("nom"),result.getString("resulta"),result.getLong("id_medecin"),result.getBoolean("nouveau_traitement")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exam;
    }
    public ArrayList findAll() {
        ArrayList<Examen> exam = new ArrayList();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM examen ");
            while(result.next()){
                exam.add(new Examen(result.getLong("numero"),result.getString("type"),result.getLong("num_patient"),result.getString("nom"),result.getString("resulta"),result.getLong("id_medecin"),result.getBoolean("nouveau_traitement")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exam;
    }

    @Override
    public Examen create(Examen obj) {
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT NEXTVAL('examen_numero_seq') as id");
            if(result.first()){
                long id = result.getLong("id");
                PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO examen (numero,num_patient,nom,resulta,id_medecin,type,nouveau_traitement) VALUES(?,?,?,?,?,?,?)");
                prepare.setLong(1, id);
                prepare.setLong(2, obj.getNum_patient());
                prepare.setString(3, obj.getNom());
                prepare.setString(4,obj.getResulta());
                prepare.setLong(5, obj.getId_medecin());
                prepare.setObject(6, obj.getType());
                prepare.setBoolean(7, obj.isNouveauTraitement());

                prepare.executeUpdate();
                obj = this.find(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Examen update(Examen obj) {
        try {
            connect.createStatement().executeUpdate("UPDATE examen SET "
                    + "resulta ='"+obj.getResulta()
                    +"' WHERE numero="
                    +obj.getNumero()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        obj = this.find(obj.getNumero());
        return obj;
    }

    @Override
    public void delete(Examen obj) {
        try {
            ResultSet result = connect.createStatement().executeQuery("DELETE FROM examen WHERE numero = "+obj.getNumero());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

