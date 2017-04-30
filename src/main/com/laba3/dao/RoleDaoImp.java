package com.laba3.dao;

import com.laba3.ConnectBase;
import com.laba3.pojo.Role;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by set on 23.04.17.
 */
public class RoleDaoImp implements RoleDao {

    final static Logger logger = Logger.getLogger(RoleDaoImp.class);

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    private static final String SELECT_ALL = "SELECT id, name FROM public.role";
    private static final String INSERT_INTO = "INSERT INTO public.role (name) VALUES (?)";
    private static final String UPDATE_WHERE = "UPDATE public.role SET name = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM public.role WHERE id=?";

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectBase.getInstance().getConnection();
        return conn;
    }

    @Override
    public Collection<Role> getAll() {
        Set<Role> roles = new HashSet<>();

        try  {
           connection = getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                roles.add(createEntity(resultSet));
            }
            logger.debug(roles);
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

            return roles;
    }

    @Override
    public Role getById(Long id) {
       Role role= null;

        try {
            connection = getConnection();
            statement = connection
                    .prepareStatement(SELECT_ALL + " WHERE id = ?");
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                role = createEntity(resultSet);
            }
            logger.debug(role);
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

        return role;
    }

    @Override
    public Long insert(Role entity) {
        long result = -1;
        try  {
            connection = getConnection();
            statement = connection.prepareStatement(INSERT_INTO,
                    Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, entity.getName());
                statement.executeUpdate();

                resultSet = statement.getGeneratedKeys();
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
    public void update(Role entity) {
        try  {
            connection = getConnection();
            statement = connection
                    .prepareStatement(UPDATE_WHERE);
            statement.setString(1, entity.getName());
            statement.setLong(2, entity.getId());

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

    private Role createEntity(ResultSet resultSet) throws SQLException {
        Role role = new Role();

        role.setId(resultSet.getLong("id"));
        role.setName(resultSet.getString("name"));

        return role;
    }
}
