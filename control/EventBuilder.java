package control;

import model.Event;
import ini.IniSection;

public abstract class EventBuilder {

	protected String tag;
	protected String[] keys;//conjunto de elementos location , maxspeed
	protected String[] defaultValues; //valores asociados a esos elementos
	
	//public EventBuilder(){}
	
	public abstract Event parse(IniSection section);
	
	public static int parseNonNegInt(IniSection section,String key){
		
		int valor = Integer.parseInt(section.getValue(key)); 
		
		if ( valor < 0)
			//throw new SimulatorErrorException("");
			valor = -1;
			
		return valor;	
		
	}
	
	public static String validId (IniSection section,String id){
		
		String valor = section.getValue(id); 
		int cnt = 0;
	
		while (cnt < valor.length()){
			
			if (!Character.isDigit(valor.charAt(cnt)) &&
				(!Character.isLetter(valor.charAt(cnt)) && 	valor.charAt(cnt)!= '_'))
				//throw excep
				return null;
		
			cnt++;
		}
				
		return valor;
			
	}
	
	public static String[] parseList(IniSection section, String list){
		
		String valor = section.getValue(list);
		String [] lista = valor.split(",");
		int cnt =0;
		
		while (cnt < lista.length){

			//Comprobamos que el id es valido
			if (validId (section,lista[cnt]) == null)
				//throw exc
				return null;
		
			cnt++;
		}
	
		return lista;
	}
	
}
