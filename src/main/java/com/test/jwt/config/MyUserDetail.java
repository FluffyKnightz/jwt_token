//package com.test.jwt.config;
//
//
//import com.test.jwt.entity.User;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//
//
//@Data
//@AllArgsConstructor
//public class MyUserDetail implements UserDetails {
//
//    private final User user;
//
//
//
//    //To Get UserName After Login
//    public String getEmail() {
//        return this.user.getEmail();
//    }
//
//
//	public String getRole() {
//        return this.user.getRole().name();
//    }
//    //To Get UserId After Login
//    public Long getUserId() {
//        return this.user.getId();
//    }
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
