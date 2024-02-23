package com.nur.cuentas.services.interfaces;

import com.nur.cuentas.entities.Gasto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IGastoService {

    List<Gasto> findAll();

    Gasto editGasto(Gasto product);

    Gasto findById(Long id);

    Gasto save(Gasto product);

}
