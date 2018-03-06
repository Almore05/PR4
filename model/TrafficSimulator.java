package model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Event;

public class TrafficSimulator {
	
	private RoadMap map;
	private List<Event> eventos;
	private int time;
	private OutputStream outStream;
	
	
	public TrafficSimulator(OutputStream flujo){
		this.map = new RoadMap();
		this.eventos = new ArrayList<Event>();
		this.time = 0;
		this.outStream = flujo;
	}
	
	public void run(int ticks) throws IOException{
		
		int limit = time + ticks - 1;
		
		while (time <= limit){
		
		// paso 1
		ejecutarEventos();
		
		//paso 2
		advanceRoads(time);
		
		//paso 3
		advanceJunctions(time);
		
		//paso 4
		time++;
		
		if (this.outStream != null)
			//printeports sca la salida en una seccion ini
			printReports();
		
		}
	}
	public void addEvent(Event evento){
		eventos.add(evento);
	}
	
	public void reset(){
		this.map.clear();
		this.eventos.clear();
		this.time =0;
	}
	
	public void setOutputStream(OutputStream flujo){
		this.outStream = flujo;
	}
	
	private void ejecutarEventos(){
		
		for (Event nuevo: eventos){
			nuevo.execute(map, time);
		}	
	}
	
	private void advanceRoads(int time){
		
		List<Road> carreteras = map.getRoads();
		
		Iterator<Road> it = carreteras.iterator();
		
		while(it.hasNext()){
			Road i = it.next();
			i.advance(time);
			//it.remove();
		}	
	}
	
	private void advanceJunctions(int time){
	
		List<Junction> cruces = map.getJunction();
			
			Iterator<Junction> it = cruces.iterator();
			
			while(it.hasNext()){
				Junction i = it.next();
				i.advance(time);
				//it.remove();
			}	
	}
	
	private void printReports() throws IOException{
		
		outStream.write(map.generateReport(time).getBytes());
	}
	
	//public void addObserver(TrafficSimulatorObserver obsv){}
	//public void removeObserver(TrafficSimulatorObserver obsv){}
	//public String toString(){return null;}
}
