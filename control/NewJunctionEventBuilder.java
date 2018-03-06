package control;

import ini.IniSection;
import model.Event;
import model.NewJunctionEvent;

public class NewJunctionEventBuilder extends EventBuilder {

	public NewJunctionEventBuilder () {
		tag = "new_junction";
		keys = new String[] { "time", "id" };
		defaultValues = new String[] { "", "" };
	}
	
	public Event parse(IniSection section) {
		if (!section.getTag().equals(tag) || section.getValue("type") != null)
			return null;
		return new NewJunctionEvent(EventBuilder.parseNonNegInt(section, keys[0]),
									EventBuilder.validId(section, keys[1]));
	} // parse

	@Override
	public String toString() {
		return "New Vehicle";
	} // toString


}
