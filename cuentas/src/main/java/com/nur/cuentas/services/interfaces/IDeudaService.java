package com.nur.cuentas.services.interfaces;

import com.nur.cuentas.entities.Deuda;
import org.springframework.data.domain.Page;

public interface IDeudaService {

    Page<Deuda> findAll(Integer page, Integer size, boolean enabled);

    Deuda editDeuda(Deuda product);

    Deuda findById(Long id);

    Deuda save(Deuda product);

}
