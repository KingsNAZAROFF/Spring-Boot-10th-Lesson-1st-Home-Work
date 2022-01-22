package com.example.SpringBoot10thLesson1stHomeWork.repository;
import com.example.SpringBoot10thLesson1stHomeWork.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
    Page<Room> findAllByHotelId(Integer id, Pageable pageable);

}
