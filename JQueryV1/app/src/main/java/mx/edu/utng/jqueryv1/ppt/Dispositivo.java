package mx.edu.utng.jqueryv1.ppt;

public class Dispositivo {
	private String name;
	private String MAC;
	
	public Dispositivo()
	{
		this.name = "Vacio";
		this.MAC = "Sin MAC";
	}
	
	public Dispositivo(String name, String MAC){
		this.MAC = MAC;
		this.name = name;
	}
	
	public void setName(String name){
		this.name=name;
	}

	public void setMAC(String MAC){
		this.MAC = MAC;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getMAC(){
		return this.MAC;
	}
}
