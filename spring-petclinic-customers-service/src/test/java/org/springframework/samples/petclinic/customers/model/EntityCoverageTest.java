package org.springframework.samples.petclinic.customers.model;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.assertj.core.api.Assertions.*;

class OwnerEntityTest {
    @Test
    void testOwnerEntityMethods() {
        Owner owner = new Owner();
        owner.setFirstName("John");
        owner.setLastName("Doe");
        owner.setAddress("123 Main St");
        owner.setCity("HCM");
        owner.setTelephone("0123456789");
        assertThat(owner.getFirstName()).isEqualTo("John");
        assertThat(owner.getLastName()).isEqualTo("Doe");
        assertThat(owner.getAddress()).isEqualTo("123 Main St");
        assertThat(owner.getCity()).isEqualTo("HCM");
        assertThat(owner.getTelephone()).isEqualTo("0123456789");
        owner.toString();
    }

    @Test
    void testAddPet() {
        Owner owner = new Owner();
        Pet pet = new Pet();
        pet.setName("Kitty");
        owner.addPet(pet);
        assertThat(owner.getPets()).contains(pet);
        assertThat(pet.getOwner()).isEqualTo(owner);
    }
}

class PetEntityTest {
    @Test
    void testPetEntityMethods() {
        Pet pet = new Pet();
        pet.setId(1);
        pet.setName("Kitty");
        Date date = new Date();
        pet.setBirthDate(date);
        PetType type = new PetType();
        type.setId(2);
        type.setName("Cat");
        pet.setType(type);
        Owner owner = new Owner();
        owner.setFirstName("John");
        pet.setOwner(owner);
        assertThat(pet.getId()).isEqualTo(1);
        assertThat(pet.getName()).isEqualTo("Kitty");
        assertThat(pet.getBirthDate()).isEqualTo(date);
        assertThat(pet.getType()).isEqualTo(type);
        assertThat(pet.getOwner()).isEqualTo(owner);
        pet.toString();
        pet.equals(new Pet());
        pet.hashCode();
    }
}

class PetTypeEntityTest {
    @Test
    void testPetTypeEntityMethods() {
        PetType type = new PetType();
        type.setId(2);
        type.setName("Cat");
        assertThat(type.getId()).isEqualTo(2);
        assertThat(type.getName()).isEqualTo("Cat");
    }
}
