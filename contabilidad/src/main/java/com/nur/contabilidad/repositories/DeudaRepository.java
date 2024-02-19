package com.nur.contabilidad.repositories;


import com.nur.contabilidad.entities.Deuda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeudaRepository extends JpaRepository<Deuda, Long> {


    @Query("SELECT p FROM Deuda p WHERE p.monto = ?1")
    Optional<Deuda> findByMonto(Long monto);


    Optional<Deuda> findByResidencia_id(Integer residencia_id);

}
