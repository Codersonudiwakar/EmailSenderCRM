package com.BulkMailSender.app.uam;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
 
 private final CRMUserRepository userRepository;
 private final PasswordEncoder passwordEncoder;
 private final JwtUtil jwtUtil;
 private final AuthenticationManager authenticationManager;
 
 public AuthResponse register(RegisterRequest request) {
     if (userRepository.existsByUsername(request.getUsername())) {
         return AuthResponse.builder()
                 .message("Username already exists")
                 .build();
     }
     
     if (userRepository.existsByEmail(request.getEmail())) {
         return AuthResponse.builder()
                 .message("Email already exists")
                 .build();
     }
     
     // Create new user
     User user = User.builder()
             .username(request.getUsername())
             .email(request.getEmail())
             .password(passwordEncoder.encode(request.getPassword()))
             .role(request.getRole() != null ? request.getRole() : Role.USER)
             .enabled(true)
             .accountNonExpired(true)
             .accountNonLocked(true)
             .credentialsNonExpired(true)
             .build();
     
     userRepository.save(user);
     
     String jwtToken = jwtUtil.generateToken(user.getId(),user);
     
     return AuthResponse.builder()
             .token(jwtToken)
             .username(user.getUsername())
             .email(user.getEmail())
             .role(user.getRole().name())
             .message("User registered successfully")
             .build();
 }
 
 public AuthResponse login(LoginRequest request) {
	    System.out.println("Login attempt for username: " + request.getUsername());
	    
	    try {
	        // First check if user exists
	        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
	        if (userOptional.isEmpty()) {
	            return AuthResponse.builder()
	                    .message("Invalid credentials")
	                    .build();
	        }
	        
	        User user = userOptional.get();
	        System.out.println("User account status - Enabled: " + user.isEnabled() + 
	                          ", NonLocked: " + user.isAccountNonLocked() +
	                          ", NonExpired: " + user.isAccountNonExpired());
	        
	        // Try authentication
	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        request.getUsername(),
	                        request.getPassword()
	                )
	        );
	        
	        user.setLastLogin(LocalDateTime.now());
	        userRepository.save(user);
	        
	        String jwtToken = jwtUtil.generateToken(user.getId(),user);
	        
	        return AuthResponse.builder()
	                .token(jwtToken)
	                .username(user.getUsername())
	                .email(user.getEmail())
	                .role(user.getRole().name())
	                .message("Login successful")
	                .build();
	                
	    } catch (LockedException e) {
	        System.out.println("Account is locked: " + request.getUsername());
	        return AuthResponse.builder()
	                .message("Your account is locked. Please contact administrator.")
	                .build();
	    } catch (BadCredentialsException e) {
	        System.out.println("Invalid credentials for: " + request.getUsername());
	        return AuthResponse.builder()
	                .message("Invalid username or password")
	                .build();
	    } catch (Exception e) {
	        System.out.println("Login error: " + e.getMessage());
	        e.printStackTrace();
	        return AuthResponse.builder()
	                .message("Login failed: " + e.getMessage())
	                .build();
	    }
	}
public AuthService(CRMUserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil,
		AuthenticationManager authenticationManager) {
	super();
	this.userRepository = userRepository;
	this.passwordEncoder = passwordEncoder;
	this.jwtUtil = jwtUtil;
	this.authenticationManager = authenticationManager;
}
}