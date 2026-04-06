package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ResumeController {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ResumeService resumeService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/uploadResume")
    public String uploadResume(@RequestParam("name") String name,
                               @RequestParam("email") String email,
                               @RequestParam("phone") String phone,
                               @RequestParam("file") MultipartFile file,
                               Model model) {

        try {
            String extractedText = resumeService.extractTextFromPdf(file);
            String extractedSkills = resumeService.extractSkills(extractedText);
            String recommendedJob = resumeService.recommendJob(extractedSkills);

            Candidate candidate = new Candidate(
                    name,
                    email,
                    phone,
                    file.getOriginalFilename(),
                    extractedText,
                    extractedSkills,
                    recommendedJob,
                    "Pending"
            );

            candidateRepository.save(candidate);

            model.addAttribute("name", name);
            model.addAttribute("email", email);
            model.addAttribute("phone", phone);
            model.addAttribute("fileName", file.getOriginalFilename());
            model.addAttribute("extractedText", extractedText);
            model.addAttribute("extractedSkills", extractedSkills);
            model.addAttribute("recommendedJob", recommendedJob);
            model.addAttribute("status", "Pending");

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error reading PDF file");
        }

        return "result";
    }

    @GetMapping("/admin/candidates")
    public String viewCandidates(Model model) {
        List<Candidate> candidates = candidateRepository.findAll();
        model.addAttribute("candidates", candidates);
        return "candidates";
    }

    @GetMapping("/select/{id}")
    public String selectCandidate(@PathVariable Long id) {
        Candidate candidate = candidateRepository.findById(id).orElse(null);
        if (candidate != null) {
            candidate.setStatus("Selected");
            candidateRepository.save(candidate);
        }
        return "redirect:/admin/candidates";
    }

    @GetMapping("/reject/{id}")
    public String rejectCandidate(@PathVariable Long id) {
        Candidate candidate = candidateRepository.findById(id).orElse(null);
        if (candidate != null) {
            candidate.setStatus("Rejected");
            candidateRepository.save(candidate);
        }
        return "redirect:/admin/candidates";
    }
}