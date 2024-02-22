package com.nur.cuentas.services.interfaces;

import com.nur.cuentas.entities.Gasto;
import org.springframework.data.domain.Page;

public interface IGastoService {

    Page<Gasto> findAll(Integer page, Integer size, boolean enabled);

    Gasto editGasto(Gasto product);

    Gasto findById(Long id);

    Gasto save(Gasto product);

}
