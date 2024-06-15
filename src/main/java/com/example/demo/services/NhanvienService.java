package com.example.demo.services;

import com.example.demo.Repository.NhanvienRepository;
import com.example.demo.models.Nhanvien;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NhanvienService {
    private final NhanvienRepository nhanvienRepository;
    // Retrieve all products from the database
    public List<Nhanvien> getAllNhanvien() {
        return nhanvienRepository.findAll();
    }
    // Retrieve a product by its id
    public Optional<Nhanvien> getNhanvienById(String id) {
        return nhanvienRepository.findById(id);
    }
    // Add a new product to the database
    public Nhanvien addNhanvien(Nhanvien nhanvien) {
        return nhanvienRepository.save(nhanvien);
    }
    // Update an existing product
    public Nhanvien updateNhanvien(@NotNull Nhanvien nhanvien) {
        Nhanvien existingNhanvien = nhanvienRepository.findById(nhanvien.getMaNV())
                .orElseThrow(() -> new IllegalStateException("Product with ID " +
                        nhanvien.getMaNV() + " does not exist."));
        existingNhanvien.setName(nhanvien.getName());
        existingNhanvien.setPhai(nhanvien.getPhai());
        existingNhanvien.setNoisinh(nhanvien.getNoisinh());
        existingNhanvien.setMaphong(nhanvien.getMaphong());
        existingNhanvien.setLuong(nhanvien.getLuong());
        return nhanvienRepository.save(existingNhanvien);
    }
    // Delete a product by its id
    public void deleteNhanvienById(String id) {
        if (!nhanvienRepository.existsById(id)) {
            throw new IllegalStateException("Product with ID " + id + " does not exist.");
        }
        nhanvienRepository.deleteById(id);
    }
}
