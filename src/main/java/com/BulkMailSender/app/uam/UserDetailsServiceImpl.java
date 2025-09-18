package com.BulkMailSender.app.uam;


import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private CRMUserRepository userRepo;
	
	public UserDetailsServiceImpl(CRMUserRepository userRepo) {
		this.userRepo = userRepo;
	}


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
	
	
    }


	
}