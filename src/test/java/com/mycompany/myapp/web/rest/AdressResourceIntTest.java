package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.PlatformKApp;

import com.mycompany.myapp.domain.Adress;
import com.mycompany.myapp.repository.AdressRepository;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AdressResource REST controller.
 *
 * @see AdressResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformKApp.class)
public class AdressResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private AdressRepository adressRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAdressMockMvc;

    private Adress adress;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AdressResource adressResource = new AdressResource(adressRepository);
        this.restAdressMockMvc = MockMvcBuilders.standaloneSetup(adressResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Adress createEntity(EntityManager em) {
        Adress adress = new Adress()
            .name(DEFAULT_NAME);
        return adress;
    }

    @Before
    public void initTest() {
        adress = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdress() throws Exception {
        int databaseSizeBeforeCreate = adressRepository.findAll().size();

        // Create the Adress
        restAdressMockMvc.perform(post("/api/adresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adress)))
            .andExpect(status().isCreated());

        // Validate the Adress in the database
        List<Adress> adressList = adressRepository.findAll();
        assertThat(adressList).hasSize(databaseSizeBeforeCreate + 1);
        Adress testAdress = adressList.get(adressList.size() - 1);
        assertThat(testAdress.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createAdressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adressRepository.findAll().size();

        // Create the Adress with an existing ID
        adress.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdressMockMvc.perform(post("/api/adresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adress)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Adress> adressList = adressRepository.findAll();
        assertThat(adressList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAdresses() throws Exception {
        // Initialize the database
        adressRepository.saveAndFlush(adress);

        // Get all the adressList
        restAdressMockMvc.perform(get("/api/adresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adress.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getAdress() throws Exception {
        // Initialize the database
        adressRepository.saveAndFlush(adress);

        // Get the adress
        restAdressMockMvc.perform(get("/api/adresses/{id}", adress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adress.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdress() throws Exception {
        // Get the adress
        restAdressMockMvc.perform(get("/api/adresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdress() throws Exception {
        // Initialize the database
        adressRepository.saveAndFlush(adress);
        int databaseSizeBeforeUpdate = adressRepository.findAll().size();

        // Update the adress
        Adress updatedAdress = adressRepository.findOne(adress.getId());
        updatedAdress
            .name(UPDATED_NAME);

        restAdressMockMvc.perform(put("/api/adresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdress)))
            .andExpect(status().isOk());

        // Validate the Adress in the database
        List<Adress> adressList = adressRepository.findAll();
        assertThat(adressList).hasSize(databaseSizeBeforeUpdate);
        Adress testAdress = adressList.get(adressList.size() - 1);
        assertThat(testAdress.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingAdress() throws Exception {
        int databaseSizeBeforeUpdate = adressRepository.findAll().size();

        // Create the Adress

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAdressMockMvc.perform(put("/api/adresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adress)))
            .andExpect(status().isCreated());

        // Validate the Adress in the database
        List<Adress> adressList = adressRepository.findAll();
        assertThat(adressList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAdress() throws Exception {
        // Initialize the database
        adressRepository.saveAndFlush(adress);
        int databaseSizeBeforeDelete = adressRepository.findAll().size();

        // Get the adress
        restAdressMockMvc.perform(delete("/api/adresses/{id}", adress.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Adress> adressList = adressRepository.findAll();
        assertThat(adressList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Adress.class);
        Adress adress1 = new Adress();
        adress1.setId(1L);
        Adress adress2 = new Adress();
        adress2.setId(adress1.getId());
        assertThat(adress1).isEqualTo(adress2);
        adress2.setId(2L);
        assertThat(adress1).isNotEqualTo(adress2);
        adress1.setId(null);
        assertThat(adress1).isNotEqualTo(adress2);
    }
}
