package com.example.coffee_shop;

import com.example.coffee_shop.adapter.BankPaymentAdapter;
import com.example.coffee_shop.adapter.CardPayment;
import com.example.coffee_shop.adapter.LegacyBankPayment;
import com.example.coffee_shop.adapter.PayPalPayment;
import com.example.coffee_shop.adapter.Payment;
import com.example.coffee_shop.decorator.Espresso;
import com.example.coffee_shop.facade.CoffeeShopFacade;
import com.example.coffee_shop.model.Status;
import com.example.coffee_shop.observer.Customer;
import com.example.coffee_shop.prototype.Order;
import com.example.coffee_shop.singleton.CoffeeShop;
import com.example.coffee_shop.strategy.GoldPricing;
import com.example.coffee_shop.strategy.SilverPricing;
import com.example.coffee_shop.template.CappuccinoMaker;
import com.example.coffee_shop.template.EspressoMaker;
import com.example.coffee_shop.template.LatteMaker;

public class Main {

    public static void main(String[] args) {

        // --- Singleton & Facade ---
        CoffeeShopFacade facade = new CoffeeShopFacade();

        // --- Customers with Pricing Strategy ---
        Customer customer1 = new Customer("Rufat", new GoldPricing());
        Customer customer2 = new Customer("Elvin", new SilverPricing());

        // --- Factory + Decorator + Strategy + Observer + Command + Adapter ---
        // Order 1
        facade.placeOrder(
                "latte",         // Coffee Type
                customer1,       // Customer
                true,            // addMilk
                true,            // addSugar
                new PayPalPayment() // Payment
        );

        // Order 2
        facade.placeOrder(
                "cappuccino",
                customer2,
                true,
                false,
                new CardPayment()
        );

        // --- Prototype Pattern ---
        Order originalOrder = new Order(new Espresso(), customer1);
        Order clonedOrder = originalOrder.cloneOrder();
        clonedOrder.setStatus(Status.READY);

        // --- Template Method Pattern ---
        System.out.println("\n--- Template Method Test ---");
        EspressoMaker espressoMaker = new EspressoMaker();
        espressoMaker.prepareRecipe();

        LatteMaker latteMaker = new LatteMaker();
        latteMaker.prepareRecipe();

        CappuccinoMaker cappuccinoMaker = new CappuccinoMaker();
        cappuccinoMaker.prepareRecipe();

        // --- Display all orders in CoffeeShop (Singleton) ---
        System.out.println("\n--- All Orders in CoffeeShop ---");
        for (Order order : CoffeeShop.getInstance().getOrders()) {
        	System.out.println(order.getCustomer().getName() + " - " 
        		    + order.getCoffee().getDescription() + " : $" 
        		    + String.format("%.2f", order.getPrice()));
        }

        // --- Legacy Bank Adapter test ---
        LegacyBankPayment legacyBank = new LegacyBankPayment();
        Payment adapterPayment = new BankPaymentAdapter(legacyBank);
        clonedOrder.pay(adapterPayment);
    }
}
