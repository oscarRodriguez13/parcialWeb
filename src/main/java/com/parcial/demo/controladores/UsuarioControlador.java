package com.parcial.demo.controladores;

import com.parcial.demo.dtos.UsuarioAuxDTO;
import com.parcial.demo.dtos.UsuarioDTO;
import com.parcial.demo.servicios.UsuarioServicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public List<UsuarioAuxDTO> listarUsuarios() {
        return usuarioServicio.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioAuxDTO obtenerUsuarioPorId(@PathVariable Long id) {
        System.out.println(id);
        try {
            return usuarioServicio.obtenerUsuarioPorId(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado", e);
        }
    }

    @PostMapping
    public UsuarioAuxDTO crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioServicio.crearUsuario(usuarioDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioServicio.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
