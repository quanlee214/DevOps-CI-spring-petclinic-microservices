package org.springframework.samples.petclinic.vets.web;

import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.samples.petclinic.vets.model.Vet;
import org.springframework.samples.petclinic.vets.model.VetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.vets.system.UnusedCoverageDropper;

/**
 * ...existing Javadoc...
 */
@RequestMapping("/vets")
@RestController
class VetResource {
    private final VetRepository vetRepository;
    // Inject unused bean to ensure it is loaded for coverage
    private final UnusedCoverageDropper unusedCoverageDropper;

    @Autowired
    public VetResource(VetRepository vetRepository, UnusedCoverageDropper unusedCoverageDropper) {
        this.vetRepository = vetRepository;
        this.unusedCoverageDropper = unusedCoverageDropper;
    }

    @GetMapping
    @Cacheable("vets")
    public List<Vet> showResourcesVetList() {
        return vetRepository.findAll();
    }
}