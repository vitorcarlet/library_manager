package com.ifc.work.servicesImpl;


import com.ifc.work.dtos.AddressDto;
import com.ifc.work.services.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Override
    public ResponseEntity<String> getAddress(Long addressId) {
        return null;
    }

    @Override
    public ResponseEntity<String> addAddress(AddressDto addressDto) {
        return null;
    }

    @Override
    public ResponseEntity<String> editAddress(Long addressId) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteAddress(Long addressId) {
        return null;
    }
}
