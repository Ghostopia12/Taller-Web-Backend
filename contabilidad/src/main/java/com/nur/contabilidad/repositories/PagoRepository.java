package com.nur.contabilidad.repositories;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.nur.contabilidad.entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {


    @Query("SELECT p FROM Pago p WHERE p.monto = ?1")
    Optional<Pago> findByMonto(Long monto);

    Optional<Pago> findByDeuda_id(Integer deuda_id);

    Optional<Pago> findByUsuario_id(Integer usuario_id);

    Optional<Pago> findByContable_id(Integer contable_id);

}
