package com.example.coffee_shop.observer;

import com.example.coffee_shop.strategy.PricingStrategy;

public class Customer implements Observer{
		private String name;
		private PricingStrategy pricingStrategy;
		public Customer(String name, PricingStrategy pricingStrategy) {
			this.name = name;
			this.pricingStrategy = pricingStrategy;
		}
			
		public void update(String message){
			System.out.println(name + ", " + message);
		}
		public String getName() {
		    return name;
		}
		public PricingStrategy getPricingStrategy() {
			return pricingStrategy;
		}
}
