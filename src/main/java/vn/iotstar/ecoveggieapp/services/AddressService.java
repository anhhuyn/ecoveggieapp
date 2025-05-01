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
    
    public AddressModel updateAddress(int id, AddressModel updatedAddress) {
        AddressModel existingAddress = addressRepository.findById(id).orElse(null);

        if (existingAddress != null) {
            // Nếu is_default là true, bỏ mặc định tất cả địa chỉ khác của user
            if (updatedAddress.isIs_default()) {
                int userId = existingAddress.getUser().getUser_id();
                List<AddressModel> userAddresses = addressRepository.findAllAddressesByUserId(userId);

                for (AddressModel addr : userAddresses) {
                    if (addr.getId() != id && addr.isIs_default()) {
                        addr.setIs_default(false);
                        addressRepository.save(addr); // cập nhật lại
                    }
                }
            }

            // Cập nhật thông tin địa chỉ hiện tại
            existingAddress.setProvince(updatedAddress.getProvince());
            existingAddress.setDistrict(updatedAddress.getDistrict());
            existingAddress.setWards(updatedAddress.getWards());
            existingAddress.setDetail(updatedAddress.getDetail());
            existingAddress.setPhone(updatedAddress.getPhone());
            existingAddress.setIs_default(updatedAddress.isIs_default());

            return addressRepository.save(existingAddress);
        }

        return null;
    }
    
    // Thêm địa chỉ
    public AddressModel insertAddress(AddressModel newAddress) {
        // Nếu địa chỉ mới là mặc định
        if (newAddress.isIs_default()) {
            int userId = newAddress.getUser().getUser_id();
            List<AddressModel> userAddresses = addressRepository.findAllAddressesByUserId(userId);

            for (AddressModel addr : userAddresses) {
                if (addr.isIs_default()) {
                    addr.setIs_default(false);
                    addressRepository.save(addr); // cập nhật lại
                }
            }
        }

        // Thêm địa chỉ mới
        return addressRepository.save(newAddress);
    }
    
    // Xoa dia chi
    public boolean deleteAddressById(int id) {
        AddressModel existingAddress = addressRepository.findById(id).orElse(null);

        if (existingAddress != null) {
            addressRepository.deleteById(id);
            return true;
        }

        return false; // Địa chỉ không tồn tại
    }


}
