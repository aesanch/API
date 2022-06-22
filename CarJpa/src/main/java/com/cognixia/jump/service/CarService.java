package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Car;
import com.cognixia.jump.repository.CarRepository;

@Service
public class CarService {

	@Autowired
	CarRepository repo;
	
	public List<Car> getStudents() {
	
		return repo.findAll();
		
	}
	
	public Optional<Car> getStudentById(int id){
		
		return repo.findById(id);
		
	}
	
	public Optional<Car> findCarByColor(String color){
		return null;
	}
}
