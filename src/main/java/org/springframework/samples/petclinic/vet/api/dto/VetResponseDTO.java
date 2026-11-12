package org.springframework.samples.petclinic.vet.api.dto;

import java.util.List;

public record VetResponseDTO(Integer id, String firstName, String lastName, List<String> specialties) {
}
