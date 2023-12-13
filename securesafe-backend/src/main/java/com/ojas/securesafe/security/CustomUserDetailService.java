package com.ojas.securesafe.security;

import com.ojas.securesafe.entity.User;
import com.ojas.securesafe.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("This is username"+username);
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with the given username : " + username));
        return new CustomUserDetails(user);
    }
}
