package com.beerhouse.services;

import com.beerhouse.helpers.BeerHelper;
import com.beerhouse.model.Beer;
import com.beerhouse.repository.implement.BeerRepository;
import com.beerhouse.services.implement.BeerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DisplayName("BeerService Testes")
public class BeerServiceTest {

    @Mock
    private BeerRepository beerRepository;

    @InjectMocks
    private BeerHelper helper;

    @InjectMocks
    private BeerService beerService;

    @BeforeEach
    public void before(){
        when(beerRepository.findAll(Pageable.unpaged())).thenReturn( helper.getAllBeers());
        when(beerRepository.save(any(Beer.class))).thenReturn( helper.getBeers().get(0));
        when(beerRepository.findById(any(Beer.class).getId())).thenReturn(Optional.of(helper.getBeers().get(0)));
    }

    @Test
    @DisplayName("Deve listar cervejas!")
    public void getAllBeersTest() {

        var beerResponse = beerService.findAll(Pageable.unpaged()).getContent();

        assertNotNull(beerResponse);
        assertEquals("BEER 1", beerResponse.get(0).getName());

    }

    @Test
    @DisplayName("Deve criar nova cerveja!")
    public void addNewBeerTest() {

        var beerResponse = beerService.create(helper.getBeers().get(0));

        assertNotNull(beerResponse);
        assertEquals("BEER 1", beerResponse.getName());

    }

    @Test
    @DisplayName("Deve encontrar uma cerveja pelo ID!")
    public void findByIdTest() {

        var beerResponse =  beerService.findById(3L);

        assertTrue(beerResponse.isPresent());
        assertEquals("BEER 3", beerResponse.get().getName());

    }

    @Test
    @DisplayName("Deverá atualizar uma cerveja pelo ID!")
    public void updateBeerByIdTest() {

        var beerResponse = beerService.update(6L);

        assertNotNull(beerResponse);
        assertEquals("BEER 6", beerResponse.getName());

    }

    @Test
    @DisplayName("Deverá atualizar parcialmente uma cerveja pelo ID!")
    public void updateBeerPartiallyByIdTest() {

        var beerResponse = beerService.create(helper.getBeers().get(0));

        assertNotNull(beerResponse);
        assertEquals("BEER 1", beerResponse.getName());

    }

    @Test
    @DisplayName("Deverá deletar uma cerveja pelo ID!")
    public void deleteBeerByIdTest() {

        var beerResponse = beerService.create(helper.getBeers().get(0));

        assertNotNull(beerResponse);
        assertEquals("BEER 1", beerResponse.getName());

    }

}
