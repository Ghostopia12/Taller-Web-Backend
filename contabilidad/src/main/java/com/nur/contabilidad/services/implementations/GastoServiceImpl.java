package com.nur.contabilidad.services.implementations;

import com.nur.contabilidad.entities.Gasto;
import com.nur.contabilidad.repositories.GastoRepository;
import com.nur.contabilidad.services.interfaces.IGastoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GastoServiceImpl implements IGastoService {

    private final GastoRepository pagoRepository;


    public GastoServiceImpl(GastoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }


    @Override
    public Page<Gasto> findAll(Integer page, Integer size, boolean enabled) {
        return pagoRepository.findAll(enabled ? PageRequest.of(page, size): Pageable.unpaged());
    }

    @Override
    public Gasto editGasto(Gasto pago) {
        if (pagoRepository.findById(pago.getId()).isPresent()){
            return pagoRepository.save(pago);
        }
        return null;
    }

    @Override
    public Gasto findById(Long id) {
        return pagoRepository.findById(id).orElseThrow();
    }

    public Optional<Gasto> findByMonto(Long monto){
        return pagoRepository.findByMonto(monto);
    }

    public Optional<Gasto> findByCondominio_id(Integer condominio_id){
        return pagoRepository.findByCondominio_id(condominio_id);
    }

    @Override
    public Gasto save(Gasto pago) {
        return pagoRepository.save(pago);
    }
}
