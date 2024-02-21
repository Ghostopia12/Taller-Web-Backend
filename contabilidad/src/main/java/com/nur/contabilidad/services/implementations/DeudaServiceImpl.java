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

    private final DeudaRepository deudaRepository;


    public DeudaServiceImpl(DeudaRepository deudaRepository) {
        this.deudaRepository = deudaRepository;
    }


    @Override
    public Page<Deuda> findAll(Integer page, Integer size, boolean enabled) {
        return deudaRepository.findAll(enabled ? PageRequest.of(page, size): Pageable.unpaged());
    }

    @Override
    public Deuda editDeuda(Deuda deuda) {
        if (deudaRepository.findById(deuda.getId()).isPresent()){
            return deudaRepository.save(deuda);
        }
        return null;
    }

    @Override
    public Deuda findById(Long id) {
        return deudaRepository.findById(id).orElseThrow();
    }

    public Optional<Deuda> findByMonto(Long monto){
        return deudaRepository.findByMonto(monto);
    }

    public Optional<Deuda> findByResidenciaId(Integer residencia_id){
        return deudaRepository.findByResidenciaId(residencia_id);
    }

    @Override
    public Deuda save(Deuda deuda) {
        return deudaRepository.save(deuda);
    }
}
