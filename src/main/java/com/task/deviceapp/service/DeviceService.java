package com.task.deviceapp.service;

import com.task.deviceapp.dto.DeviceDto;
import com.task.deviceapp.entity.Device;
import com.task.deviceapp.repository.DeviceRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> getAllDevices() {
        return Streamable.of(deviceRepository.findAll()).toList();
    }

    public Device getDeviceById(Long id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Device With Id %s Is Not Found", id)));
    }

    public Device getDeviceByBrand(String deviceBrand) {
        return deviceRepository.findDeviceByDeviceBrand(deviceBrand)
                .orElseThrow(() -> new NoSuchElementException(String.format("Device With Brand %s Is Not Found", deviceBrand)));
    }

    public void deleteDeviceById(Long id) {
        deviceRepository.deleteById(id);
    }

    public Device addDevice(DeviceDto device) {
        Device newDevice = new Device(device.getDeviceName(), device.getDeviceBrand());
        return deviceRepository.save(newDevice);
    }

    public Device updateDevice(Long id, DeviceDto updatedDevice) {
        return deviceRepository.findById(id).map(device -> {
            device.setDeviceName(updatedDevice.getDeviceName());
            device.setDeviceBrand(updatedDevice.getDeviceBrand());
            return deviceRepository.save(device);
        }).orElseThrow(() -> new NoSuchElementException(String.format("Device With ID %s Is Not Found", id)));

    }

    public Device patchDevice(Long id, DeviceDto patchedDevice) {
        return deviceRepository.findById(id).map(device -> {
            if (patchedDevice.getDeviceName() != null) {
                device.setDeviceName(patchedDevice.getDeviceName());
            }
            if (patchedDevice.getDeviceBrand() != null) {
                device.setDeviceBrand(patchedDevice.getDeviceBrand());
            }
            return deviceRepository.save(device);
        }).orElseThrow(() -> new NoSuchElementException(String.format("Device With ID %s Is Not Found", id)));

    }
}
