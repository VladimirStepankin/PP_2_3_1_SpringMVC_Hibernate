package web.dao;

import web.entity.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);

    List<User> getAllUsers();

    void removeUserById(Long userId);

    User getUserById(Long userId);

    void updateUser(User user);
}
