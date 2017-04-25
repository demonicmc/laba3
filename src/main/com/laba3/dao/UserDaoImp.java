package main.com.laba3.dao;

import main.com.laba3.ConnectBase;
import main.com.laba3.pojo.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by set on 23.04.17.
 */
public class UserDaoImp implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImp.class);


    private static final String SELECT_ALL = "SELECT id, loggin, password, mail, role_id FROM user";
    private static final String INSERT_INTO =
            "INSERT INTO user (loggin, password, mail, role_id) VALUES (?, ?, ?, ?)";

    private static final String UPDATE_WHERE =
            "UPDATE user SET loggin = ?, password = ?, mail = ?, role_id = ?   WHERE id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM user WHERE id=?";


    @Override
    public User findUserByLoginAndPassword(String loggin, String password) {

        User user = null;

        try (Connection connection = ConnectBase.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement( "SELECT * FROM user WHERE loggin = ? AND password = ?")) {

            statement.setString(1, loggin);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createEntity(resultSet);
            }

            logger.debug("user " + user);
        } catch (SQLException e) {
            logger.error(e);
        } catch (ClassNotFoundException e) {
            logger.debug(e);
        }

        return user;

    }

    @Override
    public Collection<User> getAll() {
        Set<User> users = new HashSet<>();
        try  {
            Connection connection = ConnectBase.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                users.add(createEntity(resultSet));
            }
            logger.debug(users);
        } catch (SQLException e) {
            logger.error(e);
        } catch (ClassNotFoundException e) {
            logger.debug(e);
        }

        return users;
    }

    @Override
    public User getById(Long id) {
        User user = null;

        try {
            Connection connection = ConnectBase.getConnection();
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
        } catch (ClassNotFoundException e) {
            logger.debug(e);
        }

        return user;
    }

    @Override
    public Long insert(User entity) {
        long result = -1;
        try (Connection connection = ConnectBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_INTO,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getLoggin());
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
        } catch (ClassNotFoundException e) {
            logger.debug(e);
        }
        return result;
    }

    @Override
    public void update(User entity) {

        try (Connection connection = ConnectBase.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(UPDATE_WHERE)) {

            statement.setString(1, entity.getLoggin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getMail());
            statement.setLong(4, entity.getRole_id());
            statement.setLong(5, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        } catch (ClassNotFoundException e) {
            logger.debug(e);
        }

    }

    @Override
    public void delete(User entity) {

        try (Connection connection = ConnectBase.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(DELETE_BY_ID)) {

            statement.setLong(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            logger.debug(e);
        }

    }

    private User createEntity(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getLong("id"),
                resultSet.getString("login"),
                resultSet.getString("password"),
                resultSet.getString("mail"),
                resultSet.getLong("role_id"));
    }
}
