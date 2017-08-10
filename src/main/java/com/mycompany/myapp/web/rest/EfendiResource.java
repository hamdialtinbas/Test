package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Efendi;

import com.mycompany.myapp.repository.EfendiRepository;
import com.mycompany.myapp.service.EfendiService;
import com.mycompany.myapp.service.EfendiServiceImpl;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing Efendi.
 */
@RestController
@RequestMapping("/api")
public class EfendiResource  {

    private final Logger log = LoggerFactory.getLogger(EfendiResource.class);

    private static final String ENTITY_NAME = "efendi";

    private final EfendiRepository efendiRepository;

    @Autowired
    EfendiService efendiService;

    public EfendiResource(EfendiRepository efendiRepository) {
        this.efendiRepository = efendiRepository;
    }

    /**
     * POST  /efendis : Create a new efendi.
     *
     * @param efendi the efendi to create
     * @return the ResponseEntity with status 201 (Created) and with body the new efendi, or with status 400 (Bad Request) if the efendi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/efendis")
    @Timed
    public ResponseEntity<Efendi> createEfendi(@RequestBody Efendi efendi) throws URISyntaxException {
        log.debug("REST request to save Efendi : {}", efendi);
        if (efendi.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new efendi cannot already have an ID")).body(null);
        }
        Efendi result = efendiRepository.save(efendi);
        return ResponseEntity.created(new URI("/api/efendis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /efendis : Updates an existing efendi.
     *
     * @param efendi the efendi to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated efendi,
     * or with status 400 (Bad Request) if the efendi is not valid,
     * or with status 500 (Internal Server Error) if the efendi couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/efendis")
    @Timed
    public ResponseEntity<Efendi> updateEfendi(@RequestBody Efendi efendi) throws URISyntaxException {
        log.debug("REST request to update Efendi : {}", efendi);
        if (efendi.getId() == null) {
            return createEfendi(efendi);
        }
        Efendi result = efendiRepository.save(efendi);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, efendi.getId().toString()))
            .body(result);
    }

    /**
     * GET  /efendis : get all the efendis.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of efendis in body
     */
    @GetMapping("/efendis")
    @Timed
    public List<Efendi> getAllEfendis() {
        log.debug("REST request to get all Efendis");
        return efendiRepository.findAll();
    }

    /**
     * GET  /efendis/:id : get the "id" efendi.
     *
     * @param id the id of the efendi to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the efendi, or with status 404 (Not Found)
     */
    @GetMapping("/efendis/{id}")
    @Timed
    public ResponseEntity<Efendi> getEfendi(@PathVariable Long id) {
        log.debug("REST request to get Efendi : {}", id);
        Efendi efendi = efendiRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(efendi));
    }

    /**
     * DELETE  /efendis/:id : delete the "id" efendi.
     *
     * @param id the id of the efendi to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/efendis/{id}")
    @Timed
    public ResponseEntity<Void> deleteEfendi(@PathVariable Long id) {
        log.debug("REST request to delete Efendi : {}", id);
        efendiRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/efendis/getData/")
    public List<Efendi> getData(){
       List<Efendi> efe= efendiService.findEfendi("sd");
return efe;
    }
}
