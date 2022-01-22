package com.example.SpringBoot10thLesson1stHomeWork.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    private Integer number;
    private Integer floor;
    private Integer size;
    private Integer hotelId;
}
