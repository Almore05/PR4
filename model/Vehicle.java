package model;

import ini.*;
import java.util.List;

public class Vehicle extends SimulatedObject{

	protected int maxSpeed;
	protected int currSpeed;
	private Road road;
	private int location;
	protected List<Junction> itinerary;
	protected int kilometrage;
	protected int faultyTime;
	protected boolean atJunction;
	protected boolean arrived;
	
	public Vehicle(String id, int maxSpeed, List <Junction> cruces){//, List<Junction>) {
		super(id);
		this.maxSpeed = maxSpeed;
		this.currSpeed = 0;
		this.location=0;
		this.kilometrage =0;
		this.faultyTime =0;
		this.arrived = false;
		this.atJunction =false;
		this.itinerary = cruces;
		// TODO Auto-generated constructor stub
	}

	public Road getRoad(){return this.road;}
	
	public int getMaxSpeed(){return this.maxSpeed;}
	
	public int getSpeed(){return this.currSpeed;}
	
	public int getLocacion(){return this.location;}
	
	public int getKilometrage(){return this.kilometrage;}
	
	public int getFaultyTime(){return this.faultyTime;}
	
	public boolean atDestination(){return this.arrived;}
	
	protected void makeFaulty(int faulty){
		this.faultyTime = this.faultyTime + faulty;
		this.currSpeed = 0;
		
	}
	
	protected void setSpeed(int speed){ 
		
		if (speed > road.getMaxSpeed())
			this.currSpeed =this.maxSpeed;
		else
			this.currSpeed = speed; 
	
		if (this.currSpeed > this.maxSpeed)
			this.currSpeed = this.maxSpeed;
	}
	
	protected void moveToNextRoad(){
		//Sale de su carretera
		if (this.road!= null)
			this.road.exit(this);
		//Entra en la siguiente que esta en su itinerario y loc =0;
		this.location=0;
		this.currSpeed=0;
		//this.road = itinerary.get(0).roadTo(itinerary.get(0));
	}
	
	
	@Override
	public void fillReportDetails(IniSection in) {
		// TODO Auto-generated method stub	
		in.setValue("speed",this.currSpeed);
		in.setValue("kilometrage",this.kilometrage);
		in.setValue("faulty",this.faultyTime);
		in.setValue("location","("+this.road.getID()+","+this.location+")");
	}

	@Override
	public String getReportSectionTag(int time) {
		// TODO Auto-generated method stub
		String report;
		report = "[vehicle_report]"+ "\n" +
				  "id = " + this.id + "\n" +
				  "time = " + time + "\n" +
				  "speed = " + this.currSpeed + "\n" +
				  "kilometrage = " + this.kilometrage + "\n" +
				  "faulty = " + this.faultyTime + "\n" +
				  "location = (" +this.road.getID()  +","+ this.location + ")";
				  
		return report;
	}

	@Override
	protected void advance(int time) {
		// TODO Auto-generated method stub
		if (this.faultyTime > 0)
			this.faultyTime--;
		
		else{
			//Si la nueva localizacion es mayor que la carretera por la que avanza
			if ((this.location + this.currSpeed) >= this.road.getLenght()){
				this.location = this.road.getLenght();
				//Meter vehiculo en la cola del cruce
				if(!itinerary.isEmpty()){
					//Preguntar que indice tenemos que usar
					itinerary.get(0).enter(this);
				//borrar una vez entrado?
					itinerary.remove(0);
					
				//Mover a siguiente carretera
				moveToNextRoad();
				}
			}
			else{
				this.location += this.currSpeed;
			}		
		}
	}

}
