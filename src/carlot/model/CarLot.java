/**
 * 
 */
package carlot.model;

import java.util.ArrayList;


/**
 * Manages the inventory of a car lot.
 * 
 * @author John Kasiewicz
 *
 */
public class CarLot {

	//
	// Data Members
	//
	
	private ArrayList<Car> inventory;
	public static final double TAX_RATE = 6.75;
	
	//
	// Constructors
	//
	
	/**
	 * Initializes a new car lot and fills its inventory with a stock list of cars.
	 */
	public CarLot() {
		this.inventory = new ArrayList<Car>();
		stockLot();
	}
	
	//
	// Operations
	//
	
	/**
	 * Finds and returns all cars from the inventory that match the specified car make.
	 * 
	 * @param make The car's make to search for.
	 * @return ArrayList of matching car objects or null if no match is found.
	 */
	public ArrayList<Car> findCars(String make) {
		
		ArrayList<Car> carMatches = new ArrayList<Car>();
		
		for(Car currentCar : this.inventory) {
			if(currentCar.getMake().equalsIgnoreCase(make)) {
				carMatches.add(new Car(currentCar.getMake(), currentCar.getModel(), 
						               currentCar.getMPG(), currentCar.getPrice(), 
						               currentCar.getRating()));
			}
		}
		
		if(carMatches.isEmpty()) {
			return null;
		}
		
		return carMatches;
	}
	
	/**
	 * Finds and returns the first car from the inventory that matches the specified make and model.
	 * 
	 * @param make The car's make to search for.
	 * @param model The car's model to search for.
	 * @return Found car object or null if no match is found.
	 */
	public Car findCar(String make, String model) {
		
		for(Car currentCar : this.inventory) {
			if(currentCar.getMake().equalsIgnoreCase(make) && 
			   currentCar.getModel().equalsIgnoreCase(model)) {
				return currentCar;
			}
		}
		
		return null;
	}
	
	/**
	 * First checks to see if car is in the inventory, then removes the car from the inventory
	 * list. If no car match is found, returns null. 
	 * 
	 * @param make The car's make to search for and purchase.
	 * @param model The car's model to search for and purchase.
	 * @return Purchased car object or null if no match is found.
	 */
	public Car purchaseCar(String make, String model) {
		
		Car purchasedCar = this.findCar(make, model);
		
		if(purchasedCar != null) {
			this.inventory.remove(purchasedCar);
			return purchasedCar;
		}
		
		return null;
	}
	
	/**
	 * Adds a new car to the lot inventory according to the specifications. 
	 * 
	 * @param make The new car's make.
	 * @param model The new car's model.
	 * @param mpg The new car's MPG.
	 * @param price The new car's price.
	 * @param rating The new car's rating.
	 */
	public void addCar(String make, String model, double mpg, double price, int rating) {
		
		this.inventory.add(new Car(make, model, mpg, price, rating));
	}
	
	/**
	 * Adds a new car to the lot inventory according to the specifications. 
	 * 
	 * @param make The new car's make.
	 * @param model The new car's model.
	 * @param mpg The new car's MPG.
	 * @param price The new car's price.
	 */
	public void addCar(String make, String model, double mpg, double price) {
		
		this.inventory.add(new Car(make, model, mpg, price));
	}
	
	/**
	 * Accepts a car object and returns the total cost of the purchase based on the price
	 * of the car and the tax rate.
	 * 
	 * @param currentCar Car object customer wishes to purchase.
	 * @return Total cost of car with tax.
	 */
	public static double getTotalCostOfPurchase(Car currentCar) {
		
		double tax = currentCar.getPrice() * (TAX_RATE/100);
		
		return currentCar.getPrice() + tax;
	}
	
