package control;

import ini.IniSection;
import model.Event;
import model.NewVehicleEvent;

public class NewVehicleEventBuilder extends EventBuilder{


	public NewVehicleEventBuilder () {
		tag = "new_vehicle";
		keys = new String[] { "time", "id", "max_speed", "itinerary" };
		defaultValues = new String[] { "", "", "", "" };
	}
	
	public Event parse(IniSection section) {
		if (!section.getTag().equals(tag) || section.getValue("type") != null)
			return null;
		return new NewVehicleEvent(EventBuilder.parseNonNegInt(section, keys[0]), EventBuilder.validId(section, keys[1]),
				EventBuilder.parseNonNegInt(section, keys[2]), EventBuilder.parseList(section, keys[3]));
	} // parse

	@Override
	public String toString() {
		return "New Vehicle";
	} // toString

}
