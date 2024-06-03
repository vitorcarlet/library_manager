package com.ifc.work.rest;


import com.ifc.work.dtos.AddressDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/adress")
public interface AddressRest {

    @GetMapping("/{adressId}")
    ResponseEntity<String> getAddress(@PathVariable Long addressId);

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<String> addAddress(@RequestBody AddressDto addressDto);

    @PatchMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<String> editAddress(@RequestBody Long addressId);

    @DeleteMapping("/remove")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<String> deleteAddress(@RequestBody Long addressId);
}
