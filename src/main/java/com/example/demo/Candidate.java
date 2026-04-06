package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String fileName;

    @Column(length = 10000)
    private String extractedText;

    @Column(length = 1000)
    private String extractedSkills;

    private String recommendedJob;

    private String status;

    public Candidate() {
    }

    public Candidate(String name, String email, String phone, String fileName,
                     String extractedText, String extractedSkills,
                     String recommendedJob, String status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.fileName = fileName;
        this.extractedText = extractedText;
        this.extractedSkills = extractedSkills;
        this.recommendedJob = recommendedJob;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtractedText() {
        return extractedText;
    }

    public void setExtractedText(String extractedText) {
        this.extractedText = extractedText;
    }

    public String getExtractedSkills() {
        return extractedSkills;
    }

    public void setExtractedSkills(String extractedSkills) {
        this.extractedSkills = extractedSkills;
    }

    public String getRecommendedJob() {
        return recommendedJob;
    }

    public void setRecommendedJob(String recommendedJob) {
        this.recommendedJob = recommendedJob;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}