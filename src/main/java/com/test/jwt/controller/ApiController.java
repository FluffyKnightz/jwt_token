package com.test.jwt.controller;

import com.test.jwt.config.JwtService;
import com.test.jwt.config.MyUserDetails;
import com.test.jwt.config.MyUserDetailsService;
import com.test.jwt.dto.AuthenticationRequest;
import com.test.jwt.dto.AuthenticationResponse;
import com.test.jwt.dto.UserDto;
import com.test.jwt.entity.Role;
import com.test.jwt.entity.User;
import com.test.jwt.repository.UserRepo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("akz/api/v1")
@Data
@AllArgsConstructor
public class ApiController {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;
    private final MyUserDetailsService myUserDetailsService;

    @PostMapping("/userCreate")
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserDto userDto) {

        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(Role.USER)
                .build();

        userRepo.save(user);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getName(),
                        authenticationRequest.getPassword()
                )
        );
        MyUserDetails myUserDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getName());
        var jwtToken = jwtService.generateToken(myUserDetails);
        AuthenticationResponse auth = AuthenticationResponse.builder()
                .token(jwtToken)
                .build();


        return ResponseEntity.ok(auth);
    }


}
