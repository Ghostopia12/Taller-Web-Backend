package com.nur.cuentas.services.interfaces;

import com.nur.cuentas.entities.Parametro;
import org.springframework.data.domain.Page;

public interface IParametroService {

    Page<Parametro> findAll(Integer page, Integer size, boolean enabled);

    Parametro editParametro(Parametro product);

    Parametro findById(Long id);

    Parametro save(Parametro product);

}
