package control;

import ini.IniSection;
import model.Event;
import model.NewFaultyVehicleEvent;

public class MakeVehicleFaultyEventBuilder extends EventBuilder{

	public MakeVehicleFaultyEventBuilder () {
		tag = "make_vehicle_faulty";
		keys = new String[] { "time", "vehicles", "duration" };
		defaultValues = new String[] { "", "", "" };
	}
	
	public Event parse(IniSection section) {
		if (!section.getTag().equals(tag) || section.getValue("type") != null)
			return null;
		return new NewFaultyVehicleEvent(EventBuilder.parseNonNegInt(section, keys[0]),
										EventBuilder.parseList(section, keys[1]),
										EventBuilder.parseNonNegInt(section, keys[2]));
	} // parse

	@Override
	public String toString() {
		return "New Vehicle";
	} // toString


}
