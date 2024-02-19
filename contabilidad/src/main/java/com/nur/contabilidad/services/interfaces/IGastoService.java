package com.nur.contabilidad.services.interfaces;

import com.nur.contabilidad.entities.Gasto;
import org.springframework.data.domain.Page;

public interface IGastoService {

    Page<Gasto> findAll(Integer page, Integer size, boolean enabled);

    Gasto editGasto(Gasto product);

    Gasto findById(Long id);

    Gasto save(Gasto product);

}
