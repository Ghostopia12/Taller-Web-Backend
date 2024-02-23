package com.nur.cuentas.services.implementations;

import com.nur.cuentas.entities.Parametro;
import com.nur.cuentas.repositories.ParametroRepository;
import com.nur.cuentas.services.interfaces.IParametroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParametroServiceImpl implements IParametroService {

    private final ParametroRepository parametroRepository;


    public ParametroServiceImpl(ParametroRepository parametroRepository) {
        this.parametroRepository = parametroRepository;
    }


    @Override
    public List<Parametro> findAll() {
        return parametroRepository.findAll();
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
