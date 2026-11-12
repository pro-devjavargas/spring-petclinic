package org.springframework.samples.petclinic.owner.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OwnerRestControllerTest {

	private MockMvc mockMvc;

	private OwnerRepository ownerRepository;

	@BeforeEach
	void setUp() {
		ownerRepository = Mockito.mock(OwnerRepository.class);
		OwnerRestController ownerRestController = new OwnerRestController(ownerRepository);
		this.mockMvc = MockMvcBuilders.standaloneSetup(ownerRestController).build();
	}

	@Test
	void shouldReturnListOfOwners() throws Exception {
		when(ownerRepository.findAll()).thenReturn(Collections.emptyList());

		this.mockMvc.perform(get("/api/v1/owners").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
