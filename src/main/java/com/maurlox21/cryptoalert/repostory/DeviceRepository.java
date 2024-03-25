package com.maurlox21.cryptoalert.repostory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.maurlox21.cryptoalert.entity.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long>{
    
    @Query(value = "select d from Device d join d.user u where u.id = :idUser and d.txNotificationToken = :txNotificationToken ")
    Optional<Device> findByIdUserAndTxNotificationToken(Long idUser, String txNotificationToken);
}
