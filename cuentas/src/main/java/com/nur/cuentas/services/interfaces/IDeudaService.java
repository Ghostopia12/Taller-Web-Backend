package com.nur.cuentas.services.interfaces;

import com.nur.cuentas.entities.Deuda;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IDeudaService {

    List<Deuda> findAll();

    Deuda editDeuda(Deuda product);

    Deuda findById(Long id);

    Deuda save(Deuda product);

}
