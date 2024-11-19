package com.parcial.demo.controladores;

import com.parcial.demo.dtos.ContratoDTO;
import com.parcial.demo.servicios.ContratoServicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contratos")
@CrossOrigin(origins = "http://localhost:4200")
public class ContratoControlador {

    @Autowired
    private ContratoServicio contratoServicio;

    // Consultar todos los registros
    @GetMapping
    public ResponseEntity<List<ContratoDTO>> obtenerTodos() {
        return ResponseEntity.ok(contratoServicio.obtenerTodos());
    }

    // Consultar un registro por su identificador
    @GetMapping("/{id}")
    public ResponseEntity<ContratoDTO> obtenerPorId(@PathVariable Long id) {
        ContratoDTO contrato = contratoServicio.obtenerPorId(id);
        return ResponseEntity.ok(contrato);
    }

    // Crear (insertar un nuevo registro)
    @PostMapping
    public ResponseEntity<ContratoDTO> guardar(@RequestBody ContratoDTO contratoDTO) {
        return ResponseEntity.ok(contratoServicio.guardar(contratoDTO));
    }

    // Actualizar (editar un registro existente)
    @PutMapping("/{id}")
    public ResponseEntity<ContratoDTO> actualizar(@PathVariable Long id, @RequestBody ContratoDTO contratoDTO) {
        ContratoDTO actualizado = contratoServicio.actualizar(id, contratoDTO);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar (borrar un registro por su identificador)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        contratoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
