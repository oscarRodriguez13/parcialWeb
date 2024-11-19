package com.parcial.demo.servicios;

import com.parcial.demo.dtos.ContratoDTO;
import com.parcial.demo.modelos.Contrato;
import com.parcial.demo.repositorios.ContratoRepositorio;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContratoServicio {

    @Autowired
    private ContratoRepositorio contratoRepositorio;

    @Autowired
    private ModelMapper modelMapper;

    // Consultar todos los registros
    public List<ContratoDTO> obtenerTodos() {
        return contratoRepositorio.findAll()
                .stream()
                .map(contrato -> modelMapper.map(contrato, ContratoDTO.class))
                .collect(Collectors.toList());
    }

    // Consultar un registro por su identificador
    public ContratoDTO obtenerPorId(Long id) {
        Contrato contrato = contratoRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrato no encontrado con id: " + id));
        return modelMapper.map(contrato, ContratoDTO.class);
    }

    // Crear (insertar un nuevo registro)
    public ContratoDTO guardar(ContratoDTO contratoDTO) {
        Contrato contrato = modelMapper.map(contratoDTO, Contrato.class);
        Contrato guardado = contratoRepositorio.save(contrato);
        return modelMapper.map(guardado, ContratoDTO.class);
    }

    // Actualizar (editar un registro existente)
    public ContratoDTO actualizar(Long id, ContratoDTO contratoDTO) {
        Contrato contratoExistente = contratoRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrato no encontrado con id: " + id));

        // Actualizar los campos de la entidad existente
        contratoExistente.setIdentificador(contratoDTO.getIdentificador());
        contratoExistente.setValor(contratoDTO.getValor());
        contratoExistente.setNombreContratante(contratoDTO.getNombreContratante());
        contratoExistente.setDocumentoContratante(contratoDTO.getDocumentoContratante());
        contratoExistente.setNombreContratantista(contratoDTO.getNombreContratantista());
        contratoExistente.setDocumentoContratantista(contratoDTO.getDocumentoContratantista());
        contratoExistente.setFechaInicial(contratoDTO.getFechaInicial());
        contratoExistente.setFechaFinal(contratoDTO.getFechaFinal());

        Contrato actualizado = contratoRepositorio.save(contratoExistente);
        return modelMapper.map(actualizado, ContratoDTO.class);
    }

    // Eliminar (borrar un registro por su identificador)
    public void eliminar(Long id) {
        if (!contratoRepositorio.existsById(id)) {
            throw new IllegalArgumentException("Contrato no encontrado con id: " + id);
        }
        contratoRepositorio.deleteById(id);
    }
}
