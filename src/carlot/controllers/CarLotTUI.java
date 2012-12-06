/**
 * 
 */
package carlot.controllers;

import java.util.Scanner;
import java.text.*;

import carlot.model.*;


/**
 * This project implements a simplified car lot that allows a car dealer
 * to manage the lot inventory.
 * 
 * @author John Kasiewicz
 *
 */
public class CarLotTUI {
	
	//
	// Data Members
	//
	private Scanner input;
	private CarLot carLot;
	private Shopper shopper;
	
	//
	// Constructors
	//
	
	/**
	 * Initializes a new CarLotTUI object.
	 */
	public CarLotTUI() {
		this.input = new Scanner(System.in);
	}
	
	//
	// Operations
	//
	
	/**
	 * Runs the TUI.
	 */
	public void run(){
		
		this.carLot = new CarLot();
		
		System.out.println("Welcome to Sleezy's Used Cars.");
		this.viewInventory();
		
		this.promptForAndExecuteMainAction();
		
	}
	
	//
	// Private helper methods
	//

	private void displayMainMenu() {
		System.out
				.print("\nWhat would you like to do: (s)erve customer, (a)dd car, " +
						 "(v)iew inventory, (c)lose store? ");
	}

	private void promptForAndExecuteMainAction() {

		String action;

		do {
			this.displayMainMenu();
			
			action = this.input.nextLine();

			if (action.equalsIgnoreCase("s")) {
				this.promptForAndCreateShopper();
			} else if (action.equalsIgnoreCase("a")) {
				this.promptForAndAddCar();
			} else if (action.equalsIgnoreCase("v")) {
				this.viewInventory();
			} else if (!action.equalsIgnoreCase("c")) {
				System.out.println("\nInvalid command.");
			}
		} while (!action.equalsIgnoreCase("c"));

		System.out.println("\nSleezy's Used Cars is now closed. Thank you and good-bye.");
	}
	
	private void promptForAndCreateShopper(){
		
		if(this.carLot.size() < 1) {
			System.out.println("\nI'm sorry, there are no cars currently on the lot.");
			return;
		}
		
		System.out.println("\nWelcome shopper.");
		
		String name = this.promptForAndGetString("\nWhat is your first name? ");
		
		String money = this.promptForAndGetString("\nHow much money do you have today? ");
		double moneyAvailable = Double.parseDouble(money.replaceAll(",",""));
		
		this.shopper = new Shopper(name, moneyAvailable);
		
		if(this.shopper.canIPurchase(this.carLot.getLeastExpensiveCar())) {
			this.promptForAndExecuteCustomerAction();
		} else {
			this.printInsufficientFundsMessage();
			return;
		}
		
		return;
		
	}
	
	private void printInsufficientFundsMessage() {
		System.out.println("\nSorry " + this.shopper.getName() + ", you do not have " +
							"enough money to purchase a car today. The least expensive " +
							"car with tax is " + 
							this.toCurrency(CarLot.getTotalCostOfPurchase(
											this.carLot.getLeastExpensiveCar())) + 
							". Save up and come back again.");
		
	}
	
	private void displayCustomerMenu() {
		System.out
				.print("\n" + this.shopper.getName() + ", what would you like to do: " +
						"(v)iew inventory, view specific (m)ake cars, (p)urchase car, " +
						"(l)eave store? ");
	}

	private void promptForAndExecuteCustomerAction() {

		String action;

		do {
			this.displayCustomerMenu();
			
			action = this.input.nextLine();

			if (action.equalsIgnoreCase("v")) {
				this.viewInventory();
			} else if (action.equalsIgnoreCase("m")) {
				this.promptForAndShowMake();
			} else if (action.equalsIgnoreCase("p")) {
				if(this.promptForAndPurchaseCar()) {
					return;	// if purchase of car is successful, return to main menu
				}
			} else if (!action.equalsIgnoreCase("l")) {
				System.out.println("\nInvalid command.");
			}
		} while (!action.equalsIgnoreCase("l"));
		
		printLeaveStoreMessage();
	}
	
	private void printLeaveStoreMessage() {
		System.out.println("\nThank you for shopping with us, " + this.shopper.getName() + 
							". Come back soon!");
		System.out.println("You have " + 
						    this.toCurrency(this.shopper.getMoneyAvailable()) + " left to spend.");
	
	}
	
	private void promptForAndShowMake() {
		
		String make = this.promptForAndGetString("\nEnter the make of the car: ");
		
		if(this.carLot.findCars(make) == null) {
			System.out.println("\nI'm sorry, we do not have any " + make + "'s in stock at this time.");
		} else {
			System.out.println("\nWe have the following " + make + "'s in stock:");
			for(Car currentCar : this.carLot.findCars(make)){
				this.printFormattedCarStats(currentCar);
			}
		} 
	}
	
