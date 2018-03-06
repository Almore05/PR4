package model;

import java.util.List;
import java.util.Iterator;

public class NewFaultyVehicleEvent extends Event{
	
	protected Integer duration;
	protected String [] vehicles;
	
	public NewFaultyVehicleEvent(int time,String [] vehicles, Integer duration ){
		super(time);
		this.duration = duration;
		this.vehicles = vehicles;
		
	}

	@Override
	public void execute(RoadMap map, int time){
		
		List<Vehicle> aux = parseListOfVehicles(map,vehicles);
		Iterator<Vehicle> it = aux.iterator();
		
		while(it.hasNext()){
			Vehicle i = it.next();
			i.makeFaulty(duration);
		}
	}
	
	public String toString (){
		return "New faultyEvent";
	}
}
