package org.springframework.samples.petclinic.owner.api.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OwnerRequestDTO(
		@NotBlank(message = "El nombre es obligatorio") @Size(min = 1, max = 30,
				message = "El nombre debe tener entre 1 y 30 caracteres") String firstName,

		@NotBlank(message = "El apellido es obligatorio") @Size(min = 1, max = 30,
				message = "El apellido debe tener entre 1 y 30 caracteres") String lastName,

		@NotBlank(message = "La dirección es obligatoria") @Size(max = 255,
				message = "La dirección no puede superar los 255 caracteres") String address,

		@NotBlank(message = "La ciudad es obligatoria") @Size(max = 80,
				message = "La ciudad no puede superar los 80 caracteres") String city,

		@NotBlank(message = "El teléfono es obligatorio") @Digits(fraction = 0, integer = 10,
				message = "El teléfono debe contener solo números de hasta 10 dígitos") @Size(min = 7, max = 10,
						message = "El teléfono debe tener entre 7 y 10 dígitos") String telephone) {
}
