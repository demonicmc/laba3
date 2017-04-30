package com.laba3.dao;

import com.laba3.ConnectBase;
import com.laba3.MyMath;
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
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;


    private static final String SELECT_ALL = "SELECT * FROM public.user";
    private static final long ADMIN_ROLE_KEY = 2;
    private static final long USER_ROLE_KEY = 1;
    private static final String INSERT_INTO =
            "INSERT INTO public.user (login, password, mail, role_id) VALUES (?, ?, ?, ?)";

    private static final String UPDATE_WHERE =
            "UPDATE public.user SET login = ?, password = ?, mail = ?, role_id = ?   WHERE id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM public.user WHERE id=?";


    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectBase.getInstance().getConnection();
        return conn;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {

        User user = null;
        String pass = "";
        pass = MyMath.createMD5(MyMath.createMD5(password));

        try {
             connection = getConnection();
              statement = connection
                     .prepareStatement( "SELECT * FROM public.user WHERE login = ? AND password = ?");

            statement.setString(1, login);
            statement.setString(2, pass);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createEntity(resultSet);
            }

            logger.debug("user " + user);
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return user;

    }

    @Override
    public List<User> getAll() {
        Statement statement = null;
        List<User> list = new ArrayList<>();

        try {
            connection = getConnection();
             statement = connection.createStatement();
             resultSet =
                    statement.executeQuery("SELECT * FROM public.user");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setMail(resultSet.getString(4));
                user.setRole_id(resultSet.getInt("Role_id"));

                list.add(user);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
            System.out.println("Что-то не так");
//
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return list;

    }

    @Override
    public User getById(Long id) {
        User user = null;

        try {
            connection = getConnection();
             statement = connection
                    .prepareStatement(SELECT_ALL + " WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createEntity(resultSet);
            }
            logger.debug(user);
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return user;
    }

    @Override
    public Long insert(User entity) {
        long result = -1;
        try  {
             connection = getConnection();
             statement = connection.prepareStatement(INSERT_INTO,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getMail());
//            statement.setLong(4, entity.getRole_id());
            statement.setLong(4, USER_ROLE_KEY);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getLong(1);
            }

        } catch (SQLException e) {
            logger.error(e);
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    @Override
    public void update(User entity) {

        try  {
             connection = getConnection();
             statement = connection
                    .prepareStatement(UPDATE_WHERE);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getMail());
            statement.setLong(4, entity.getRole_id());
            statement.setLong(5, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


    @Override
    public void deleteById(int id) {

        try  {
            connection = getConnection();
             statement = connection
                    .prepareStatement(DELETE_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e);
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

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
