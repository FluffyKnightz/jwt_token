package com.test.jwt.config;

import com.test.jwt.entity.User;
import com.test.jwt.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByName(username);
        if (user.isPresent()) {

            return new MyUserDetails(user.get());
        }

        throw new UsernameNotFoundException("User Not Found");
    }
}
