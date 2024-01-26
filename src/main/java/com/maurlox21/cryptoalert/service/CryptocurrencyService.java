package com.maurlox21.cryptoalert.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maurlox21.cryptoalert.entity.Cryptocurrency;
import com.maurlox21.cryptoalert.repostory.CryptocurrencyRepository;
import com.maurlox21.cryptoalert.repostory.projection.CryptocurrencyProjection;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CryptocurrencyService {
    
    @Autowired
    private CryptocurrencyRepository repository;

    public Cryptocurrency create(Cryptocurrency cryptocurrency){
        
        Cryptocurrency criated = this.repository.save(cryptocurrency);
        
        return criated;
    }

    public Page<CryptocurrencyProjection> getAll(Pageable pageable){

        return this.repository.findAllPageable(pageable);
    }

    public Cryptocurrency getById(Long id) {

        Optional<Cryptocurrency> optCryptocurrency = this.repository.findById(id);

        if(optCryptocurrency.isPresent())
            return optCryptocurrency.get();
        
        throw new EntityNotFoundException("Cryptocurrency not found");
    }

    public void update(Long id, Cryptocurrency cryptocurrency) {
        
        Optional<Cryptocurrency> optCryptocurrency = this.repository.findById(id);

        if(optCryptocurrency.isEmpty())
            throw new EntityNotFoundException("Cryptocurrency not found");
           
        cryptocurrency.setId(id);

        this.repository.save(cryptocurrency);
        
    }
}
