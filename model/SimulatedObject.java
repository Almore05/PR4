package model;
import ini.*;

public abstract class SimulatedObject {
	
	protected String id;
	
	public SimulatedObject(String nombre){
		this.id = nombre;
	}
	
	public String getID(){
		return this.id;
	}
	
	public String toString(){
		return this.id;
	}
	
	public String generateReport(int time){
		IniSection is = new IniSection(getReportSectionTag(time));
		is.setValue("id",id);
		is.setValue("time", time);
		fillReportDetails(is);
		return is.toString();
	}

	public abstract void fillReportDetails(IniSection in);
	
	public abstract String getReportSectionTag(int time);
	
	protected abstract void advance (int in); 
	
}
