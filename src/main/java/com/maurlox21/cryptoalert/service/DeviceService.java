package com.maurlox21.cryptoalert.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maurlox21.cryptoalert.entity.Device;
import com.maurlox21.cryptoalert.exception.BusinessRuleException;
import com.maurlox21.cryptoalert.repostory.DeviceRepository;
import com.maurlox21.cryptoalert.repostory.projection.DeviceProjection;

@Service
public class DeviceService {
    
    @Autowired
    private DeviceRepository deviceRepository;

    public Device create (Device device){

        Optional<Device> existentilyDevice = this.deviceRepository.findByIdUserAndTxNotificationToken(device.getUser().getId(), device.getTxNotificationToken());

        existentilyDevice.ifPresent( exDevice -> {
            throw new BusinessRuleException("This device has already been registered for you");
        });

        return this.deviceRepository.save(device);
    }

    public List<DeviceProjection> getDevicesByIdUser (Long idUser){
        
        return this.deviceRepository.getDevicesByIdUser(idUser);
    }


}
