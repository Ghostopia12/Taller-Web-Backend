package com.nur.contabilidad.services.implementations;

import com.nur.contabilidad.entities.Deuda;
import com.nur.contabilidad.repositories.DeudaRepository;
import com.nur.contabilidad.services.interfaces.IDeudaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeudaServiceImpl implements IDeudaService {

    private final DeudaRepository pagoRepository;


    public DeudaServiceImpl(DeudaRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }


    @Override
    public Page<Deuda> findAll(Integer page, Integer size, boolean enabled) {
        return pagoRepository.findAll(enabled ? PageRequest.of(page, size): Pageable.unpaged());
    }

    @Override
    public Deuda editDeuda(Deuda pago) {
        if (pagoRepository.findById(pago.getId()).isPresent()){
            return pagoRepository.save(pago);
        }
        return null;
    }

    @Override
    public Deuda findById(Long id) {
        return pagoRepository.findById(id).orElseThrow();
    }

    public Optional<Deuda> findByMonto(Long monto){
        return pagoRepository.findByMonto(monto);
    }

    @Override
    public Deuda save(Deuda pago) {
        return pagoRepository.save(pago);
    }
}
