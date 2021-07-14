package com.beerhouse.services;

import com.beerhouse.exceptions.BeerException;
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
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        when(beerRepository.exists(Example.of(helper.getBeers().get(0)))).thenReturn(false);
        when(beerRepository.findById(any(Long.class))).thenReturn(Optional.of(helper.getBeers().get(0)));
    }

    @Test
    @DisplayName("Deverá listar cervejas!")
    public void getAllBeersTest() throws BeerException {

        var beerResponse = beerService.findAll(Pageable.unpaged()).getContent();

        assertNotNull(beerResponse);
        assertEquals("BEER 1", beerResponse.get(0).getName());

    }

    @Test
    @DisplayName("Deverá criar nova cerveja!")
    public void addNewBeerTest() throws BeerException {

        var beerResponse = beerService.createBeer(helper.getBeers().get(0));

        // TODO: 14/07/2021 - Verificar confiabilidade desse teste...
        assertNotNull(beerResponse);
        assertEquals("BEER 1", beerResponse.getName());

    }

    @Test
    @DisplayName("Deverá encontrar uma cerveja pelo ID!")
    public void findByIdTest() {

        var beerResponse =  beerService.findById(3L);

        assertTrue(beerResponse.isPresent());
        assertEquals("BEER 3", beerResponse.get().getName());

    }

    @Test
    @DisplayName("Deverá atualizar uma cerveja pelo ID!")
    public void updateBeerByIdTest() throws BeerException {

        var oldBeer = helper.getBeers().get(5);
        var newBeer = helper.getUpdatedBeer(oldBeer);

        var beerResponse = beerService.updateBeer(newBeer);

        assertNotNull(beerResponse);
        assertEquals("BEER 6", oldBeer.getName());
        assertEquals("BLUE INGREDIENTS", oldBeer.getIngredients());
        assertEquals("10 ALCOHOL", oldBeer.getAlcoholContent());
        assertEquals(22.0, oldBeer.getPrice());
        assertEquals("BLUE OCEAN", oldBeer.getCategory());

        assertEquals("BEER 6 UPDATED", beerResponse.getName());
        assertEquals("BLUE INGREDIENTS UPDATED", beerResponse.getIngredients());
        assertEquals("10 ALCOHOL UPDATED", beerResponse.getAlcoholContent());
        assertEquals(44.0, beerResponse.getPrice());
        assertEquals("BLUE OCEAN UPDATED", beerResponse.getCategory());

    }

    @Test
    @DisplayName("Deverá atualizar parcialmente uma cerveja!")
    public void updateBeerPartiallyTest() throws BeerException {

        var oldBeer = helper.getBeers().get(4);
        var partiallyNewBeer = helper.getPartiallyUpdatedBeer(oldBeer);

        var beerResponse = beerService.updatePartiallyBeer(partiallyNewBeer);

        assertNotNull(beerResponse);
        assertEquals("BEER 5", oldBeer.getName());
        assertEquals("BLUE OCEAN", oldBeer.getCategory());

        assertEquals("BEER 5 UPDATED", beerResponse.getName());
        assertEquals("BLUE OCEAN UPDATED", beerResponse.getCategory());

    }

    @Test
    @DisplayName("Deverá deletar uma cerveja!")
    public void deleteBeerTest() {

        var beerResponse = beerService.deleteBeer(4L);

        // TODO: 14/07/2021 - Verificar as assertivas desse teste...
        assertNotNull(beerResponse);
        assertEquals("BEER 1", beerResponse.getName());

        verify(beerRepository, times(1)).deleteById(4L);

    }

}
