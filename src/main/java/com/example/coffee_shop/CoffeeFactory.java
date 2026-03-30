package com.example.coffee_shop;

public class CoffeeFactory {
	public Coffee createCoffee(String type) {
		if(type.equalsIgnoreCase("espresso")) {return new Espresso();}
		else if(type.equalsIgnoreCase("latte")) {return new Latte();}
		else if(type.equalsIgnoreCase("cappuccino")) {return new Cappuccino();}
		throw new IllegalArgumentException("Unknown coffee type");
	}
}
