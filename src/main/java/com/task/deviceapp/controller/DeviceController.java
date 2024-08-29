package com.task.deviceapp.controller;

import com.task.deviceapp.dto.DeviceDto;
import com.task.deviceapp.entity.Device;
import com.task.deviceapp.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService service) {
        this.deviceService = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Device>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(deviceService.getDeviceById(id));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/brand/{deviceBrand}")
    public ResponseEntity<Device> getDeviceByBrand(@PathVariable String deviceBrand) {
        try {
            return ResponseEntity.ok(deviceService.getDeviceByBrand(deviceBrand));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDeviceById(id);
        return ResponseEntity.ok()
                .body(String.format("Device With ID %s Is Deleted Successfully", id));
    }

    @PostMapping("/")
    public ResponseEntity<Device> addDevice(@RequestBody DeviceDto device) {
        Device addedDevice = deviceService.addDevice(device);
        return ResponseEntity.created(URI.create("/"))
                .body(addedDevice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @RequestBody DeviceDto device) {
        try {
            Device updatedDevice = deviceService.updateDevice(id, device);
            return ResponseEntity.ok(updatedDevice);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Device> patchDevice(@PathVariable Long id, @RequestBody DeviceDto device) {
        try {
            Device patchedDevice = deviceService.patchDevice(id, device);
            return ResponseEntity.ok(patchedDevice);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

        }
    }
}
