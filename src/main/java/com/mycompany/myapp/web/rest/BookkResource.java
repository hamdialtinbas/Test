package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Bookk;

import com.mycompany.myapp.repository.BookkRepository;
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
 * REST controller for managing Bookk.
 */
@RestController
@RequestMapping("/api")
public class BookkResource {

    private final Logger log = LoggerFactory.getLogger(BookkResource.class);

    private static final String ENTITY_NAME = "bookk";

    private final BookkRepository bookkRepository;

    public BookkResource(BookkRepository bookkRepository) {
        this.bookkRepository = bookkRepository;
    }

    /**
     * POST  /bookks : Create a new bookk.
     *
     * @param bookk the bookk to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bookk, or with status 400 (Bad Request) if the bookk has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bookks")
    @Timed
    public ResponseEntity<Bookk> createBookk(@RequestBody Bookk bookk) throws URISyntaxException {
        log.debug("REST request to save Bookk : {}", bookk);
        if (bookk.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new bookk cannot already have an ID")).body(null);
        }
        Bookk result = bookkRepository.save(bookk);
        return ResponseEntity.created(new URI("/api/bookks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bookks : Updates an existing bookk.
     *
     * @param bookk the bookk to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bookk,
     * or with status 400 (Bad Request) if the bookk is not valid,
     * or with status 500 (Internal Server Error) if the bookk couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bookks")
    @Timed
    public ResponseEntity<Bookk> updateBookk(@RequestBody Bookk bookk) throws URISyntaxException {
        log.debug("REST request to update Bookk : {}", bookk);
        if (bookk.getId() == null) {
            return createBookk(bookk);
        }
        Bookk result = bookkRepository.save(bookk);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bookk.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bookks : get all the bookks.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bookks in body
     */
    @GetMapping("/bookks")
    @Timed
    public List<Bookk> getAllBookks() {
        log.debug("REST request to get all Bookks");
        return bookkRepository.findAll();
    }

    /**
     * GET  /bookks/:id : get the "id" bookk.
     *
     * @param id the id of the bookk to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bookk, or with status 404 (Not Found)
     */
    @GetMapping("/bookks/{id}")
    @Timed
    public ResponseEntity<Bookk> getBookk(@PathVariable Long id) {
        log.debug("REST request to get Bookk : {}", id);
        Bookk bookk = bookkRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bookk));
    }

    /**
     * DELETE  /bookks/:id : delete the "id" bookk.
     *
     * @param id the id of the bookk to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bookks/{id}")
    @Timed
    public ResponseEntity<Void> deleteBookk(@PathVariable Long id) {
        log.debug("REST request to delete Bookk : {}", id);
        bookkRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
