package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.model.Faq;
import com.nutrimeal.nutrimeal.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faq")
@RequiredArgsConstructor
public class RestFaq {

    private final FaqService faqService;

    @GetMapping()
    public ResponseEntity<List<Faq>> getAllFaq() {
        return ResponseEntity.ok(faqService.getAllFaqs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faq> getFaqById(@PathVariable int id) {
        return ResponseEntity.ok(faqService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<Faq> addFaq(@RequestBody Faq faq) {
        return ResponseEntity.ok(faqService.save(faq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faq> updateFaq(@PathVariable int id, @RequestBody Faq faq) {
        return ResponseEntity.ok(faqService.update(id, faq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFaq(@PathVariable int id) {
        faqService.deleteById(id);
        return ResponseEntity.ok("Faq with id " + id + " deleted");
    }
}
