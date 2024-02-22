package com.nur.cuentas.repositories;


import com.nur.cuentas.entities.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Long> {


    @Query("SELECT p FROM Parametro p WHERE p.monto = ?1")
    Optional<Parametro> findByMonto(Long monto);

    Optional<Parametro> findByActivo(Boolean activo);

    //Optional<Parametro> findByTipo(Integer tipo);

}
