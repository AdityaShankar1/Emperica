package com.emperica.demo.controller;

import com.emperica.demo.service.AiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/summarize/{id}")
    public ResponseEntity<?> summarizeEmployee(@PathVariable Long id) {
        try {
            String summary = aiService.summarizeEmployee(id);
            return ResponseEntity.ok(Map.of("summary", summary));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Summarization failed: " + e.getMessage());
        }
    }
}