	private boolean promptForAndPurchaseCar() {
		
		
		String make = this.promptForAndGetString("\nEnter make of purchase: ");
		
		String model = this.promptForAndGetString("\nEnter model of purchase: ");

		Car desiredCar = this.carLot.findCar(make, model);
		
		if(desiredCar == null) {
			System.out.println("\nI'm sorry, we do not have a " + make + " " + model + 
					           " in stock at this time.");
		} else {
			if(this.shopper.canIPurchase(desiredCar)){
				if(this.carLot.purchaseCar(make, model) != null) {
					this.shopper.purchase(desiredCar);
					this.printPurchaseReciept();
					return true;
				}
			} else {
				System.out.println("\nI'm sorry but you do not have enough money available to " +
						"purchase the " + desiredCar.getMake() + " " + desiredCar.getModel() + ".");
			}
		} 
		return false;
	}
	
	private void printPurchaseReciept() {
		System.out.println("\nThank you for your purchase! We appreciate your " +
				"business!\nThe total cost of the " + this.shopper.getCar().getMake() + " " + 
				this.shopper.getCar().getModel() + " is " + 
				this.toCurrency(CarLot.getTotalCostOfPurchase(this.shopper.getCar())) + ".");
		System.out.println("You have " + this.toCurrency(this.shopper.getMoneyAvailable()) + 
							" left to spend.\nEnjoy your new ride, " + this.shopper.getName() + ".");
		
	}
	
	private void promptForAndAddCar() {
		
		String make = this.promptForAndGetString("\nEnter the make of the car: ");
		
		String model = this.promptForAndGetString("\nEnter the model: ");
		
		double mpg = this.promptForAndGetMPG();
		
		double price= this.promptForAndGetPrice();
		
		int rating = this.promptForAndGetRating();
		
		if(rating != 0) {
			this.carLot.addCar(make, model, mpg, price, rating);
		} else {
			this.carLot.addCar(make, model, mpg, price);
		}
	}
	
	private double promptForAndGetMPG() {
		
		double mpg;
		
		do {
			System.out.print("\nEnter the MPG: ");
			String value = this.input.nextLine();
			mpg = Double.parseDouble(value);
			if(mpg <= 0) {
				System.out.print("\nInvalid MPG.");
			}
		} while (mpg <= 0);
		
		return mpg;
	}
	
	private double promptForAndGetPrice() {
		
		double price;
		
		do {
			System.out.print("\nEnter the price: ");
			String value = this.input.nextLine();
			price = Double.parseDouble(value.replaceAll(",",""));
			if(price <= 0) {
				System.out.print("\nInvalid price.");
			}
		} while (price <= 0);
		
		return price;
	}
	
	private int promptForAndGetRating() {
		
		int rating;
		
		do {
			System.out.print("\nEnter the rating: ");
			String value = this.input.nextLine();
			rating = Integer.parseInt(value);
			if(rating < 0 || rating > 5) {
				System.out.print("\nInvalid rating. Please choose a number between 0 and 5.");
			}
		} while (rating < 0 || rating > 5);
		
		return rating;
	}
	
	private String promptForAndGetString(String promptText) {
		
		String userInput;
		
		do {
			System.out.print(promptText);
			userInput = this.input.nextLine();
			if(userInput.equals("")) {
				System.out.print("\nField cannot be blank.");
			}
		} while (userInput.equals(""));
			
		return userInput;
	}
	
	private void viewInventory() {
		
		if (this.carLot.size() < 1) {
			System.out.println("\nThere are no cars currently on the lot.");
		} else {
			System.out.printf("\nInventory of %d cars with average rating of %.1f" + 
					" for rated cars.\n\n", this.carLot.size(), 
					this.carLot.getAverageRating());

			for(Car currentCar : this.carLot.getInventory()) {
				this.printFormattedCarStats(currentCar);
			}
			
			System.out.println("\nMost Expensive:");
			this.printFormattedCarStats(this.carLot.getMostExpensiveCar());
			
			System.out.println("\nLease Expensive:");
			this.printFormattedCarStats(this.carLot.getLeastExpensiveCar());
			
			System.out.println("\nBest MPG:");
			this.printFormattedCarStats(this.carLot.getBestMPG());
			
			System.out.println("\nWorst MPG:");
			this.printFormattedCarStats(this.carLot.getWorstMPG());		
		}
		
	}
	
	private void printFormattedCarStats(Car currentCar) {
		
		String makeAndModel = currentCar.getMake() + " " + currentCar.getModel();
		String price = this.toCurrency(currentCar.getPrice());
		
		System.out.printf("%-25s %-12s %5.1fmpg  Rating: %s\n", makeAndModel, price, 
				           currentCar.getMPG(), this.formatRating(currentCar.getRating()));

	}
	
	private String formatRating(int rating) {
		
		if(rating == 0) {
			return "?";
		}
		
		return Integer.toString(rating);
	}

	private String toCurrency(double amount) {
	
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		
		return currency.format(amount);
	}
}
