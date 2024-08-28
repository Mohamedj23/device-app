package com.task.deviceapp.repository;

import com.task.deviceapp.entity.Device;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeviceRepository extends CrudRepository<Device, Long> {

    Optional<Device> findDeviceByDeviceBrand(String deviceBrand);
}
