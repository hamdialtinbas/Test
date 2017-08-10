package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Kole;

import com.mycompany.myapp.repository.KoleRepository;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Kole.
 */
@RestController
@RequestMapping("/api")
public class KoleResource {

    private final Logger log = LoggerFactory.getLogger(KoleResource.class);

    private static final String ENTITY_NAME = "kole";

    private final KoleRepository koleRepository;

    public KoleResource(KoleRepository koleRepository) {
        this.koleRepository = koleRepository;
    }

    /**
     * POST  /koles : Create a new kole.
     *
     * @param kole the kole to create
     * @return the ResponseEntity with status 201 (Created) and with body the new kole, or with status 400 (Bad Request) if the kole has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/koles")
    @Timed
    public ResponseEntity<Kole> createKole(@RequestBody Kole kole) throws URISyntaxException {
        log.debug("REST request to save Kole : {}", kole);
        if (kole.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new kole cannot already have an ID")).body(null);
        }
        Kole result = koleRepository.save(kole);
        return ResponseEntity.created(new URI("/api/koles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /koles : Updates an existing kole.
     *
     * @param kole the kole to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated kole,
     * or with status 400 (Bad Request) if the kole is not valid,
     * or with status 500 (Internal Server Error) if the kole couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/koles")
    @Timed
    public ResponseEntity<Kole> updateKole(@RequestBody Kole kole) throws URISyntaxException {
        log.debug("REST request to update Kole : {}", kole);
        if (kole.getId() == null) {
            return createKole(kole);
        }
        Kole result = koleRepository.save(kole);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, kole.getId().toString()))
            .body(result);
    }

    /**
     * GET  /koles : get all the koles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of koles in body
     */
    @GetMapping("/koles")
    @Timed
    public List<Kole> getAllKoles() {
        log.debug("REST request to get all Koles");
        return koleRepository.findAll();
    }

    /**
     * GET  /koles/:id : get the "id" kole.
     *
     * @param id the id of the kole to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the kole, or with status 404 (Not Found)
     */
    @GetMapping("/koles/{id}")
    @Timed
    public ResponseEntity<Kole> getKole(@PathVariable Long id) {
        log.debug("REST request to get Kole : {}", id);
        Kole kole = koleRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(kole));
    }

    /**
     * DELETE  /koles/:id : delete the "id" kole.
     *
     * @param id the id of the kole to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/koles/{id}")
    @Timed
    public ResponseEntity<Void> deleteKole(@PathVariable Long id) {
        log.debug("REST request to delete Kole : {}", id);
        koleRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
