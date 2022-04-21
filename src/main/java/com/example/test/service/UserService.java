package com.example.test.service;

import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getEmail());

        if (userFromDB != null) {
            return null;
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            return null;
        }
        user.setActive(true);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public User userById(int id) {
        return userRepository.getUserById(id);
    }

    public User deleteById(int id) {

        return userRepository.deleteById(id);

    }

    public void saveActivate(int id) {
        User user = userRepository.getUserById(id);
        user.setActive(!user.isActive());

        userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }
}