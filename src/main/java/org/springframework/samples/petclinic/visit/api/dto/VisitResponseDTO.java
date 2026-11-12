package org.springframework.samples.petclinic.visit.api.dto;

import java.time.LocalDate;

public record VisitResponseDTO(Integer id, LocalDate date, String description, Integer petId) {
}
