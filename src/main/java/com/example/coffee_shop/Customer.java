package com.example.coffee_shop;

public class Customer implements Observer{
		private String name;
		public Customer(String name) {
			this.name = name;
		}
			
		public void update(String message){
			System.out.println(name + ", " + message);
		}
		public String getName() {
		    return name;
		}
}
