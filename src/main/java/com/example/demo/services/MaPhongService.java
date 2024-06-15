package com.example.demo.services;

import com.example.demo.Repository.MaphongRepository;
import com.example.demo.models.Maphong;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MaPhongService {
    private final MaphongRepository maphongRepository;
    /**
     * Retrieve all categories from the database.
     * @return a list of categories
     */
    public List<Maphong> getAllMaphong() {
        return maphongRepository.findAll();
    }
    /**
     * Retrieve a category by its id.
     * @param id the id of the category to retrieve
     * @return an Optional containing the found category or empty if not found
     */
    public Optional<Maphong> getMaphongById(String id) {
        return maphongRepository.findById(id);
    }
    /**
     * Add a new category to the database.
     * @param maphong the category to add
     */
    public void addMaPhong(Maphong maphong) {
        maphongRepository.save(maphong);
    }
    /**
     * Update an existing category.
     * @param maphong the category with updated information
     */
    public void updateMaPhong(@NotNull Maphong maphong) {
        Maphong existingMaphong = maphongRepository.findById(maphong.getMa_Phong())
                .orElseThrow(() -> new IllegalStateException("Maphong with ID " +
                        maphong.getMa_Phong() + " does not exist."));
        existingMaphong.setName(maphong.getName());
        maphongRepository.save(existingMaphong);
    }
    public void deleteMaphongById(String id) {
        if (!maphongRepository.existsById(id)) {
            throw new IllegalStateException("Category with ID " + id + " does not exist.");
        }
        maphongRepository.deleteById(id);
    }
}
