package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Event {
	
	protected Integer time;//tiempo de ejecucion del evento
	
	protected Event(Event nuevo){
	}
	
	public Event (Integer ent){
		this.time=ent;
	}
	
	public int getScheduledTime(){
		return this.time;
	}
	
	public int compareTo(Event nuevo){
		return (this.time = nuevo.getScheduledTime());
	}
	
	public Junction checkIfJunctionExists(RoadMap rm, String id){
		return rm.getJunction(id);
	}
	
	public Vehicle checkIfVehicleExists(RoadMap rm, String id){
		return rm.getVehicle(id);
	}
	
	public Road checkIfRoadExists(RoadMap rm,String id){		
		return rm.getRoad(id);
	}
	
	public SimulatedObject checkIfSimObjExists(RoadMap rm, String id){
		return rm.getJunction(id);
	}
	
	/*AÑADIR EXCEPCION?*/
	public List<Junction> parseListOfJunctions (RoadMap rm, String[] ids){
		
		List<Junction> nueva = new ArrayList<Junction>();
		
		for(String nuevocruce: ids){
			if (rm.getJunction(nuevocruce)!= null)
				nueva.add(rm.getJunction(nuevocruce));
			//else{
			
		//}
		}
		
		return nueva;
	}
	
	public List<Road> parseListOfRoads(RoadMap rm, String id){
		return null;
	}
	public List<SimulatedObject> parseListOfSimObj(RoadMap rm, String [] ids){
		return null;
	}
	
	public List<Vehicle> parseListOfVehicles(RoadMap rm, String [] ids){
		
		List<Vehicle> nueva = new ArrayList<Vehicle>();
		
		for(String nuevoVehiculo: ids){
			if (rm.getJunction(nuevoVehiculo)!= null)
				nueva.add(rm.getVehicle(nuevoVehiculo));
			//else{
			
		//}
		}
		return nueva;
	}
	
	public abstract void execute(RoadMap rm, int in);
}
