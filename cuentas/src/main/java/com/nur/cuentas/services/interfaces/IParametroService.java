package com.nur.cuentas.services.interfaces;

import com.nur.cuentas.entities.Parametro;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IParametroService {

    List<Parametro> findAll();

    Parametro editParametro(Parametro product);

    Parametro findById(Long id);

    Parametro save(Parametro product);

}
