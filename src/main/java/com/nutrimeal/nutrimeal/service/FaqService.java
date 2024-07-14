package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.model.Faq;
import com.nutrimeal.nutrimeal.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;

    public List<Faq> getAllFaqs() {
        return faqRepository.findAll();
    }

    public Faq findById(int id) {
        return faqRepository.findById(id).orElse(null);
    }

    public Faq save(Faq faq) {
        return faqRepository.save(faq);
    }

    public void deleteById(int id) {
        faqRepository.deleteById(id);
    }


}
