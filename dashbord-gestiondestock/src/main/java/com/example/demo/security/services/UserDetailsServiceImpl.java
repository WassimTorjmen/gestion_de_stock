package com.example.demo.security.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.auth.User;
import com.example.demo.dao.auth.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
    public List<User> findAll(){
        return userRepo.findAll();
    }
    @Transactional
    public User updateUser(User updatedUser) {
        Optional<User> optionalUser = userRepo.findById(updatedUser.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setTel(updatedUser.getTel());
            user.setRegion(updatedUser.getRegion());

            return userRepo.save(user);
        } else {
            throw new RuntimeException("User not found with ID: " + updatedUser.getId());
        }
    }
    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }
}
