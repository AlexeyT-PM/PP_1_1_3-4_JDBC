package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String SQLcreat = "CREATE TABLE IF NOT EXISTS kataTest (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40), last_name VARCHAR(40), age INT)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQLcreat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String SQLdrop = "DROP TABLE IF EXISTS kataTest";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQLdrop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQLsave = "INSERT INTO kataTest (name, last_name, age) VALUES (?, ?, ?)";
        try (PreparedStatement pstm = connection.prepareStatement(SQLsave)) {
            pstm.setString(1, name);
            pstm.setString(2, lastName);
            pstm.setByte(3, age);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
//        String SQLremove = "SELECT * FROM kataTest " + id;
        String SQLremove = "DELETE FROM kataTest WHERE id= " + id;
        try (PreparedStatement pstm = connection.prepareStatement(SQLremove)) {
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        String SQLget = "SELECT * FROM kataTest";
        try (ResultSet resultSet = connection.createStatement().executeQuery(SQLget)) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("last_name"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public void cleanUsersTable() {
        String SQLclean = "TRUNCATE TABLE kataTest";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQLclean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
