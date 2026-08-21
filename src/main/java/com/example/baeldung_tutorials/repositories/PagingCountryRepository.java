package com.example.baeldung_tutorials.repositories;

import com.example.baeldung_tutorials.entities.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * see https://www.baeldung.com/spring-data-jpa-pagination-sorting
 * Works like a regular JpaRepository
 */
public interface PagingCountryRepository extends PagingAndSortingRepository<Country,Integer> {

}
