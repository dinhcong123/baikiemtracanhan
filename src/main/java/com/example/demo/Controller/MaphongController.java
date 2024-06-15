package com.example.demo.Controller;


import com.example.demo.models.Maphong;
import com.example.demo.services.MaPhongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class MaphongController {
    @Autowired
    private final MaPhongService maPhongService;

    @GetMapping("/maphong/add")
    public String showAddForm(Model model) {
        model.addAttribute("maphong", new Maphong());
        return "maphong/add-maphong";
    }

    @PostMapping("/maphong/add")
    public String addMaphong(@Valid Maphong maphong, BindingResult result) {
        if (result.hasErrors()) {
            return "maphong/add-maphong";
        }
        maPhongService.addMaPhong(maphong);
        return "redirect:/maphong";
    }

    @GetMapping("/maphong")
    public String listCategories(Model model) {
        List<Maphong> maphong = maPhongService.getAllMaphong();
        model.addAttribute("maphong", maphong);
        return "maphong/maphong-list";
    }
    // GET request to show category edit form
    @GetMapping("/maphong/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Maphong maphong = maPhongService.getMaphongById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid maphong Id:"
                        + id));
        model.addAttribute("maphong", maphong);
        return "maphong/update-maphong";
    }
    // POST request to update category
    @PostMapping("/maphong/update/{id}")
    public String updateMaphong(@PathVariable("id") String id, @Valid Maphong maphong,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            maphong.setMa_Phong(id);
            return "maphong/update-maphong";
        }
        maPhongService.updateMaPhong(maphong);
        model.addAttribute("maphong", maPhongService.getAllMaphong());
        return "redirect:/maphong";
    }
    // GET request for deleting category
    @GetMapping("/maphong/delete/{id}")
    public String deleteCategory(@PathVariable("id") String id, Model model) {
        Maphong maphong = maPhongService.getMaphongById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid maphong Id:"
                        + id));
        maPhongService.deleteMaphongById(id);
        model.addAttribute("maphong", maPhongService.getAllMaphong());
        return "redirect:/maphong";
    }
}
