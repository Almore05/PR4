package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class IncomingRoad {
	
	protected Road road;
	protected List<Vehicle> queue;
	protected boolean green;
	
	public IncomingRoad(Road rd){
		this.road = rd;
		this.queue = new ArrayList<Vehicle>();
		this.green = false;
	}
	
	public Road getRoad(){return this.road;}
	
	public boolean hasGreenLight(){
		return green;
	}
	public void setGreen(boolean green){
		this.green = green;
	}
	
	/*DEBE RECIBIR INT TIME*/
	public void advanceFirstVehicle(int time){
		queue.get(0).advance(time);
		queue.remove(0);
	}
	
	public void addVehicle(Vehicle in){
		queue.add(in);
	}
	
	public String printQueue(){
	
		String colaV = new String() ;

		Iterator<Vehicle> it = queue.iterator();
		
		while(it.hasNext()){
			Vehicle i = it.next();
			colaV += i.getID()+",";
			colaV +=",";
		}	
		
		colaV = colaV.substring(0,colaV.length()-1);
		
		return colaV;	
	}
	
	public String toString(){
		if (hasGreenLight())
			return "green";
		else
			return "red";
	}
	
}
