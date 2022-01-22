package com.example.SpringBoot10thLesson1stHomeWork.controller;

import com.example.SpringBoot10thLesson1stHomeWork.entity.Hotel;
import com.example.SpringBoot10thLesson1stHomeWork.entity.Room;
import com.example.SpringBoot10thLesson1stHomeWork.payload.RoomDto;
import com.example.SpringBoot10thLesson1stHomeWork.repository.HotelRepository;
import com.example.SpringBoot10thLesson1stHomeWork.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/room")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping
    public Page<Room> getAllRooms(@RequestParam int page){
        Pageable pageable = PageRequest.of(page,10);
        Page<Room> roomPage = roomRepository.findAll(pageable);
        return roomPage;
    }

    @GetMapping("/byHotel/{id}")
    public Page<Room> getRoomById(@RequestParam int page,@PathVariable Integer id){
        Pageable pageable = PageRequest.of(page,10);
        Page<Room> roomPage = roomRepository.findAllByHotelId(id,pageable);
        return roomPage;

    }
    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Integer id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        return optionalRoom.orElseGet(Room::new);
    }

    @PostMapping
    public String addRoom(@RequestBody RoomDto dto){
        Optional<Hotel> optionalHotel = hotelRepository.findById(dto.getHotelId());
        if (optionalHotel.isPresent()){
            Hotel hotel = optionalHotel.get();
            Room room = new Room();
            room.setFloor(dto.getFloor());
            room.setNumber(dto.getNumber());
            room.setSize(dto.getSize());
            room.setHotel(hotel);
            roomRepository.save(room);
            return "Room added";
        }
        return "Hotel not found";
    }
    @PutMapping("/{id}")
    public String editRoom(@PathVariable Integer id, @RequestBody RoomDto dto){
        Optional<Hotel> optionalHotel = hotelRepository.findById(dto.getHotelId());
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalHotel.isPresent()&&optionalRoom.isPresent()){
            Room editingRoom=optionalRoom.get();
            editingRoom.setFloor(dto.getFloor());
            editingRoom.setNumber(dto.getNumber());
            editingRoom.setSize(dto.getSize());
            editingRoom.setHotel(optionalHotel.get());
            roomRepository.save(editingRoom);
            return "Room edited";
        }
        return "Hotel or Room not found";
    }
    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()){
            roomRepository.deleteById(id);
            return "Room deleted";
        }
        return "Room not found";
    }
}
