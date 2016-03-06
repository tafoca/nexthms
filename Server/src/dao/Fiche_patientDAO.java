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

import main.beans.Fiche_patient;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author JOB
 */
public class Fiche_patientDAO extends DAO<Fiche_patient>{

    @Override
    public Fiche_patient find(long id) {
        Fiche_patient fich = new Fiche_patient();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM fiche_patient WHERE mun_fiche_patient = "+id);
            if(result.first()){
                fich = new Fiche_patient(result.getLong("mun_fiche_patient"), result.getString("nom"), result.getString("prenom"), result.getLong("cni"), result.getDate("date_naissance"), result.getLong("tel"), result.getString("profession"), result.getObject("sexe").toString().charAt(0),result.getBoolean("est_en_cours_de_traitement"),result.getString("garde_malade"),result.getLong("tel_garde"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fich;
    }
    public ArrayList findAll() {
        ArrayList<Fiche_patient> fich = new ArrayList();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM fiche_patient");
            while(result.next()){
                fich.add(new Fiche_patient(result.getLong("mun_fiche_patient"), result.getString("nom"), result.getString("prenom"), result.getLong("cni"), result.getDate("date_naissance"), result.getLong("tel"), result.getString("profession"), result.getObject("sexe").toString().charAt(0),result.getBoolean("est_en_cours_de_traitement"),result.getString("garde_malade"),result.getLong("tel_garde")));
            }
            for(int i=0;i<fich.size();i++){
                System.out.println(fich.get(i).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fich;
    }
    public ArrayList findAllMalade() {
        ArrayList<Fiche_patient> fich = new ArrayList();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM fiche_patient WHERE est_en_cours_de_traitement = true");
            while(result.next()){
                fich.add(new Fiche_patient(result.getLong("mun_fiche_patient"), result.getString("nom"), result.getString("prenom"), result.getLong("cni"), result.getDate("date_naissance"), result.getLong("tel"), result.getString("profession"), result.getObject("sexe").toString().charAt(0),result.getBoolean("est_en_cours_de_traitement"),result.getString("garde_malade"),result.getLong("tel_garde")));
            }
            for(int i=0;i<fich.size();i++){
                System.out.println(fich.get(i).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fich;
    }
    public Fiche_patient findByNom(String nom, String prenom) {
        Fiche_patient fich = new Fiche_patient();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM fiche_patient WHERE nom = '"+nom+"' AND prenom = '"+prenom+"'");
            if(result.first()){
                fich = new Fiche_patient(result.getLong("mun_fiche_patient"), result.getString("nom"), result.getString("prenom"), result.getLong("cni"), result.getDate("date_naissance"), result.getLong("tel"), result.getString("profession"), result.getObject("sexe").toString().charAt(0),result.getBoolean("est_en_cours_de_traitement"),result.getString("garde_malade"),result.getLong("tel_garde"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fich;
    }
    public Fiche_patient findByCni(long cni) {
        Fiche_patient fich = new Fiche_patient();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM fiche_patient WHERE cni = "+cni);
            if(result.first()){
                fich = new Fiche_patient(result.getLong("mun_fiche_patient"), result.getString("nom"), result.getString("prenom"), result.getLong("cni"), result.getDate("date_naissance"), result.getLong("tel"), result.getString("profession"), result.getObject("sexe").toString().charAt(0),result.getBoolean("est_en_cours_de_traitement"),result.getString("garde_malade"),result.getLong("tel_garde"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fich;
    }

    @Override
    public Fiche_patient create(Fiche_patient obj) {
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT NEXTVAL('fiche_patient_id_seq') as id");
            if(result.first()){
                long id = result.getLong("id");
                PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO fiche_patient (mun_fiche_patient,nom,prenom,cni,date_naissance,tel,profession,sexe,est_en_cours_de_traitement,garde_malade,tel_garde) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
                prepare.setLong(1, id);
                prepare.setString(2, obj.getNom());
                prepare.setString(3, obj.getPrenom());
                prepare.setLong(4, obj.getCni());
                prepare.setDate(5, (java.sql.Date) obj.getDate_naissance());
                prepare.setLong(6, obj.getTel());
                prepare.setString(7, obj.getProfession()); // a verifier
                prepare.setObject(8, obj.getSexe());
                prepare.setObject(8, obj.getSexe());
                prepare.setBoolean(9, obj.isEstEnCoursDeTraitement());
                prepare.setString(10, obj.getGardeMalade());
                prepare.setLong(11,obj.getTelGarde());

                prepare.executeUpdate();
                obj = this.find(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Fiche_patient update(Fiche_patient obj) {
        try {
                PreparedStatement prepare = this.connect.prepareStatement("UPDATE fiche_patient SET "
                    +"nom = '"+obj.getNom()
                    +"',prenom = '"+obj.getPrenom()
                    +"',cni = "+obj.getCni()
                    +",date_naissance = ?"
                    +",tel = "+obj.getTel()
                    +",profession = '"+obj.getProfession()
                    +"',sexe = '"+obj.getSexe()
                    +"',est_en_cours_de_traitement = "+obj.isEstEnCoursDeTraitement()
                    +",garde_malade = '"+obj.getGardeMalade()
                    +"',tel_garde = "+obj.getTelGarde()
                    +" WHERE mun_fiche_patient = "
                    +obj.getNum_fiche());
                prepare.setDate(1, obj.getDate_naissance());
                prepare.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        obj = this.find(obj.getNum_fiche());
        return obj;
    }

    @Override
    public void delete(Fiche_patient obj) {
        try {
            ResultSet result = connect.createStatement().executeQuery("DELETE FROM fiche_patient WHERE num_fiche_patient = "+obj.getNum_fiche());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

