package com.xcelore.api.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity

public class Patient { @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

    @Size(min = 3, message = "Name should be at least 3 characters")
    private String name;

    @Size(max = 20, message = "City name must be at most 20 characters")
    private String city;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 10, message = "Phone number must be at least 10 digits")
    private String phone;

    @NotNull(message = "Symptom is required")
    @Enumerated(EnumType.STRING)
    private Symptom symptom;

    public enum Symptom {
        ARTHRITIS,
        BACK_PAIN,
        TISSUE_INJURIES,
        DYSMENORRHEA,
        SKIN_INFECTION,
        SKIN_BURN,
        EAR_PAIN
    }

    // Getters and Setters
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }
}
