
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import main.beans.emploi_de_temps;

/**
 *
 * @author JOB DESIRE NGOUNOU
 */
public class emploi_de_tempsDAO extends DAO<emploi_de_temps>{

    @Override
    public emploi_de_temps find(long id) {
        emploi_de_temps emp = new emploi_de_temps();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM emploi_de_temps WHERE id_emploi = "+id);
            if(result.first()){
                emp = new emploi_de_temps();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }

    @Override
    public emploi_de_temps create(emploi_de_temps obj) {
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT NEXTVAL('emploi_de_temps_id_seq') as id");
            if(result.first()){
                long id = result.getLong("id");
                PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO emploi_de_temps (id_emploi,horaire,lundi,mardi,mercredi,jeudi,vendredi,samedi) VALUES(?,?,?,?,?,?,?.?)");
                prepare.setLong(1, id);
                prepare.setTime(2, (java.sql.Time)obj.getHoraire());
                prepare.setString(3, obj.getLundi());
                prepare.setString(4, obj.getMardi());
                prepare.setString(5, obj.getMercredi());
                prepare.setString(6, obj.getJeudi());
                prepare.setString(7, obj.getVendredi());
                prepare.setString(6, obj.getSamedi());


                prepare.executeUpdate();
                obj = this.find(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

     public LinkedList<emploi_de_temps> sellectAll(long id) {
        LinkedList<emploi_de_temps> all = new LinkedList<>();
        try {
            ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM emploi_de_temps WHERE id_emploi="+id);
            while(result.next()){
                all.add(new emploi_de_temps(result.getString("lindi"), result.getString("mardi"), result.getString("mercredi"), result.getString("jeudi"), result.getString("vendredi"), result.getString("samedi"),result.getTime("horaire"),result.getLong("id_emploi")));
            }
        } catch (SQLException e) {
            int errorCode = e.getErrorCode();
        }
        return all;
    }


    @Override
    public emploi_de_temps update(emploi_de_temps obj) {
        try {
            ResultSet result = connect.createStatement().executeQuery("UPDATE emploi_de_temps SET "
                    + "horaire ="+obj.getHoraire()
                    +",lundi = "+obj.getLundi()
                    +",mardi = "+obj.getMardi()
                    +",mercredi = "+obj.getMercredi()
                    +",jeudi = "+obj.getJeudi()
                    +",vendredi = "+obj.getVendredi()
                    +",samedi = "+obj.getSamedi()
                    +" WHERE id_emploi="
                    +obj.getId_emploi()

            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        obj = this.find(obj.getId_emploi());
        return obj;
    }

    @Override
    public void delete(emploi_de_temps obj) {
        try {
            ResultSet result = connect.createStatement().executeQuery("DELETE FROM emploi_de_temps WHERE id_emploi = "+obj.getId_emploi());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

