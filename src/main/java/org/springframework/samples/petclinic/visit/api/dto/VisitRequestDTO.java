package org.springframework.samples.petclinic.visit.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record VisitRequestDTO(@NotNull(message = "El ID de la mascota es obligatorio") Integer petId,

		@NotNull(message = "La fecha de la visita es obligatoria") LocalDate date,

		@NotBlank(message = "La descripción es obligatoria") String description) {
}