	/**
	 * Find the least expensive car in the current inventory. 
	 * 
	 * @return The car object with the lowest price.
	 */
	public Car getLeastExpensiveCar() {
		
		if(!this.inventory.isEmpty()) {
		
			Car leastExpensive = new Car("", "", 0, 0);
			double lowestPrice = Double.MAX_VALUE;
			
			for(Car currentCar : this.inventory) {
				if(currentCar.getPrice() < lowestPrice) {
					leastExpensive = currentCar;
					lowestPrice = leastExpensive.getPrice();
				}
			}
			
			return leastExpensive;
		}
		
		return null;
	}
	
	/**
	 * Find the most expensive car in the current inventory. 
	 * 
	 * @return The car object with the highest price.
	 */
	public Car getMostExpensiveCar() {
		
		if(!this.inventory.isEmpty()) {
		
			Car mostExpensive = new Car("", "", 0, 0);
			double highestPrice = Double.MIN_VALUE;
			
			for(Car currentCar : this.inventory) {
				if(currentCar.getPrice() > highestPrice) {
					mostExpensive = currentCar;
					highestPrice = mostExpensive.getPrice();
				}
			}
			
			return mostExpensive;
		}
		
		return null;
	}
	
	/**
	 * Find the car with the best MPG in the current inventory. 
	 * 
	 * @return The car object with the best MPG.
	 */
	public Car getBestMPG() {
		
		if(!this.inventory.isEmpty()) {
		
			Car bestMPG = new Car("", "", 0, 0);
			double highestMPG = Double.MIN_VALUE;
			
			for(Car currentCar : this.inventory) {
				if(currentCar.getMPG() > highestMPG) {
					bestMPG = currentCar;
					highestMPG = bestMPG.getMPG();
				}
			}
			
			return bestMPG;
		}
		
		return null;
	}
	
	/**
	 * Find the car with the worst MPG in the current inventory. 
	 * 
	 * @return The car object with the worst MPG.
	 */
	public Car getWorstMPG() {
		
		if(!this.inventory.isEmpty()) {
		
			Car worstMPG = null;
			double lowestMPG = Double.MAX_VALUE;
			
			for(Car currentCar : this.inventory) {
				if(currentCar.getMPG() < lowestMPG) {
					worstMPG = currentCar;
					lowestMPG = worstMPG.getMPG();
				}
			}
			
			return worstMPG;
		}
		
		return null;
	}
	
	/**
	 * Returns the total number of cars stored in the current inventory.
	 * 
	 * @return The number of cars in the current inventory.
	 */
	public int size() {
		return this.inventory.size();

	}
	
	/**
	 * Returns an ArrayList of the store's inventory.
	 * 
	 * @return An ArrayList of the current inventory.
	 */
	public ArrayList<Car> getInventory() {
		return this.inventory;
	}
	
	/**
	 * Returns the average rating of all the cars with ratings (ignores any car 
	 * with a rating of zero) in the current inventory.
	 * 
	 * @return Average rating of all car's with ratings. Returns 0 if inventory is empty or
	 * if remaining cars on lot all have unknown ratings.
	 */
	public double getAverageRating() {
		
		int totalRatingSum = 0;
		int totalCarsWithRatings = 0;
		
		if(!this.inventory.isEmpty()) {
			for (Car currentCar : this.inventory) {
				if(currentCar.getRating() != 0) {
					totalRatingSum += currentCar.getRating();
					totalCarsWithRatings++;
				}
			}
			if(totalCarsWithRatings > 0) {
				return totalRatingSum / (double) totalCarsWithRatings;	
			}
		}		
		return 0;
	}
	
	
	//
	// Private helper methods
	//
	
	private void stockLot() {
		this.inventory.add(new Car("Ford", "Focus ST", 28.3, 26298.98));
		this.inventory.add(new Car("Chevrolet", "Camaro ZL1", 19, 65401.23, 5));
		this.inventory.add(new Car("Honda", "Accord Sedan EX", 30.2, 26780, 4));
		this.inventory.add(new Car("Lexus", "ES 350", 24.1, 42101.10));
	}

	
}
