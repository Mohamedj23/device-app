package com.task.deviceapp.service;

import com.task.deviceapp.dto.DeviceDto;
import com.task.deviceapp.entity.Device;
import com.task.deviceapp.repository.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    @Test
    void testGetAllDevices_Success() {
        // Arrange
        List<Device> devices = List.of(
                buildDevice(),
                buildDevice()
        );

        when(deviceRepository.findAll()).thenReturn(devices);

        // Act
        List<Device> result = deviceService.getAllDevices();

        // Assert
        assertNotNull(result);
        assertEquals("Device Name 1", result.get(0).getDeviceName());
        assertEquals("Device Brand 1", result.get(0).getDeviceBrand());
        assertEquals(devices.size(), 2);
    }

    @Test
    void testGetDeviceById_Success() {
        Long deviceId = 1L;

        Optional<Device> existedDevice = Optional.of(buildDevice());

        when(deviceRepository.findById(any())).thenReturn(existedDevice);

        Device result = deviceService.getDeviceById(deviceId);

        assertNotNull(result);
        assertEquals(deviceId, result.getId());
        assertEquals("Device Name 1", result.getDeviceName());
        assertEquals("Device Brand 1", result.getDeviceBrand());
    }

    @Test
    void testGetDeviceById_DeviceNotFound() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> deviceService.getDeviceById(2L));
        assertEquals(exception.getMessage(), String.format("Device With Id %s Is Not Found", 2L));
    }

    @Test
    void testGetDeviceByBrand_Success() {
        String deviceBrand = "Device Brand 1";

        Optional<Device> existedDevice = Optional.of(buildDevice());

        when(deviceRepository.findDeviceByDeviceBrand(deviceBrand)).thenReturn(existedDevice);

        Device result = deviceService.getDeviceByBrand(deviceBrand);

        assertNotNull(result);
        assertEquals("Device Brand 1", result.getDeviceBrand());
    }

    @Test
    void testGetDeviceByBrand_DeviceNotFound() {
        String deviceBrand = "Device Brand 1";
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> deviceService.getDeviceByBrand(deviceBrand));
        assertEquals(exception.getMessage(), String.format("Device With Brand %s Is Not Found", deviceBrand));
    }

    @Test
    void testAddDevice(){
        DeviceDto newDeviceDto = buildDeviceDto();

        when(deviceRepository.save(any())).thenReturn(buildDevice());

        Device newDevice = deviceService.addDevice(newDeviceDto);

        assertNotNull(newDevice);
        assertEquals(1L, newDevice.getId());
    }

    @Test
    void testUpdateDevice_Success(){
        Long deviceId = 1L;

        Optional<Device> existedDevice = Optional.of(buildDevice());
        DeviceDto deviceDto = buildDeviceDto();
        deviceDto.setDeviceName("Device Name 2");
        deviceDto.setDeviceBrand("Device Brand 2");

        when(deviceRepository.findById(deviceId)).thenReturn(existedDevice);
        when(deviceRepository.save(any(Device.class))).thenReturn(existedDevice.get());

        Device updatedDevice = deviceService.updateDevice(deviceId, deviceDto);

        assertNotNull(updatedDevice);
        assertEquals("Device Name 2", updatedDevice.getDeviceName());
        assertEquals("Device Brand 2", updatedDevice.getDeviceBrand());

    }

    @Test
    void testUpdateDevice_DeviceNotFound(){
        Long deviceId = 1L;
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> deviceService.updateDevice(1L, new DeviceDto()));
        assertEquals(exception.getMessage(), String.format("Device With ID %s Is Not Found", deviceId));
    }

    private Device buildDevice() {
        return new Device(1L, "Device Name 1", "Device Brand 1");
    }

    private DeviceDto buildDeviceDto() {
        return new DeviceDto("Device Name 1", "Device Brand 1");
    }
}
