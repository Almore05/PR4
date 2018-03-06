package model;

public class NewJunctionEvent extends Event {

	protected String _id;
	
	/* New Juntion Event añade un nuevo cruce a la simulación con los siguientes parámetros: 
	 * [new_junction]  
	 * time = <NONEG-INTEGER>  
	 * id = <JUNC-ID>*/
	
	public NewJunctionEvent(int time, String id) {
		super(time);
		this._id = id;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(RoadMap map, int time) {
		map.addJunction(new Junction(this._id));
	}
	
	public String toString(){
		return "New Junction " + _id;
	}

}
