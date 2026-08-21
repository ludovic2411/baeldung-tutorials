package com.example.baeldung_tutorials.controllers;

import com.example.baeldung_tutorials.entities.Country;
import com.example.baeldung_tutorials.services.CountryQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    @Autowired private CountryQueryService countryQueryService;

    @GetMapping("/all/{page}")
    public ResponseEntity<List<Country>> getAllCountry(@PathVariable int page, @RequestParam(required = false) Integer items){
       return items == null || items == 0 ? ResponseEntity.ok(countryQueryService.getAllCountries(page)) :
               ResponseEntity.ok(countryQueryService.getAllCountries(page,items));
    }

    @GetMapping
    public ResponseEntity<Object> getCountry(@RequestParam(value = "name", required = false) String name,@RequestParam(value = "code",required = false) String code){
        if((name == null || name.trim().isBlank()) && (code == null || code.trim().isBlank())) return ResponseEntity.badRequest().body("No parameter found!");

        if(name != null && !name.trim().isBlank()) {
            //put first letter to uppercase everytime
            name = name.replace(name.charAt(0),Character.toUpperCase(name.charAt(0)));
        }

        if(code != null && !code.trim().isBlank()) {
            if(code.length() > 3) return ResponseEntity.badRequest().body("Country code is too long");
            code = code.toUpperCase();
        }
        var country = countryQueryService.findCountry(name,code);

       return country.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok("No country found"));
    }


}
