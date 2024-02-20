package com.maurlox21.cryptoalert.repostory;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.maurlox21.cryptoalert.entity.Alert;
import com.maurlox21.cryptoalert.repostory.projection.AlertProjection;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    
    @Query(value = "select a from Alert a join a.user u where u.id = :idUser")
    public Page<AlertProjection> findByIdUser(Long idUser, Pageable pageable);

    @Query(value = "select a from Alert a join a.user u where a.id = :idAlert and u.id = :idUser")
    public Optional<Alert> findByIdAndIdUser(Long idAlert, Long idUser);

    @Query(value = "select a from Alert a join a.cryptocurrency c where c.id = :idCryptocurrency")
    public Page<Alert> findAllByIdCryptocurrency(Long idCryptocurrency, Pageable pageable);

}
