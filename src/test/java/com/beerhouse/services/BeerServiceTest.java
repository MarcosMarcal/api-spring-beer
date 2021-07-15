package com.beerhouse.services;

import com.beerhouse.exceptions.BeerException;
import com.beerhouse.helpers.BeerHelper;
import com.beerhouse.models.Beer;
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
    public void findByIdTest() throws BeerException {

        when(beerRepository.findById(3L)).thenReturn(Optional.of(helper.getBeers().get(2)));

        var beerResponse =  beerService.findById(3L);

        assertNotNull(beerResponse);
        assertEquals("BEER 3", beerResponse.getName());

    }

    @Test
    @DisplayName("Should update a beer by ID!")
    public void updateBeerByIdTest() throws BeerException {

        var updatedBeer = helper.getUpdatedBeer();

        when(beerRepository.save(helper.getBeers().get(5))).thenReturn(helper.getUpdatedBeer());
        when(beerRepository.findById(any(Long.class))).thenReturn(Optional.of(helper.getBeers().get(5)));

        var beerResponse = beerService.updateBeer(helper.getUpdatedBeer(), 6L);

        assertNotNull(beerResponse);
        assertEquals(updatedBeer.getName(), beerResponse.getName());
        assertEquals(updatedBeer.getIngredients(), beerResponse.getIngredients());
        assertEquals(updatedBeer.getAlcoholContent(), beerResponse.getAlcoholContent());
        assertEquals(updatedBeer.getPrice(), beerResponse.getPrice());
        assertEquals(updatedBeer.getCategory(), beerResponse.getCategory());

    }

    @Test
    @DisplayName("Should partially update a beer!")
    public void partiallyUpdateBeerTest() throws BeerException {

        var updatedBeer = helper.getBeers().get(4);
        var partiallyNewBeer = helper.getPartiallyUpdatedBeerBody();
        when(beerRepository.save(helper.getBeers().get(4))).thenReturn(helper.getPartiallyUpdatedBeer());
        when(beerRepository.findById(any(Long.class))).thenReturn(Optional.of(helper.getBeers().get(4)));


        var beerResponse = beerService.updatePartiallyBeer(partiallyNewBeer, 5L);

        assertNotNull(beerResponse);
        assertEquals(updatedBeer.getName(), beerResponse.getName());
        assertEquals(updatedBeer.getCategory(), beerResponse.getCategory());

    }

    @Test
    @DisplayName("Should delete a beer!")
    public void deleteBeerTest() throws BeerException {

        when(beerRepository.findById(any(Long.class))).thenReturn(Optional.of(helper.getBeers().get(3)));

        beerService.deleteBeer(4L);

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
