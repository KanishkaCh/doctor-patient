package com.xcelore.api.service;

import com.xcelore.api.model.Doctor;
import com.xcelore.api.model.Patient;
import com.xcelore.api.model.Doctor.Speciality;
import com.xcelore.api.model.Patient.Symptom;
import com.xcelore.api.repository.DoctorRepository;
import com.xcelore.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SuggestDoctorService {

    private static final List<String> VALID_CITIES = Arrays.asList("Delhi", "Noida", "Faridabad");

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * Map symptoms to their specialties.
     */
    private Speciality getSpecialityForSymptom(Symptom symptom) {
        switch (symptom) {
            case ARTHRITIS:
            case BACK_PAIN:
            case TISSUE_INJURIES:
                return Speciality.ORTHOPAEDIC;
            case DYSMENORRHEA:
                return Speciality.GYNECOLOGY;
            case SKIN_INFECTION:
            case SKIN_BURN:
                return Speciality.DERMATOLOGY;
            case EAR_PAIN:
                return Speciality.ENT;
            default:
                return null;
        }
    }

    /**
     * Suggest doctors based on patient ID.
     */
    public Object suggestDoctors(Long patientId) {
        Optional<Patient> patientOpt = patientRepository.findById(patientId);

        if (patientOpt.isEmpty()) {
            return "Patient not found with ID: " + patientId;
        }

        Patient patient = patientOpt.get();

        // Check if city is valid
        if (!VALID_CITIES.contains(patient.getCity())) {
            return "We are still waiting to expand to your location";
        }

        Speciality speciality = getSpecialityForSymptom(patient.getSymptom());
        if (speciality == null) {
            return "There isn’t any doctor present at your location for your symptom";
        }

        List<Doctor> doctors = doctorRepository.findByCityAndSpeciality(patient.getCity(), speciality);

        if (doctors.isEmpty()) {
            return "There isn’t any doctor present at your location for your symptom";
        }

        return doctors;
    }
}
