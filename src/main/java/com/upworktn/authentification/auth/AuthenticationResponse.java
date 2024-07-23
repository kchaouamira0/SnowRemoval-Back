package com.upworktn.authentification.auth;


import com.upworktn.authentification.User.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {


    private String token ;
   private String name;
   private Role role;
}
