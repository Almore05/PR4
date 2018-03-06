package model;
import ini.*;
import java.lang.Math;
import java.util.List;
//import java.util.ArrayList;
import other.SortedArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Road extends SimulatedObject {

	protected int lenght;
	protected int maxSpeed;
	protected Junction srcJunc;
	protected Junction destJunt;
	protected List<Vehicle> vehicles;
	
	public Road(String nombre, int len, int maxS, Junction src, Junction dest){
		super(nombre);
		this.lenght=len;
		this.maxSpeed=maxS;
		this.srcJunc = src;
		this.destJunt = dest;
		
		Comparator<Vehicle> cmp = new Comparator<Vehicle>(){
			public int compare (Vehicle v1, Vehicle v2){
			
				if (v1.getLocacion()> v2.getLocacion())
					return 1;
				else if (v1.getLocacion() < v2.getLocacion())
					return -1;
				else
					return 0;
			}
		};
		
		this.vehicles = new SortedArrayList<Vehicle>(cmp);
		// TODO Auto-generated constructor stub
	}

	public Junction getSource(){return this.srcJunc;}
	
	public Junction getDestination(){return this.destJunt;}
	
	public int getLenght(){return this.lenght;}
	
	public int getMaxSpeed(){return this.maxSpeed;}
	
	public List<Vehicle> getVehicles(){return this.vehicles;}
	
	protected void enter (Vehicle in){vehicles.add(in);}
	
	public void exit (Vehicle out){vehicles.remove(out);}
	
	protected int calculateBaseSpeed(){
		int baseSpeed = Math.min(this.maxSpeed, (this.maxSpeed / Math.max(vehicles.size(),1)+1));
		return baseSpeed;
	}
	
	/*RELLENAR*/
	protected int reduceSpeedFactor(int dec){
		int factor = 2;
		
		if (dec ==0)
			factor =1;
	
		return factor;
	}
	
	@Override
	public void fillReportDetails(IniSection in) {
		// TODO Auto-generated method stub
		in.setValue("state", printState());
	}

	@Override
	public String getReportSectionTag(int time) {
		// TODO Auto-generated method stub
		String report;
		report = "[road_report]"+ "\n" +
				  "id = " + this.id + "\n" +
				  "time = " + time +"\n" +
				  "state = " + printState();
				  
		return report;

	}

	private String printState(){
		
		String state = new String();
	
		Iterator<Vehicle> it = vehicles.iterator();
		
		while(it.hasNext()){
			Vehicle i = it.next();
			state += "("+i.getID()+","+i.getLocacion()+")";
			state +=",";
		}	
		
		state = state.substring(0,state.length()-1);
		
		return state;
	}
	
	
	@Override
	protected void advance(int in) {
		// TODO Auto-generated method stub
		int faulty =0;
		
		for(Vehicle vehiculo: vehicles){
			
			if (vehiculo.getFaultyTime() > 0)
				faulty++;
			
			vehiculo.setSpeed(this.calculateBaseSpeed()/
							this.reduceSpeedFactor(faulty));
			
			/*PREGUNTAR SI IN = AL RECIBIDO COMO PARAMETRO*/
			vehiculo.advance(in);
			
			}
		//ordenar  la lista por localizacion
		
	}

}
