/**
 * 
 */
package carlot.controllers;

import carlot.controllers.CarLotTUI;

/**
 * Entry point for the app.
 * 
 * @author John Kasiewicz
 *
 */
public class ApplicationController {

	/**
	 * Entry point for the app.
	 * 
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		
		CarLotTUI demoTUI = new CarLotTUI();
		demoTUI.run();

	}
}
