package com.example.baeldung_tutorials.services;

import com.example.baeldung_tutorials.entities.Country;
import com.example.baeldung_tutorials.repositories.CountryRepository;
import com.example.baeldung_tutorials.repositories.PagingCountryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CountryQueryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private PagingCountryRepository pagingCountryRepository;

    @InjectMocks private CountryQueryService countryQueryService;

    @Test
    public void testGetAllCountries() {
        Country country = new Country(1, "Spain", "SPA");
        Page<Country> page = new PageImpl<>(List.of(country));

        Mockito.when(pagingCountryRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);

        assertFalse(countryQueryService.getAllCountries(0).isEmpty());
    }

    @Test
    public void testGetAllCountriesWithItemperPage() {
        Country country = new Country(1, "Spain", "SPA");
        Page<Country> page = new PageImpl<>(List.of(country));

        Mockito.when(pagingCountryRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);

        assertFalse(countryQueryService.getAllCountries(0,16).isEmpty());
    }

    @Test
    void testFindCountryByName() {
        Mockito.when(countryQueryService.findCountry("France","FRA")).thenReturn(Optional.of(new Country(1,"France","FRA")));
        assertTrue(countryQueryService.findCountry("France","FRA").isPresent());
    }

    @Test
    void testFindCountryByCode() {
        Mockito.when(countryQueryService.findCountry("","ITA")).thenReturn(Optional.of(new Country(12,"Italy","ITA")));
        assertTrue(countryQueryService.findCountry("","ITA").isPresent());
    }

    @Test
    void testFindCountryNotFound() {
        Mockito.when(countryQueryService.findCountry("Mexico","MEX")).thenReturn(Optional.empty());
        assertTrue(countryQueryService.findCountry("Mexico","MEX").isEmpty());
    }


}