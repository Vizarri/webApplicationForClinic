package com.studentProject.webApplicationForClinic.security;

import com.studentProject.webApplicationForClinic.models.User;
import com.studentProject.webApplicationForClinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("ЕМАЙЛ" +email);
        User user = userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User doesnt exist"));
        return SecurityUser.fromUser(user);
    }
}
