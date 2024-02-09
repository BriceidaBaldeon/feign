package com.ejercicio.ejercicio.repository;

import com.ejercicio.ejercicio.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity,Long> {
}
