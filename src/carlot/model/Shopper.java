/**
 * 
 */
package carlot.model;

/**
 * @author John Kasiewicz
 *
 */
public class Shopper {
	
	//
	// Data Members
	//
	
	private String name;
	private double moneyAvailable;
	private Car car;
	
	//
	// Constructors
	//
	
	/**
	 * Initializes a Shopper object.
	 */
	private Shopper() {
	}
	
	/**
	 * Initializes the name and money available of a Shopper to the value of the corresponding
	 * parameters. Initializes the Shopper's car to null.
	 *  
	 * @param name The shopper's name.
	 * @param moneyAvailable The shopper's money available.
	 */
	public Shopper(String name, double moneyAvailable) {
		this.name = name;
		this.moneyAvailable = moneyAvailable;
		this.car = null;
	}
	
	//
	// Operations
	//
	
	/**
	 * Checks to see if shopper has enough money to purchase a car of that price including tax.
	 * 
	 * @return Returns true if shopper has enough money, otherwise false.
	 */
	public boolean canIPurchase(Car possibleCar) {
		
		if(this.moneyAvailable >= CarLot.getTotalCostOfPurchase(possibleCar)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Assigns the purchased car to the shopper as well as calculates and sets the amount 
	 * of money the shopper has left after purchasing the car.
	 * 
	 * @param purchasedCar The car purchased by the shopper.
	 */
	public void purchase(Car purchasedCar) {
		
		this.car = purchasedCar;
		this.moneyAvailable -= CarLot.getTotalCostOfPurchase(purchasedCar);
	}
	
	
	
	//
	// Accessors and Mutators
	//
	
	/**
	 * Accessor for Shopper's name.
	 * 
	 * @return The shopper's name.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Accessor for Shopper's money available.
	 * 
	 * @return The shopper's money available.
	 */
	public double getMoneyAvailable() {
		return this.moneyAvailable;
	}
	
	/**
	 * Accessor for Shopper's car.
	 * 
	 * @return The shopper's car.
	 */
	public Car getCar() {
		return this.car;
	}

}
