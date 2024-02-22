package com.nur.cuentas.repositories;


import com.nur.cuentas.entities.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {


    @Query("SELECT p FROM Deuda p WHERE p.monto = ?1")
    Optional<Gasto> findByMonto(Long monto);


    Optional<Gasto> findByCondominioId(Integer condominio_id);

}
