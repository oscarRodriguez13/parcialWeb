package com.parcial.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.parcial.demo.modelos.Contrato;


public interface ContratoRepositorio extends JpaRepository<Contrato, Long> {
}
