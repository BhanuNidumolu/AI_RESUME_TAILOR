package com.example.AIResumeBuilder.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class HelperService {

    public String extractTextFromFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        if (filename == null) {
            throw new IOException("File has no name");
        }
        String lower = filename.toLowerCase();

        try (InputStream in = file.getInputStream()) {
            if (lower.endsWith(".pdf")) {
                return extractPdf(in);
            } else if (lower.endsWith(".docx")) {
                return extractDocx(in);
            } else if (lower.endsWith(".doc")) {
                return extractDoc(in);
            } else if (lower.endsWith(".txt")) {
                return new String(file.getBytes(), StandardCharsets.UTF_8);
            } else {
                return extractWithTika(in);
            }
        }
    }

    private String extractPdf(InputStream in) throws IOException {
        try (PDDocument doc = PDDocument.load(in)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(doc);
        }
    }

    private String extractDocx(InputStream in) throws IOException {
        try (XWPFDocument doc = new XWPFDocument(in);
             XWPFWordExtractor extractor = new XWPFWordExtractor(doc)) {
            return extractor.getText();
        }
    }

    private String extractDoc(InputStream in) throws IOException {
        try (HWPFDocument doc = new HWPFDocument(in);
             WordExtractor extractor = new WordExtractor(doc)) {
            return extractor.getText();
        }
    }

    private String extractWithTika(InputStream in) throws IOException {
        Tika tika = new Tika();
        try {
            return tika.parseToString(in);
        } catch (TikaException e) {
            throw new IOException("Tika parse error", e);
        }
    }
}
