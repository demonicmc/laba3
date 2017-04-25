package main.com.laba3.service;

import main.com.laba3.dao.UserDao;
import main.com.laba3.dao.UserDaoImp;
import main.com.laba3.pojo.User;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by set on 23.04.17.
 */
public class UserServiceImp implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImp.class);

    private static UserDao userDAO = new UserDaoImp();

    @Override
    public User authentication (String login, String password) {
        User user = userDAO.findUserByLoginAndPassword(login, password);
        logger.debug("user: " + user);

        if (user == null)  return null;
        return user;
    }

    @Override
    public List<User> getAllUser() throws ClassNotFoundException {
        return (List<User>) userDAO.getAll();
    }

}
