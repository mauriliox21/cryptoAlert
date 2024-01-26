package com.maurlox21.cryptoalert.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.maurlox21.cryptoalert.entity.Cryptocurrency;
import com.maurlox21.cryptoalert.repostory.projection.CryptocurrencyProjection;

@Repository
public interface CryptocurrencyRepository extends JpaRepository<Cryptocurrency, Long> {
    
    @Query("select c from Cryptocurrency c")
    public Page<CryptocurrencyProjection> findAllPageable(Pageable pageable);
}
