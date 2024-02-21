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

    private final ParametroRepository parametroRepository;


    public ParametroServiceImpl(ParametroRepository parametroRepository) {
        this.parametroRepository = parametroRepository;
    }


    @Override
    public Page<Parametro> findAll(Integer page, Integer size, boolean enabled) {
        return parametroRepository.findAll(enabled ? PageRequest.of(page, size): Pageable.unpaged());
    }

    @Override
    public Parametro editParametro(Parametro parametro) {
        if (parametroRepository.findById(parametro.getId()).isPresent()){
            return parametroRepository.save(parametro);
        }
        return null;
    }

    @Override
    public Parametro findById(Long id) {
        return parametroRepository.findById(id).orElseThrow();
    }

    public Optional<Parametro> findByMonto(Long monto){
        return parametroRepository.findByMonto(monto);
    }

    public Optional<Parametro> findByActivo(Boolean activo){
        return parametroRepository.findByActivo(activo);
    }

//    public Optional<Parametro> findByTipo(Integer tipo){
//        return parametroRepository.findByTipo(tipo);
//    }

    @Override
    public Parametro save(Parametro parametro) {
        return parametroRepository.save(parametro);
    }
}
