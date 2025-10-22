package com.example.AIResumeBuilder.controller;

import com.example.AIResumeBuilder.service.LLMService;
import com.example.AIResumeBuilder.service.ResumeBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ResumeBuilderController {

    @Autowired
    private ResumeBuilderService resumeBuilderService;
    @Autowired
    private LLMService llmService;

    @PostMapping("/generateResume")
    public ResponseEntity<?> generateResume(
            @RequestParam(value = "resumeFile", required = false) MultipartFile resumeFile,
            @RequestParam(value = "jdFile", required = false) MultipartFile jdFile,
            @RequestParam(value = "jdText", required = false) String jdText,
            @RequestParam(value = "instructions", required = true) String instructions
    ) throws IOException { // Let exceptions propagate to the global handler

        List<String> resJdInstruc = resumeBuilderService.processUserInput(resumeFile, jdFile, jdText, instructions);
        String llmPrompt = llmService.generatePrompt(resJdInstruc);
        String generatedResume = llmService.createResume(llmPrompt);

        return ResponseEntity.ok().body(generatedResume);
    }
}