package com.ifc.work.restImpl;

import com.ifc.work.dtos.AddressDto;
import com.ifc.work.rest.AddressRest;
import com.ifc.work.services.AddressService;
import com.ifc.work.utils.LibraryUtils;
import constants.LibraryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressRestImpl implements AddressRest {


    @Autowired
    AddressService addressService;

    @Override
    public ResponseEntity<String> getAddress(Long addressId) {
        try{
            return addressService.getAddress(addressId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);


    }

    @Override
    public ResponseEntity<String> addAddress(AddressDto addressDto) {
        try{
            return addressService.addAddress(addressDto);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);


    }

    @Override
    public ResponseEntity<String> editAddress(Long addressId) {
        try{
            return addressService.editAddress(addressId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);


    }

    @Override
    public ResponseEntity<String> deleteAddress(Long addressId) {
        try{
            return addressService.deleteAddress(addressId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);


    }
}
