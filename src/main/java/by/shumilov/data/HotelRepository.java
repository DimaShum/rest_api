package by.shumilov.data;

import by.shumilov.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long>{

    List<Hotel> findByNameContainingOrAddressContaining(String name, String address);
}
