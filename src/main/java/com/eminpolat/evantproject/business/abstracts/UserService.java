package com.eminpolat.evantproject.business.abstracts;

import com.eminpolat.evantproject.entites.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);
    void delete(User user);
    void update(User user);
    List<User> getAll();
    Optional<User> getById(Long id);
}

