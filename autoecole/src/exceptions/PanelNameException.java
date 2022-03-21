package exceptions;

public class PanelNameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelNameException() {
		super("Ce panneau n'existe pas veuillez changer la constante passée en paramètre lors de l'initialisation");
	}

}
