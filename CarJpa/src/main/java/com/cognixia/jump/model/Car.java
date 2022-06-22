package com.cognixia.jump.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


// @Entity  -> define the class that is used to map out the table in SQL
//			-> let JPA know that this class should match a table in the db named "student"
//			-> if no table with name "student", JPA will create that table

@Entity
public class Car {

	@Id 												// primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	private Integer id;
	
	// @Column -> provide definitions for how we want to set up the column
	
	@Column(name="color")
	private String color;
	
	@Column(name="price")
	private double price;
	
	private String brand;
	
	private String model;

	
	
	public Car() {
		
	}

	
	

	public Car(Integer id, String color, double price, String Vin, String brand, String model) {
		super();
		this.id = id;
		this.color = color;
		this.price = price;
		this.brand = brand;
		this.model = model;
	}




	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	@Override
	public String toString() {
		return "Car [id=" + id + ", color=" + color + ", price=" + price + ", brand " +  brand 
				+ ", model=" + model + "]";
	}
	
	
}
