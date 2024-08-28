package com.task.deviceapp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "device_brand")
    private String deviceBrand;

    @Column(name = "creation_time")
    private LocalDateTime creationTime = LocalDateTime.now();

    public Device() {
    }

    public Device(String deviceName, String deviceBrand) {
        this.deviceName = deviceName;
        this.deviceBrand = deviceBrand;
    }

    public Device(Long id, String deviceName, String deviceBrand) {
        this.id = id;
        this.deviceName = deviceName;
        this.deviceBrand = deviceBrand;
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
