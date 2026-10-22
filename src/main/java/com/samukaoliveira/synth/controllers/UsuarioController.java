package com.samukaoliveira.synth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samukaoliveira.synth.models.Usuario;
import com.samukaoliveira.synth.models.UsuarioDTO;
import com.samukaoliveira.synth.services.UsuarioService;

import lombok.AllArgsConstructor;

@AllArgsConstructor 
@RestController 
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping ("/criar")
    public ResponseEntity<UsuarioDTO> criar(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.save(usuario));
    }


}
