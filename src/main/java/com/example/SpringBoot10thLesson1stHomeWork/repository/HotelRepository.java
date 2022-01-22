package com.example.SpringBoot10thLesson1stHomeWork.repository;

import com.example.SpringBoot10thLesson1stHomeWork.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer> {

}
