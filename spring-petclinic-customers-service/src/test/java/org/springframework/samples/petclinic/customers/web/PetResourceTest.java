package org.springframework.samples.petclinic.customers.web;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.customers.model.Owner;
import org.springframework.samples.petclinic.customers.model.OwnerRepository;
import org.springframework.samples.petclinic.customers.model.Pet;
import org.springframework.samples.petclinic.customers.model.PetRepository;
import org.springframework.samples.petclinic.customers.model.PetType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Maciej Szarlinski
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(PetResource.class)
@ActiveProfiles("test")
class PetResourceTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PetRepository petRepository;

    @MockBean
    OwnerRepository ownerRepository;

    @Test
    void shouldGetAPetInJSonFormat() throws Exception {

        Pet pet = setupPet();

        given(petRepository.findById(2)).willReturn(Optional.of(pet));


        mvc.perform(get("/owners/2/pets/2").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id").value(2))
            .andExpect(jsonPath("$.name").value("Basil"))
            .andExpect(jsonPath("$.type.id").value(6));
    }

        @Test
    void shouldReturnPetTypes() throws Exception {
        PetType type = new PetType();
        type.setId(1);
        type.setName("Dog");
        given(petRepository.findPetTypes()).willReturn(java.util.Arrays.asList(type));
        mvc.perform(get("/petTypes").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("Dog"));
    }

    @Test
    void shouldCreatePet() throws Exception {
        Owner owner = new Owner();
        java.lang.reflect.Field idField = Owner.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(owner, 1);
        given(ownerRepository.findById(1)).willReturn(Optional.of(owner));
        PetType type = new PetType();
        type.setId(2);
        given(petRepository.findPetTypeById(2)).willReturn(Optional.of(type));
        Pet pet = new Pet();
        pet.setId(10);
        given(petRepository.save(org.mockito.Mockito.any(Pet.class))).willReturn(pet);
        String json = "{\"name\":\"Tom\",\"birthDate\":\"2020-01-01\",\"typeId\":2}";
        mvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/owners/1/pets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdatePet() throws Exception {
        Pet pet = new Pet();
        pet.setId(5);
        given(petRepository.findById(5)).willReturn(Optional.of(pet));
        PetType type = new PetType();
        type.setId(3);
        given(petRepository.findPetTypeById(3)).willReturn(Optional.of(type));
        given(petRepository.save(org.mockito.Mockito.any(Pet.class))).willReturn(pet);
        String json = "{\"id\":5,\"name\":\"Jerry\",\"birthDate\":\"2021-02-02\",\"typeId\":3}";
        mvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/owners/*/pets/5")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnNotFoundForMissingPet() throws Exception {
        given(petRepository.findById(99)).willReturn(Optional.empty());
        mvc.perform(get("/owners/2/pets/99").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    private Pet setupPet() {
        Owner owner = new Owner();
        owner.setFirstName("George");
        owner.setLastName("Bush");

        Pet pet = new Pet();

        pet.setName("Basil");
        pet.setId(2);

        PetType petType = new PetType();
        petType.setId(6);
        pet.setType(petType);

        owner.addPet(pet);
        return pet;
    }
}