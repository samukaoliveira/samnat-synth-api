package com.samukaoliveira.synth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samukaoliveira.synth.models.AuthRequest;
import com.samukaoliveira.synth.models.AuthResponse;
import com.samukaoliveira.synth.services.JwtService;

import lombok.AllArgsConstructor;

@AllArgsConstructor 
@RestController 
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @PostMapping({"", "/"})
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.email(), request.senha())
        );

        String token = jwtService.generateToken(request.email());

        return ResponseEntity.ok(new AuthResponse(token));
    }


}
