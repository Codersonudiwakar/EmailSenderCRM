package com.BulkMailSender.app.uam;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @NotBlank
 @Size(max = 50)
 @Column(unique = true)
 private String username;
 
 @NotBlank
 @Size(max = 100)
 @Email
 @Column(unique = true)
 private String email;
 
 @NotBlank
 @Size(max = 120)
 private String password;
 
 @Enumerated(EnumType.STRING)
 private Role role;
 
 private boolean enabled = true;
 private boolean accountNonExpired = true;
 private boolean accountNonLocked = true;
 private boolean credentialsNonExpired = true;
 
 private LocalDateTime createdAt;
 private LocalDateTime updatedAt;
 private LocalDateTime lastLogin;
 
 @PrePersist
 protected void onCreate() {
     createdAt = LocalDateTime.now();
     updatedAt = LocalDateTime.now();
 }
 
 @PreUpdate
 protected void onUpdate() {
     updatedAt = LocalDateTime.now();
 }
 
 @Override
 public Collection<? extends GrantedAuthority> getAuthorities() {
     return List.of(new SimpleGrantedAuthority(role.name()));
 }
 
 @Override
 public boolean isAccountNonExpired() {
     return accountNonExpired;
 }
 
 @Override
 public boolean isAccountNonLocked() {
     return accountNonLocked;
 }
 
 @Override
 public boolean isCredentialsNonExpired() {
     return credentialsNonExpired;
 }
 
 @Override
 public boolean isEnabled() {
     return enabled;
 }
}
