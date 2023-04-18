package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    private Connection connection;

    public UserDaoJdbcImpl() {
        this.connection = Util.getConnection();
    }

    public void createUsersTable() {
        String sql = "create table if not exists users(id serial primary key,name varchar, last_name varchar,age smallint)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Successfully create table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "drop table users";
        try (Statement statement = connection.createStatement()) {
            int i = statement.executeUpdate(sql);
            System.out.println(i + " dropped!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into users(name,last_name,age)values (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("user is saved!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = "delete from users where id = ?";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            prepared.setLong(1, id);
            int i = prepared.executeUpdate();
            System.out.println(i + " deleted!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> all = new ArrayList<>();
        String sql = "select * from users";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {
                all.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return all;
    }

    public void cleanUsersTable() {
        String sql = " delete * table users";
        try (PreparedStatement prepared = connection.prepareStatement(sql)) {
            int i = prepared.executeUpdate();
            System.out.println(i + " clean user table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}