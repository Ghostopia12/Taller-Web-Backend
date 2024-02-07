package com.nur.contabilidad.services.interfaces;

import com.sd.rainbowback.entities.Ingredient;
import org.springframework.data.domain.Page;

public interface IIngredientService {

    Page<Ingredient> findAll(Integer page, Integer size, boolean enabled);

    Ingredient editIngredient(Ingredient product);

    Ingredient findById(Long id);

    Ingredient save(Ingredient product);

}
