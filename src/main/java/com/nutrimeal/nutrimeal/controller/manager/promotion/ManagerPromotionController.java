package com.nutrimeal.nutrimeal.controller.manager.promotion;

import com.nutrimeal.nutrimeal.model.Promotion;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.service.PromotionService;
import com.nutrimeal.nutrimeal.service.UserPromotionService;
import com.nutrimeal.nutrimeal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/manager")
@Controller
@RequiredArgsConstructor
public class ManagerPromotionController {

    private final PromotionService promotionService;
    private final UserPromotionService userPromotionService;
    private final UserService userService;

    @GetMapping("/promotion")
    public String promotionManager(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 6);
        model.addAttribute("promotions", promotionService.getAllPromotions(pageable));
        return "manager/promotion/promotion";
    }

    @GetMapping("/promotion/search")
    public String searchPromotion(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            Model model
    ) {
        Pageable pageable = PageRequest.of(page, 6);
        model.addAttribute("name", name.trim());
        model.addAttribute("promotions", promotionService.findAllByPromotionCode(name, pageable));
        return "manager/promotion/promotion";
    }

    @GetMapping("/promotion/add")
    public String addPromotion() {
        return "manager/promotion/addPromotion";
    }

    @PostMapping("/promotion/add")
    public String addPromotion(@ModelAttribute Promotion promotion) {
        try {
            if(promotionService.existsByPromotionCode(promotion.getPromotionCode())){
                return "redirect:/manager/promotion/add?duplicate=true";
            }
            promotion.setPromotionStatus(true);
            promotionService.save(promotion);
            return "redirect:/manager/promotion/add?success=true";
        } catch (Exception e) {
            return "redirect:/manager/promotion/add?error=true";
        }
    }

    @GetMapping("/promotion/update/{id}")
    public String updatePromotion(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("promotion", promotionService.findPromotionById(id));
        return "manager/promotion/updatePromotion";
    }

    @PostMapping("/promotion/update/{id}")
    public String updatePromotion(@PathVariable("id") Integer id, @ModelAttribute Promotion promotion) {
        try {
            Promotion oldPromotion = promotionService.findPromotionById(id);
            if(userPromotionService.existsByPromotionId(id) && !promotion.getPromotionCode().equals(oldPromotion.getPromotionCode())){
                return "redirect:/manager/promotion/update/" + id + "?fail=true";
            }
            if(promotionService.existsByPromotionCode(promotion.getPromotionCode()) && !promotion.getPromotionCode().equals(oldPromotion.getPromotionCode()) ){
                return "redirect:/manager/promotion/update/" + id + "?duplicate=true";
            }
            promotionService.updatePromotion(id, promotion);
            return "redirect:/manager/promotion/update/" + id + "?success=true";
        } catch (Exception e) {
            return "redirect:/manager/promotion/update/" + id + "?error=true";
        }
    }

    @GetMapping("/promotion/delete/{id}")
    public String deleteDish(@PathVariable("id") Integer id) {
        promotionService.deletePromotionById(id);
        return "redirect:/manager/promotion";
    }

    private User getUser(Principal principal, Model model) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken && principal != null) {
            boolean isOauth2User = principal instanceof OAuth2AuthenticationToken && principal != null;
            model.addAttribute("isOauth2User", isOauth2User);
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
            OAuth2User oauthUser = token.getPrincipal();
            user = userService.findByEmail(oauthUser.getAttribute("email"));
        } else {
            model.addAttribute("isOauth2User", false);
            user = userService.findByUsername(principal.getName());
        }
        return user;
    }
}