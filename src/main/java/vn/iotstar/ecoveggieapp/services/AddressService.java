package vn.iotstar.ecoveggieapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iotstar.ecoveggieapp.models.AddressModel;
import vn.iotstar.ecoveggieapp.repository.AddressRepository;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public AddressModel getDefaultAddressByUserId(int userId) {
        return addressRepository.findDefaultAddressByUserId(userId);
    }
    
    // Lấy tất cả địa chỉ theo user_id
    public List<AddressModel> getAllAddressesByUserId(int userId) {
        return addressRepository.findAllAddressesByUserId(userId);
    }
}
