package com.nur.contabilidad.services.interfaces;

import com.sd.rainbowback.entities.Product;
import org.springframework.data.domain.Page;

public interface IProductService {

    Page<Product> findAll(Integer page, Integer size, boolean enabled);

    Product editProduct(Product product);

    Product findById(Long id);

    Product save(Product product);

}
