package org.springframework.samples.petclinic.owner.api.dto;

import java.time.LocalDate;

public record PetResponseDTO(Integer id, String name, LocalDate birthDate, String type) {
}
