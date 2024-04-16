package com.maurlox21.cryptoalert.repostory.projection;

public interface DeviceProjection {
    Long getId();
    String getTxNotificationToken();
    String getNmManufacturer();
    String getNmOs();
    String getTxOsVersion();
    String getTxDeviceType();
}
