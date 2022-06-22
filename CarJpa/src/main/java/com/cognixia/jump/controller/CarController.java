package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Car;
import com.cognixia.jump.repository.CarRepository;
import com.cognixia.jump.service.CarService;

@RestController
@RequestMapping("/api")
public class CarController {
	
	@Autowired
	CarRepository repo;
	
//	@Autowired
//	StudentService service;
	
	@GetMapping("/car")
	public List<Car> getCar(){
		
		//return service.getStudents();
		return repo.findAll();
		
	}
	
	@GetMapping("/car/{id}")
	public ResponseEntity<?> getCar(@PathVariable int id){
		
		Optional<Car> found = repo.findById(id);
		
//		Optional<Student> found = service.getStudentById(id);
		
		if(found.isEmpty()) {
			return ResponseEntity.status(404).body("Car with id = " + id + " not found");
		}
		else {
			return ResponseEntity.status(200).body(found.get());
		}
		
	}
	
	// Get all cars using color
	@GetMapping("/car/hightolow")
	public ResponseEntity<?> getHightoLowCars(){
			
		Optional<Car> found = repo.sortHightoLow();
		
//			Optional<Student> found = service.getStudentById(id);
		
		if(found.isEmpty()) {
			return ResponseEntity.status(404).body("No cars in registry");
		}
		else {
			return ResponseEntity.status(200).body(found.get());
		}
		
	}
	
//	Get all cars using color
	@GetMapping("/car/color")
	public ResponseEntity<?> getCarsByColor(@PathVariable (value="color")String color){
		
		Optional<Car> found = repo.findCarByColor(color);
		
//		Optional<Student> found = service.getStudentById(id);
		
		if(found.isEmpty()) {
			return ResponseEntity.status(404).body("Car with color = " + color + " not found");
		}
		else {
			return ResponseEntity.status(200).body(found.get());
		}
		
	}
	
	@GetMapping("/car/brand")
	public ResponseEntity<?> getCarsByBrand(@PathVariable (value="brand")String brand){
		
		Optional<Car> found = repo.getCarByBrand(brand);
		
//		Optional<Student> found = service.getStudentById(id);
		
		if(found.isEmpty()) {
			return ResponseEntity.status(404).body("Car with brand = " + brand + " not in lot");
		}
		else {
			return ResponseEntity.status(200).body(found.get());
		}
		
	}
	
		
	@PostMapping("/car")
	public ResponseEntity<?> createCar(@RequestBody Car car){
		//set id to null so we can auto-increment with our table
		car.setId(null);
		
		// save() -> used for both updates and insertions of data
		//		  -> if the primary key of the object given already exists in the table -> update
		//		  -> if primary key NOT present -> insert instead of an update
		Car created = repo.save(car);
		
		return ResponseEntity.status(201).body(created);
	}
	
	
	@PutMapping("/car")
	public ResponseEntity<?> updateCar(@RequestBody Car car){
		
		boolean exists = repo.existsById(car.getId());
		
		// can do update if id exists
		if(exists) {
			Car updated = repo.save(car);
			return ResponseEntity.status(200).body(updated);
		}
		else { // id doesn't exists, can't do update
			return ResponseEntity.status(404).body("Can't perform update, car doesn't exists");
		}
	}
	
	@DeleteMapping("/car/{id}")
	public ResponseEntity<?> deleteCar(@PathVariable int id){
		
		boolean exists = repo.existsById(id);
		
		if(exists) {
			repo.deleteById(id);
			return ResponseEntity.status(200).body("Car was deleted");
		}
		else { 
			return ResponseEntity.status(404).body("Can't delete, car doesn't exists");
		}
		
	}
	
	@PatchMapping("/car/color")
	public ResponseEntity<?> updateColor(@PathParam(value="id")int id, @PathParam(value="color")String color){
		
		Optional<Car> found = repo.findById(id);
		
		if(found.isEmpty()) {
			return ResponseEntity.status(404).body("Car with that id not found");
		}
		else {
			Car toUpdate = found.get();
			
			toUpdate.setColor(color);
			repo.save(toUpdate);
			
			return ResponseEntity.status(200).body("color was changed");
		}
		
	}
	
}
