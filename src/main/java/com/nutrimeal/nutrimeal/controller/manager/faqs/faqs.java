package com.nutrimeal.nutrimeal.controller.manager.faqs;
import com.nutrimeal.nutrimeal.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class faqs {
    @GetMapping("/faqs")
    public String faqs() {
        return "manager/faqs/faqs";
    }
}