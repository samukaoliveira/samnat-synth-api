package com.samukaoliveira.synth.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.samukaoliveira.synth.models.Usuario;
import com.samukaoliveira.synth.models.UsuarioDTO;
import com.samukaoliveira.synth.repositories.UsuarioRepository;


@Service 
public class UsuarioService {

    @Autowired 
    UsuarioRepository usuarioRepository;

    @Autowired 
    PasswordEncoder passwordEncoder;

    public List<Usuario> findByNome(String nome){
        return usuarioRepository.findByNome(nome);
    }

    public Usuario findById(Long id){
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public UsuarioDTO save(Usuario usuario){

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        usuario.setSenha(
            passwordEncoder.encode(usuario.getSenha())
        );
        
        return usuarioRepository.save(usuario).tUsuarioDTO();
    }

    public Optional<Usuario> getUsuarioAtual(String email){
        return usuarioRepository.findByEmail(email);
    }

    public Usuario getUsuarioAtual(){
        
        Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return getUsuarioAtual(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }


}
