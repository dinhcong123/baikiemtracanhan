package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="Mã Phòng")
public class Maphong {
    @Id
    private String Ma_Phong;
    @NotBlank(message = "Tên là bắt buộc")
    private String name;
}
