package com.eminpolat.evantproject.business.concretes;

import com.eminpolat.evantproject.business.abstracts.UserService;
import com.eminpolat.evantproject.dataAccess.UserRepository;
import com.eminpolat.evantproject.entites.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManager implements UserService {
    private final UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void save(User user) {
        userRepository.save(user);
    }
    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void update(User user) {
        // Eğer user.getId() null değilse ve DB'de varsa, güncelleme yapılır.
        userRepository.save(user);
    }
}
