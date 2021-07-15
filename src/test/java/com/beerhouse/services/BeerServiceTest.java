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

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DisplayName("BeerService Tests")
public class BeerServiceTest {

    @Mock
    private BeerRepository beerRepository;

    @InjectMocks
    private BeerHelper helper;

    @InjectMocks
    private BeerService beerService;

    @BeforeEach
    public void before(){
        when(beerRepository.save(helper.getBeers().get(0))).thenReturn(helper.getBeers().get(0));
        when(beerRepository.exists(Example.of(helper.getBeers().get(0)))).thenReturn(false);
    }

    @Test
    @DisplayName("Should list the beers!")
    public void getAllBeersTest() throws BeerException {

        when(beerRepository.findAll(Pageable.unpaged())).thenReturn(helper.getAllBeers());

        var beerResponse = beerService.findAll(Pageable.unpaged()).getContent();

        assertNotNull(beerResponse);
        assertEquals("BEER 1", beerResponse.get(0).getName());

    }

    @Test
    @DisplayName("Should create a new beer!")
    public void addNewBeerTest() throws BeerException {

        var beerResponse = beerService.createBeer(helper.getBeers().get(0));

        assertNotNull(beerResponse);
        assertEquals("BEER 1", beerResponse.getName());
        verify(beerRepository, times(1)).save(any(Beer.class));
    }

    @Test
    @DisplayName("Should throw an exception trying to create an existing beer!")
    public void tryAddExistingBeerTest(){

        when(beerRepository.exists(Example.of(helper.getBeers().get(0)))).thenReturn(true);

        BeerException thrown = assertThrows(
                BeerException.class,
                () -> beerService.createBeer(helper.getBeers().get(0)),
                "createBeer() didn't throw"
        );

        assertTrue(thrown.getMessage().contains("beer already exists"));
    }

    @Test
    @DisplayName("Should find a beer by ID!")
    public void findByIdTest() {

        when(beerRepository.findById(3L)).thenReturn(Optional.of(helper.getBeers().get(2)));

        var beerResponse =  beerService.findById(3L);

        assertTrue(beerResponse.isPresent());
        assertEquals("BEER 3", beerResponse.get().getName());

    }

    @Test
    @DisplayName("Should update a beer by ID!")
    public void updateBeerByIdTest() throws BeerException {

        var oldBeer = helper.getBeers().get(5);
        var newBeer = helper.getUpdatedBeer(oldBeer);

        var beerResponse = beerService.updateBeer(newBeer, 4L);

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
    @DisplayName("Should partially update a beer!")
    public void updateBeerPartiallyTest() throws BeerException {

        when(beerRepository.save(helper.getBeers().get(4))).thenReturn(helper.getPartiallyUpdatedBeer(helper.getBeers().get(4)));

        var oldBeer = helper.getBeers().get(4);
        var partiallyNewBeer = helper.getPartiallyUpdatedBeerBody();

        var beerResponse = beerService.updatePartiallyBeer(partiallyNewBeer, 5L);

        assertNotNull(beerResponse);
        assertEquals("BEER 5", oldBeer.getName());
        assertEquals("BLUE OCEAN", oldBeer.getCategory());

        assertEquals("BEER 5 UPDATED", beerResponse.getName());
        assertEquals("BLUE OCEAN UPDATED", beerResponse.getCategory());

    }

    @Test
    @DisplayName("Should delete a beer!")
    public void deleteBeerTest() throws BeerException {

        when(beerRepository.findById(any(Long.class))).thenReturn(Optional.of(helper.getBeers().get(3)));

        beerService.deleteBeer(4L);

        // TODO: 14/07/2021 - Verificar as assertivas desse teste...
        verify(beerRepository, times(1)).deleteById(4L);
    }

    @Test
    @DisplayName("Should throw an exception trying to delete without existing!")
    public void tryDeleteBeerTest() {

        when(beerRepository.findById(3L)).thenReturn(Optional.empty());

        BeerException thrown = assertThrows(
                BeerException.class,
                () -> beerService.deleteBeer(3L),
                "deleteBeer() didn't throw"
        );

        assertTrue(thrown.getMessage().contains("beer does not exist"));
    }

}
