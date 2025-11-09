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
        void shouldGetPetTypes() throws Exception {
            PetType type1 = new PetType();
            type1.setId(1);
            type1.setName("Dog");
            PetType type2 = new PetType();
            type2.setId(2);
            type2.setName("Cat");

            given(petRepository.findPetTypes()).willReturn(java.util.List.of(type1, type2));

            mvc.perform(get("/petTypes").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].name").value("Cat"));
        }

        @Test
        void shouldCreatePet() throws Exception {
        Owner owner = new Owner();
            given(ownerRepository.findById(1)).willReturn(Optional.of(owner));

            PetType type = new PetType();
            type.setId(6);
            given(petRepository.findPetTypeById(6)).willReturn(Optional.of(type));

            Pet pet = new Pet();
            pet.setId(2);
            pet.setName("Basil");
            pet.setType(type);
            pet.setBirthDate(new java.util.Date());

            given(petRepository.save(org.mockito.ArgumentMatchers.any(Pet.class))).willReturn(pet);

            String json = """
                {
                    \"id\": 2,
                    \"birthDate\": \"2020-01-01\",
                    \"name\": \"Basil\",
                    \"typeId\": 6
                }
                """;

            mvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/owners/1/pets")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isCreated());
        }

        @Test
        void shouldReturnPetDetails() throws Exception {
            Pet pet = setupPet();
            given(petRepository.findById(2)).willReturn(Optional.of(pet));

            mvc.perform(get("/owners/2/pets/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Basil"));
        }

        @Test
        void shouldReturnNotFoundForMissingPet() throws Exception {
            given(petRepository.findById(99)).willReturn(Optional.empty());

            mvc.perform(get("/owners/2/pets/99").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        }

        @Test
        void shouldGetPetWithDifferentType() throws Exception {
            Pet pet = setupPet();
            PetType newType = new PetType();
            newType.setId(7);
            pet.setType(newType);

            given(petRepository.findById(2)).willReturn(Optional.of(pet));

            mvc.perform(get("/owners/2/pets/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type.id").value(7));
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
