package com.example.AIResumeBuilder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@Service
public class ResumeBuilderService{

    @Autowired
    private HelperService helpers;

    public List<String> processUserInput(MultipartFile resumeFile, MultipartFile jdFile, String jdText, String instructions) throws IOException{
        String resumeContent = "";
        String jdContent = "";

        if(resumeFile != null && !resumeFile.isEmpty()){
            resumeContent = helpers.extractTextFromFile(resumeFile);
            System.out.println("Resume Content Extracted: " + resumeContent);
        }

        if(jdFile != null && !jdFile.isEmpty()){
            jdContent = helpers.extractTextFromFile(jdFile);
            System.out.println("JD Content Extracted from File: " + jdContent);
        }
        if(jdText != null && !jdText.trim().isEmpty()){
            jdContent += jdText;
            System.out.println("JD Content Provided as Text: " + jdContent);
        }

        List<String> resJdInstruc = new ArrayList<>();
        resJdInstruc.add(resumeContent);
        resJdInstruc.add(jdContent);
        resJdInstruc.add(instructions);

        return resJdInstruc;
    }
}
