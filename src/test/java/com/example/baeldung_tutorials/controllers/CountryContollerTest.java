package com.example.baeldung_tutorials.controllers;

import com.example.baeldung_tutorials.entities.Country;
import com.example.baeldung_tutorials.services.CountryQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CountryContollerTest {

    @Mock
    private CountryQueryService countryQueryService;

    @InjectMocks
    private CountryController countryContoller;

    @Test
    void testGetAllCountries() {
        Mockito.when(countryQueryService.getAllCountries(Mockito.anyInt())).thenReturn(List.of(new Country(1,"Portugal","POR")));
        var res = countryContoller.getAllCountry(0,null);
        assertTrue(res.getStatusCode().is2xxSuccessful() && res.hasBody());
    }

    @Test
    void getCountryByName() {
        Mockito.when(countryQueryService.findCountry(Mockito.anyString(),Mockito.anyString())).thenReturn(Optional.of(new Country(1,"Portugal","POR")));
        var res = countryContoller.getCountry("portugal","");
        assertTrue(res.getStatusCode().is2xxSuccessful() && res.hasBody());
        Country body = (Country) res.getBody();
        assertEquals("POR",body.getCode());
    }

    @Test
    void getCountryNotFound() {
        Mockito.when(countryQueryService.findCountry(Mockito.anyString(),Mockito.anyString())).thenReturn(Optional.empty());
        var res = countryContoller.getCountry("portugal","");
        assertTrue(res.getStatusCode().is2xxSuccessful() && res.hasBody() && res.getBody().equals("No country found"));
    }

    @Test
    void getCountryNoParamFound() {
        var res = countryContoller.getCountry(null," ");
        assertTrue(res.getStatusCode().is4xxClientError() && res.getBody().equals("No parameter found!"));
        res = countryContoller.getCountry("  ",null);
        assertTrue(res.getStatusCode().is4xxClientError() && res.getBody().equals("No parameter found!"));
    }
}