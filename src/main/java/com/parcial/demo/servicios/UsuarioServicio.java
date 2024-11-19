package com.parcial.demo.servicios;

import com.parcial.demo.dtos.UsuarioAuxDTO;
import com.parcial.demo.dtos.UsuarioDTO;
import com.parcial.demo.modelos.Usuario;
import com.parcial.demo.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepository;

    // Listar usuarios (sin contraseña)
    public List<UsuarioAuxDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertirAUsuarioAuxDTO)
                .collect(Collectors.toList());
    }

    // Obtener usuario por ID (sin contraseña)
    public UsuarioAuxDTO obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::convertirAUsuarioAuxDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Crear usuario (se recibe UsuarioDTO con contraseña)
    public UsuarioAuxDTO crearUsuario(UsuarioDTO usuarioCrearDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioCrearDTO.getNombre());
        usuario.setApellido(usuarioCrearDTO.getApellido());
        usuario.setCorreo(usuarioCrearDTO.getCorreo());
        usuario.setContrasenia(usuarioCrearDTO.getContrasenia());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return convertirAUsuarioAuxDTO(usuarioGuardado);
    }

    // Eliminar usuario por ID
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Convertir Usuario a UsuarioAuxDTO (sin contraseña)
    private UsuarioAuxDTO convertirAUsuarioAuxDTO(Usuario usuario) {
        UsuarioAuxDTO dto = new UsuarioAuxDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setCorreo(usuario.getCorreo());
        return dto;
    }
}

