package com.xcelore.api.controller;

import com.xcelore.api.service.SuggestDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suggestion")
public class SuggestionController {

    @Autowired
    private SuggestDoctorService suggestDoctorService;

    @GetMapping("/doctor/{patientId}")
    public Object suggestDoctor(@PathVariable Long patientId) {
        return suggestDoctorService.suggestDoctors(patientId);
    }
}

