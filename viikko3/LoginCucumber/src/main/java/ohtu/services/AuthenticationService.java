package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;
import java.util.regex.*;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        Pattern userNamePattern = Pattern.compile("[a-z]{3,}");
        Pattern passwordPattern = Pattern.compile("^(?=.*\\d).{8,}$");

        if (!userNamePattern.matcher(username).matches()) {
            return true;
        }

        if (!passwordPattern.matcher(password).matches()) {
            return true;
        }
        return false;
    }
}
