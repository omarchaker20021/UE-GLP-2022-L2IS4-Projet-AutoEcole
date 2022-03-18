package data;


/**
 * Cette classe est un classe de données d'un rond point (roundabout)
 * Elle n'a pas encore été utilisée
 * @author Rayane KHAMAILY
 * */

public abstract class Roundabout extends MapElement {
	
	private Line circleLine;

	public Roundabout(Position position, Line circleLine) {
		super(position);
		this.circleLine = circleLine;
	}

	public Line getCircleLine() {
		return circleLine;
	}

	public void setCircleLine(Line circleLine) {
		this.circleLine = circleLine;
	}

}
