package com.beerhouse.persistence.relational.repositories;

import com.beerhouse.persistence.relational.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBeerRepository extends JpaRepository<Beer, Long> {
}
