package model;

import ini.IniSection;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Junction extends SimulatedObject{

	protected List<Vehicle> queue;
	protected List<IncomingRoad> roads;
	protected Map<Junction, Road> incomingRoads;
	private int cnt;
	
	public Junction(String nombre) {
		super(nombre);
		this.roads = new ArrayList<IncomingRoad>();
		this.incomingRoads = new HashMap<Junction,Road>();
		this.cnt=-1;
	}

	/*PREGUNTAR*/
	public Road roadTo(Junction to){
		return incomingRoads.get(to);
	}
	/*PREGUNTAR*/
	public Road roadFrom(Junction from){
		return null;
	}
	
	public List<IncomingRoad> getRoadsInfo(){
		return this.roads;
	}
	
	/*PREGUNTAR*/
	protected void addIncommingRoad(Road in){
		IncomingRoad nueva = new IncomingRoad(in);
		roads.add(nueva);
		//añadir a map?
		
	}
	/*PREGUNTAR*/
	protected void addOutGoingRoad(Road out){
		
	}
	
	protected void enter (Vehicle in){
		queue.add(in);
	}
	
	protected void switchLights(){
		
		if (cnt >=0){
			roads.get(this.cnt).setGreen(false);
			
		}
		
		cnt++;
		
		 if (cnt >= roads.size())
			 cnt%=roads.size();
		
		 roads.get(cnt).setGreen(true);
		 

	}
	
	/*PREGUNTAR*/
	protected IncomingRoad createIncommingRoadQueue(Road in){
	
		return new IncomingRoad(in);
		
	}
	
	@Override
	public void fillReportDetails(IniSection in) {
		// TODO Auto-generated method stub
		in.setValue("queues",getRoadQueues());
	}

	@Override
	public String getReportSectionTag(int time) {
		// TODO Auto-generated method stub
		String report;
		report = "[junction_report]"+ "\n" +
				  "id = " + this.id + "\n" +
				  "time = " + time +  "\n" +
				  "queues = " + getRoadQueues();
				  
		return report;
	}
	
	private String getRoadQueues (){
		
		Iterator<IncomingRoad> it = roads.iterator();
		String salida = new String();
		
		salida = "(";
		while(it.hasNext()){
			IncomingRoad i = it.next();
			salida += i.getRoad().getID()+ "," + it + ",[";
			salida += i.printQueue() + "]";
			salida += ")" + ",";
		}	
		
		//Elimina la ultima coma
		salida = salida.substring(0,salida.length()-1);

		return salida;
		
	}
	
	@Override
	protected void advance(int time) {
		// TODO Auto-generated method stub
		
			
	}
	
}
