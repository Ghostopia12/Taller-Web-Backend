package com.nur.contabilidad.services.interfaces;

import com.sd.rainbowback.entities.Order;
import org.springframework.data.domain.Page;

public interface IOrderService {

    Page<Order> findAll(Integer page, Integer size, boolean enabled);

    Order editOrder(Order product);

    Order findById(Long id);

    Order save(Order product);

}
