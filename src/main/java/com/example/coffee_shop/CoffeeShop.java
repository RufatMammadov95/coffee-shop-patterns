package com.example.coffee_shop;

import java.util.ArrayList;
import java.util.List;

public class CoffeeShop {
	private List<Order> orders = new ArrayList<>();
	
	private static CoffeeShop instance;
	private CoffeeShop() {}
	
	public static CoffeeShop getInstance() {
		if(instance == null) {
			instance = new CoffeeShop();
		}
		return instance;
	}
	public List<Order> getOrders(){
		return orders;
	}
	public void addOrder(Order order) {
		orders.add(order);
	}
	
}
