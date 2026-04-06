package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeService {

    public String extractTextFromPdf(MultipartFile file) throws IOException {
        PDDocument document = Loader.loadPDF(file.getBytes());
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();
        return text;
    }

    public String extractSkills(String text) {
        text = text.toLowerCase();

        List<String> skills = new ArrayList<>();

        String[] predefinedSkills = {
            "java", "spring boot", "mysql", "html", "css", "javascript",
            "aws", "docker", "kubernetes", "jenkins", "git", "github",
            "ansible", "python", "react", "mongodb"
        };

        for (String skill : predefinedSkills) {
            if (text.contains(skill)) {
                skills.add(skill);
            }
        }

        if (skills.isEmpty()) {
            return "No matching skills found";
        }

        return String.join(", ", skills);
    }

    public String recommendJob(String skills) {
        String lowerSkills = skills.toLowerCase();

        if (lowerSkills.contains("java") && lowerSkills.contains("spring boot") && lowerSkills.contains("mysql")) {
            return "Java Backend Developer";
        } else if (lowerSkills.contains("html") && lowerSkills.contains("css") && lowerSkills.contains("javascript")) {
            return "Frontend Developer";
        } else if (lowerSkills.contains("aws") && lowerSkills.contains("docker") && lowerSkills.contains("jenkins")) {
            return "DevOps Engineer";
        } else if (lowerSkills.contains("mongodb") && lowerSkills.contains("react") && lowerSkills.contains("javascript")) {
            return "Full Stack Developer";
        } else {
            return "General Software Engineer";
        }
    }
}