package com.example.coffee_shop.prototype;

import java.util.ArrayList;
import java.util.List;

import com.example.coffee_shop.adapter.Payment;
import com.example.coffee_shop.decorator.Coffee;
import com.example.coffee_shop.model.Status;
import com.example.coffee_shop.observer.Customer;
import com.example.coffee_shop.observer.Observer;

public class Order {
	private Coffee coffee;
	private double price;
	private Status status;
	private Customer customer;
	private List<Observer> observers = new ArrayList<>();
	
	public Order(Coffee coffee, Customer customer) {
		this.coffee = coffee;
		this.customer = customer;
		this.price =  customer.getPricingStrategy().calculatePrice(coffee.getCost());
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
	
	public Order cloneOrder() {
		return new Order(this.coffee, this.customer);
	}
	public String getFormattedPrice() {
	    return String.format("%.2f", price);
	}
	
	//Getters
	public Coffee getCoffee() {return coffee;}
	public double getPrice() {return price;}
	public Status getStatus() {return status;}
	public Customer getCustomer() {return customer;}
	
	//Setters
	public void setStatus(Status status) {
		this.status= status;
		if(status == Status.READY) {
			notifyObservers("Your order is ready!");
		}
	}
}
