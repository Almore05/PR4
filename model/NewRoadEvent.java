package model;

public class NewRoadEvent extends Event {

	protected String _id;
	protected String srcJunc;
	protected String destJunc;
	protected int maxSpeed;
	protected int lenght;
	
	/* New Road Event añade una nueva carretera al simulador con los siguientes parámetros:*/
	/*[new_road]  
	 * time = <NONEG-INTEGER>  
	 * id = <ROAD-ID>  
	 * src = <JUNC-ID>  
	 * dest = <JUNC-ID>  
	 * max_speed = <POSITIVE-INTEGER>  
	 * length = <POSITIVE-INTEGER> */
	public NewRoadEvent(int time, String id, String src, String dest,int maxSpeed, int lenght){
		super(time);
		this._id = id;
		this.srcJunc = src;
		this.destJunc = dest;
		this.maxSpeed = maxSpeed;
		this.lenght = lenght;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(RoadMap map, int time) {
		// TODO Auto-generated method stub
		map.addRoad(new Road(_id, lenght, maxSpeed, new Junction(srcJunc), new Junction(destJunc)));
		
	}
	
	public String toString(){
		return "New Road " + _id;
	}

}
