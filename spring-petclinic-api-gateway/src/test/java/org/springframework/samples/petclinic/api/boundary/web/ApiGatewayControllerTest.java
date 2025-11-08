
package org.springframework.samples.petclinic.api.boundary.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.samples.petclinic.api.application.CustomersServiceClient;
import org.springframework.samples.petclinic.api.application.VisitsServiceClient;
import org.springframework.samples.petclinic.api.dto.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ApiGatewayController.class)
@Import({ReactiveResilience4JAutoConfiguration.class, CircuitBreakerConfiguration.class})
class ApiGatewayControllerTest {
    @MockBean
    private CustomersServiceClient customersServiceClient;

    @MockBean
    private VisitsServiceClient visitsServiceClient;

    @Autowired
    private WebTestClient client;


    @Test
    void getOwnerDetails_shouldReturnError() {
        Mockito.when(customersServiceClient.getOwner(999)).thenReturn(Mono.error(new RuntimeException("Not found")));
        client.get()
            .uri("/api/gateway/owners/999")
            .exchange()
            .expectStatus().is5xxServerError();
    }
}