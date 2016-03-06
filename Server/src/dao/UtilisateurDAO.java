/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import main.beans.Utilisateur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Adrien MOMO
 */
public class UtilisateurDAO extends DAO<Utilisateur>{

    @Override
    public Utilisateur find(long id) {
        Utilisateur user = new Utilisateur();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM utilisateur WHERE num_utilisateur = "+id);
            if(result.first()){
                user = new Utilisateur(id, result.getString("nom"), result.getString("prenom"), result.getString("login"), result.getString("mot_de_passe"), result.getString("statut"), result.getObject("type").toString().charAt(0), result.getLong("phone"), result.getString("email"), result.getString("statut"),result.getString("adresse"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public LinkedList<Utilisateur> sellectAll() {
        LinkedList<Utilisateur> all = new LinkedList<>();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM utilisateur");
            while(result.next()){
                all.add(new Utilisateur(result.getLong("num_utilisateur"), result.getString("nom"), result.getString("prenom"), result.getString("login"), result.getString("mot_de_passe"), result.getString("statut"), result.getObject("type").toString().charAt(0), result.getLong("phone"), result.getString("email"), result.getString("statut"),result.getString("adresse")));
            }
        } catch (SQLException e) {
            int errorCode = e.getErrorCode();
        }
        return all;
    }

    @Override
    public Utilisateur create(Utilisateur obj) {
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT NEXTVAL('utilisateur_id_seq') as id");
            if(result.first()){
                long id = result.getLong("id");
                PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO utilisateur (num_utilisateur,nom,prenom,login,mot_de_passe,statut,type,phone,email,profil,adresse) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
                prepare.setLong(1, id);
                prepare.setString(2, obj.getNom());
                prepare.setString(3, obj.getPrenom());
                prepare.setString(4, obj.getLogin());
                prepare.setString(5, obj.getMot_de_passe());
                prepare.setString(6, obj.getStatut());
                prepare.setObject(7, obj.getType()); // a verifier
                prepare.setLong(8, obj.getPhone());
                prepare.setString(9, obj.getMail());
                prepare.setString(10, obj.getProil());
                prepare.setString(11, obj.getAdresse());

                prepare.executeUpdate();
                obj = this.find(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Utilisateur update(Utilisateur obj) {
        try {
            String request="UpDATE utilisateur SET nom=?, prenom=?, login=?, mot_de_passe=?, statut=?, type=?, phone=?, email=?, profil=?, adresse=? WHERE num_utilisateur=?";
            PreparedStatement prepare=connect.prepareStatement(request);
            prepare.setString(1, obj.getNom());
            prepare.setString(2, obj.getPrenom());
            prepare.setString(3, obj.getLogin());
            prepare.setString(4, obj.getMot_de_passe());
            prepare.setString(5, obj.getStatut());
            prepare.setObject(6, obj.getType());
            prepare.setLong(7, obj.getPhone());
            prepare.setString(8,obj.getMail());
            prepare.setString(9,obj.getProil());
            prepare.setString(10, obj.getAdresse());
            prepare.setLong(11, obj.getNum_utilisateur());
            int executeUpdate = prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        obj = this.find(obj.getNum_utilisateur());
        return obj;
    }

    @Override
    public void delete(Utilisateur obj) {
        try {
            ResultSet result = connect.createStatement().executeQuery("DELETE FROM utilisateur WHERE num_utilisateur = "+obj.getNum_utilisateur());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object findByLogin(String login) {
        Utilisateur user = new Utilisateur();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM utilisateur WHERE login = '"+login+"'");
            if(result.first()){
                user = new Utilisateur(result.getLong("num_utilisateur"), result.getString("nom"), result.getString("prenom"), result.getString("login"), result.getString("mot_de_passe"), result.getString("statut"), result.getObject("type").toString().charAt(0), result.getLong("phone"), result.getString("email"), result.getString("statut"),result.getString("adresse"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
