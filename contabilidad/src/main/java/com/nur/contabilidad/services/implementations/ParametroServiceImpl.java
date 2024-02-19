package com.nur.contabilidad.services.implementations;

import com.nur.contabilidad.entities.Parametro;
import com.nur.contabilidad.repositories.ParametroRepository;
import com.nur.contabilidad.services.interfaces.IParametroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParametroServiceImpl implements IParametroService {

    private final ParametroRepository pagoRepository;


    public ParametroServiceImpl(ParametroRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }


    @Override
    public Page<Parametro> findAll(Integer page, Integer size, boolean enabled) {
        return pagoRepository.findAll(enabled ? PageRequest.of(page, size): Pageable.unpaged());
    }

    @Override
    public Parametro editParametro(Parametro pago) {
        if (pagoRepository.findById(pago.getId()).isPresent()){
            return pagoRepository.save(pago);
        }
        return null;
    }

    @Override
    public Parametro findById(Long id) {
        return pagoRepository.findById(id).orElseThrow();
    }

    public Optional<Parametro> findByMonto(Long monto){
        return pagoRepository.findByMonto(monto);
    }

    public Optional<Parametro> findByActivo(Boolean activo){
        return pagoRepository.findByActivo(activo);
    }

    public Optional<Parametro> findByTipo(Integer tipo){
        return pagoRepository.findByTipo(tipo);
    }

    @Override
    public Parametro save(Parametro pago) {
        return pagoRepository.save(pago);
    }
}
