package by.epam.mentoring.service;

import by.epam.mentoring.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
