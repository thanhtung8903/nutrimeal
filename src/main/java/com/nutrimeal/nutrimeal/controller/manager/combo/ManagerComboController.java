package com.nutrimeal.nutrimeal.controller.manager.combo;

import com.nutrimeal.nutrimeal.model.Combo;
import com.nutrimeal.nutrimeal.model.ComboType;
import com.nutrimeal.nutrimeal.service.ComboService;
import com.nutrimeal.nutrimeal.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerComboController {

    private final ComboService comboService;
    private final ImageUploadService imageUploadService;

    @GetMapping("/combo")
    public String combo(Model model) {
        model.addAttribute("listCombos", comboService.getAllCombos());
        return "manager/combo/combo";
    }

    @GetMapping("/combo/add")
    public String addCombo() {
        return "manager/combo/addCombo";
    }

    @PostMapping("/combo/add")
    public String addCombo(@ModelAttribute Combo combo,
                           @RequestParam(value = "image", required = false) MultipartFile image,
                           @RequestParam(value = "combo_type_id", required = true) String comboTypeId) {
        try {
            ComboType comboType = comboService.getComboTypeById(Integer.parseInt(comboTypeId));
            if (image != null && !image.isEmpty()) {
                combo.setComboImage(imageUploadService.uploadFile(image));
            } else {
                combo.setComboImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNk7lxboBaTCE78SGmXH8pM4Gx3uXLf8m_gZUtpEPdTA&s"); // or set to a default image URL
            }
            combo.setComboType(comboType);
            comboService.addCombo(combo);
            return "redirect:/manager/combo/add?success=true";
        } catch (Exception e) {
            return "redirect:/manager/combo/add?error=true";
        }
    }

    @GetMapping("/combo/update/{id}")
    public String updateCombo(@PathVariable int id, Model model) {
        model.addAttribute("combo", comboService.getComboById(id));
        return "manager/combo/updateCombo";
    }

    @PostMapping("/combo/update/{id}")
    public String updateCombo(@PathVariable int id, @ModelAttribute Combo combo,
                              @RequestParam(value = "image", required = false) MultipartFile image,
                              @RequestParam(value = "combo_type_id", required = true) String comboTypeId) {
        try {
            Combo oldCombo = comboService.getComboById(id);
            ComboType comboType = comboService.getComboTypeById(Integer.parseInt(comboTypeId));
            if (image != null && !image.isEmpty()) {
                combo.setComboImage(imageUploadService.uploadFile(image));
            } else {
                combo.setComboImage(oldCombo.getComboImage());
            }
            combo.setComboType(comboType);
            comboService.updateCombo(id, combo);
            return "redirect:/manager/combo/update/" + id + "?success=true";
        } catch (Exception e) {
            return "redirect:/manager/combo/update/" + id + "?error=true";
        }
    }

    @GetMapping("/combo/delete/{id}")
    public String deleteCombo(@PathVariable int id) {
        comboService.deleteCombo(id);
        return "redirect:/manager/combo";
    }
}
