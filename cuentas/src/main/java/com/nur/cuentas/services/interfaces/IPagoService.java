package com.nur.cuentas.services.interfaces;

import com.nur.cuentas.entities.Pago;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPagoService {

    List<Pago> findAll();

    Pago editPago(Pago product);

    Pago findById(Long id);

    Pago save(Pago product);

}
