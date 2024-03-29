package com.nur.cuentas.services.implementations;

import com.nur.cuentas.entities.Pago;
import com.nur.cuentas.repositories.PagoRepository;
import com.nur.cuentas.services.interfaces.IPagoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImpl implements IPagoService {

    private final PagoRepository pagoRepository;


    public PagoServiceImpl(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }


    @Override
    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }

    @Override
    public Pago editPago(Pago pago) {
        if (pagoRepository.findById(pago.getId()).isPresent()){
            return pagoRepository.save(pago);
        }
        return null;
    }

    @Override
    public Pago findById(Long id) {
        return pagoRepository.findById(id).orElseThrow();
    }

    public Optional<Pago> findByMonto(Long monto){
        return pagoRepository.findByMonto(monto);
    }

    public Optional<Pago> findByDeudaId(Integer deuda_id){
        return pagoRepository.findByDeudaId(deuda_id);
    }

    public Optional<Pago> findByUsuarioId(Integer usuario_id){
        return pagoRepository.findByUsuarioId(usuario_id);
    }

    public Optional<Pago> findByContableId(Integer contable_id){
        return pagoRepository.findByContableId(contable_id);
    }

    @Override
    public Pago save(Pago pago) {
        return pagoRepository.save(pago);
    }
}
