package data;

import exceptions.PanelNameException;

public class Panel extends MobileElement {
	
	private String name;

	public Panel(Position position) {
		super(position);
		this.name = "stop";
	}
	
	public Panel(String name, Position position) throws PanelNameException{
		this(position);
		if(name.equals("stop")) {
			this.name = name;
		}
		else {
			throw new PanelNameException();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
