package com.example.SpringBoot10thLesson1stHomeWork.controller;

import com.example.SpringBoot10thLesson1stHomeWork.entity.Hotel;
import com.example.SpringBoot10thLesson1stHomeWork.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping
    public List<Hotel> getHotelsALl(){
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        return optionalHotel.orElseGet(Hotel::new);
    }
    @PostMapping
    public String addNewHotel(@RequestBody Hotel hotel){
        hotelRepository.save(hotel);
        return "Hotel added";
    }
    @PutMapping("/{id}")
    public String editHotel(@PathVariable Integer id,@RequestBody Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()){
            Hotel editingHotel = optionalHotel.get();
            editingHotel.setHotelName(hotel.getHotelName());
            hotelRepository.save(editingHotel);
            return "Hotel edited";
        }
        return "Hotel not found";
    }
    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()){
            hotelRepository.deleteById(id);
            return "Hotel deleted";
        }
        return "Hotel not found";
    }
}
