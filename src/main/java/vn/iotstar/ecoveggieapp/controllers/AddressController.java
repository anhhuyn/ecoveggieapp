package vn.iotstar.ecoveggieapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.iotstar.ecoveggieapp.models.AddressModel;
import vn.iotstar.ecoveggieapp.services.AddressService;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/default")
    public ResponseEntity<AddressModel> getDefaultAddress(@RequestParam("user_id") int userId) {
        AddressModel defaultAddress = addressService.getDefaultAddressByUserId(userId);
        if (defaultAddress == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(defaultAddress);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<AddressModel>> getAllAddresses(@RequestParam("user_id") int userId) {
        List<AddressModel> addresses = addressService.getAllAddressesByUserId(userId);
        if (addresses == null || addresses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(addresses);
    }
}
