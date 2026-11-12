package org.springframework.samples.petclinic.visit.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.owner.Visit;
import org.springframework.samples.petclinic.visit.api.dto.VisitRequestDTO;
import org.springframework.samples.petclinic.visit.api.dto.VisitResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
@Tag(name = "Visits", description = "Gestión de visitas médicas para mascotas")
public class VisitRestController {

	private final OwnerRepository ownerRepository;

	public VisitRestController(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@GetMapping
	@Operation(summary = "Listar visitas", description = "Obtiene el registro de todas las visitas médicas.")
	public ResponseEntity<List<VisitResponseDTO>> getAllVisits() {
		List<VisitResponseDTO> responseDTOs = new ArrayList<>();
		List<Owner> owners = this.ownerRepository.findAll();

		for (Owner owner : owners) {
			for (Pet pet : owner.getPets()) {
				for (Visit visit : pet.getVisits()) {
					responseDTOs
						.add(new VisitResponseDTO(visit.getId(), visit.getDate(), visit.getDescription(), pet.getId()));
				}
			}
		}
		return ResponseEntity.ok(responseDTOs);
	}

	@PostMapping
	@Operation(summary = "Registrar visita",
			description = "Crea un nuevo registro de visita médica asociado a una mascota.")
	public ResponseEntity<VisitResponseDTO> createVisit(@Valid @RequestBody VisitRequestDTO requestDTO) {
		List<Owner> owners = this.ownerRepository.findAll();
		Owner targetOwner = null;
		Pet targetPet = null;

		for (Owner owner : owners) {
			for (Pet pet : owner.getPets()) {
				if (pet.getId() != null && pet.getId().equals(requestDTO.petId())) {
					targetOwner = owner;
					targetPet = pet;
					break;
				}
			}
			if (targetPet != null) {
				break;
			}
		}

		if (targetPet == null || targetOwner == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		Visit visit = new Visit();
		visit.setDate(requestDTO.date());
		visit.setDescription(requestDTO.description());

		targetPet.addVisit(visit);
		this.ownerRepository.save(targetOwner);

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(new VisitResponseDTO(visit.getId(), visit.getDate(), visit.getDescription(), targetPet.getId()));
	}

}
