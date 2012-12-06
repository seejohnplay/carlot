/**
 * 
 */
package carlot.model;

/**
 * Manages information (make, model, mpg, price, rating) about a Car.
 * 
 * @author John Kasiewicz
 *
 */
public class Car {
	
	//
	// Data Members
	//
	
	private String make;
	private String model;
	private double mpg;
	private double price;
	private int rating;
	
	//
	// Constructors
	//
	
	/**
	 *	Initializes a Car object. 
	 */
	private Car() {
	}
	
	/**
	 * Initializes the make, model, mpg, and price of the Car to the
	 * value of the corresponding parameters. Initializes the Car's 
	 * rating to 0. 
	 * 
	 * @param make The Car's make.
	 * @param model The Car's model.
	 * @param mpg The Car's MPG.
	 * @param price The Car's price.
	 */
	public Car(String make, String model, double mpg, double price) {
		this(make, model, mpg, price, 0);
	}
	
	/**
	 * Initializes the make, model, mpg, price, and rating of the Car
	 * to the value of the corresponding parameters. 
	 * 
	 * @param make The Car's make.
	 * @param model The Car's model.
	 * @param mpg The Car's MPG.
	 * @param price The Car's price.
	 * @param rating The Car's rating.
	 */
	public Car(String make, String model, double mpg, double price, int rating) {
		this.make = make;
		this.model = model;
		this.mpg = mpg;
		this.price = price;
		this.rating = rating;
	}
	
	//
	// Accessors and Mutators
	//
	
	/**
	 * Accessor for Car's make.
	 * 
	 * @return The Car's make.
	 */
	public String getMake() {
		return this.make;
	}
	
	/**
	 * Accessor for Car's model.
	 * 
	 * @return The Car's model.
	 */
	 public String getModel() {
		return this.model;
	}
	 
	/**
	 * Accessor for Car's MPG.
	 * 
	 * @return The Car's MPG.
	 */
	 public double getMPG() {
		return this.mpg;
	}
	
	/**
	 * Accessor for Car's price.
	 * 
	 * @return The Car's price.
	 */
	 public double getPrice() {
		return this.price;
	}
	 
	/**
	 * Mutator to set the Car's price.
	 * 
	 * @param price The Car's price to set.
	 */
	 public void setPrice(double price) {
		this.price = price;
	}
	 
	/**
	 * Accessor for Car's rating.
	 * 
	 * @return The Car's rating.
	 */
	 public int getRating() {
		return this.rating;
	}
	 
	/**
	 * Mutator to set the Car's rating.
	 * 
	 * @param rating The Car's rating to set.
	 */
	 public void setRating(int rating) {
		this.rating = rating;
	}
	
}
