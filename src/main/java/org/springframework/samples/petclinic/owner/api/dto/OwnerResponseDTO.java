package org.springframework.samples.petclinic.owner.api.dto;

import java.util.List;

public record OwnerResponseDTO(Integer id, String firstName, String lastName, String address, String city,
		String telephone, List<PetResponseDTO> pets) {
}
