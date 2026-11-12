package org.springframework.samples.petclinic.owner.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.owner.api.dto.OwnerRequestDTO;
import org.springframework.samples.petclinic.owner.api.dto.OwnerResponseDTO;
import org.springframework.samples.petclinic.owner.api.dto.PetResponseDTO;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/owners")
@Tag(name = "Owners", description = "Gestión de propietarios de mascotas")
public class OwnerRestController {

	private final OwnerRepository ownerRepository;

	public OwnerRestController(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@GetMapping
	@Operation(summary = "Listar propietarios",
			description = "Obtiene una lista con todos los propietarios registrados.")
	public ResponseEntity<List<OwnerResponseDTO>> getAllOwners() {
		List<OwnerResponseDTO> owners = this.ownerRepository.findAll()
			.stream()
			.map(this::toResponseDTO)
			.collect(Collectors.toList());
		return ResponseEntity.ok(owners);
	}

	@GetMapping("/{ownerId}")
	@Operation(summary = "Obtener propietario por ID",
			description = "Busca un propietario específico por su identificador único.")
	@ApiResponse(responseCode = "200", description = "Propietario encontrado")
	@ApiResponse(responseCode = "404", description = "Propietario no encontrado")
	public ResponseEntity<OwnerResponseDTO> getNestOwner(@PathVariable("ownerId") int ownerId) {
		return this.ownerRepository.findById(ownerId)
			.map(owner -> ResponseEntity.ok(toResponseDTO(owner)))
			.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Crear propietario", description = "Registra un nuevo propietario en el sistema.")
	public ResponseEntity<OwnerResponseDTO> createOwner(@Valid @RequestBody OwnerRequestDTO requestDTO) {
		Owner owner = new Owner();
		updateOwnerFromDTO(owner, requestDTO);
		Owner savedOwner = this.ownerRepository.save(owner);
		return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(savedOwner));
	}

	@PutMapping("/{ownerId}")
	@Operation(summary = "Actualizar propietario",
			description = "Actualiza la información de un propietario existente.")
	public ResponseEntity<OwnerResponseDTO> updateOwner(@PathVariable("ownerId") int ownerId,
			@Valid @RequestBody OwnerRequestDTO requestDTO) {
		return this.ownerRepository.findById(ownerId).map(owner -> {
			updateOwnerFromDTO(owner, requestDTO);
			Owner updatedOwner = this.ownerRepository.save(owner);
			return ResponseEntity.ok(toResponseDTO(updatedOwner));
		}).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	// Métodos utilitarios de mapeo (pueden extraerse a un Mapper independiente después)
	private OwnerResponseDTO toResponseDTO(Owner owner) {
		List<PetResponseDTO> pets = owner.getPets()
			.stream()
			.map(pet -> new PetResponseDTO(pet.getId(), pet.getName(), pet.getBirthDate(),
					pet.getType() != null ? pet.getType().getName() : null))
			.collect(Collectors.toList());

		return new OwnerResponseDTO(owner.getId(), owner.getFirstName(), owner.getLastName(), owner.getAddress(),
				owner.getCity(), owner.getTelephone(), pets);
	}

	private void updateOwnerFromDTO(Owner owner, OwnerRequestDTO dto) {
		owner.setFirstName(dto.firstName());
		owner.setLastName(dto.lastName());
		owner.setAddress(dto.address());
		owner.setCity(dto.city());
		owner.setTelephone(dto.telephone());
	}

}
