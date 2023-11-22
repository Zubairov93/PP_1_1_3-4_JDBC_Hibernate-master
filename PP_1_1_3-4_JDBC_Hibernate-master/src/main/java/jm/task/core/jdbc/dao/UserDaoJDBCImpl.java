package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = null;
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Statement statement = null;
        String sql = "CREATE TABLE IF NOT EXISTS `Users` ( `id` bigint NOT NULL AUTO_INCREMENT, `name` varchar(45) NOT NULL, `lastName` varchar(45) NOT NULL,  `age` tinyint NOT NULL, PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;"

        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ea) {
                System.out.println("Rollback ecxeption");
            }
        }
    }

    public void dropUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "DROP TABLE IF EXISTS Users";
        try{
            connection = Util.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ea) {
                System.out.println("Rollback ecxeption");
            }
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        String sql = " INSERT INTO Users (name, lastName, age) VALUES(?, ?, ?,)";

        try {
            connection = Util.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            Statement statement = connection.createStatement();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ea) {
                System.out.println("Rollback ecxeption");
            }

        }

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
