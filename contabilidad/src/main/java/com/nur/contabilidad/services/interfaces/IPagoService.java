package com.nur.contabilidad.services.interfaces;

import com.nur.contabilidad.entities.Pago;
import org.springframework.data.domain.Page;

public interface IPagoService {

    Page<Pago> findAll(Integer page, Integer size, boolean enabled);

    Pago editPago(Pago product);

    Pago findById(Long id);

    Pago save(Pago product);

}
