package com.nur.contabilidad.services.interfaces;

import com.sd.rainbowback.entities.Category;
import org.springframework.data.domain.Page;

public interface ICategoryService {

    Page<Category> findAll(Integer page, Integer size, boolean enabled);

    Category editCategory(Category product);

    Category findById(Long id);

    Category save(Category product);

}
