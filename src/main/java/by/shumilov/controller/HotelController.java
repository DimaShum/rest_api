package by.shumilov.controller;

import by.shumilov.data.HotelRepository;
import by.shumilov.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<Hotel> hotelsPageable(Pageable pageable) {
        return hotelRepository.findAll(pageable);
    }

    @GetMapping("/hotel")
    public List<Hotel> index() {
        return hotelRepository.findAll();
    }

    @GetMapping("/hotel/{id}")
    public Hotel show(@PathVariable String id) {
        long hotelId = Integer.parseInt(id);
        return hotelRepository.findOne(hotelId);
    }

    @PostMapping("/hotel")
    public Hotel create(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        int rating = Integer.parseInt(body.get("rating"));
        String address = body.get("address");
        return hotelRepository.save(new Hotel(name, rating, address));
    }

    @PostMapping("/hotel/search")
    public List<Hotel> search(@RequestBody Map<String, String> body) {
        String searchName = body.get("name");
        String searchAdress = body.get("address");
        return hotelRepository.findByNameContainingOrAddressContaining(searchName, searchAdress);
    }

    @PutMapping("/hotel/{id}")
    public Hotel update(@PathVariable String id, @RequestBody Map<String, String> body) {
        long hotelId = Integer.parseInt(id);
        Hotel hotel = hotelRepository.findOne(hotelId);
        hotel.setName(body.get("name"));
        hotel.setRating(Integer.parseInt(body.get("rating")));
        hotel.setAddress(body.get("address"));
        return hotelRepository.save(hotel);
    }
    @DeleteMapping("/hotel/{id}")
    public boolean delete(@PathVariable String id) {
        long hotelId = Integer.parseInt(id);
        hotelRepository.delete(hotelId);
        return true;
    }
}
