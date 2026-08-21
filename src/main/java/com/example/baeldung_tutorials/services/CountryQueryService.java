package com.example.baeldung_tutorials.services;

import com.example.baeldung_tutorials.entities.Country;
import com.example.baeldung_tutorials.repositories.CountryRepository;
import com.example.baeldung_tutorials.repositories.PagingCountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryQueryService {

    @Autowired private CountryRepository countryRepository;
    @Autowired private PagingCountryRepository pagingCountryRepository;

    private final static int DEFAULT_ITEMS_PER_PAGE =  5;

    public List<Country> getAllCountries(int page) {
        Pageable pageWithFiveCountries = PageRequest.of(page, DEFAULT_ITEMS_PER_PAGE);
        return pagingCountryRepository.findAll(pageWithFiveCountries).toList();
    }

    public List<Country> getAllCountries(int page, int itemPerPage) {
        Pageable pageWithFiveCountries = PageRequest.of(page, itemPerPage);
        return pagingCountryRepository.findAll(pageWithFiveCountries).toList();
    }

    public Optional<Country> findCountry(String name, String code){
        return name != null && !name.trim().isBlank() ? this.countryRepository.findByName(name) : this.countryRepository.findByCode(code.trim());
    }

}
