package model;

public class SimulatorError extends RuntimeException {

	private String info;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SimulatorError (String in){
		this.info = in;
	}
}
