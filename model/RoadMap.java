package model;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RoadMap {
	
	private List<Vehicle> vehicles;
	private List<Road> roads;
	private List<Junction> junctions;
	private Map<String,Vehicle> mapVehicles;
	private Map<String,Road> mapRoads;
	private Map<String,Junction> mapJunctions;

	protected RoadMap(){}
	
	public Vehicle getVehicle(String id){
		return mapVehicles.get(id);
	}
	
	public Road getRoad(String id){
		return mapRoads.get(id);
	}
	
	/*¿ESTO QUE HACE?*/
	public SimulatedObject getSimObj(String id){
		return null;
	}
	
	public Junction getJunction(String id){
		return mapJunctions.get(id);
	}
	
	public List<Vehicle> getVehicles(){
		return this.vehicles;
	}
	
	public List<Road> getRoads(){
		return this.roads;
	}
	
	public List<Junction> getJunction(){
		return this.junctions;
	}
	
	/*AÑADIR A LOS DOS SITIOS , ¿que hacer si ya existe en el mapeado?*/
	protected void addJunction(Junction nuevo){
		
		if (!mapJunctions.containsKey(nuevo)){
			mapJunctions.put(nuevo.getID(), nuevo);
			junctions.add(nuevo);
		}
		
	}
	protected void addVehicle(Vehicle nuevo){
		
		if (!mapVehicles.containsKey(nuevo)){
			mapVehicles.put(nuevo.getID(), nuevo);
			vehicles.add(nuevo);
		}
		
	}
	protected void addRoad(Road nuevo){
		
		if (!mapRoads.containsKey(nuevo)){
			mapRoads.put(nuevo.getID(), nuevo);
			roads.add(nuevo);
		}
		
	}
	protected void clear(){
		
		vehicles.clear();
		junctions.clear();
		roads.clear();
		mapVehicles.clear();
		mapJunctions.clear();
		mapRoads.clear();
		
	}
	
	private String printJunctions(int time){
		
		Iterator<Junction> it = junctions.iterator();
		String salida = new String();
		
		while(it.hasNext()){
			Junction i = it.next();
			salida += i.generateReport(time);
		}	
		return salida;
	}
	
	private String printVehicles(int time){
		
		Iterator<Vehicle> it = vehicles.iterator();
		String salida = new String();
		
		while(it.hasNext()){
			Vehicle i = it.next();
			salida += i.generateReport(time);
		}	
		return salida;
	}
	
	private String printRoads(int time){
		
		Iterator<Road> it = roads.iterator();
		String salida = new String();
		
		while(it.hasNext()){
			Road i = it.next();
			salida += i.generateReport(time);
		}	
		return salida;
	}
	
	
	
	public String generateReport(int time){
		return printJunctions(time)+ printRoads(time) + printVehicles(time);
	}

}
