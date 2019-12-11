package com.eiv.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eiv.entities.ProvinciaEntity;
import com.eiv.entities.QProvinciaEntity;
import com.eiv.interfaces.Provincia;
import com.eiv.repositories.ProvinciaRepository;
import com.eiv.utiles.ExceptionUtils;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;
    
    @Transactional(readOnly = true)
    public Optional<ProvinciaEntity> findById(Long id) {
        return provinciaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<ProvinciaEntity> findAll() {
        return provinciaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<ProvinciaEntity> findAll(Function<QProvinciaEntity, BooleanExpression> function) {
        
        QProvinciaEntity provinciaQuery = QProvinciaEntity.provinciaEntity;
        BooleanExpression exp = function.apply(provinciaQuery);
        
        return (List<ProvinciaEntity>) provinciaRepository.findAll(exp);
    }
    
    @Transactional
    public ProvinciaEntity save(Provincia provincia) {
        
        ProvinciaEntity provinciaEntity = new ProvinciaEntity();
        
        provinciaEntity.setNombre(provincia.getNombre());
        provinciaEntity.setRegion(provincia.getRegion());
        
        provinciaRepository.save(provinciaEntity);
        
        return provinciaEntity;
    }

    @Transactional
    public ProvinciaEntity save(Long id, Provincia provincia) {
        
        ProvinciaEntity provinciaEntity = provinciaRepository.findById(id)
                .orElseThrow(exceptionSupplier(id));
        
        provinciaEntity.setNombre(provincia.getNombre());
        provinciaEntity.setRegion(provincia.getRegion());
        
        provinciaRepository.save(provinciaEntity);
        
        return provinciaEntity;
    }

    @Transactional
    public void delete(Long id) {
        
        ProvinciaEntity provinciaEntity = provinciaRepository.findById(id)
                .orElseThrow(exceptionSupplier(id));
        
        provinciaRepository.delete(provinciaEntity);
    }

    private Supplier<? extends RuntimeException> exceptionSupplier(Long id) {
        return ExceptionUtils.notFoundExceptionSupplier(
                "NO EXISTE UNA PROVINCIA CON ID %s", id);
    }
}
