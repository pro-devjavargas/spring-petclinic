package org.springframework.samples.petclinic.vet.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.vet.api.dto.VetResponseDTO;
import org.springframework.samples.petclinic.vet.VetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vets")
@Tag(name = "Vets", description = "Consulta de veterinarios y especialidades")
public class VetRestController {

	private final VetRepository vetRepository;

	public VetRestController(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	@GetMapping
	@Operation(summary = "Listar veterinarios",
			description = "Obtiene la lista de veterinarios con sus respectivas especialidades.")
	public ResponseEntity<List<VetResponseDTO>> getAllVets() {
		List<VetResponseDTO> vets = this.vetRepository.findAll()
			.stream()
			.map(vet -> new VetResponseDTO(vet.getId(), vet.getFirstName(), vet.getLastName(),
					vet.getSpecialties().stream().map(s -> s.getName()).collect(Collectors.toList())))
			.collect(Collectors.toList());
		return ResponseEntity.ok(vets);
	}

}
