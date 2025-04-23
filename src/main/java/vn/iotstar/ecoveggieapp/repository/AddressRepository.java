package vn.iotstar.ecoveggieapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.iotstar.ecoveggieapp.models.AddressModel;

@Repository
public interface AddressRepository extends CrudRepository<AddressModel, Integer> {

    // Truy vấn địa chỉ mặc định theo user_id
    @Query(value = "SELECT * FROM address WHERE user_id = :userId AND is_default = 1", nativeQuery = true)
    AddressModel findDefaultAddressByUserId(int userId);
}
