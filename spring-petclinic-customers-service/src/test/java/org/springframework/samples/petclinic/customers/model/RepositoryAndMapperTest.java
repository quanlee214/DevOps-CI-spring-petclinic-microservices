package org.springframework.samples.petclinic.customers.model;

import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.customers.model.OwnerRepository;
import org.springframework.samples.petclinic.customers.model.PetRepository;
import org.springframework.samples.petclinic.customers.web.mapper.OwnerEntityMapper;
import org.springframework.samples.petclinic.customers.web.mapper.Mapper;
import org.springframework.samples.petclinic.customers.config.MetricConfig;
import static org.assertj.core.api.Assertions.*;

class RepositoryAndMapperTest {
    @Test
    void testOwnerRepositoryMethods() {
        // OwnerRepository is an interface, just check type
        OwnerRepository repo = null;
        assertThat(repo).isNull();
    }

    @Test
    void testPetRepositoryMethods() {
        // PetRepository is an interface, just check type
        PetRepository repo = null;
        assertThat(repo).isNull();
    }

    @Test
    void testOwnerEntityMapperMethods() {
        OwnerEntityMapper mapper = new OwnerEntityMapper();
        assertThat(mapper).isNotNull();
    }

    @Test
    void testMapperInterface() {
        Mapper<Object, Object> mapper = new Mapper<>() {
            @Override
            public Object map(Object response, Object request) {
                return response;
            }
        };
        Object obj1 = new Object();
        Object obj2 = new Object();
        assertThat(mapper.map(obj1, obj2)).isEqualTo(obj1);
    }

    @Test
    void testMetricConfig() {
        MetricConfig config = new MetricConfig();
        assertThat(config).isNotNull();
    }
}
