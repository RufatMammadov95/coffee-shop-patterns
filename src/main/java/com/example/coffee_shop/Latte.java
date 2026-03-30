package com.example.coffee_shop;

public class Latte implements Coffee{
	@Override
	public String getDescription() {
		return "Latte";
	}
	@Override
	public double getCost() {
		return 3.5;
	}
}
