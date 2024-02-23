package com.nur.cuentas.services.implementations;

import com.nur.cuentas.entities.Gasto;
import com.nur.cuentas.repositories.GastoRepository;
import com.nur.cuentas.services.interfaces.IGastoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GastoServiceImpl implements IGastoService {

    private final GastoRepository gastoRepository;


    public GastoServiceImpl(GastoRepository gastoRepository) {
        this.gastoRepository = gastoRepository;
    }


    @Override
    public List<Gasto> findAll() {
        return gastoRepository.findAll();
    }

    @Override
    public Gasto editGasto(Gasto gasto) {
        if (gastoRepository.findById(gasto.getId()).isPresent()){
            return gastoRepository.save(gasto);
        }
        return null;
    }

    @Override
    public Gasto findById(Long id) {
        return gastoRepository.findById(id).orElseThrow();
    }

    public Optional<Gasto> findByMonto(Long monto){
        return gastoRepository.findByMonto(monto);
    }

    public Optional<Gasto> findByCondominioId(Integer condominio_id){
        return gastoRepository.findByCondominioId(condominio_id);
    }

    @Override
    public Gasto save(Gasto gasto) {
        return gastoRepository.save(gasto);
    }
}
