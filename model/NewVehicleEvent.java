package model;

public class NewVehicleEvent extends Event{

	protected String _id;
	protected int maxSpeed;
	protected String[] itinerario;
	/*New Vehicle Event añade un nuevo vehículo a la simulación. 
	 * El itinerario es una lista de identificadores de cruces y debe contener al menos dos. 
	 * [new_vehicle] 
	 * time = <NONEG-INTEGER>  
	 * id = <VEHICLE-ID>  
	 * max_speed = <POSITIVE-INTEGER>  
	 * itinerary = <JUNC-ID>,<JUNC-ID>(,<JUNC-ID>)*/
	
	public NewVehicleEvent(int time, String id, int maxSpeed, String[] itinerario){
		super(time);
		this._id = id;
		this.maxSpeed = maxSpeed;
		this.itinerario = itinerario;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(RoadMap map, int time) {
		// TODO Auto-generated method stub
		map.addVehicle(new Vehicle(_id,maxSpeed,parseListOfJunctions(map,itinerario)));
		
	}
	
	public String toString(){
		return "New Vehicle " + _id;
	}
}
