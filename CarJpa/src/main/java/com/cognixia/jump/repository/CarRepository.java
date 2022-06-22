package com.cognixia.jump.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Car;

//JpaRepository -> interface that we can use to manage data (in DB)
//				-> we want to manage data in a Student table with an id (primary key) of type integer

// @Repository -> marks/labels this as a repository(source of data)

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{

	
	@Query(value = "SELECT * FROM Car WHERE color = ?1 ", nativeQuery = true )
    Optional<Car> findCarByColor(String color);
	
	@Query(value = "SELECT * FROM Car ORDER BY price DESC", nativeQuery = true )
    Optional<Car> sortHightoLow();
	
	@Query(value = "SELECT * FROM Car WHERE brand = ?1", nativeQuery = true )
	Optional<Car> getCarByBrand(String brand);
	
}
