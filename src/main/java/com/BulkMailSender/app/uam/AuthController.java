package com.BulkMailSender.app.uam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequiredArgsConstructor
public class AuthController {
 
 private final AuthService authService;
 
 @PostMapping("/register")
 public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
     return ResponseEntity.ok(authService.register(request));
 }
 
 @PostMapping("/login")
 public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
     return ResponseEntity.ok(authService.login(request));
 }
}