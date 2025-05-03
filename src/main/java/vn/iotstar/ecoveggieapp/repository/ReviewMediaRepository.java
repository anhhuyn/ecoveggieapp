package vn.iotstar.ecoveggieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.ecoveggieapp.models.ReviewMediaModel;

@Repository
public interface ReviewMediaRepository extends  CrudRepository<ReviewMediaModel, Integer> {

    // cũng dùng save() hoặc saveAll()
}
