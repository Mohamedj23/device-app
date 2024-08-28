package com.task.deviceapp.dto;

public class DeviceDto {

    private String deviceName;

    private String deviceBrand;

    public DeviceDto() {
    }

    public DeviceDto(String deviceName, String deviceBrand) {
        this.deviceName = deviceName;
        this.deviceBrand = deviceBrand;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }
}
