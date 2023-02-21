package com.zik.springsecurityDatabase.service;

import com.zik.springsecurityDatabase.model.User;
import com.zik.springsecurityDatabase.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        CustomUserDetails userDetails = null;
        if(user !=null)
        {
            userDetails = new CustomUserDetails();
            userDetails.setUser(user);
        }
        else {
            throw new UsernameNotFoundException("user not exits with the name" + username);
        }


        return userDetails;
    }
}
