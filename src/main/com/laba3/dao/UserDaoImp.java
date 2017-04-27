package com.laba3.dao;

import com.laba3.ConnectBase;
import com.laba3.pojo.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by set on 23.04.17.
 */
public class UserDaoImp implements UserDao {

    final static Logger logger = Logger.getLogger(UserDaoImp.class);


    private static final String SELECT_ALL = "SELECT * FROM public.user";
    private static final String INSERT_INTO =
            "INSERT INTO public.user (login, password, mail, role_id) VALUES (?, ?, ?, ?)";

    private static final String UPDATE_WHERE =
            "UPDATE public.user SET login = ?, password = ?, mail = ?, role_id = ?   WHERE id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM public.user WHERE id=?";


    @Override
    public User findUserByLoginAndPassword(String login, String password) {

        User user = null;

        try (Connection connection = ConnectBase.initConnection();
             PreparedStatement statement = connection
                     .prepareStatement( "SELECT * FROM public.user WHERE login = ? AND password = ?")) {

            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createEntity(resultSet);
            }

            logger.debug("user " + user);
        } catch (SQLException e) {
            logger.error(e);
        }

        return user;

    }

    @Override
    public List<User> getAll() {
        Connection connection = null;
        Statement statement = null;
        List<User> list = new ArrayList<>();

        try {
            connection =  ConnectBase.initConnection();
             statement = connection.createStatement();
            ResultSet result =
                    statement.executeQuery("SELECT * FROM public.user");

            while (result.next()) {
                User user = new User();
                user.setId(result.getLong(1));
                user.setLogin(result.getString(2));
                user.setPassword(result.getString(3));
                user.setMail(result.getString(4));
                user.setRole_id(result.getInt("Role_id"));

                list.add(user);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
            System.out.println("Что-то не так");
//        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    logger.info(ex);
//                }
//            }
        }
        return list;

    }

    @Override
    public User getById(Long id) {
        User user = null;

        try {
            Connection connection = ConnectBase.initConnection();
            PreparedStatement statement = connection
                    .prepareStatement(SELECT_ALL + " WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createEntity(resultSet);
            }
            logger.debug(user);
        } catch (SQLException e) {
            logger.error(e);
        }

        return user;
    }

    @Override
    public Long insert(User entity) {
        long result = -1;
        try (Connection connection = ConnectBase.initConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_INTO,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getMail());
            statement.setLong(4, entity.getRole_id());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getLong(1);
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public void update(User entity) {

        try (Connection connection = ConnectBase.initConnection();
             PreparedStatement statement = connection
                     .prepareStatement(UPDATE_WHERE)) {

            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getMail());
            statement.setLong(4, entity.getRole_id());
            statement.setLong(5, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }

    }

    @Override
    public void delete(User entity) {

        try (Connection connection = ConnectBase.initConnection();
             PreparedStatement statement = connection
                     .prepareStatement(DELETE_BY_ID)) {

            statement.setLong(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e);
        }

    }

    private User createEntity(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getLong("id"),
                resultSet.getString("login"),
                resultSet.getString("password"),
                resultSet.getString("mail"),
                resultSet.getInt("role_id"));
    }
}
