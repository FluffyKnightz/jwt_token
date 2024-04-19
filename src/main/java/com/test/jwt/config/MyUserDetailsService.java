package com.test.jwt.config;

import com.test.jwt.entity.User;
import com.test.jwt.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return new MyUserDetails(user);
    }
}
