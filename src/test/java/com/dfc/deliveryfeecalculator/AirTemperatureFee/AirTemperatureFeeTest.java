package com.dfc.deliveryfeecalculator.AirTemperatureFee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AirTemperatureFeeTest {

    @Mock
    private AirTemperatureFeeRepository airTemperatureFeeRepository;

    @InjectMocks
    private AirTemperatureFeeService airTemperatureFeeService;

    private AirTemperatureFee sampleAirTemperatureFee;

    @BeforeEach
    void setUp() {
        sampleAirTemperatureFee = new AirTemperatureFee(-10F, 0F, 1F, 0.5F, 0F);
    }

    @Test
    void testGetAirTemperatureFee_Success() {
        when(airTemperatureFeeRepository.findById(1L)).thenReturn(Optional.of(sampleAirTemperatureFee));

        ResponseEntity<AirTemperatureFee> response = airTemperatureFeeService.getAirTemperatureFee();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sampleAirTemperatureFee, response.getBody());
    }

    @Test
    void testGetAirTemperatureFee_NotFound() {
        when(airTemperatureFeeRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<AirTemperatureFee> response = airTemperatureFeeService.getAirTemperatureFee();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testUpdateAirTemperatureFee_Success() {
        // Create a sample AirTemperatureFee object with the expected values
        AirTemperatureFee expectedAirTemperatureFee = new AirTemperatureFee(-20.0f, 0.0f, 1.0f, 0.5f, 0.0f);

        // Mock the behavior of the repository to return the sample AirTemperatureFee object
        when(airTemperatureFeeRepository.findById(1L)).thenReturn(Optional.of(expectedAirTemperatureFee));

        // Create an updated AirTemperatureFee object with the updated values
        AirTemperatureFee updatedAirTemperatureFee = new AirTemperatureFee(-20.0f, 0.0f, 1.0f, 0.5f, 0.0f);

        // Mock the behavior of the repository to return the updated AirTemperatureFee object after saving
        when(airTemperatureFeeRepository.save(any(AirTemperatureFee.class))).thenReturn(updatedAirTemperatureFee);

        // Call the method under test
        ResponseEntity<AirTemperatureFee> response = airTemperatureFeeService.updateAirTemperatureFee(updatedAirTemperatureFee);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedAirTemperatureFee, response.getBody());

    }


    @Test
    void testUpdateAirTemperatureFee_NotFound() {
        when(airTemperatureFeeRepository.findById(1L)).thenReturn(Optional.empty());

        AirTemperatureFee updatedAirTemperatureFee = new AirTemperatureFee(-20F, 0F, 1F, 0.5F, 0F);

        ResponseEntity<AirTemperatureFee> response = airTemperatureFeeService.updateAirTemperatureFee(updatedAirTemperatureFee);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        // Verify that save method is never invoked
        verify(airTemperatureFeeRepository, never()).save(any());
    }

    // Add more tests as needed
}
