package com.upworktn.authentification.auth;


import com.upworktn.authentification.User.Role;
import com.upworktn.authentification.config.SessionRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final SessionRegistry sessionRegistry;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/connected-users")
    @PreAuthorize("hasAuthority('ADMINISTRATEUR')")
    public ResponseEntity<List<String>> getConnectedUsers(Authentication authentication) {
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(Role.ADMINISTRATEUR.name()))) {

            List<String> connectedUsers = sessionRegistry.getActiveSessions().stream()
                    .filter(email -> !email.equals(authentication.getName()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(connectedUsers);
        }
        return ResponseEntity.status(403).build();
    }
}
