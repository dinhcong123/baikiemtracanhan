package com.example.demo.Controller;

import com.example.demo.models.Nhanvien;
import com.example.demo.services.MaPhongService;
import com.example.demo.services.NhanvienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhanvien")
public class NhanvienController {
    @Autowired
    private NhanvienService nhanvienService;
    @Autowired
    private MaPhongService maPhongService; // Đảm bảo bạn đã inject

    // Display a list of all products
    @GetMapping("")
    public String showNhanvienList(Model model) {
        model.addAttribute("nhanvien", nhanvienService.getAllNhanvien());
        return "/nhanvien/nhanvien-list";
    }
    // For adding a new product
    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("nhanvien", new Nhanvien());
        model.addAttribute("maphong", maPhongService.getAllMaphong());
        return "/nhanvien/add-nhanvien";
    }
    // Process the form for adding a new product
    @PostMapping("/add")
    public String addNhanvien(@Valid Nhanvien nhanvien,  BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("nhanvien", nhanvien);
            return "/nhanvien/add-nhanvien";
        }
        nhanvienService.addNhanvien(nhanvien);
        return "redirect:/nhanvien";
    }
    // For editing a product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Nhanvien nhanvien = nhanvienService.getNhanvienById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("nhanvien", nhanvien);
        model.addAttribute("maphong", maPhongService.getAllMaphong());
        return "/nhanvien/update-nhanvien";
    }
    // Process the form for updating a product
    @PostMapping("/edit/{id}")
    public String updateNhanvien(@PathVariable String id,  @Valid Nhanvien nhanvien, BindingResult result, Model model) {
        if (result.hasErrors()) {
            nhanvien.setMaNV(id);
            return "/nhanvien/update-nhanvien";
        }
        nhanvienService.updateNhanvien(nhanvien);
        return "redirect:/nhanvien";
    }
    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteNhanvien(@PathVariable String id) {
        nhanvienService.deleteNhanvienById(id);
        return "redirect:/nhanvien";
    }
}
