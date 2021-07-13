package com.beerhouse.repository;

import com.beerhouse.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBeerRepository extends JpaRepository<Beer, Long> {
}
