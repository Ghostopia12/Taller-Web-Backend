package com.nur.contabilidad.services.interfaces;

import com.sd.rainbowback.entities.OrderDetail;
import org.springframework.data.domain.Page;

public interface IOrderDetailService {

    Page<OrderDetail> findAll(Integer page, Integer size, boolean enabled);

    OrderDetail editOrderDetail(OrderDetail product);

    OrderDetail findById(Long id);

    OrderDetail save(OrderDetail product);

}
