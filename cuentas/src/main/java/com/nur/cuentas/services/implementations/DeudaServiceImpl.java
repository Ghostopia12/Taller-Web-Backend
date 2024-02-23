package com.nur.cuentas.services.implementations;

import com.nur.cuentas.entities.Deuda;
import com.nur.cuentas.repositories.DeudaRepository;
import com.nur.cuentas.services.interfaces.IDeudaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeudaServiceImpl implements IDeudaService {

    private final DeudaRepository deudaRepository;


    public DeudaServiceImpl(DeudaRepository deudaRepository) {
        this.deudaRepository = deudaRepository;
    }


    @Override
    public List<Deuda> findAll() {
        return deudaRepository.findAll();
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
