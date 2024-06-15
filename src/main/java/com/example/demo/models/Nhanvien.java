package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Nhân Viên")
public class Nhanvien {
    @Id
    @Length(min =0,max =50, message="Mã nhân viên không quá 3 ký tự")
    private String MaNV;
    @NotBlank( message = "tên Nhân viên không được để trống !!!")
    private String name;

    @Length(min =0,max =50, message="Giới tính không quá 3 ký tự")
    private String Phai;

    @Length(min =0,max =200, message="Nơi sinh không quá 200 ký tự")
    private String Noisinh;

    @ManyToOne
    @JoinColumn(name = "Mã Phòng")
    private Maphong maphong;

    private int Luong;
}
