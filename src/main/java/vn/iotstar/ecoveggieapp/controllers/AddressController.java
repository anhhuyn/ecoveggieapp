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
    
    // Cập nhật địa chỉ
    @PutMapping("/{id}")
    public ResponseEntity<AddressModel> updateAddress(@PathVariable int id, @RequestBody AddressModel updatedAddress) {
        AddressModel updated = addressService.updateAddress(id, updatedAddress);
        
        if (updated == null) {
            return ResponseEntity.notFound().build();  // Trả về 404 nếu địa chỉ không tồn tại
        }
        
        return ResponseEntity.ok(updated);  // Trả về địa chỉ đã được cập nhật
    }
    
 // Thêm địa chỉ mới
    @PostMapping("")
    public ResponseEntity<AddressModel> insertAddress(@RequestBody AddressModel newAddress) {
        try {
            AddressModel createdAddress = addressService.insertAddress(newAddress);
            return ResponseEntity.ok(createdAddress);  // 200 OK với địa chỉ mới
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();  // 400 Bad Request nếu có lỗi
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable int id) {
        boolean deleted = addressService.deleteAddressById(id);
        if (deleted) {
            return ResponseEntity.ok("Địa chỉ đã được xóa thành công.");
        } else {
            return ResponseEntity.notFound().build(); // 404 nếu không tìm thấy địa chỉ
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AddressModel> getAddressById(@PathVariable int id) {
        AddressModel address = addressService.getAddressById(id);
        if (address == null) {
            return ResponseEntity.notFound().build(); // 404 nếu không tìm thấy
        }
        return ResponseEntity.ok(address); // 200 OK với địa chỉ chi tiết
    }

}
