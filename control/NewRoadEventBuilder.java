package control;

import ini.IniSection;
import model.Event;
import model.NewRoadEvent;


public class NewRoadEventBuilder extends EventBuilder{

	public NewRoadEventBuilder () {
		tag = "new_road";
		keys = new String[] { "time", "id", "src", "dest", "max_speed", "lenght" };
		defaultValues = new String[] { "", "", "", "", "", "" };
	}
	
	public Event parse(IniSection section) {
		if (!section.getTag().equals(tag) || section.getValue("type") != null)
			return null;
		return new NewRoadEvent(EventBuilder.parseNonNegInt(section, keys[0]),//time
								EventBuilder.validId(section, keys[1]),//id
								EventBuilder.validId(section, keys[2]),//src
								EventBuilder.validId(section, keys[3]),//dst
								EventBuilder.parseNonNegInt(section, keys[4]),
								EventBuilder.parseNonNegInt(section, keys[5]));
	} // parse

	@Override
	public String toString() {
		return "New Road";
	} // toString

}
