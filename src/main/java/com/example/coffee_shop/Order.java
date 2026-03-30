package com.example.coffee_shop;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private Coffee coffee;
	private double price;
	private Status status;
	private String customerName;
	private List<Observer> observers = new ArrayList<>();
	
	public Order(Coffee coffee, String customerName) {
		this.coffee = coffee;
		this.customerName = customerName;
		this.price = coffee.getCost();
		this.status = Status.PREPARING; 
	}
	
	public void addObserver(Observer o) {
		observers.add(o);
	}
	
	public void notifyObservers(String message) {
		for(Observer o : observers) {
			o.update(message);
		}
	}
	
	public void pay(Payment payment) {
		payment.pay(this.price);
	}
	
	//Getters
	public Coffee getCoffee() {return coffee;}
	public double getPrice() {return price;}
	public Status getStatus() {return status;}
	public String getCustomerName() {return customerName;}
	
	//Setters
	public void setStatus(Status status) {
		this.status= status;
		if(status == Status.READY) {
			notifyObservers("Your order is ready!");
		}
	}
}
