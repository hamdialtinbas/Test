package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Adress;

import com.mycompany.myapp.repository.AdressRepository;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing Adress.
 */
@RestController
@RequestMapping("/api")
public class AdressResource {

    private final Logger log = LoggerFactory.getLogger(AdressResource.class);

    private static final String ENTITY_NAME = "adress";

    private final AdressRepository adressRepository;

    public AdressResource(AdressRepository adressRepository) {
        this.adressRepository = adressRepository;
    }

    /**
     * POST  /adresses : Create a new adress.
     *
     * @param adress the adress to create
     * @return the ResponseEntity with status 201 (Created) and with body the new adress, or with status 400 (Bad Request) if the adress has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/adresses")
    @Timed
    public ResponseEntity<Adress> createAdress(@RequestBody Adress adress) throws URISyntaxException {
        log.debug("REST request to save Adress : {}", adress);
        if (adress.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new adress cannot already have an ID")).body(null);
        }
        Adress result = adressRepository.save(adress);
        return ResponseEntity.created(new URI("/api/adresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /adresses : Updates an existing adress.
     *
     * @param adress the adress to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated adress,
     * or with status 400 (Bad Request) if the adress is not valid,
     * or with status 500 (Internal Server Error) if the adress couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/adresses")
    @Timed
    public ResponseEntity<Adress> updateAdress(@RequestBody Adress adress) throws URISyntaxException {
        log.debug("REST request to update Adress : {}", adress);
        if (adress.getId() == null) {
            return createAdress(adress);
        }
        Adress result = adressRepository.save(adress);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, adress.getId().toString()))
            .body(result);
    }

    /**
     * GET  /adresses : get all the adresses.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of adresses in body
     */
    @GetMapping("/adresses")
    @Timed
    public List<Adress> getAllAdresses(@RequestParam(required = false) String filter) {
        if ("person-is-null".equals(filter)) {
            log.debug("REST request to get all Adresss where person is null");
            return StreamSupport
                .stream(adressRepository.findAll().spliterator(), false)
                .filter(adress -> adress.getPerson() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all Adresses");
        return adressRepository.findAll();
    }

    /**
     * GET  /adresses/:id : get the "id" adress.
     *
     * @param id the id of the adress to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the adress, or with status 404 (Not Found)
     */
    @GetMapping("/adresses/{id}")
    @Timed
    public ResponseEntity<Adress> getAdress(@PathVariable Long id) {
        log.debug("REST request to get Adress : {}", id);
        Adress adress = adressRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(adress));
    }

    /**
     * DELETE  /adresses/:id : delete the "id" adress.
     *
     * @param id the id of the adress to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/adresses/{id}")
    @Timed
    public ResponseEntity<Void> deleteAdress(@PathVariable Long id) {
        log.debug("REST request to delete Adress : {}", id);
        adressRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
