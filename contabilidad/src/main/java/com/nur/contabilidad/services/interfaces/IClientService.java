package com.nur.contabilidad.services.interfaces;

import com.sd.rainbowback.entities.Client;
import org.springframework.data.domain.Page;

public interface IClientService {

    Page<Client> findAll(Integer page, Integer size, boolean enabled);

    Client editClient(Client product);

    Client findById(Long id);

    Client save(Client product);

}
