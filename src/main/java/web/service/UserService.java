package web.service;

import web.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    List<User> getAllUsers();

    void removeUserById(Long userId);

    User getUserById(Long userId);

    void updateUser(User user);
}
