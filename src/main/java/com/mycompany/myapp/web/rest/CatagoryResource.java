package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Catagory;

import com.mycompany.myapp.repository.CatagoryRepository;
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
 * REST controller for managing Catagory.
 */
@RestController
@RequestMapping("/api")
public class CatagoryResource {

    private final Logger log = LoggerFactory.getLogger(CatagoryResource.class);

    private static final String ENTITY_NAME = "catagory";

    private final CatagoryRepository catagoryRepository;

    public CatagoryResource(CatagoryRepository catagoryRepository) {
        this.catagoryRepository = catagoryRepository;
    }

    /**
     * POST  /catagories : Create a new catagory.
     *
     * @param catagory the catagory to create
     * @return the ResponseEntity with status 201 (Created) and with body the new catagory, or with status 400 (Bad Request) if the catagory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/catagories")
    @Timed
    public ResponseEntity<Catagory> createCatagory(@RequestBody Catagory catagory) throws URISyntaxException {
        log.debug("REST request to save Catagory : {}", catagory);
        if (catagory.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new catagory cannot already have an ID")).body(null);
        }
        Catagory result = catagoryRepository.save(catagory);
        return ResponseEntity.created(new URI("/api/catagories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /catagories : Updates an existing catagory.
     *
     * @param catagory the catagory to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated catagory,
     * or with status 400 (Bad Request) if the catagory is not valid,
     * or with status 500 (Internal Server Error) if the catagory couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/catagories")
    @Timed
    public ResponseEntity<Catagory> updateCatagory(@RequestBody Catagory catagory) throws URISyntaxException {
        log.debug("REST request to update Catagory : {}", catagory);
        if (catagory.getId() == null) {
            return createCatagory(catagory);
        }
        Catagory result = catagoryRepository.save(catagory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, catagory.getId().toString()))
            .body(result);
    }

    /**
     * GET  /catagories : get all the catagories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of catagories in body
     */
    @GetMapping("/catagories")
    @Timed
    public List<Catagory> getAllCatagories() {
        log.debug("REST request to get all Catagories");
        return catagoryRepository.findAll();
    }

    /**
     * GET  /catagories/:id : get the "id" catagory.
     *
     * @param id the id of the catagory to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the catagory, or with status 404 (Not Found)
     */
    @GetMapping("/catagories/{id}")
    @Timed
    public ResponseEntity<Catagory> getCatagory(@PathVariable Long id) {
        log.debug("REST request to get Catagory : {}", id);
        Catagory catagory = catagoryRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(catagory));
    }

    /**
     * DELETE  /catagories/:id : delete the "id" catagory.
     *
     * @param id the id of the catagory to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/catagories/{id}")
    @Timed
    public ResponseEntity<Void> deleteCatagory(@PathVariable Long id) {
        log.debug("REST request to delete Catagory : {}", id);
        catagoryRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
