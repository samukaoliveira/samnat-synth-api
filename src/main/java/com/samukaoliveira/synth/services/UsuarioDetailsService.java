package com.samukaoliveira.synth.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.samukaoliveira.synth.models.Usuario;
import com.samukaoliveira.synth.repositories.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class UsuarioDetailsService implements UserDetailsService{

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> 
                new UsernameNotFoundException("Usuário não encontrado"));
        
        return User
            .withUsername(usuario.getEmail())
            .password(usuario.getSenha())
            .roles("USER")
            .build();
    }



}
