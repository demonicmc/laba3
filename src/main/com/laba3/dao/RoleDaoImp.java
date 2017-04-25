package main.com.laba3.dao;

import main.com.laba3.ConnectBase;
import main.com.laba3.pojo.Role;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by set on 23.04.17.
 */
public class RoleDaoImp implements RoleDao {

    private static final Logger logger = Logger.getLogger(RoleDaoImp.class);

    private static final String SELECT_ALL = "SELECT id, name FROM role";
    private static final String INSERT_INTO = "INSERT INTO role (name) VALUES (?)";
    private static final String UPDATE_WHERE = "UPDATE role SET name = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM role WHERE id=?";

    @Override
    public Collection<Role> getAll() {
        Set<Role> roles = new HashSet<>();

        try  {
            Connection connection = ConnectBase.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                roles.add(createEntity(resultSet));
            }
            logger.debug(roles);
        } catch (SQLException e) {
            logger.error(e);
        } catch (ClassNotFoundException e) {
            logger.debug(e);
        }

        return roles;
    }

    @Override
    public Role getById(Long id) {
       Role role= null;

        try {
            Connection connection = ConnectBase.getConnection();
            PreparedStatement statement = connection
                    .prepareStatement(SELECT_ALL + " WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                role = createEntity(resultSet);
            }
            logger.debug(role);
        } catch (SQLException e) {
            logger.error(e);
        } catch (ClassNotFoundException e) {
            logger.debug(e);
        }

        return role;
    }

    @Override
    public Long insert(Role entity) {
        long result = -1;
        try (Connection connection = ConnectBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_INTO,
                     Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, entity.getName());
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
    public void update(Role entity) {
        try  {
            Connection connection = ConnectBase.getConnection();
            PreparedStatement statement = connection
                    .prepareStatement(UPDATE_WHERE);
            statement.setString(1, entity.getName());
            statement.setLong(2, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        } catch (ClassNotFoundException e) {
            logger.debug(e);
        }
    }

    @Override
    public void delete(Role entity) {
        try  {
            Connection connection = ConnectBase.getConnection();
            PreparedStatement statement = connection
                    .prepareStatement(DELETE_BY_ID);
            statement.setLong(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
           logger.error(e);
        } catch (ClassNotFoundException e) {
            logger.debug(e);
        }
    }

    private Role createEntity(ResultSet resultSet) throws SQLException {
        Role role = new Role();

        role.setId(resultSet.getLong("id"));
        role.setName(resultSet.getString("name"));

        return role;
    }
}
