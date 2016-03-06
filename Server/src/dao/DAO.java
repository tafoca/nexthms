/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
import jdbc.ConnectionPostgreSQL;

/**
 *
 * @author Adrien MOMO
 */
public abstract class DAO<T> {
    public Connection connect = ConnectionPostgreSQL.getInstance();

    public abstract T find(long id);
    public abstract T create(T obj);
    public abstract T update(T obj);
    public abstract void delete(T obj);
}
