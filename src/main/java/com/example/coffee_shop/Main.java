package com.example.coffee_shop;

public class Main {

	public static void main(String[] args) {
		
		CoffeeFactory factory = new CoffeeFactory();
		Coffee myCoffee = factory.createCoffee("latte");
		
		myCoffee = new Milk(myCoffee);
		myCoffee = new Sugar(myCoffee);
		
		Customer customer1 = new Customer("Rufat");
		Order order1 = new Order(myCoffee, customer1.getName());
		
		order1.addObserver(customer1);
		order1.setStatus(Status.READY);
		
		System.out.println(myCoffee.getDescription());
		System.out.println(myCoffee.getCost());
		
		CoffeeShop shop = CoffeeShop.getInstance();
		shop.addOrder(order1);
		
		for(Order o : shop.getOrders()) {
		    System.out.println(o.getCustomerName() + " - " + o.getCoffee().getDescription() + " : " + o.getPrice());
		}
		Payment paypal = new PayPalPayment();
		Payment card = new CardPayment();
	
		order1.pay(paypal);
		order1.pay(card);
		
		LegacyBankPayment legacy = new LegacyBankPayment();
		Payment bankAdapter = new BankPaymentAdapter(legacy);

		order1.pay(bankAdapter);
		

	}

}
